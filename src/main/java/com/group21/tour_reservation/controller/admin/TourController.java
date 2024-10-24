package com.group21.tour_reservation.controller.admin;

import com.group21.tour_reservation.entity.*;
import com.group21.tour_reservation.repository.TourScheduleRepository;
import com.group21.tour_reservation.repository.TransportRepository;
import com.group21.tour_reservation.service.CategoryService;
import com.group21.tour_reservation.service.TourService;
import com.group21.tour_reservation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TourController {

    @Autowired
    private TourService tourService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/tour")
    public String tour (Model model) {

        model.addAttribute("tours", tourService.getAllTours());
        return "/admin/tour/tour.html";
    }

    @GetMapping("/admin/tour/add")
    public String tourAddView(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        List<String> states = new ArrayList<String>();
        for (Category category : categories) {
            states.add(category.getCategoryId()+"-"+category.getCategoryName());
        }

        Tour tour = new Tour();
        model.addAttribute("states",states);
        model.addAttribute("tour", tour); // Thêm đối tượng vào mô hình

        return "admin/tour/tour-add.html";
    }

    @GetMapping("/admin/tour/tour-overview/{slug}")
    public String tourOverView(Model model, @PathVariable("slug") String slug) {
        Tour tour = tourService.getTour(slug);
        if (tour == null) {
            return "admin/404.html";
        }

//        List<TourSchedule> tourSchedules = new ArrayList<>();
//        tour.getTourSchedules().forEach(tourSchedule -> {
//            if (tourSchedule.getStatus() == 1) {
//                tourSchedules.add(tourSchedule);
//            }
//        });

        model.addAttribute("tour", tour);
        model.addAttribute("tourScheduleTableResponse", tourService.tourOverView(tour));

        return "admin/tour/tour-overview.html";
    }



    @PostMapping ("/admin/tour/add-tour")
    public String addTour (@RequestParam("imgmain") MultipartFile imgMain,
                           @RequestParam("img1") MultipartFile img1,
                           @RequestParam("img2") MultipartFile img2,
                           @RequestParam("categoryslug") String categorySlug,
                           Model model,
                           @ModelAttribute("tour") Tour tour,
                           RedirectAttributes redirectAttributes
                           ) {

      Tour tourRes = tourService.addTour(imgMain, img1, img2, categorySlug, tour);
        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới tour thành công");
        return "redirect:/admin/tour/tour-overview/" + StringUtils.createSlug(tourRes.getTourName()) + "-" + tourRes.getTourId();
    }

    @PostMapping ("/admin/tour/edit-tour")
    public String editTour (@RequestParam("imgmain") MultipartFile imgMain,
                            @RequestParam("img1") MultipartFile img1,
                            @RequestParam("img2") MultipartFile img2,
                            @RequestParam("ipImg1") String ipImg1,
                            @RequestParam("ipImg2") String ipImg2,
                            @RequestParam("categoryslug") String categorySlug,
                            Model model,
                            @ModelAttribute("tour") Tour tour,
                            RedirectAttributes redirectAttributes)
    {
        Tour tourRes = tourService.editTour(imgMain, img1, img2, categorySlug, tour,ipImg1,ipImg2);
        redirectAttributes.addFlashAttribute("successMessage", "Đổi thông tin tour thành công");
        return "redirect:/admin/tour/tour-overview/" + StringUtils.createSlug(tourRes.getTourName()) + "-" + tourRes.getTourId();
    }

    @GetMapping("/admin/tour/{slug}")
    public String tourEditView(Model model,@PathVariable("slug") String slug) {
        Tour tour = tourService.getTour(slug);
        if (tour == null) {
            return "admin/404.html";
        }
        List<Category> categories = categoryService.getAllCategories();

        List<String> states = new ArrayList<String>();
        for (Category category : categories) {
            states.add(category.getCategoryId()+"-"+category.getCategoryName());
        }
        String imgmain = "";
        String img1 = "";
        String img2 = "";
        for (Image image : tour.getImages()) {
            if (image.getStatus() ==0) {
                imgmain = image.getUrl();
            } else if (image.getStatus() ==1) {
                img1 = image.getUrl();
            } else if (image.getStatus() ==2) {
                img2 = image.getUrl();
            }
        }
        System.out.println("img main : " + imgmain);



        model.addAttribute("states",states);
        model.addAttribute("tour", tour);
        model.addAttribute("imgmain", imgmain);
        model.addAttribute("img1", img1);
        model.addAttribute("img2", img2);
        model.addAttribute("selected",tour.getCategory().getCategoryId()+"-"+
                tour.getCategory().getCategoryName());
        System.out.println(img1);
        System.out.println(img2);
        return "admin/tour/tour-edit.html";
    }


    @GetMapping("/admin/tour/delete-tour/{id}")
    public String deleteTour (Model model,@PathVariable("id") Integer tourId,
                              RedirectAttributes redirectAttributes) {
        if (tourService.deleteTour(tourId) != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công!");
        } else {
            return "admin/404.html";
        }
        return "redirect:/admin/tour";
    }





}
