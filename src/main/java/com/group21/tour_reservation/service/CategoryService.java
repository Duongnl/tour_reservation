package com.group21.tour_reservation.service;

import com.group21.tour_reservation.entity.Category;
import com.group21.tour_reservation.repository.CategoryRepository;
import com.group21.tour_reservation.utils.StringUtils;
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

    public Category getCategory(String slug) {

        return categoryRepository.findById(StringUtils.getIdFromSlug(slug)).orElse(null);
    }


    public Category addCategory(Category category) {
        category.setStatus(1);
        return categoryRepository.save(category);
    }

    public Category editCategory(Category newCategory) {
//        Category category = categoryRepository.findById( Integer.parseInt(categoryId)).orElseThrow(null);

//        newCategory.setStatus(1);
        return categoryRepository.save(newCategory);
    }

    public Category deleteCategory(String categoryId) {
        Category category = categoryRepository.findById( Integer.parseInt(categoryId)).orElseThrow(null);

        System.out.println(categoryId);
        if (category == null) {
            return null;
        }
        category.setStatus(0);

        return categoryRepository.save(category);
    }

}
