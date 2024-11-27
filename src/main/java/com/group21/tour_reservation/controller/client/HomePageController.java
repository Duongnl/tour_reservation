package com.group21.tour_reservation.controller.client;

import com.group21.tour_reservation.dto.response.TourReserveResponse;
import com.group21.tour_reservation.repository.AccountRepository;
import com.group21.tour_reservation.service.AccountService;
import com.group21.tour_reservation.service.TourService;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.group21.tour_reservation.entity.Account;
import com.group21.tour_reservation.entity.Customer;
import com.group21.tour_reservation.entity.Reserve;
import com.group21.tour_reservation.entity.ReserveDetail;import com.group21.tour_reservation.service.AccountService;
import com.group21.tour_reservation.service.CustomerService;
import com.group21.tour_reservation.service.ReserveService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomePageController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TourService tourService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ReserveService reserveService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public String getHomePage(Model model, HttpServletRequest request, @AuthenticationPrincipal OAuth2User user) {
        reserveService.autoDestroyReserve();
        // sử dụng khi có session
        HttpSession session = request.getSession(false);
        System.out.println(">>> check id" + session.getAttribute("id"));
        // int id = (int) session.getAttribute("id");

        if (user != null) {

            Map<String, Object> attributes = user.getAttributes();

            // Hiển thị các thuộc tính của người dùng
            attributes.forEach((key, value) -> {
                System.out.println(">>> check:  "+key + ": " + value);
            });
            model.addAttribute("name", user.getAttribute("name"));
            model.addAttribute("email", user.getAttribute("email"));
            model.addAttribute("attributes", attributes);
        }
        return "client/home.html";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        Account account = new Account();
        Customer customer = new Customer();
        customer.setAccount(account);
        model.addAttribute("customer", customer);
        return "client/auth/register.html";
    }

    @PostMapping("/register")
    public String createAccount(@ModelAttribute("customer") Customer customer,
            RedirectAttributes redirectAttributes) {

        customerService.createAccount(customer);

        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới khách hàng thành công!");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "client/auth/login.html";
    }

    @GetMapping("/access-deny")
    public String getDenyPage(Model model) {

        return "client/auth/deny.html";
    }



    @GetMapping("/tour")
    public String tourView(Model model, @AuthenticationPrincipal OAuth2User user) {
        if ( user != null) {
            model.addAttribute("name", user.getAttribute("name"));
        }
        
        return "client/tour-page.html";
    }

    @GetMapping("/reserve/{slug}")
    public String reserveView(Model model, @PathVariable("slug") String slug, HttpServletRequest request, @AuthenticationPrincipal OAuth2User user ) {

        TourReserveResponse tourReserveResponse = new TourReserveResponse();
        tourReserveResponse=   tourService.getTourReserveClient(slug);

        if (tourReserveResponse == null) {
            return "admin/404.html";
        }

        HttpSession session = request.getSession(false);
        Integer id = (Integer) session.getAttribute("id");

        if (id != null) {

            int idValue = id;
            Account account = accountService.getAccount(String.valueOf(idValue));
            if (account.getCustomer() !=null) {
                Customer customer = customerService.getCustomerById(account.getCustomer().getCustomerId());
                tourReserveResponse.setName(customer.getCustomerName());
                tourReserveResponse.setAddress(customer.getAddress());
                tourReserveResponse.setEmail(customer.getEmail());
                tourReserveResponse.setPhone(customer.getPhoneNumber());

            }

        } else
        if ( user != null) {
            model.addAttribute("name", user.getAttribute("name"));
            tourReserveResponse.setName(user.getAttribute("name"));
            tourReserveResponse.setEmail(user.getAttribute("email"));
        }


        model.addAttribute("tourReserveResponse", tourReserveResponse);
        return "client/reserve-page.html";
    }

    @GetMapping("/confirm_info/{slug}")
    public String getHomePage(Model model, @PathVariable("slug") String slug, @AuthenticationPrincipal OAuth2User user) {

        if ( user != null) {
            model.addAttribute("name", user.getAttribute("name"));
        }

        Reserve reserve = reserveService.getReserve(slug);
        if (reserve == null) {
            return "redirect:/admin/404.html";
        }

        if (reserve.getStatus() == 3) {
        model.addAttribute("expire", reserve.getTime().plusMinutes(15));
        } else if (reserve.getStatus() == 1) {
            model.addAttribute("expire", reserve.getTime().plusDays(2));
        } else if (reserve.getStatus() == 2) {
            model.addAttribute("expire","Đã thanh toán");
        } else {
            model.addAttribute("expire","Hết hạn");
        }



        model.addAttribute("reserve", reserve);
        return "client/confirm_info.html";
    }

    @GetMapping("/reserve-account")
    public String reserveAccount(Model model, HttpServletRequest request) {
        // Lấy session hiện tại
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login";
        }

        // Lấy id từ session
        Integer id = (Integer) session.getAttribute("id");
        Account account = accountRepository.findById(id).orElse(null);

        // Lấy thông tin Customer
        Customer customer = customerService.getCustomerById(account.getCustomer().getCustomerId());

        // Lấy danh sách Reserve từ Customer
        List<Reserve> listDatChoAccount = new ArrayList<>(customer.getReserves());

        // Truyền danh sách vào model
        model.addAttribute("listDatChoAccount", listDatChoAccount);

        // Trả về view hiển thị
        return "client/reserve-account-page.html";
    }

    @GetMapping("/reserve-account/reserve-detail-account/{slug}")
    public String reserveDetailAccount(Model model, @PathVariable("slug") String slug, HttpServletRequest request) {
        // Kiểm tra nếu slug có thể chuyển thành int
        Reserve reserve = reserveService.getReserve(slug);
        // Truyền danh sách ReserveDetail vào model
        model.addAttribute("reserveDetails", reserve.getReserveDetails());

        // Trả về view hiển thị
        return "client/reserve-detail-account-page.html";
    }


    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal OAuth2User user) {
        if (user != null) {

            Map<String, Object> attributes = user.getAttributes();

            // Hiển thị các thuộc tính của người dùng
            attributes.forEach((key, value) -> {
                System.out.println(">>> check:  "+key + ": " + value);
            });
            model.addAttribute("name", user.getAttribute("name"));
            model.addAttribute("email", user.getAttribute("email"));
            model.addAttribute("attributes", attributes);
        }
        return "client/logingoogle.html";
    }
}
