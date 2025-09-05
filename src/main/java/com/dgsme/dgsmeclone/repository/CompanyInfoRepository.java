package com.dgsme.dgsmeclone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgsme.dgsmeclone.dto.CompanyInfoDto;

public interface CompanyInfoRepository extends JpaRepository<CompanyInfoDto, Long> {

    CompanyInfoDto findTopByOrderByIdDesc();

    Optional<CompanyInfoDto> findByCompanyEmail(String email);
    
}
