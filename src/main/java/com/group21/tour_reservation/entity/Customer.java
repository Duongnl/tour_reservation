package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @Column(name = "relationship_name")
    private String relationshipName;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_type")
    private int customerType;

    @Column(name = "sex")
    private int sex;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "birthday",columnDefinition  ="date")
    private LocalDate birthday;

    @Column(name = "visa_expire", columnDefinition = "date")
    private LocalDate visaExpire;

    @Column(name = "status")
    private int status;

    //    Người đại diện
    @ManyToOne
    @JoinColumn(name ="relationship_id" , nullable = true, referencedColumnName = "customer_id")
    @JsonBackReference
    Customer customer;

    //    Người liên quan
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Customer> customers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @JsonManagedReference
    private Account account;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Reserve> reserves;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ReserveDetail> reserveDetails;



}
