package com.example.data22aexamprojectlnl2.controllers;

import com.example.data22aexamprojectlnl2.models.Image;
import com.example.data22aexamprojectlnl2.models.Poster;
import com.example.data22aexamprojectlnl2.models.Security;
import com.example.data22aexamprojectlnl2.repositories.PosterRepository;
import com.example.data22aexamprojectlnl2.services.ImageService;
import com.example.data22aexamprojectlnl2.services.PasswordHashingService;
import com.example.data22aexamprojectlnl2.services.PosterService;
import com.example.data22aexamprojectlnl2.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@CrossOrigin
@Controller
public class PosterController
{
    final PasswordHashingService passwordHashing = new PasswordHashingService();

    @Autowired
    PosterRepository posterRepository;
    @Autowired
    PosterService posterService;
    @Autowired
    ImageService imageService;

    @Autowired
    SecurityService securityService;


    @GetMapping("/getImages")
    public ResponseEntity<List<Image>> getAllImagesByPosterId(@RequestParam("poster_id") int poster_id)
    {
        List<Image> images = imageService.getImagesByPosterId(poster_id);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }


    @DeleteMapping("/deletePoster")
    public ResponseEntity<String> deletePosterByPosterId(@RequestParam("poster_id") int poster_id,
                                                         @RequestParam("username") String username,
                                                         @RequestParam("password") String password)
    {
        String hashedUsername = passwordHashing.doHashing(username);
        String hashedPassword = passwordHashing.doHashing(password);
        Optional<Security> checkSecurity = securityService.getSecurityByUsernameAndPassword(hashedUsername, hashedPassword);
        if (checkSecurity.isPresent())
        {
            Optional<Poster> checkPoster = posterService.getPosterById(poster_id);
            checkPoster.get().setOperation(null);
            posterService.savePoster(checkPoster.get());
            checkPoster = posterService.getPosterById(poster_id);
            if (checkPoster.isPresent())
            {
                posterService.deletePoster(poster_id);
                return ResponseEntity.ok("Poster deleted");
            } else
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Poster not found");
            }
        } else
        {
            System.out.println("The password must have been wrong");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    // dette er faktisk en /postPost endpoint. der skal tilføjes Description og Titel.
    @PostMapping("/createPost")
    public ResponseEntity<String> createPoster(@RequestParam("title") String title,
                                               @RequestParam("description") String description,
                                               @RequestParam("images") MultipartFile[] images,
                                               @RequestParam("username") String username,
                                               @RequestParam("password") String password
    )
    {

        String hashedUsername = passwordHashing.doHashing(username);
        String hashedPassword = passwordHashing.doHashing(password);
        Optional<Security> checkSecurity = securityService.getSecurityByUsernameAndPassword(hashedUsername, hashedPassword);
        System.out.println(checkSecurity.isPresent());
        if (checkSecurity.isPresent())
        {
            try
            {
                // Create a new Poster
                Poster poster = new Poster();
                poster.setPoster_Title(title);
                poster.setPoster_Description(description);

                // Save the Poster entity
                Poster savedPoster = posterService.savePoster(poster);
                // Save the associated images
                for (MultipartFile imageFile : images)
                {
                    Image image = new Image();
                    image.setByte_img(imageFile.getBytes());
                    image.setPoster(savedPoster);
                    imageService.saveImage(image);
                }
                return ResponseEntity.status(HttpStatus.OK).body("Poster and images created successfully");
            } catch (IOException e)
            {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating poster and images");
            }
        }
        System.out.println("Appearently the password was wrong");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/getPosts")
    public ResponseEntity<List<Map<String, Object>>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size)
    {
        try
        {
            Pageable pageable = PageRequest.of(page, size);
            List<Map<String, Object>> result = new ArrayList<>();

            for (Poster poster : posterRepository.findAll(pageable))
            {
                Map<String, Object> posterInfo = new HashMap<>();
                posterInfo.put("poster", poster);

                List<Image> posterImages = imageService.getImagesByPosterId(poster.getId());
                posterInfo.put("images", posterImages);

                result.add(posterInfo);
            }

            return ResponseEntity.ok(result);
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getPostsPwd")
    public ResponseEntity<List<Map<String, Object>>> getAllPostsIfPassword(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    )
    {
        String hashedUsername = passwordHashing.doHashing(username);
        String hashedPassword = passwordHashing.doHashing(password);
        Optional<Security> checkSecurity = securityService.getSecurityByUsernameAndPassword(hashedUsername, hashedPassword);
        if (checkSecurity.isPresent())
        {
            try
            {
                List<Map<String, Object>> result = new ArrayList<>();

                for (Poster poster : posterRepository.findAll())
                {
                    Map<String, Object> posterInfo = new HashMap<>();
                    posterInfo.put("poster", poster);

                    List<Image> posterImages = imageService.getImagesByPosterId(poster.getId());
                    posterInfo.put("images", posterImages);

                    result.add(posterInfo);
                }

                return ResponseEntity.ok(result);
            } catch (Exception e)
            {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // Eventuelle andre metoder...
}
