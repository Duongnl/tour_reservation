package com.group21.tour_reservation.controller.admin;

import com.group21.tour_reservation.entity.Tour;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.entity.TransportDetail;
import com.group21.tour_reservation.service.TransportDetailService;
import com.group21.tour_reservation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class TransportDetailController {

    @Autowired
    private TransportDetailService transportDetailService;

    @PostMapping("/admin/tour/schedule/add-transport-detail")
    public String addTransportDetail(
            @RequestParam("schedule-slug") String scheduleSlug,
            @RequestParam("transport-slug") String transportSlug,
            @ModelAttribute("transportDetail") TransportDetail transportDetail,
            RedirectAttributes redirectAttributes,
            Model model
    ) {

        TransportDetail transportDetailRes = transportDetailService.addTransportDetail(scheduleSlug, transportSlug, transportDetail);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm phương tiện di chuyển thành công");
        return "redirect:/admin/tour/schedule/schedule-overview/" +
                StringUtils.createSlug(transportDetailRes.getTourSchedule().getScheduleName()) + "-" +
                transportDetailRes.getTourSchedule().getScheduleId();
    }

    @GetMapping("/admin/tour/schedule/delete-transport-detail/{id}")
    public String deleteTransportDetail(@PathVariable("id") Integer transportDetailId,
                                        RedirectAttributes redirectAttributes) {

      String slug =   transportDetailService.deleteTransportDetail(transportDetailId);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa phương tiện di chuyển thành công");


        return "redirect:/admin/tour/schedule/schedule-overview/" +
                StringUtils.createSlug(slug);
    }

    @PostMapping("/admin/tour/schedule/edit-transport-detail")
    public String editTransportDetail(
            @RequestParam("schedule-slug") String scheduleSlug,
            @RequestParam("transport-slug") String transportSlug,

            @ModelAttribute("transportDetail") TransportDetail transportDetail,
            RedirectAttributes redirectAttributes,
            Model model
    ) {


        TransportDetail transportDetailRes = transportDetailService.editTransportDetail(scheduleSlug, transportSlug, transportDetail);
        redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa thông tin phương tiện di chuyển thành công");
        return "redirect:/admin/tour/schedule/schedule-overview/" +
                StringUtils.createSlug(transportDetailRes.getTourSchedule().getScheduleName()) + "-" +
                transportDetailRes.getTourSchedule().getScheduleId();

//        return "redirect:/admin/tour/schedule/schedule-overview/du-lich-vao-mua-he-vui-nhat-sale-1";
    }
}
