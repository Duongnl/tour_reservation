package com.group21.tour_reservation.controller.admin;

import com.group21.tour_reservation.entity.Category;
import com.group21.tour_reservation.entity.Tour;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.service.CategoryService;
import com.group21.tour_reservation.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        System.out.println(states);

        Tour tour = new Tour();
        model.addAttribute("states",states);
        model.addAttribute("tour", tour); // Thêm đối tượng vào mô hình

        return "admin/tour/tour-add.html";
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

       tourService.addTour(imgMain, img1, img2, categorySlug, tour);

        return "redirect:/admin/tour";
    }




}
