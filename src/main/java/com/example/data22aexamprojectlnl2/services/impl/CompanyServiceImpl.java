package com.example.data22aexamprojectlnl2.services.impl;

import com.example.data22aexamprojectlnl2.models.Company;
import com.example.data22aexamprojectlnl2.repositories.CompanyRepository;
import com.example.data22aexamprojectlnl2.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService
{
    //Autowires in the regarding repository to access the JPArepository
    @Autowired
    private CompanyRepository companyRepository;

    //All the funtioncs here overrides the functions that was made in the interface regarding each model
    //Saves a company object to the database
    @Override
    public Company saveCompany(Company company)
    {
        return companyRepository.save(company);
    }

    //retrieves all companies from the database
    @Override
    public List<Company> getAllCompanies()
    {
        return companyRepository.findAll();
    }

    //finds a company by an id, if it is present it will retrieve it
    @Override
    public Company getCompanyById(int id)
    {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent())
        {
            return company.get();
        } else
        {
            // Håndter situation, hvor firmaet ikke findes
            return null; // eller kast en exception
        }
    }

    //Updates a company with new company information based on a id
    @Override
    public Company updateCompany(Company updatedCompany, int id)
    {
        Company existingCompany = getCompanyById(id);
        if (existingCompany != null)
        {
            existingCompany.setCompany_Title(updatedCompany.getCompany_Title());
            existingCompany.setCompany_Description(updatedCompany.getCompany_Description());
            existingCompany.setCVR(updatedCompany.getCVR());
            existingCompany.setTelephone(updatedCompany.getTelephone());
            return companyRepository.save(existingCompany);
        } else
        {
            // Håndter situation, hvor firmaet ikke findes
            return null; // eller kast en exception
        }
    }

    //deletes a company based on an id
    @Override
    public void deleteCompany(int id)
    {
        companyRepository.deleteById(id);
    }
}
