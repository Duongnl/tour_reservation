package com.group21.tour_reservation.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group21.tour_reservation.entity.Account;
import com.group21.tour_reservation.entity.Customer;
import com.group21.tour_reservation.entity.Transport;
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
        customer.setStatus(1);
        customerpository.save(customer);

    }

    public Customer editCustomer(Customer customer) {
        customer.setStatus(1);
        return customerpository.save(customer);
    }

    public Customer deleteCustomer(String customerId) {
        Customer customer = customerpository.findById( Integer.parseInt(customerId)).orElseThrow(null);
        if (customer != null) {
            customer.setStatus(0);
        } else {
            return null;
        }
        return customerpository.save(customer);
    }
}
