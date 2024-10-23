package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tour_schedule")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @OneToMany(mappedBy = "tourSchedule", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Reserve> reserves;


}
