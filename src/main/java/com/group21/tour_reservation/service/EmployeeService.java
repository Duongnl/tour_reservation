package com.group21.tour_reservation.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group21.tour_reservation.entity.Account;
import com.group21.tour_reservation.entity.Employee;
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

    public Employee addEmployee(Employee employee) {
        employee.getAccount().setStatus(1);
        return employeeRepository.save(employee);
    }

    public void createEmployee(Account account, Employee employee) {

        // if (accountRepository.existsByEmail(account.getEmail())) {
        // throw new IllegalArgumentException("Email đã tồn tại trong hệ thống.");
        // }
        account.setEmployee(employee);
        accountRepository.save(account);
        employeeRepository.save(employee);

    }
}
