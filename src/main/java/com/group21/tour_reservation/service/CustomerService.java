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
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomer() {
        return customerRepository.findAllByStatus(1);
    }

    public Customer getCustomer(String slug) {

        return customerRepository.findById(StringUtils.getIdFromSlug(slug)).orElse(null);
    }

    public void createCustomer(Customer customer, int selectedCustomerId) {

        // if (accountRepository.existsByEmail(account.getEmail())) {
        // throw new IllegalArgumentException("Email đã tồn tại trong hệ thống.");
        // }
        if (selectedCustomerId != 0) {
            // Tìm người chủ hộ từ cơ sở dữ liệu theo customer_id
            Customer representative = customerRepository.findById(selectedCustomerId)
                    .orElseThrow(() -> new IllegalArgumentException("Người chủ hộ không tồn tại"));

            // Gán relationship_id của customer với người chủ hộ đã chọn
            customer.setCustomer(representative);
        }
        customer.setStatus(1);
        customerRepository.save(customer);

    }

    public List<Customer> findCustomersWithNullRelationshipId() {

        return customerRepository.findByCustomerIsNullAndStatus(1);
    }

    public Customer editCustomer(Customer customer, int selectedCustomerId) {

        if (selectedCustomerId != 0) {
            // Tìm người chủ hộ từ cơ sở dữ liệu theo customer_id
            Customer representative = customerRepository.findById(selectedCustomerId)
                    .orElseThrow(() -> new IllegalArgumentException("Người chủ hộ không tồn tại"));

            // Gán relationship_id của customer với người chủ hộ đã chọn
            customer.setCustomer(representative);
        }
        customer.setStatus(1);
        return customerRepository.save(customer);
    }

    public Customer deleteCustomer(String customerId) {
        Customer customer = customerRepository.findById(Integer.parseInt(customerId)).orElseThrow(null);
        if (customer != null) {
            customer.setStatus(0);
        } else {
            return null;
        }
        return customerRepository.save(customer);
    }
}
