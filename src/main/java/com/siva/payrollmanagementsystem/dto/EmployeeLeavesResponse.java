package com.siva.payrollmanagementsystem.dto;

import java.util.List;

import com.siva.payrollmanagementsystem.entity.Employee;

public class EmployeeLeavesResponse {

	private Long id;
    private String name;
    private String contact;
    private double basicSalary;
    private double hra;
    private List<LeaveResponse> leaves;
    
    public EmployeeLeavesResponse(Employee employee, List<LeaveResponse> leaves)	{
    	this.id = employee.getId();
    	this.name = employee.getName();
    	this.contact = employee.getContact();
    	this.basicSalary = employee.getBasicSalary();
    	this.hra = employee.getHra();
    	this.leaves = leaves;
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public double getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}
	public double getHra() {
		return hra;
	}
	public void setHra(double hra) {
		this.hra = hra;
	}
	public List<LeaveResponse> getLeaves() {
		return leaves;
	}
	public void setLeaves(List<LeaveResponse> leaves) {
		this.leaves = leaves;
	}
	
    
}
