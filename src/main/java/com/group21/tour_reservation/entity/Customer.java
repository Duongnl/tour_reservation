package com.group21.tour_reservation.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    Account account;

    @ManyToOne
    @JoinColumn(name ="relationship_id" , nullable = true, referencedColumnName = "customer_id")
    @JsonBackReference
    Customer customer;

    @Column(name = "relationship_name")
    String relationshipName;

    @Column(name = "customer_name")
    String customerName;

    @Column(name = "customer_type")
    String customerType;

    @Column(name = "sex")
    String sex;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "email")
    String emai;

    @Column(name = "address")
    String address;

    @Column(name = "birthday")
    LocalDate birthday;

    @Column(name = "visa_expire")
    LocalDate visaExpire;

    @Column(name = "status")
    int status;

    public Customer() {
        this.customerID = 0;
        this.account = null;
        this.customer = null;
        this.relationshipName = null;
        this.customerName = null;
        this.customerType = null;
        this.sex = null;
        this.phoneNumber = null;
        this.emai = null;
        this.address = null;
        this.birthday = null;
        this.visaExpire = null;
        this.status = 0;
    }

    public Customer(int customerID, Account account, Customer customer, String relationshipName, String customerName,
            String customerType, String sex, String phoneNumber, String emai, String address, LocalDate birthday,
            LocalDate visaExpire, int status) {
        this.customerID = customerID;
        this.account = account;
        this.customer = customer;
        this.relationshipName = relationshipName;
        this.customerName = customerName;
        this.customerType = customerType;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.emai = emai;
        this.address = address;
        this.birthday = birthday;
        this.visaExpire = visaExpire;
        this.status = status;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
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

    
}
