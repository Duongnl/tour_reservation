package com.group21.tour_reservation.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.group21.tour_reservation.entity.Account;
import com.group21.tour_reservation.entity.Employee;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/admin/employee")
    public String transport(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "admin/employee/employee.html";
    }

    @GetMapping("/admin/employee/add")
    public String employeeAddView(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee); // Thêm đối tượng vào mô hình
        return "admin/employee/employee-add.html";
    }

    @PostMapping("/admin/employee/add-employee")
    public String addEmployee(Model model, @ModelAttribute("employee") Employee employee,@ModelAttribute("account") Account account,  RedirectAttributes redirectAttributes) {
        employeeService.createEmployee(account, employee);

        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới nhân viên thành công!");
        return "redirect:/admin/employee";
    }
    
}
