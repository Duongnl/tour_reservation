package com.group21.tour_reservation.service;


import com.group21.tour_reservation.entity.Category;
import com.group21.tour_reservation.entity.Image;
import com.group21.tour_reservation.entity.Tour;
import com.group21.tour_reservation.repository.CategoryRepository;
import com.group21.tour_reservation.repository.ImgRepository;
import com.group21.tour_reservation.repository.TourRepository;
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

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImgRepository imgRepository;

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
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
        image1.setStatus(1);
        image1.setTour(tour);
        imgRepository.save(image1);

        Image image2 = new Image();
        image2.setUrl(uploadImage(img2));
        image2.setStatus(2);
        image2.setTour(tour);
        imgRepository.save(image2);

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

}
