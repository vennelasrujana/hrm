package com.dgsme.dgsmeclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgsme.dgsmeclone.dto.ShiftDetailsDto;

public interface ShiftDetailsRepo extends JpaRepository<ShiftDetailsDto, Long> {
    
}
