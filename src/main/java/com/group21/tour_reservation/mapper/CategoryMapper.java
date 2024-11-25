package com.group21.tour_reservation.mapper;

import com.group21.tour_reservation.dto.response.CategorySelect2Response;
import com.group21.tour_reservation.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategorySelect2Response toCategorySelect2Response(Category category);

}
