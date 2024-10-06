package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "birthday", columnDefinition = "date")
    private LocalDate birthday;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Reserve> reserves;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @JsonManagedReference
    private Account account;

    public Employee() {}

    public Employee(Integer employeeId, String employeeName, LocalDate birthday, String phoneNumber, Set<Reserve> reserves, Account account) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.reserves = reserves;
        this.account = account;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Reserve> getReserves() {
        return reserves;
    }

    public void setReserves(Set<Reserve> reserves) {
        this.reserves = reserves;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
