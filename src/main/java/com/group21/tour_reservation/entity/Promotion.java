package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "promotion")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Promotion {

    @Id
    @Column(name = "promotion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer promotionId;

    @Column(name = "promotion_name")
    private String promotionName;

    @Column(name = "percentage_adult")
    private double percentageAdult;

    @Column(name = "percentage_child")
    private double percentageChild;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column (name = "end_time")
    private LocalDateTime endTime;

    @Column (name = "status")
    private int status;

    @ManyToMany(mappedBy = "promotions")
    @JsonBackReference
    private Set<TourSchedule> tourSchedules;

}
