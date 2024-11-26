package com.group21.tour_reservation.repository;

import com.group21.tour_reservation.dto.request.TourFilterRequest;
import com.group21.tour_reservation.entity.Tour;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class TourRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    public List<Tour> filterTourRepository(TourFilterRequest tourFilterRequest) {

        StringBuilder sql = new StringBuilder("SELECT t FROM Tour t WHERE t.status = 1");
        if (tourFilterRequest.getDepartureDate() != null) {
             sql = new StringBuilder("SELECT t FROM Tour t JOIN t.tourSchedules tt " +
                     "WHERE t.status = 1 AND tt.status =1 AND tt.departureDate = :departureDate ");
        } else {
             sql = new StringBuilder("SELECT t FROM Tour t WHERE t.status = 1");
        }

        if (tourFilterRequest.getCategory() != 0) {
            sql.append(" AND t.category.categoryId = :category");
        }
        if (!"all".equals(tourFilterRequest.getDepartureLocation())) {
            sql.append(" AND t.departureLocation LIKE :departureLocation");
        }
        if (!"all".equals(tourFilterRequest.getDestinationLocation())) {
            sql.append(" AND t.country LIKE :destinationLocation");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Query query = entityManager.createQuery(sql.toString(), Tour.class);

        if (tourFilterRequest.getCategory() != 0) {
            query.setParameter("category", tourFilterRequest.getCategory());
        }
        if (!"all".equals(tourFilterRequest.getDepartureLocation())) {
            query.setParameter("departureLocation", "%" + tourFilterRequest.getDepartureLocation() + "%");
        }
        if (!"all".equals(tourFilterRequest.getDestinationLocation())) {
            query.setParameter("destinationLocation", "%" + tourFilterRequest.getDestinationLocation() + "%");
        }

        if(tourFilterRequest.getDepartureDate() != null) {
            query.setParameter("departureDate", LocalDate.parse(tourFilterRequest.getDepartureDate(), formatter));
        }

        return query.getResultList();
    }




}
