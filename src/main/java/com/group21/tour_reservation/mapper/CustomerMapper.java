package com.group21.tour_reservation.mapper;

import com.group21.tour_reservation.dto.request.CusInfRequest;
import com.group21.tour_reservation.dto.request.ReserveRequest;
import com.group21.tour_reservation.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomerFromReserveRequest(ReserveRequest reserveRequest);
    Customer toCustomerFromCusInfRequest(CusInfRequest cusInfRequest);
    @Mapping(target = "customers", ignore = true)
    void updateCustomer(@MappingTarget Customer customer, ReserveRequest request);

}
