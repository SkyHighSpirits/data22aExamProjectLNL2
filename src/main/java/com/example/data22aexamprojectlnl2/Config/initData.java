package com.example.data22aexamprojectlnl2.Config;

import com.example.data22aexamprojectlnl2.models.Company;
import com.example.data22aexamprojectlnl2.models.Image;
import com.example.data22aexamprojectlnl2.models.Security;
import com.example.data22aexamprojectlnl2.repositories.CompanyRepository;
import com.example.data22aexamprojectlnl2.services.PasswordHashingService;
import com.example.data22aexamprojectlnl2.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// denne class er bare for at test databasen
@Component
public class initData implements CommandLineRunner
{

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    SecurityService securityService;

    @Autowired
    PasswordHashingService passwordHashingService;

        @Override
        public void run(String... args) throws Exception
        {
            Company c1 = new Company();
            c1.setCompany_Title("Ilsø Construct");
            c1.setCVR("12345678");
            c1.setCompany_Description("Velkommen til Odin's Borg");
            c1.setId(1);
            c1.setTelephone("12 34 56 78");
            System.out.println(c1);
            companyRepository.save(c1);

            Security security1 = new Security();
            security1.setId(1);
            String username = passwordHashingService.doHashing("IlsoeAdmin");
            String password = passwordHashingService.doHashing("0807");
            security1.setUsername(username);
            security1.setPassword(password);
            securityService.saveSecurity(security1);


        }


}
