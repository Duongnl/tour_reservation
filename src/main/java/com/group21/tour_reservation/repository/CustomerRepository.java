package com.group21.tour_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import com.group21.tour_reservation.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{

    
    List<Customer> findAllByStatus(int status);
    // Truy vấn khách hàng có relationship_id là null và status bằng 0
    List<Customer> findByCustomerIsNullAndStatus(int status);
    Optional<Customer> findByCustomerId(int customerId);

    // List<Customer> findByCustomer(int customer);

    @Query("SELECT c FROM Customer c WHERE c.customer.customerId = :customerId")
List<Customer> findByCustomer_RelationshipId(@Param("customerId") Integer customerId);
}
