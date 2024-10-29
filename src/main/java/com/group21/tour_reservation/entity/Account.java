package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "phone_number")
    private String phoneNumber;


    @Column(name = "status")
    private int status;

    @OneToOne(mappedBy = "account")
    @JsonBackReference
    private Employee employee;

    @OneToOne(mappedBy = "account")
    @JsonBackReference
    private Customer customer;

}
