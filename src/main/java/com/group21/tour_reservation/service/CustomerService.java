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
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<Customer> getAllCustomer() {
        return customerRepository.findAllByStatus(1);
    }

    public Customer getCustomer(String slug) {

        return customerRepository.findById(StringUtils.getIdFromSlug(slug)).orElse(null);
    }

    // Hàm lấy Customer theo ID
    public Customer getCustomerById(int customerId) {
        // Sử dụng Optional để xử lý trường hợp không tìm thấy Customer
        return customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khách hàng với ID: " + customerId));
    }
    public void createAccount(Customer customer) {
        Account account = customer.getAccount();
        account.setTime(LocalDateTime.now());
        account.setRole("ROLE_USER");
        account.setEmail(customer.getEmail());
        account.setStatus(1);
        customer.setStatus(1);
        accountRepository.save(account);
        customerRepository.save(customer);
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

    public Customer editCustomer(Customer customer, Integer selectedCustomerId) {

        if (selectedCustomerId != null && selectedCustomerId != 0) {
            // Tìm người chủ hộ từ cơ sở dữ liệu theo customer_id
            Customer representative = customerRepository.findById(selectedCustomerId)
                    .orElseThrow(() -> new IllegalArgumentException("Người chủ hộ không tồn tại"));

            // Gán relationship_id của customer với người chủ hộ đã chọn
            customer.setCustomer(representative);
        }
     else {
        // Nếu không có người chủ hộ được chọn, thiết lập relationship_id là null
        customer.setCustomer(null);
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

     // Phương thức tìm khách hàng theo relationship_id
     public List<Customer> getCustomersByRelationshipId(Integer customerId) {
        return customerRepository.findByCustomer_RelationshipId(customerId);
    }
}
