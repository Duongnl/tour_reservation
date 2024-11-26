package com.group21.tour_reservation.mapper;

import com.group21.tour_reservation.dto.request.CusInfRequest;
import com.group21.tour_reservation.dto.request.ReserveRequest;
import com.group21.tour_reservation.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomerFromReserveRequest(ReserveRequest reserveRequest);
    Customer toCustomerFromCusInfRequest(CusInfRequest cusInfRequest);
}
