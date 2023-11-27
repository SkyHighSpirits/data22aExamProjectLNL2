package com.example.data22aexamprojectlnl2.controllers;

import com.example.data22aexamprojectlnl2.models.Image;
import com.example.data22aexamprojectlnl2.repositories.ImageRepository;
import com.example.data22aexamprojectlnl2.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.*;

@Controller
public class ImageController
{
    @Autowired
    ImageService imageService;
    @Autowired
    ImageRepository imageRepository;

    @GetMapping("/getImages")
    public ResponseEntity<List<Image>> getAllImagesByPosterId(@RequestParam("poster_id") int poster_id)
    {
        List<Image> images = imageService.getImagesByPosterId(poster_id);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @PostMapping("/postImage")
    public ResponseEntity<String> saveUploadedImages(@RequestParam("image") MultipartFile image )
    {

        try
        {
            Byte[] bytes = new Byte[image.getBytes().length]; //opretter nyt byteArray
            int i = 0;
            for (byte b : image.getBytes())
            {
                bytes[i++] = b;
            }

        Image imageEntity = new Image(bytes);
        imageService.saveImage(imageEntity);

        return ResponseEntity.status(HttpStatus.OK).body("Image uploaded successfully");
    }catch(IOException e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Image");
       }
    } //

}
