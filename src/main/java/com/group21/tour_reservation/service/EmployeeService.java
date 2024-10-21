package com.group21.tour_reservation.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group21.tour_reservation.entity.Account;
import com.group21.tour_reservation.entity.Employee;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.repository.AccountRepository;
import com.group21.tour_reservation.repository.EmployeeRepository;
import com.group21.tour_reservation.utils.StringUtils;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAllByStatus(1);
    }

    public Employee getEmployee(String slug) {

        return employeeRepository.findById(StringUtils.getIdFromSlug(slug)).orElse(null);
    }

    public void createEmployee(Employee employee) {

        // if (accountRepository.existsByEmail(account.getEmail())) {
        // throw new IllegalArgumentException("Email đã tồn tại trong hệ thống.");
        // }
        Account account = employee.getAccount();
        account.setTime(LocalDateTime.now());
        account.setStatus(1);
        accountRepository.save(account);
        employeeRepository.save(employee);

    }

    public Employee editEmployee(Employee employee) {
        Account account = employee.getAccount();

        if (account.getAccountId() != null) {
            // Đặt lại thời gian và trạng thái nếu cần thay đổi
            account.setTime(LocalDateTime.now());
            account.setStatus(1);
    
            // Lưu lại Account hiện có (không thêm mới)
            accountRepository.save(account);
        } else {
            return null;
        }
        // account.setTime(LocalDateTime.now());
        // account.setStatus(1);
        // accountRepository.save(account);
        return employeeRepository.save(employee);
    }

    public Employee deleteEmployee(String employeeId) {
        Employee employee = employeeRepository.findById(Integer.parseInt(employeeId)).orElseThrow(null);
        Account account = employee.getAccount();
        if (employee != null && account != null) {
            account.setStatus(0);
            accountRepository.save(account);
        } else {
            return null;
        }
        return employeeRepository.save(employee);
    }
}
