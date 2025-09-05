package com.dgsme.dgsmeclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgsme.dgsmeclone.dto.CompanyInfoDto;
import com.dgsme.dgsmeclone.repository.AdminRepository;
import com.dgsme.dgsmeclone.repository.CompanyInfoRepository;
import com.dgsme.dgsmeclone.service.CompanyInfoService;
import com.dgsme.dgsmeclone.util.ResponseStructure;



@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyInfoService companyInfoService;

    @Autowired
    private CompanyInfoRepository repo;

    
    @PostMapping
    public ResponseEntity<ResponseStructure<CompanyInfoDto>> addCompany(@RequestBody CompanyInfoDto company) {
        return new ResponseEntity<>(companyInfoService.saveCompanyInfo(company), HttpStatus.CREATED);
    }

    @GetMapping("/getCompany/{email}")
    public ResponseEntity<ResponseStructure<CompanyInfoDto>> getCompanyById(@PathVariable String email) {
        ResponseStructure<CompanyInfoDto> response = new ResponseStructure<>();
        CompanyInfoDto company = repo.findByCompanyEmail(email).orElse(null);
        if (company != null) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Company found");
            response.setData(company);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Company not found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<ResponseStructure<CompanyInfoDto>> updateCompany(@PathVariable Long id, @RequestBody CompanyInfoDto companyDetails) {
        ResponseStructure<CompanyInfoDto> response = new ResponseStructure<>();
        CompanyInfoDto existingCompany = repo.findById(id).orElse(null);
        
        if (existingCompany != null) {
            existingCompany.setCompanyName(companyDetails.getCompanyName());
            existingCompany.setCompanyAddress(companyDetails.getCompanyAddress());
            existingCompany.setCompanyEmail(companyDetails.getCompanyEmail());
            existingCompany.setCompanyPhone(companyDetails.getCompanyPhone());
            existingCompany.setCompanyWebsite(companyDetails.getCompanyWebsite());
            
            CompanyInfoDto updatedCompany = repo.save(existingCompany);
            
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Company updated successfully");
            response.setData(updatedCompany);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Company not found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    


}
