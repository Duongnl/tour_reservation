package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_detail")
    private String categoryDetail;

    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Tour> tours;

}
