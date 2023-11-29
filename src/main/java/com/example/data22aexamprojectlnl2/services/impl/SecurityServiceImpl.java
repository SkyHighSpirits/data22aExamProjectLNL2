package com.example.data22aexamprojectlnl2.services.impl;

import com.example.data22aexamprojectlnl2.models.Company;
import com.example.data22aexamprojectlnl2.models.Security;
import com.example.data22aexamprojectlnl2.repositories.SecurityRepository;
import com.example.data22aexamprojectlnl2.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final SecurityRepository securityRepository;

    @Autowired
    public SecurityServiceImpl(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @Override
    public Security saveSecurity(Security security) {
        return null;
    }

    @Override
    public Optional<Security> getSecurityByUsernameAndPassword(String username, String password) {
        Optional<Security> foundSecurity = getSecurityByUsernameAndPassword(username, password);
        return foundSecurity;
    }

}
