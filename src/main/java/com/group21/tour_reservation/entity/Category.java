package com.group21.tour_reservation.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryID;

    @Column(name = "category_name")
    String categoryName;

    @Column(name = "category_detail")
    String categoryDetail;

    @Column(name = "status")
    int status;

    public Category(int categoryID, String categoryName, String categoryDetail, int status) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryDetail = categoryDetail;
        this.status = status;
    }

    public Category() {
        this.categoryID = 0;
        this.categoryName = null;
        this.categoryDetail = null;
        this.status = 0;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCategoryDetail() {
        return categoryDetail;
    }

    public void setCategoryDetail(String categoryDetail) {
        this.categoryDetail = categoryDetail;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
