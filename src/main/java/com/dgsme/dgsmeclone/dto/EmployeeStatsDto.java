package com.dgsme.dgsmeclone.dto;


public class EmployeeStatsDto {
 private Long totalEmployees;
 private Long todayPunchIns;
 private Long latePunchIns;

public Long getTotalEmployees() {
	return totalEmployees;
}
public void setTotalEmployees(Long totalEmployees) {
	this.totalEmployees = totalEmployees;
}
public Long getTodayPunchIns() {
	return todayPunchIns;
}
public void setTodayPunchIns(Long todayPunchIns) {
	this.todayPunchIns = todayPunchIns;
}
public Long getLatePunchIns() {
	return latePunchIns;
}
public void setLatePunchIns(Long latePunchIns) {
	this.latePunchIns = latePunchIns;
}
public EmployeeStatsDto(Long totalEmployees, Long todayPunchIns, Long latePunchIns) {
	super();
	this.totalEmployees = totalEmployees;
	this.todayPunchIns = todayPunchIns;
	this.latePunchIns = latePunchIns;
}
 
 
}

