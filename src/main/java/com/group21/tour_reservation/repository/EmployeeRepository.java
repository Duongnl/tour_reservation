package com.group21.tour_reservation.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.group21.tour_reservation.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

    @Query("SELECT e FROM Employee e JOIN e.account a WHERE a.status = :status")
    List<Employee> findAllByStatus(@Param("status") int status);
}

