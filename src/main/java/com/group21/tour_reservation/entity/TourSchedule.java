package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tour_schedule")
public class TourSchedule {

    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    @Column(name = "schedule_name")
    private String scheduleName;

    @Column(name = "departure_date",columnDefinition = "date")
    private LocalDate departureDate;

    @Column(name = "return_date",columnDefinition = "date")
    private LocalDate returnDate;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_adult")
    private Integer priceAdult;

    @Column(name = "price_child")
    private Integer priceChild;

    @Column(name = "visa_expire", columnDefinition = "date")
    private LocalDate visaExpire;

    @Column(name = "status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false, referencedColumnName = "tour_id")
    @JsonBackReference
    private Tour tour;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "promotion_detail",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id"))
    @JsonManagedReference
    private Set<Promotion> promotions;

    @OneToMany(mappedBy = "tourSchedule", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<TransportDetail> transportDetails;

    public TourSchedule() {}

    public TourSchedule(Integer scheduleId, String scheduleName, LocalDate departureDate, LocalDate returnDate, int quantity, Integer priceAdult, Integer priceChild, LocalDate visaExpire, int status, Tour tour, Set<Promotion> promotions, Set<TransportDetail> transportDetails) {
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.quantity = quantity;
        this.priceAdult = priceAdult;
        this.priceChild = priceChild;
        this.visaExpire = visaExpire;
        this.status = status;
        this.tour = tour;
        this.promotions = promotions;
        this.transportDetails = transportDetails;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getPriceAdult() {
        return priceAdult;
    }

    public void setPriceAdult(Integer priceAdult) {
        this.priceAdult = priceAdult;
    }

    public Integer getPriceChild() {
        return priceChild;
    }

    public void setPriceChild(Integer priceChild) {
        this.priceChild = priceChild;
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

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Set<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Set<TransportDetail> getTransportDetails() {
        return transportDetails;
    }

    public void setTransportDetails(Set<TransportDetail> transportDetails) {
        this.transportDetails = transportDetails;
    }
}
