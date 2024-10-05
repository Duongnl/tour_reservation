package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transport_detail")
public class TransportDetail {

    @Id
    @Column(name = "transport_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transportDetailId;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "transport_id", nullable = false, referencedColumnName = "transport_id")
    @JsonBackReference
    private Transport transport;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false, referencedColumnName = "schedule_id")
    @JsonBackReference
    private TourSchedule tourSchedule;

    public TransportDetail() {}

    public TransportDetail(Integer transportDetailId, LocalDateTime departureTime, LocalDateTime arrivalTime, int status, Transport transport, TourSchedule tourSchedule) {
        this.transportDetailId = transportDetailId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = status;
        this.transport = transport;
        this.tourSchedule = tourSchedule;
    }

    public Integer getTransportDetailId() {
        return transportDetailId;
    }

    public void setTransportDetailId(Integer transportDetailId) {
        this.transportDetailId = transportDetailId;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public TourSchedule getTourSchedule() {
        return tourSchedule;
    }

    public void setTourSchedule(TourSchedule tourSchedule) {
        this.tourSchedule = tourSchedule;
    }
}
