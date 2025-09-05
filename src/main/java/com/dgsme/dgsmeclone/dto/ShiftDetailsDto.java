package com.dgsme.dgsmeclone.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShiftDetailsDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shiftType;

    @JsonFormat(pattern = "hh:mm a")
    private LocalTime shiftStartTime;

    @JsonFormat(pattern = "hh:mm a")
    private LocalTime shiftEndTime;

    public ShiftDetailsDto() {
    }

    public ShiftDetailsDto(Long id, String shiftType, LocalTime shiftStartTime, LocalTime shiftEndTime) {
        this.id = id;
        this.shiftType = shiftType;
        this.shiftStartTime = shiftStartTime;
        this.shiftEndTime = shiftEndTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public LocalTime getShiftStartTime() {
        return shiftStartTime;
    }

    public void setShiftStartTime(LocalTime shiftStartTime) {
        this.shiftStartTime = shiftStartTime;
    }

    public LocalTime getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(LocalTime shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
    }

    @Override
    public String toString() {
        return "ShiftDetailsDto [id=" + id + ", shiftType=" + shiftType +
                ", shiftStartTime=" + shiftStartTime + ", shiftEndTime=" + shiftEndTime + "]";
    }
}
