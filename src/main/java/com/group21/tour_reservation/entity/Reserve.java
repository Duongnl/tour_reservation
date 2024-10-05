package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "reserve")
public class Reserve {

    @Id
    @Column(name = "reserve_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reserveId;

    @Column(name = "reserve_detail")
    private String reserveDetail;

    @Column(name = "adult_count")
    private int adultCount;

    @Column(name = "child_count")
    private int childCount;

    @Column(name = "price")
    private int price;

    @Column(name = "time")
    LocalDateTime time;

    @Column(name = "status")
    int status;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, referencedColumnName = "employee_id")
    @JsonBackReference
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "customer_id")
    @JsonBackReference
    private Customer customer;

    @OneToMany(mappedBy = "reserve", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ReserveDetail> reserveDetails;

    public Reserve() {}

    public Reserve(Integer reserveId, String reserveDetail, int adultCount, int childCount, int price, LocalDateTime time, int status, Employee employee, Customer customer, Set<ReserveDetail> reserveDetails) {
        this.reserveId = reserveId;
        this.reserveDetail = reserveDetail;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.price = price;
        this.time = time;
        this.status = status;
        this.employee = employee;
        this.customer = customer;
        this.reserveDetails = reserveDetails;
    }

    public Integer getReserveId() {
        return reserveId;
    }

    public void setReserveId(Integer reserveId) {
        this.reserveId = reserveId;
    }

    public String getReserveDetail() {
        return reserveDetail;
    }

    public void setReserveDetail(String reserveDetail) {
        this.reserveDetail = reserveDetail;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<ReserveDetail> getReserveDetails() {
        return reserveDetails;
    }

    public void setReserveDetails(Set<ReserveDetail> reserveDetails) {
        this.reserveDetails = reserveDetails;
    }
}
