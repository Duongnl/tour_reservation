package com.group21.tour_reservation.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image {

    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    @Column (name = "url")
    private String url;

    @Column(name = "status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false, referencedColumnName = "tour_id")
    @JsonBackReference
    private Tour tour;

}
