package com.group21.tour_reservation.service;


import com.group21.tour_reservation.entity.Category;
import com.group21.tour_reservation.entity.Image;
import com.group21.tour_reservation.entity.Tour;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.repository.CategoryRepository;
import com.group21.tour_reservation.repository.ImgRepository;
import com.group21.tour_reservation.repository.TourRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImgRepository imgRepository;

    public List<Tour> getAllTours() {
        return tourRepository.findAllByStatus(1);
    }

    public Tour addTour(MultipartFile imgMain,
                        MultipartFile img1,
                        MultipartFile img2,
                        String categorySlug,
                        Tour tour) {
        Category category = categoryRepository.findById(
                com.group21.tour_reservation.utils.StringUtils.getIdFirstFromSlug(categorySlug))
                .orElse(null);

        tour.setCategory(category);
        tour.setStatus(1);
        tourRepository.save(tour);

        Image imageMain = new Image();
        imageMain.setUrl(uploadImage(imgMain));
        imageMain.setStatus(0);
        imageMain.setTour(tour);
        imgRepository.save(imageMain);

        Image image1 = new Image();
        image1.setUrl(uploadImage(img1));
        if (!image1.getUrl().equals("/admin/images/")) {
            image1.setStatus(1);
            image1.setTour(tour);
            imgRepository.save(image1);
        }


        Image image2 = new Image();
        image2.setUrl(uploadImage(img2));
        if (!image2.getUrl().equals("/admin/images/")) {
            image2.setStatus(2);
            image2.setTour(tour);
            imgRepository.save(image2);
        }


        return tour;
    }

    public Tour editTour(MultipartFile imgMain,
                         MultipartFile img1,
                         MultipartFile img2,
                         String categorySlug,
                         Tour tour,
                         String ipImg1,
                         String ipImg2
                        ){
        Category category = categoryRepository.findById(
                        com.group21.tour_reservation.utils.StringUtils.getIdFirstFromSlug(categorySlug))
                .orElse(null);
        tour.setCategory(category);
        tour.setStatus(1);

        entityManager.clear();// This should work

//      Code  Chỉnh sửa ảnh
        Tour tourNew = tourRepository.findById(tour.getTourId()).orElse(null);
        Image imageMain = null;
        Image image1 = null;
        Image image2 = null;

//       Lấy tất cả ảnh tương ứng của tour
        for (Image image : tourNew.getImages()) {
            if (image.getStatus() == 0) {
                imageMain = image;
            } else if (image.getStatus() == 1) {
                image1=image;
            } else if (image.getStatus() == 2 ) {
                image2 = image;
            }
        }

//      Bắt đầu xóa và chỉnh sửa ảnh

        if (!imgMain.isEmpty()) {
            imageMain.setUrl(uploadImage(imgMain));
            imageMain.setStatus(0);
            imageMain.setTour(tour);
            imgRepository.save(imageMain);
        }

        if (img1.isEmpty() && ipImg1.isEmpty() && image1 != null) {
            tourNew.getImages().remove(image1);
            imgRepository.delete(image1); // Chắc chắn xóa image khỏi DB
            tourRepository.save(tourNew);
        } else if (!img1.isEmpty()) {
            if (image1 == null)
            {
            image1 = new Image();
            }
            image1.setUrl(uploadImage(img1));
            image1.setStatus(1);
            image1.setTour(tour);
            imgRepository.save(image1);
        }

        if (img2.isEmpty() && ipImg2.isEmpty() && image2 != null) {
            tourNew.getImages().remove(image2);
            imgRepository.delete(image2); // Chắc chắn xóa image khỏi DB
            tourRepository.save(tourNew);
        } else if (!img2.isEmpty()) {
            if (image2 ==null) {
                image2 = new Image();
            }
            image2.setUrl(uploadImage(img2));
            image2.setStatus(2);
            image2.setTour(tour);
            imgRepository.save(image2);
        }

        return tour;

    }

    public String uploadImage( MultipartFile multipartFile)  {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        String uploadDir = "src/main/resources/static/admin/images/";

        saveFile(uploadDir, fileName, multipartFile);
        return "/admin/images/" + fileName;
    }

    public  void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) {

        Path uploadPath = Paths.get(uploadDir);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {

        }
    }

    public Tour getTour(String slug) {
        Tour tour = tourRepository.findById(
                com.group21.tour_reservation.utils.StringUtils.getIdFromSlug(slug)
        ).orElse(null);

        return tour;
    }

    public Tour deleteTour(Integer tourId) {
        Tour tour = tourRepository.findById(tourId).orElseThrow(null);
        if (tour != null) {
            tour.setStatus(0);
        } else {
            return null;
        }
        return tourRepository.save(tour);
    }

}
