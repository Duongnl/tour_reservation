package com.group21.tour_reservation.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tour")
public class Tour {

    @Id
    @Column(name = "tour_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tourId;

    @Column(name = "tour_name")
    private String tourName;;

    @Column (name = "tour_detail")
    private String tourDetail;

    @Column (name = "city")
    private String city;

    @Column (name = "country")
    private String country;

    @Column (name = "status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "category_id")
    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Image> images;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<TourSchedule> tourSchedules;

    public Tour() {}

    public Tour(Integer tourId, String tourName, String tourDetail, String city, String country, int status, Category category, Set<Image> images, Set<TourSchedule> tourSchedules) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.tourDetail = tourDetail;
        this.city = city;
        this.country = country;
        this.status = status;
        this.category = category;
        this.images = images;
        this.tourSchedules = tourSchedules;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourDetail() {
        return tourDetail;
    }

    public void setTourDetail(String tourDetail) {
        this.tourDetail = tourDetail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<TourSchedule> getTourSchedules() {
        return tourSchedules;
    }

    public void setTourSchedules(Set<TourSchedule> tourSchedules) {
        this.tourSchedules = tourSchedules;
    }
}
