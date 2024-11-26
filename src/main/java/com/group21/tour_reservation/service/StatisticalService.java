package com.group21.tour_reservation.service;

import com.group21.tour_reservation.entity.Reserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class StatisticalService {
    @Autowired
    private ReserveService reserveService;

    public int sumPrice(List<Reserve> data) {
        return data.stream()
                .mapToInt(Reserve::getPrice)
                .sum();
    }

    public int[] getPrice12Months(List<Reserve> data) {
        int[] money_mm = new int[12];
        for (Reserve reserve : data) {
            int mm = reserve.getTime().getMonthValue()-1;
            money_mm[mm] += reserve.getPrice();
        }
        return money_mm;
    }

    public String directContent(String pathFile){
        return "/admin/statistical/component/" + pathFile + ".html";
    }

    public int getYear(String year){
        return Integer.parseInt(year);
    }

    public YearMonth getYearMonth(String slug){
        String[] parts = slug.split("\\.");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        return YearMonth.of(year, month);
    }

    public int[] getPriceAllDayInMonth(List<Reserve> data, int numberDay) {
        int[] money_mm = new int[numberDay];
        for (Reserve reserve : data) {
            int mm = reserve.getTime().getDayOfMonth()-1;
            money_mm[mm] = reserve.getPrice();
        }
        return money_mm;
    }




//    @GetMapping("/admin/statistical/year/{slug}")
//    public String year(Model model, @PathVariable("slug") String slug) {
//        int year  = Integer.parseInt(slug);
//        List<Reserve> data = reserveService.getAllReserveByYear(year);
//        int[] money_mm = new int[13];
//        for (Reserve reserve : data) {
//            int mm = reserve.getTime().getMonthValue();
//            money_mm[mm] += reserve.getPrice();
//        }
//
//        model.addAttribute("reserves", reserveService.getAllReserveByYear(year));
//        model.addAttribute("money_mm", money_mm);
//        model.addAttribute("year", year);
//        model.addAttribute("type", "/admin/statistical/component/revenue_year.html");
////        model.addAttribute("sum", sum);
//        return "admin/statistical/index.html";
//    }
//
//    @GetMapping("/admin/statistical/date/{slug}")
//    public String month(Model model, @PathVariable("slug") String slug) {
//
//
//        List<Reserve> data = reserveService.getAllReserveByYearAndMonth(year, month);
//        int[] money_mm = new int[32];
//        int[] labels = IntStream.rangeClosed(1, YearMonth.of(year, month).lengthOfMonth()).toArray();
//        int sum = 0;
//        for (Reserve reserve : data) {
//
//            int mm = reserve.getTime().getDayOfMonth();
//            System.out.println(mm);
//            money_mm[mm] = reserve.getPrice();
//            sum += reserve.getPrice();
//        }
//
//        model.addAttribute("reserves", reserveService.getAllReserveByYear(year));
//        model.addAttribute("money_mm", money_mm);
//        model.addAttribute("year", year);
//        model.addAttribute("month", month);
//        model.addAttribute("labels", labels);
//        model.addAttribute("sum", sum);
//
//        model.addAttribute("type", "/admin/statistical/component/revenue_month.html");
//
//        return "admin/statistical/index.html";
//    }

//    @GetMapping("/admin/category/add")
//    public String AddView(Model model) {
//        model.addAttribute("category", new Category()); // Thêm đối tượng vào mô hình
//        return "admin/category/add.html";
//    }
//
//    @GetMapping("/admin/category/edit/{slug}")
//    public String EditView(Model model,@PathVariable("slug") String slug) {
//        Category category = categoryService.getCategory(slug);
//        if (category == null) {
//            return "admin/404.html";
//        }
//
//        model.addAttribute("category", category); // Thêm đối tượng vào mô hình
//        return "admin/category/edit.html";
//    }
//
//    @PostMapping("/admin/category/add")
//    public String add(Model model, @ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
//        categoryService.addCategory(category);
//
//        // Thêm thông báo vào RedirectAttributes
//        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới phương tiện thành công!");
//        return "redirect:/admin/category";
//    }
//
//    @PostMapping("/admin/category/edit")
//    public String edit(Model model, @ModelAttribute("category") Category category,
//                       RedirectAttributes redirectAttributes) {
//
//        categoryService.editCategory(category);
//
//        // Thêm thông báo vào RedirectAttributes
//        redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa phương tiện thành công!");
//        return "redirect:/admin/category";
//    }
//
//    @GetMapping("/admin/category/delete/{id}")
//    public String delete (Model model,@PathVariable("id") String categoryId, RedirectAttributes redirectAttributes) {
//        Category category = categoryService.deleteCategory(categoryId);
//        if (category == null) {
//            return "admin/404.html";
//        }
//        else
//        if (category.getStatus() == 1) {
//            redirectAttributes.addFlashAttribute("errorMessage", "Phương tiện này đang được sử dụng không thể xóa!");
//        } else if (category.getStatus() == 0) {
//            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công!");
//        }
//        return "redirect:/admin/category";
//    }

    // code mới






}
