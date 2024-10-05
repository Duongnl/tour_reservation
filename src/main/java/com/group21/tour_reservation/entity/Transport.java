package com.group21.tour_reservation.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "transport")
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

    public Transport() {}

    public Transport(Integer transportId, String transportName, String transportDetail, String departureLocation, String destinationLocation, int status, Set<TransportDetail> transportDetails) {
        this.transportId = transportId;
        this.transportName = transportName;
        this.transportDetail = transportDetail;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.status = status;
        this.transportDetails = transportDetails;
    }

    public Integer getTransportId() {
        return transportId;
    }

    public void setTransportId(Integer transportId) {
        this.transportId = transportId;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public String getTransportDetail() {
        return transportDetail;
    }

    public void setTransportDetail(String transportDetail) {
        this.transportDetail = transportDetail;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<TransportDetail> getTransportDetails() {
        return transportDetails;
    }

    public void setTransportDetails(Set<TransportDetail> transportDetails) {
        this.transportDetails = transportDetails;
    }
}
