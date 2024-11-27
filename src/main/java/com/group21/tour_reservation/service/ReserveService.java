package com.group21.tour_reservation.service;

import com.group21.tour_reservation.dto.request.CusInfRequest;
import com.group21.tour_reservation.dto.request.ReserveRequest;
import com.group21.tour_reservation.dto.response.ReserveResponse;
import com.group21.tour_reservation.entity.*;
import com.group21.tour_reservation.mapper.CustomerMapper;
import com.group21.tour_reservation.repository.*;
import com.group21.tour_reservation.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Optional;

@Service
@Slf4j
public class ReserveService {
    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private TourScheduleRepository tourScheduleRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReserveDetailRepository reserveDetailRepository;

    @Autowired
    private TourService tourService;
    @Autowired
    private AccountRepository accountRepository;

    public List<Reserve> getAllReserve() {
        return reserveRepository.findAll();
    }

    public List<Reserve> getAllReserveByYear(int year) {
        return reserveRepository.findAllReserveByYear(year);
    }

    public List<Reserve> getAllReserveByYearAndMonth(int year, int month) {
        return reserveRepository.findAllReserveByYearAndMonth(year, month);
    }

    public List<Integer> getAllYear() {
        List<Integer> yearList = reserveRepository.getAllYears();
        Collections.sort(yearList);
        return yearList;
    }

    public Reserve getTour(String slug) {
        Reserve reserve = reserveRepository.findById(
                com.group21.tour_reservation.utils.StringUtils.getIdFromSlug(slug)).orElse(null);

        return reserve;
    }

    public Reserve getReserve(String slug) {
        Reserve reserve = reserveRepository.findById(
                com.group21.tour_reservation.utils.StringUtils.getIdFromSlug(slug)).orElse(null);

        return reserve;
    }

    public Reserve deleteReserve(Integer reserveID) {
        Reserve reserve = reserveRepository.findById(reserveID).orElseThrow(null);
        if (reserve != null) {
            reserve.setStatus(0);
        } else {
            return null;
        }
        return reserveRepository.save(reserve);
    }

    public Reserve addReserve(Integer reserveID) {
        Reserve reserve = reserveRepository.findById(reserveID).orElseThrow(null);
        if (reserve != null) {
            reserve.setStatus(2);
        } else {
            return null;
        }
        return reserveRepository.save(reserve);
    }

    public Integer handleQuantityLeftOfSchedule(TourSchedule tourSchedule) {
        int quantityReserved = 0;
        for (Reserve reserve : tourSchedule.getReserves()) {
            quantityReserved += reserve.getReserveDetails().size();
        }

        return tourSchedule.getQuantity() - quantityReserved;
    }

    public Integer countAdult(ReserveRequest reserveRequest) {
        int countAdult = 0;
        for (CusInfRequest cusInfRequest : reserveRequest.getCustomers()) {
            if (cusInfRequest.getCustomerType().equals("adult")) {
                countAdult += 1;
            }
        }
        return countAdult;
    }

    public Integer countChild(ReserveRequest reserveRequest) {
        int countChild = 0;
        for (CusInfRequest cusInfRequest : reserveRequest.getCustomers()) {
            if (cusInfRequest.getCustomerType().equals("child")) {
                countChild += 1;
            }
        }
        return countChild;
    }

    public Integer handlePrice(TourSchedule tourSchedule, ReserveRequest reserveRequest) {
        int price = (tourService.handlePriceAdultSale(tourSchedule) * countAdult(reserveRequest)) +
                (tourService.handlePriceChildSale(tourSchedule) * countChild(reserveRequest));
        return price;
    }

    public Integer handlePriceReserveDetail(TourSchedule tourSchedule, Customer customer) {
        if (customer.getCustomerType().equals("adult")) {
            return tourService.handlePriceAdultSale(tourSchedule);
        } else if (customer.getCustomerType().equals("child")) {
            return tourService.handlePriceChildSale(tourSchedule);
        }
        return -1;
    }
    public List<ReserveDetail> getAllReserveDetailsByReserveId(int reserveId) {
        return reserveDetailRepository.findByReserveId(reserveId);
    }
    public ReserveResponse reserve(ReserveRequest reserveRequest, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Integer id = (Integer) session.getAttribute("id");



        ReserveResponse reserveResponse = new ReserveResponse();

        TourSchedule tourSchedule = tourScheduleRepository.findById(reserveRequest.getTourScheduleId())
                .orElse(null);
        if (tourSchedule != null && reserveRequest.getCustomers() != null) {
            if (reserveRequest.getCustomers().size() <= handleQuantityLeftOfSchedule(tourSchedule)) {

                Customer customerInf = new Customer();
                // them thong tin khach hang chinh
                Account account = new Account();
                if (id != null) {
                    account = accountRepository.findById(id).orElse(null);
                }

                if (id != null && account.getRole().equals("USER")) {

                    customerInf = customerRepository.findById(account.getCustomer().getCustomerId()).orElse(null);
                    customerInf.setCustomerName(reserveRequest.getCustomerName());
                    customerInf.setEmail(reserveRequest.getEmail());
                    customerInf.setPhoneNumber(reserveRequest.getPhoneNumber());
                    customerInf.setAddress(reserveRequest.getAddress());
                } else {
                    customerInf = new Customer();
                     customerInf = customerMapper.toCustomerFromReserveRequest(reserveRequest);
                }

                System.out.println(">>>> Có sẵn" + customerInf.getCustomerId());
                customerInf.setCustomerType("adult");
                customerInf.setStatus(1);

                // tao reserve
                Reserve reserve = new Reserve();


                Set<Customer> customers = new HashSet<>();
                if (id != null &&  account.getRole().equals("USER")) {
                    customers = customerInf.getCustomers();
                }
                Set<ReserveDetail> reserveDetails = new HashSet<>();


                for (CusInfRequest customerReq : reserveRequest.getCustomers()) {
                    // customer
                    Customer customer = new Customer();
                    customer = customerMapper.toCustomerFromCusInfRequest(customerReq);
                    customer.setCustomer(customerInf);
                    customer.setStatus(1);
                    if (id != null &&  account.getRole().equals("USER")) {
                        customer = customerRepository.save(customer);
                    }

                    customers.add(customer);

                    // reserve detail
                    ReserveDetail reserveDetail = new ReserveDetail();
                    reserveDetail.setReserve(reserve);
                    reserveDetail.setCustomer(customer);
                    reserveDetail.setPrice(handlePriceReserveDetail(tourSchedule, customer));
                    reserveDetail.setStatus(1);
                    reserveDetails.add(reserveDetail);

                }

                reserve.setCustomer(customerInf);
                reserve.setTourSchedule(tourSchedule);
                reserve.setReserveDetail(reserveRequest.getReserveDetail());
                reserve.setAdultCount(countAdult(reserveRequest));
                reserve.setChildCount(countChild(reserveRequest));
                reserve.setPrice(handlePrice(tourSchedule, reserveRequest));
                reserve.setTime(LocalDateTime.now());
                reserve.setStatus(1);

                customerInf.setCustomers(customers);
                reserve.setReserveDetails(reserveDetails);

                customerRepository.save(customerInf);
                System.out.println( ">>> "+reserve.getCustomer().getCustomerId());

                Reserve savedReserve = reserveRepository.save(reserve); // Lưu reserve và nhận lại đối tượng đã lưu
                Integer reserveId = savedReserve.getReserveId();


                reserveResponse.setCode(200);
                reserveResponse.setMessage("Success");
                reserveResponse.setReserveId(reserveId);
            } else {
                reserveResponse.setCode(201);
                reserveResponse.setMessage("Không đủ chổ");
            }

        }
        System.out.println("reserveResponse >>> " + reserveResponse);

        return reserveResponse;
    }
}
