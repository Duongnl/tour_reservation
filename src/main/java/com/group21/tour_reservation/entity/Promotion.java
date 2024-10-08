package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @Column(name = "promotion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer promotionId;

    @Column(name = "promotion_name")
    private String promotionName;

    @Column(name = "percentage")
    private double percentage;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column (name = "end_time")
    private LocalDateTime endTime;

    @Column (name = "status")
    private int status;

    @ManyToMany(mappedBy = "promotions")
    @JsonBackReference
    private Set<TourSchedule> tourSchedules;

    public Promotion() {}

    public Promotion(Integer promotionId, String promotionName, double percentage, LocalDateTime startTime, LocalDateTime endTime, int status, Set<TourSchedule> tourSchedules) {
        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.percentage = percentage;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.tourSchedules = tourSchedules;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<TourSchedule> getTourSchedules() {
        return tourSchedules;
    }

    public void setTourSchedules(Set<TourSchedule> tourSchedules) {
        this.tourSchedules = tourSchedules;
    }
}
