package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transport_detail")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
}
