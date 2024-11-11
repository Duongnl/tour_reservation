package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reserve_detail")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

}
