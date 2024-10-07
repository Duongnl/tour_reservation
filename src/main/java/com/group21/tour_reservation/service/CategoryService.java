package com.group21.tour_reservation.service;

import com.group21.tour_reservation.entity.Category;
import com.group21.tour_reservation.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAllByStatus(1);
    }


}
