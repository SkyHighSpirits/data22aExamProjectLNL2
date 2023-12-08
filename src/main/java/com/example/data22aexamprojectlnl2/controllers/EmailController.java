package com.example.data22aexamprojectlnl2.controllers;

import com.example.data22aexamprojectlnl2.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class EmailController
{

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService)
    {
        this.emailService = emailService;
    }

    //PostMapping that sends an email to default email using the emailService
    @PostMapping("/send-email")
    @ResponseBody
    public ResponseEntity sendEmail(
            @RequestParam String name,
            @RequestParam String email,
            //@RequestParam String telefon, skal tilføjes
            @RequestParam String city,
            @RequestParam String about,
            @RequestParam String message)
    {

        String to = "Ilsoecc@outlook.dk";
        String subject = about;
        String body = message + "\n\nBeskeden blev sendt af " + name + " fra " + city +
                "\nBeskeden kan besvares på email: " + email + "\n";// + "Eller telefon: " + telefon;

        return emailService.sendEmail(to, subject, body);
    }
}
