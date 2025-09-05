package com.dgsme.dgsmeclone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgsme.dgsmeclone.dao.EmployeeLoginCredentialsDao;
import com.dgsme.dgsmeclone.dto.EmployeeLoginCredentialsDto;

@Service
public class EmployeeLoginCredentialsService {

    @Autowired
    private EmployeeLoginCredentialsDao dao;

    public EmployeeLoginCredentialsDto save(EmployeeLoginCredentialsDto dto) {
        dto.setEmployeeRole(dto.getEmployeeRole().toLowerCase());
        return dao.saveCredentials(dto);
    }

    public EmployeeLoginCredentialsDto validateCredentialsAndFetchUser(String email, String password) {
        return dao.validateAndFetchUser(email, password);
    }
}
