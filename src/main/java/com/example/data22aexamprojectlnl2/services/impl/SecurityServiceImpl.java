package com.example.data22aexamprojectlnl2.services.impl;

import com.example.data22aexamprojectlnl2.models.Security;
import com.example.data22aexamprojectlnl2.repositories.SecurityRepository;
import com.example.data22aexamprojectlnl2.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityServiceImpl implements SecurityService
{

    //Autowires in the regarding repository to access the JPArepository
    //All the funtioncs here overrides the functions that was made in the interface regarding each model
    private final SecurityRepository securityRepository;

    @Autowired
    public SecurityServiceImpl(SecurityRepository securityRepository)
    {
        this.securityRepository = securityRepository;
    }

    //saves a security object to the database
    @Override
    public Security saveSecurity(Security security)
    {
        return securityRepository.save(security);
    }

    //retrives a security obejct based on a username and password
    @Override
    public Optional<Security> getSecurityByUsernameAndPassword(String username, String password)
    {
        Optional<Security> foundSecurity = securityRepository.findByUsernameAndPassword(username, password);
        return foundSecurity;
    }

}
