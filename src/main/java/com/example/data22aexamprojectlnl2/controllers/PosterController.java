package com.example.data22aexamprojectlnl2.controllers;

import com.example.data22aexamprojectlnl2.models.Image;
import com.example.data22aexamprojectlnl2.models.Poster;
import com.example.data22aexamprojectlnl2.repositories.ImageRepository;
import com.example.data22aexamprojectlnl2.repositories.PosterRepository;
import com.example.data22aexamprojectlnl2.services.ImageService;
import com.example.data22aexamprojectlnl2.services.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class PosterController {
    @Autowired
    private PosterRepository posterRepository;
    @Autowired
    private PosterService posterService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/getImages")
    public ResponseEntity<List<Image>> getAllImagesByPosterId(@RequestParam("poster_id") int poster_id) {
        List<Image> images = imageService.getImagesByPosterId(poster_id);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @PostMapping("/createPost")
    public ResponseEntity<String> createPoster(@RequestParam("title") String title,
                                               @RequestParam("description") String description,
                                               @RequestParam("images") MultipartFile[] images) {
        try {
            Poster poster = new Poster();
            poster.setPoster_Title(title);
            poster.setPoster_Description(description);

            Poster savedPoster = posterService.savePoster(poster);

            for (MultipartFile imageFile : images) {
                Image image = new Image();
                image.setByte_img(imageFile.getBytes());
                image.setPoster(savedPoster);
                imageService.saveImage(image);
            }

            return ResponseEntity.status(HttpStatus.OK).body("Poster and images created successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating poster and images");
        }
    }

    @GetMapping("/getPosts")
    public ResponseEntity<List<Map<String, Object>>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<Map<String, Object>> result = new ArrayList<>();

            for (Poster poster : posterRepository.findAll(pageable)) {
                Map<String, Object> posterInfo = new HashMap<>();
                posterInfo.put("poster", poster);

                List<Image> posterImages = imageService.getImagesByPosterId(poster.getId());
                posterInfo.put("images", posterImages);

                result.add(posterInfo);
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Eventuelle andre metoder...
}
