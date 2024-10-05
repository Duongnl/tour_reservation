package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "reserve_detail")
public class ReserveDetail {

    @Id
    @Column(name = "reserve_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reserveDetailId;

    @Column(name = "price")
    private int price;

    @Column(name = "detail")
    private String detail;

    @Column(name = "status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "customer_id")
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "reserve_id", nullable = false, referencedColumnName = "reserve_id")
    @JsonBackReference
    private Reserve reserve;

    public ReserveDetail() {}

    public ReserveDetail(Integer reserveDetailId, int price, String detail, int status, Customer customer, Reserve reserve) {
        this.reserveDetailId = reserveDetailId;
        this.price = price;
        this.detail = detail;
        this.status = status;
        this.customer = customer;
        this.reserve = reserve;
    }

    public Integer getReserveDetailId() {
        return reserveDetailId;
    }

    public void setReserveDetailId(Integer reserveDetailId) {
        this.reserveDetailId = reserveDetailId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public Reserve getReserve() {
        return reserve;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }
}
