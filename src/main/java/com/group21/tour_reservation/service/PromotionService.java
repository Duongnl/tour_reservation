package com.group21.tour_reservation.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group21.tour_reservation.entity.Customer;
import com.group21.tour_reservation.entity.Employee;
import com.group21.tour_reservation.entity.Promotion;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.repository.PromotionRepository;
import com.group21.tour_reservation.utils.StringUtils;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    public List<Promotion> getAllPromotion() {
        return promotionRepository.findAllByStatus(1);
    }

   public Promotion getPromotion(String slug) {

        return promotionRepository.findById(StringUtils.getIdFromSlug(slug)).orElse(null);
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
}
