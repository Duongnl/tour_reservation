package com.group21.tour_reservation.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tour")
public class Tour {

    @Id
    @Column(name = "tour_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tourId;

    @Column(name = "tour_name")
    private String tourName;;

    @Column (name = "tour_detail")
    private String tourDetail;

    @Column (name = "city")
    private String city;

    @Column (name = "country")
    private String country;

    @Column (name = "status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "category_id")
    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Image> images;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<TourSchedule> tourSchedules;


}
