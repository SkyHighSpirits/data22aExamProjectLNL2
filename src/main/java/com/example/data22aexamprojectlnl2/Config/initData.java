package com.example.data22aexamprojectlnl2.Config;

import com.example.data22aexamprojectlnl2.models.Company;
import com.example.data22aexamprojectlnl2.models.Image;
import com.example.data22aexamprojectlnl2.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// denne class er bare for at test databasen
@Component
public class initData implements CommandLineRunner
{

    @Autowired
    CompanyRepository companyRepository;

        @Override
        public void run(String... args) throws Exception
        {
            Company c1 = new Company();
            c1.setCompany_Title("Ilsø Construct");
            c1.setCVR(12345678);
            c1.setCompany_Description("Velkommen til Odin's Borg");
            c1.setId(1);
            System.out.println(c1);
            companyRepository.save(c1);




        }


}
