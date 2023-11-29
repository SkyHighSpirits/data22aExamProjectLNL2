package com.example.data22aexamprojectlnl2.controllers;

import com.example.data22aexamprojectlnl2.models.Image;
import com.example.data22aexamprojectlnl2.repositories.ImageRepository;
import com.example.data22aexamprojectlnl2.services.ImageService;
import com.example.data22aexamprojectlnl2.models.Poster;
import com.example.data22aexamprojectlnl2.repositories.PosterRepository;
import com.example.data22aexamprojectlnl2.services.PosterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@Controller
public class PosterController
{
    @Autowired

    PosterRepository posterRepository;
    @Autowired
    PosterService posterService;
    @Autowired

    ImageService imageService;
    @Autowired
    ImageRepository imageRepository;

    // flyt til image controller
    @GetMapping("/getImages")
    public ResponseEntity<List<Image>> getAllImagesByPosterId(@RequestParam("poster_id") int poster_id)
    {
        List<Image> images = imageService.getImagesByPosterId(poster_id);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }




    // dette er faktisk en /postPost endpoint. der skal tilføjes Description og Titel.
    @PostMapping("/createPost")
    public ResponseEntity<String> createPoster( @RequestParam("title") String title,
                                                @RequestParam("description") String description,
                                                @RequestParam("images") MultipartFile[] images) {

        try {
            // Create a new Poster
            Poster poster = new Poster();
            poster.setPoster_Title(title);
            poster.setPoster_Description(description);

            // Save the Poster entity
            Poster savedPoster = posterService.savePoster(poster);

            // Save the associated images
            for (MultipartFile imageFile : images) {
                Image image = new Image();
                image.setByte_img(imageFile.getBytes());
                image.setPoster(savedPoster); // Set the Poster entity for the image
                imageService.saveImage(image);
            }

            return ResponseEntity.status(HttpStatus.OK).body("Poster and images created successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating poster and images");
        }
    }

    @GetMapping("/getPosts")
    public ResponseEntity<List<Map<String, Object>>> getAllPosts() {
        try {
            List<Map<String, Object>> result = new ArrayList<>();

            for (Poster poster : posterRepository.findAll()) {
                Map<String, Object> posterInfo = new HashMap<>();
                posterInfo.put("poster", poster);

                // Henter billeder ved hjælp af imageService
                List<Image> posterImages = imageService.getImagesByPosterId(poster.getId());
                posterInfo.put("images", posterImages);

                result.add(posterInfo);
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
