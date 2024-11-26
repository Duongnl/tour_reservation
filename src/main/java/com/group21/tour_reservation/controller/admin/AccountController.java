package com.group21.tour_reservation.controller.admin;

import com.group21.tour_reservation.entity.Account;
import com.group21.tour_reservation.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    @GetMapping("/admin/account")
    public String index(Model model) {
        model.addAttribute("accounts", accountService.getAllAccount());
        return "admin/account/index.html";
    }

    @GetMapping("/admin/account/add")
    public String AddView(Model model) {
        model.addAttribute("account", new Account()); // Thêm đối tượng vào mô hình
        return "admin/account/add.html";
    }

    @GetMapping("/admin/account/edit/{slug}")
    public String EditView(Model model,@PathVariable("slug") String slug) {
        Account account = accountService.getAccount(slug);
        if (account == null) {
            return "admin/404.html";
        }

        model.addAttribute("account", account); // Thêm đối tượng vào mô hình
        return "admin/account/edit.html";
    }

    @PostMapping("/admin/account/add")
    public String add(Model model, @ModelAttribute("account") Account account, RedirectAttributes redirectAttributes) {
        accountService.addAccount(account);

        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới phương tiện thành công!");
        return "redirect:/admin/account";
    }

    @PostMapping("/admin/account/edit")
    public String edit(Model model, @ModelAttribute("account") Account account, RedirectAttributes redirectAttributes) {
        accountService.editAccount(account);

        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa phương tiện thành công!");
        return "redirect:/admin/account";
    }

    @GetMapping("/admin/account/delete/{id}")
    public String delete (Model model,@PathVariable("id") String accountId, RedirectAttributes redirectAttributes) {
        Account account = accountService.deleteAccount(accountId);
        if (account == null) {
            return "admin/404.html";
        }
        else
        if (account.getStatus() == 1) {
            redirectAttributes.addFlashAttribute("errorMessage", "Phương tiện này đang được sử dụng không thể xóa!");
        } else if (account.getStatus() == 0) {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công!");
        }
        return "redirect:/admin/account";
    }

    // code mới






}
