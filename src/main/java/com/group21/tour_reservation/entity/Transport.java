package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "transport")
//tao get set
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transport {

    @Id
    @Column(name = "transport_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transportId;

    @Column(name = "transport_name")
    private String transportName;

    @Column(name = "transport_detail")
    private String transportDetail;

    @Column(name = "departure_location")
    private String departureLocation;

    @Column (name = "destination_location")
    private String destinationLocation;

    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<TransportDetail> transportDetails;


}
