package com.group21.tour_reservation.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String employee(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "admin/employee/employee.html";
    }

    @GetMapping("/admin/employee/add")
    public String employeeAddView(Model model) {
        Account account = new Account();
        Employee employee = new Employee();
        employee.setAccount(account);
        model.addAttribute("employee", employee); // Thêm đối tượng vào mô hình
        return "admin/employee/employee-add.html";
    }

    @PostMapping("/admin/employee/add-employee")
    public String addEmployee(Model model, @ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        employeeService.createEmployee(employee);

        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới nhân viên thành công!");
        return "redirect:/admin/employee";
    }


    @GetMapping("/admin/employee/{slug}")
    public String employeeEditView(Model model,@PathVariable("slug") String slug) {
        Employee employee = employeeService.getEmployee(slug);
        if (employee == null) {
            return "admin/404.html";
        }

        model.addAttribute("employee", employee); // Thêm đối tượng vào mô hình
        return "admin/employee/employee-edit.html";
    }
    @PostMapping("/admin/employee/edit-employee")
    public String editEmployee(Model model, @ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        employeeService.editEmployee(employee);

        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa nhân viên thành công!");
        return "redirect:/admin/employee";
    }
    @GetMapping("/admin/employee/delete-employee/{id}")
    public String deleteEmployee(Model model,@PathVariable("id") String employeeId, RedirectAttributes redirectAttributes) {
        if (employeeService.deleteEmployee(employeeId) != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công!");
        } else {
            return "admin/404.html";
        }
        return "redirect:/admin/employee";
    }

    

    
}
