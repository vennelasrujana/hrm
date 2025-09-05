package com.dgsme.dgsmeclone.controller;

import com.dgsme.dgsmeclone.dto.PunchOutDto;
import com.dgsme.dgsmeclone.service.PunchOutService;
import com.dgsme.dgsmeclone.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/punch-out")

public class PunchOutController {

    private final PunchOutService service;

    @Autowired
    public PunchOutController(PunchOutService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseStructure<PunchOutDto>> punchOut(@RequestBody PunchOutDto punchOut) {
        ResponseStructure<PunchOutDto> response = new ResponseStructure<>();
        try {
            PunchOutDto saved = service.recordPunchOut(punchOut);
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Punch-out recorded successfully.");
            response.setData(saved);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        } catch (IllegalStateException e) {
            response.setStatus(HttpStatus.CONFLICT.value());
            response.setMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<ResponseStructure<List<PunchOutDto>>> getPunchOuts(
            @PathVariable String employeeId,
            @RequestParam(required = false) LocalDate date) {
        ResponseStructure<List<PunchOutDto>> response = new ResponseStructure<>();
        try {
            if (date == null) {
                date = LocalDate.now();
            }
            List<PunchOutDto> punchOuts = service.getEmployeePunchOuts(employeeId, date);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Punch-out data fetched.");
            response.setData(punchOuts);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{employeeId}/last")
    public ResponseEntity<ResponseStructure<PunchOutDto>> getLastPunchOut(@PathVariable String employeeId) {
        ResponseStructure<PunchOutDto> response = new ResponseStructure<>();
        try {
            Optional<PunchOutDto> punchOut = service.getLastPunchOut(employeeId);
            if (punchOut.isPresent()) {
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Last punch-out record found.");
                response.setData(punchOut.get());
                return ResponseEntity.ok(response);
            } else {
                response.setStatus(HttpStatus.NOT_FOUND.value());
                response.setMessage("No punch-out record found.");
                response.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{employeeId}/range")
    public ResponseEntity<ResponseStructure<List<PunchOutDto>>> getPunchOutsByDateRange(
            @PathVariable String employeeId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        ResponseStructure<List<PunchOutDto>> response = new ResponseStructure<>();
        try {
            List<PunchOutDto> punchOuts = service.getPunchOutsByDateRange(employeeId, startDate, endDate);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Punch-outs in range fetched.");
            response.setData(punchOuts);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/early/{date}")
    public ResponseEntity<ResponseStructure<List<PunchOutDto>>> getEarlyPunchOuts(@PathVariable LocalDate date) {
        ResponseStructure<List<PunchOutDto>> response = new ResponseStructure<>();
        try {
            List<PunchOutDto> data = service.getEarlyPunchOuts(date);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Early punch-outs fetched.");
            response.setData(data);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/late/{date}")
    public ResponseEntity<ResponseStructure<List<PunchOutDto>>> getLatePunchOuts(@PathVariable LocalDate date) {
        ResponseStructure<List<PunchOutDto>> response = new ResponseStructure<>();
        try {
            List<PunchOutDto> data = service.getLatePunchOuts(date);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Late punch-outs fetched.");
            response.setData(data);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{employeeId}/time-range")
    public ResponseEntity<ResponseStructure<List<PunchOutDto>>> getPunchOutsByTimeRange(
            @PathVariable String employeeId,
            @RequestParam LocalDate date,
            @RequestParam LocalTime startTime,
            @RequestParam LocalTime endTime) {
        ResponseStructure<List<PunchOutDto>> response = new ResponseStructure<>();
        try {
            List<PunchOutDto> data = service.getPunchOutsByTimeRange(employeeId, date, startTime, endTime);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Punch-outs in time range fetched.");
            response.setData(data);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
