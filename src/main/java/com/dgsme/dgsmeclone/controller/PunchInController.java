package com.dgsme.dgsmeclone.controller;

import com.dgsme.dgsmeclone.dto.PunchInDto;
import com.dgsme.dgsmeclone.service.PunchInService;
import com.dgsme.dgsmeclone.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/punch")

public class PunchInController {

    private final PunchInService punchInService;

    @Autowired
    public PunchInController(PunchInService punchInService) {
        this.punchInService = punchInService;
    }

    
    @PostMapping("/punchin")
    public ResponseEntity<ResponseStructure<PunchInDto>> punchIn(@RequestBody PunchInDto punchInDto) {
        ResponseStructure<PunchInDto> response = punchInService.recordPunchIn(punchInDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
    @GetMapping("/today")
    public ResponseEntity<ResponseStructure<List<PunchInDto>>> getAllPunchInsForToday() {
        ResponseStructure<List<PunchInDto>> response = punchInService.getAllPunchInsForToday();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{employeeId}/date/{date}")
    public ResponseEntity<ResponseStructure<List<PunchInDto>>> getPunchInsByEmployeeAndDate(
            @PathVariable String employeeId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        ResponseStructure<List<PunchInDto>> response = punchInService.getEmployeePunchIns(employeeId, date);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{employeeId}/all")
    public ResponseEntity<ResponseStructure<List<PunchInDto>>> getAllPunchInsByEmployeeId(@PathVariable String employeeId) {
        ResponseStructure<List<PunchInDto>> response = punchInService.getAllPunchInDetailsByEmployeeId(employeeId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{employeeId}/last")
    public ResponseEntity<ResponseStructure<PunchInDto>> getLastPunchIn(@PathVariable String employeeId) {
        ResponseStructure<PunchInDto> response = punchInService.getLastPunchIn(employeeId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{employeeId}/range")
    public ResponseEntity<ResponseStructure<List<PunchInDto>>> getPunchInsByDateRange(
            @PathVariable String employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        ResponseStructure<List<PunchInDto>> response = punchInService.getPunchInsByDateRange(employeeId, start, end);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{employeeId}/today")
    public ResponseEntity<ResponseStructure<Boolean>> hasPunchedInToday(@PathVariable String employeeId) {
        ResponseStructure<Boolean> response = punchInService.hasEmployeePunchedInToday(employeeId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/late")
    public ResponseEntity<ResponseStructure<List<PunchInDto>>> getLatePunchIns(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        ResponseStructure<List<PunchInDto>> response = punchInService.getLatePunchIns(date);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/early")
    public ResponseEntity<ResponseStructure<List<PunchInDto>>> getEarlyPunchIns(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        ResponseStructure<List<PunchInDto>> response = punchInService.getEarlyPunchIns(date);
        return ResponseEntity.ok(response);
    }
    
 
}
