package com.group21.tour_reservation.controller.admin;

import com.group21.tour_reservation.entity.Category;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.service.CategoryService;
import com.group21.tour_reservation.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/category")
    public String index(Model model) {
        model.addAttribute("categorys", categoryService.getAllCategories());
        return "admin/category/index.html";
    }

    @GetMapping("/admin/category/add")
    public String AddView(Model model) {
        model.addAttribute("category", new Category()); // Thêm đối tượng vào mô hình
        return "admin/category/add.html";
    }

    @GetMapping("/admin/category/edit/{slug}")
    public String EditView(Model model,@PathVariable("slug") String slug) {
        Category category = categoryService.getCategory(slug);
        if (category == null) {
            return "admin/404.html";
        }

        model.addAttribute("category", category); // Thêm đối tượng vào mô hình
        return "admin/category/edit.html";
    }

    @PostMapping("/admin/category/add")
    public String add(Model model, @ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        categoryService.addCategory(category);

        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới phương tiện thành công!");
        return "redirect:/admin/category";
    }

    @PostMapping("/admin/category/edit")
    public String edit(Model model, @ModelAttribute("category") Category category,
                       RedirectAttributes redirectAttributes) {

        categoryService.editCategory(category);

        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa phương tiện thành công!");
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String delete (Model model,@PathVariable("id") String categoryId, RedirectAttributes redirectAttributes) {
        Category category = categoryService.deleteCategory(categoryId);
        if (category == null) {
            return "admin/404.html";
        }
        else
        if (category.getStatus() == 1) {
            redirectAttributes.addFlashAttribute("errorMessage", "Phương tiện này đang được sử dụng không thể xóa!");
        } else if (category.getStatus() == 0) {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công!");
        }
        return "redirect:/admin/category";
    }

    // code mới






}
