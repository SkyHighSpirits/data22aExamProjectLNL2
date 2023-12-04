package com.example.data22aexamprojectlnl2.controllers;

import com.example.data22aexamprojectlnl2.models.Image;
import com.example.data22aexamprojectlnl2.models.Security;
import com.example.data22aexamprojectlnl2.services.CompanyService;
import com.example.data22aexamprojectlnl2.services.ImageService;
import com.example.data22aexamprojectlnl2.services.PasswordHashingService;
import com.example.data22aexamprojectlnl2.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ImageController
{


    final PasswordHashingService passwordHashing = new PasswordHashingService();
    @Autowired
    ImageService imageService;
    @Autowired
    CompanyService companyService;

    @Autowired
    SecurityService securityService;

    @DeleteMapping("/deleteImage")
    public ResponseEntity<String> deleteImageById(@RequestParam("image_id") int image_id,
                                                  @RequestParam("username") String username,
                                                  @RequestParam("password") String password)
    {
        String hashedUsername = passwordHashing.doHashing(username);
        String hashedPassword = passwordHashing.doHashing(password);
        Optional<Security> checkSecurity = securityService.getSecurityByUsernameAndPassword(hashedUsername, hashedPassword);
        if (checkSecurity.isPresent())
        {
            Optional<Image> foundImage = imageService.getImageById(image_id);
            if (foundImage.isPresent())
            {
                imageService.deleteImage(image_id);
                return ResponseEntity.ok("Image was deleted");
            } else
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image was not found");
            }
        } else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wrong username or password");
        }
    }
}
