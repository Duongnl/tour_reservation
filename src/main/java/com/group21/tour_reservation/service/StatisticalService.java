package com.group21.tour_reservation.service;

import com.group21.tour_reservation.entity.Reserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class StatisticalService {
    @Autowired
    private ReserveService reserveService;
    static Map<Object,Object> map = null;
    static List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
    static List<Map<Object,Object>> dataPoints1 = new ArrayList<Map<Object,Object>>();













    public int sumPrice(List<Reserve> data) {
        return data.stream()
                .mapToInt(Reserve::getPrice)
                .sum();
    }

    public int[] getPrice12Months(List<Reserve> data) {
        int[] money_mm = new int[12];
        for (Reserve reserve : data) {
            int mm = reserve.getTime().getMonthValue()-1;
            money_mm[mm] += reserve.getPrice();
        }
        return money_mm;
    }

    public String directContent(String pathFile){
        return "/admin/statistical/component/" + pathFile + ".html";
    }

    public int getYear(String year){
        return Integer.parseInt(year);
    }

    public YearMonth getYearMonth(String slug){
        String[] parts = slug.split("\\.");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        return YearMonth.of(year, month);
    }

    public int[] getPriceAllDayInMonth(List<Reserve> data, int numberDay) {
        int[] money_mm = new int[numberDay];
        for (Reserve reserve : data) {
            int mm = reserve.getTime().getDayOfMonth()-1;
            money_mm[mm] = reserve.getPrice();
        }
        return money_mm;
    }

    public int getYearToDay(){
        LocalDate today = LocalDate.now();
        return today.getYear();
    }


    public int[] getTotalAdultsByYear(int year){
        int[] data = new int[12];
        for (int i = 0; i < 12; i++) {
            data[i]=0;
            List<Reserve> data_raw = reserveService.getAllReserveByYearAndMonth(year,i+1);
            for (Reserve reserve : data_raw) {
                data[i] += reserve.getAdultCount();
            }
        }
        return data;
    }

    public int getAllTotalAdultsByYear(int year){
        int[] data = this.getTotalAdultsByYear(year);
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum+=data[i];
        }
        return sum;
    }

    public int[] getTotalChildrenByYear(int year){
        int[] data = new int[12];
        for (int i = 0; i < 12; i++) {
            data[i]=0;
            List<Reserve> data_raw = reserveService.getAllReserveByYearAndMonth(year,i+1);
            for (Reserve reserve : data_raw) {
                data[i] += reserve.getChildCount();
            }
        }
        return data;
    }
    public int getAllTotalChildrenByYear(int year){
        int[] data = this.getTotalChildrenByYear(year);
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum+=data[i];
        }
        return sum;
    }

    public int[] getTotalCustomerByYear(int year){
        int[] data = new int[12];
        for (int i = 0; i < 12; i++) {
            data[i]=0;
            List<Reserve> data_raw = reserveService.getAllReserveByYearAndMonth(year,i+1);
            for (Reserve reserve : data_raw) {
                int sum = reserve.getAdultCount() + reserve.getChildCount();
                data[i] += sum;
            }
        }
        return data;
    }
}


