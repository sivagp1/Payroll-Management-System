package com.siva.payrollmanagementsystem.dto;

import com.siva.payrollmanagementsystem.entity.Employee;

public class IncrementResponse {

	private String status;
    private String message;
    private Employee employee;
    
    public IncrementResponse(String status, String message)	{
    	this.status = status;
    	this.message = message;
    	this.employee = null;
    }
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
    
    
}
