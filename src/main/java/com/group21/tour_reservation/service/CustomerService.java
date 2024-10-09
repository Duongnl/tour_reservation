package com.group21.tour_reservation.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group21.tour_reservation.entity.Account;
import com.group21.tour_reservation.entity.Customer;
import com.group21.tour_reservation.repository.AccountRepository;
import com.group21.tour_reservation.repository.CustomerRepository;
import com.group21.tour_reservation.utils.StringUtils;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerpository;

    @Autowired
    private AccountRepository accountRepository;

    public List<Customer> getAllCustomer() {
        return customerpository.findAllByStatus(1);
    }

    public Customer getCustomer(String slug) {

        return customerpository.findById(StringUtils.getIdFromSlug(slug)).orElse(null);
    }

    public void createCustomer(Customer customer) {

        // if (accountRepository.existsByEmail(account.getEmail())) {
        // throw new IllegalArgumentException("Email đã tồn tại trong hệ thống.");
        // }
        Account account = customer.getAccount();
        account.setTime(LocalDateTime.now());
        account.setStatus(1);
        accountRepository.save(account);
        customerpository.save(customer);

    }

    public Customer editEmployee(Customer customer) {
        Account account = customer.getAccount();

        if (account.getAccountId() != null) {
            // Đặt lại thời gian và trạng thái nếu cần thay đổi
            account.setTime(LocalDateTime.now());
            account.setStatus(1);
    
            // Lưu lại Account hiện có (không thêm mới)
            accountRepository.save(account);
        } else {
            return null;
        }
        account.setTime(LocalDateTime.now());
        account.setStatus(1);
        accountRepository.save(account);
        return customerpository.save(customer);
    }

    public Customer deleteCustomer(String customerId) {
        Customer customer = customerpository.findById(Integer.parseInt(customerId)).orElseThrow(null);
        Account account = customer.getAccount();
        if (customer != null && account != null) {
            account.setStatus(0);
            accountRepository.save(account);
        } else {
            return null;
        }
        return customerpository.save(customer);
    }
}
