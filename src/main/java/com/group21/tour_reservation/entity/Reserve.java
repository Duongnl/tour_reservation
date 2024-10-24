package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "reserve")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false, referencedColumnName = "schedule_id")
    @JsonBackReference
    private TourSchedule tourSchedule;

}
