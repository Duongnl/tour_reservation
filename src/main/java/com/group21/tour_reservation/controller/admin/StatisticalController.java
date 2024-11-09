package com.group21.tour_reservation.controller.admin;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

import com.group21.tour_reservation.entity.Category;
import com.group21.tour_reservation.entity.Reserve;
import com.group21.tour_reservation.service.CategoryService;
import com.group21.tour_reservation.service.ReserveService;
import com.group21.tour_reservation.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StatisticalController {
    @Autowired
    private ReserveService reserveService;

    @Autowired
    private StatisticalService statisticalService;

    @GetMapping("/admin/statistical/year/{slug}")
    public String year(Model model, @PathVariable("slug") String slug) {
        int year = statisticalService.getYear(slug);
        List<String> labels = List.of("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        List<Reserve> data = reserveService.getAllReserveByYear(year);
        model.addAttribute("reserves", reserveService.getAllReserveByYear(year));
        model.addAttribute("money_mm", statisticalService.getPrice12Months(data));
        model.addAttribute("year", year);
        model.addAttribute("labels", labels);
        model.addAttribute("type", statisticalService.directContent("revenue_year"));
        model.addAttribute("sum", statisticalService.sumPrice(data));
        return "admin/statistical/index.html";
    }

    @GetMapping("/admin/statistical/date/{slug}")
    public String month(Model model, @PathVariable("slug") String slug) {
        YearMonth yearMonth = statisticalService.getYearMonth(slug);
        List<Reserve> data = reserveService.getAllReserveByYearAndMonth(yearMonth.getYear(), yearMonth.getMonthValue());
        int[] labels = IntStream.rangeClosed(1, yearMonth.lengthOfMonth()).toArray();
        model.addAttribute("reserves", reserveService.getAllReserveByYear(yearMonth.getYear()));
        model.addAttribute("money_mm", statisticalService.getPrice12Months(data));
        model.addAttribute("date", statisticalService.getYearMonth(slug));
        model.addAttribute("labels", labels);
        model.addAttribute("sum", statisticalService.sumPrice(data));
        model.addAttribute("type", statisticalService.directContent("revenue_month"));
        return "admin/statistical/index.html";
    }
}
