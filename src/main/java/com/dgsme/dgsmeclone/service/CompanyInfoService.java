package com.dgsme.dgsmeclone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dgsme.dgsmeclone.dto.AdminAddingEmpDto;
import com.dgsme.dgsmeclone.dto.CompanyInfoDto;
import com.dgsme.dgsmeclone.repository.AdminAddingEmpRepository;
import com.dgsme.dgsmeclone.repository.CompanyInfoRepository;
import com.dgsme.dgsmeclone.util.ResponseStructure;

@Service
public class CompanyInfoService {
    
    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Autowired
    private AdminAddingEmpRepository adminAddingEmpRepository;

    public ResponseStructure<CompanyInfoDto> saveCompanyInfo(CompanyInfoDto company) {
        ResponseStructure<CompanyInfoDto> response = new ResponseStructure<>();
        CompanyInfoDto savedDto = companyInfoRepository.save(company);
        
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Company added successfully");
        response.setData(savedDto);
        
        return response;
    }

    public ResponseStructure<AdminAddingEmpDto> saveAdminAddingEmp(AdminAddingEmpDto admin) {
        ResponseStructure<AdminAddingEmpDto> response = new ResponseStructure<>();
        AdminAddingEmpDto savedDto = adminAddingEmpRepository.save(admin);
        
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Admin added successfully");
        response.setData(savedDto);
        
        return response;
    }

    



}
