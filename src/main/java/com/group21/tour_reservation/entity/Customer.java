package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @Column(name = "relationship_name")
    private String relationshipName;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_type")
    private int customerType;

    @Column(name = "sex")
    private int sex;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "birthday", columnDefinition = "date")
    private LocalDate birthday;

    @Column(name = "visa_expire", columnDefinition = "date")
    private LocalDate visaExpire;

    @Column(name = "status")
    private int status;

    // Người đại diện
    @ManyToOne
    @JoinColumn(name = "relationship_id", nullable = true, referencedColumnName = "customer_id")
    @JsonBackReference
    Customer customer;

    // Người liên quan
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Customer> customers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @JsonManagedReference
    private Account account;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Reserve> reserves;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ReserveDetail> reserveDetails;

    public Customer() {
    }

    public Customer(Integer customerId, String relationshipName, String customerName, int customerType, int sex,
            String phoneNumber, String email, String address, LocalDate birthday, LocalDate visaExpire, int status,
            Customer customer, Set<Customer> customers, Account account, Set<Reserve> reserves,
            Set<ReserveDetail> reserveDetails) {
        this.customerId = customerId;
        this.relationshipName = relationshipName;
        this.customerName = customerName;
        this.customerType = customerType;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.visaExpire = visaExpire;
        this.status = status;
        this.customer = customer;
        this.customers = customers;
        this.account = account;
        this.reserves = reserves;
        this.reserveDetails = reserveDetails;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerType() {
        return customerType;
    }

    public void setCustomerType(int customerType) {
        this.customerType = customerType;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getVisaExpire() {
        return visaExpire;
    }

    public void setVisaExpire(LocalDate visaExpire) {
        this.visaExpire = visaExpire;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Reserve> getReserves() {
        return reserves;
    }

    public void setReserves(Set<Reserve> reserves) {
        this.reserves = reserves;
    }

    public Set<ReserveDetail> getReserveDetails() {
        return reserveDetails;
    }

    public void setReserveDetails(Set<ReserveDetail> reserveDetails) {
        this.reserveDetails = reserveDetails;
    }
}
