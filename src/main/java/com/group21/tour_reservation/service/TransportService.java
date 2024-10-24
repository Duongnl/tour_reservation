package com.group21.tour_reservation.service;

import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.repository.TransportRepository;
import com.group21.tour_reservation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {

    @Autowired
    private TransportRepository transportRepository;


    public List<Transport> getAllTransports() {
        return transportRepository.findAllByStatus(1);
    }

    public Transport getTransport(String slug) {

        return transportRepository.findById(StringUtils.getIdFromSlug(slug)).orElse(null);
    }



    public Transport addTransport(Transport transport) {
        transport.setStatus(1);
        return transportRepository.save(transport);
    }

    public Transport editTransport(Transport transport) {
        transport.setStatus(1);
        return transportRepository.save(transport);
    }

    public Transport deleteTransport(String transportId) {
        Transport transport = transportRepository.findById( Integer.parseInt(transportId)).orElseThrow(null);
        if (transport == null) {
            return null;
        } else
        if (!transport.getTransportDetails().isEmpty()) {
            return transport;
        } else {
            transport.setStatus(0);
        }
        return transportRepository.save(transport);
    }


}
