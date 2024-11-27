package com.group21.tour_reservation.controller.client;

import com.group21.tour_reservation.dto.request.TourFilterRequest;
import com.group21.tour_reservation.dto.response.CategorySelect2Response;
import com.group21.tour_reservation.dto.response.TourCardResponse;
import com.group21.tour_reservation.dto.response.TourPageResponse;
import com.group21.tour_reservation.entity.Category;
import com.group21.tour_reservation.entity.Tour;
import com.group21.tour_reservation.service.CategoryService;
import com.group21.tour_reservation.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class TourPageRestController {

    @Autowired
    private TourService tourService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/api/client/tour")
    public List<TourCardResponse> getClientTours() {
        return tourService.getTourCards();
    }

    @GetMapping("/api/client/category")
    public List<CategorySelect2Response> getClientCategories() {
        return categoryService.getClientCategories();
    }

    @GetMapping("/api/client/departure-location")
    public Set<String> getClientDepartureLocations() {
        return tourService.getClientDepartureLocations();
    }

    @GetMapping("/api/client/destination-location")
    public Set<String> getClientDestinationLocations() {
        return tourService.getClientDestinationLocations();
    }

    @PostMapping("/api/client/filter-tour")
    public TourPageResponse FilterTours (@RequestBody TourFilterRequest tourFilterRequest) {
        return tourService.filterTours(tourFilterRequest);
    }



}
