package com.example.data22aexamprojectlnl2.controllers;

import com.example.data22aexamprojectlnl2.models.Company;
import com.example.data22aexamprojectlnl2.models.Security;
import com.example.data22aexamprojectlnl2.repositories.CompanyRepository;
import com.example.data22aexamprojectlnl2.services.CompanyService;
import com.example.data22aexamprojectlnl2.services.PasswordHashingService;
import com.example.data22aexamprojectlnl2.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CompanyController
{
    final PasswordHashingService passwordHashing = new PasswordHashingService();

    @Autowired
    CompanyService companyService;

    @Autowired
    SecurityService securityService;

    @PostMapping("/update-company")
    public ResponseEntity<String> updateCompanyInformation(
            @RequestParam String company_title,
            @RequestParam String company_description,
            @RequestParam String cvr,
            @RequestParam String telephone,
            @RequestParam String username,
            @RequestParam String password
    ) {

        String hashedUsername = passwordHashing.doHashing(username);
        String hashedPassword = passwordHashing.doHashing(password);
        Optional<Security> checkSecurity = securityService.getSecurityByUsernameAndPassword(hashedUsername, hashedPassword);

        if(checkSecurity.isPresent())
        {
            Company updatedCompany = new Company();
            updatedCompany.setCompany_Title(company_title);
            updatedCompany.setCompany_Description(company_description);
            updatedCompany.setCVR(cvr);
            updatedCompany.setTelephone(telephone);

            companyService.updateCompany(updatedCompany, 1);


            return ResponseEntity.ok("Company information updated successfully");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/company")
    public ResponseEntity<Company> getCompany() {
        Company company = companyService.getCompanyById(1);
        if (company != null) {
            return ResponseEntity.ok(company);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
