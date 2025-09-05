package com.dgsme.dgsmeclone.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgsme.dgsmeclone.dto.ShiftDetailsDto;
import com.dgsme.dgsmeclone.repository.ShiftDetailsRepo;
import com.dgsme.dgsmeclone.util.ResponseStructure;

@Service
public class ShiftService {
    
    @Autowired
    private ShiftDetailsRepo shiftDetailsRepo;
    
    public ResponseStructure<ShiftDetailsDto> addShift(ShiftDetailsDto shiftDetails) {
        LocalTime start = shiftDetails.getShiftStartTime();
        LocalTime end = shiftDetails.getShiftEndTime();

        if (start == null || end == null) {
            throw new IllegalArgumentException("Shift start time and end time cannot be null");
        }

        if (start.equals(end)) {
            throw new IllegalArgumentException("Shift start time and end time cannot be the same");
        }

        Duration duration;
        if (start.isAfter(end)) {
            duration = Duration.between(start, end.plusHours(24));
        } else {
            duration = Duration.between(start, end);
        }


        if (duration.toHours() > 16) {
            throw new IllegalArgumentException("Shift duration too long, please check input");
        }

        ShiftDetailsDto savedShift = shiftDetailsRepo.save(shiftDetails);

        ResponseStructure<ShiftDetailsDto> responseStructure = new ResponseStructure<>();
        responseStructure.setStatus(201);
        responseStructure.setMessage("Shift added successfully");
        responseStructure.setData(savedShift);

        return responseStructure;
    }

    public List<ShiftDetailsDto> getAllShifts() {
        return shiftDetailsRepo.findAll();
    }

    public ResponseStructure<ShiftDetailsDto> deleteShift(Long id) {
        ShiftDetailsDto shiftDetails = shiftDetailsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Shift not found with id: " + id));
        
        shiftDetailsRepo.delete(shiftDetails);

        ResponseStructure<ShiftDetailsDto> responseStructure = new ResponseStructure<>();
        responseStructure.setStatus(200);
        responseStructure.setMessage("Shift deleted successfully");
        responseStructure.setData(shiftDetails);
        return responseStructure;
    }
}
