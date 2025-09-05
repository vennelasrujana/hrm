package com.dgsme.dgsmeclone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgsme.dgsmeclone.dto.ShiftDetailsDto;
import com.dgsme.dgsmeclone.service.ShiftService;
import com.dgsme.dgsmeclone.util.ResponseStructure;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @PostMapping("/add")
    public ResponseEntity<ResponseStructure<ShiftDetailsDto>> addShift(@RequestBody ShiftDetailsDto shiftDetails) {
        ResponseStructure<ShiftDetailsDto> response = shiftService.addShift(shiftDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<ShiftDetailsDto>>> getAllShifts() {
        ResponseStructure<List<ShiftDetailsDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Shifts retrieved successfully");
        response.setData(shiftService.getAllShifts()); 
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<ShiftDetailsDto>> deleteShift(@PathVariable Long id) {
        ResponseStructure<ShiftDetailsDto> response = shiftService.deleteShift(id);
        return ResponseEntity.ok(response);
    }

}
