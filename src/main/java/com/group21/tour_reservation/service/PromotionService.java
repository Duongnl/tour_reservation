package com.group21.tour_reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group21.tour_reservation.entity.Promotion;
import com.group21.tour_reservation.entity.TourSchedule;
import com.group21.tour_reservation.repository.PromotionRepository;
import com.group21.tour_reservation.repository.TourScheduleRepository;
import com.group21.tour_reservation.utils.StringUtils;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private TourScheduleRepository tourScheduleRepository;

    public List<Promotion> getAllPromotion() {
        return promotionRepository.findAllByStatus(1);
    }

   public Promotion getPromotion(String slug) {

        return promotionRepository.findById(StringUtils.getIdFromSlug(slug)).orElse(null);
    }

    public Optional<Promotion> getPromotionDetail(int id) {

        return promotionRepository.findById(id);
    }

    public void createPromotion(Promotion promotion) {
        promotion.setStatus(1);
        promotionRepository.save(promotion);
    }

    public Promotion editPromotion(Promotion promotion) {
        promotion.setStatus(1);
        return promotionRepository.save(promotion);
    }

    public Promotion deletePromotion(String promotionId) {
        Promotion promotion = promotionRepository.findById(Integer.parseInt(promotionId)).orElseThrow(null);
        if (promotion != null) {
            promotion.setStatus(0);
        } else {
            return null;
        }
        return promotionRepository.save(promotion);
    }

    public List<TourSchedule> getAllShedules() {
        return tourScheduleRepository.findAllByStatus(1);  // Lấy tất cả lịch trình có status = 1
    }

    public void addPromotionToSchedule(Integer promotionId, Integer scheduleId){
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(() -> new RuntimeException("Không tìm thấy khuyễn mãi"));
        TourSchedule tourSchedule = tourScheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("Không tìm thấy lịch trình"));

        tourSchedule.getPromotions().add(promotion); // Thêm promotion vào danh sách của TourSchedule
        tourScheduleRepository.save(tourSchedule); 
    }
    public List<TourSchedule> getSchedulesByPromotionId(String promotionId) {
        // Giả sử bạn có phương thức trong repository để lấy lịch trình theo promotionId
        return promotionRepository.findSchedulesByPromotionId(promotionId);
    }
    

    // Hàm xóa lịch trình sau khi thêm vào khuyễn mãi
    public void removeScheduleFromPromotion(Integer promotionId, Integer scheduleId) {
        promotionRepository.removeTourScheduleFromPromotion(promotionId, scheduleId);
    }
    
}