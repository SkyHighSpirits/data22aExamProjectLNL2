package com.example.data22aexamprojectlnl2.controllers;

import com.example.data22aexamprojectlnl2.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    @ResponseBody
    public ResponseEntity sendEmail(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String city,
            @RequestParam String about,
            @RequestParam String message) {

        String to = "dovser.chinastore@gmail.com";
        String subject = about;
        String body = message + "\n\nBeskeden blev sendt af " + name + " fra " + city +
                "\nSvar kan sendes på hans email adresse: " + email + "\n";

        return emailService.sendEmail(to, subject, body);
    }
}
