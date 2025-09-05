package com.dgsme.dgsmeclone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgsme.dgsmeclone.dto.AdminDto;
import com.dgsme.dgsmeclone.repository.AdminRepository;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;

    public AdminDto findByAdminEmail(String email) {
        return adminRepository.findByAdminEmail(email);
    }

}
