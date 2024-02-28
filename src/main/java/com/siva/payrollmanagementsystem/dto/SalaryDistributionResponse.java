package com.siva.payrollmanagementsystem.dto;

import java.util.List;

public class SalaryDistributionResponse {

	private Long employeeId;
    private String employeeName;
    private String employeeContact;
    private double employeeBasicSalary;
    private double employeeHra;
    private double salaryForMonth;
    
    private List<SalaryDistributionComponent> salaryDistributions;
    public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeContact() {
		return employeeContact;
	}
	public void setEmployeeContact(String employeeContact) {
		this.employeeContact = employeeContact;
	}
	public double getEmployeeBasicSalary() {
		return employeeBasicSalary;
	}
	public void setEmployeeBasicSalary(double employeeBasicSalary) {
		this.employeeBasicSalary = employeeBasicSalary;
	}
	public double getEmployeeHra() {
		return employeeHra;
	}
	public void setEmployeeHra(double employeeHra) {
		this.employeeHra = employeeHra;
	}
	public double getSalaryForMonth() {
		return salaryForMonth;
	}
	public void setSalaryForMonth(double salaryForMonth) {
		this.salaryForMonth = salaryForMonth;
	}
	public List<SalaryDistributionComponent> getSalaryDistributions() {
		return salaryDistributions;
	}
	public void setSalaryDistributions(List<SalaryDistributionComponent> salaryDistributions) {
		this.salaryDistributions = salaryDistributions;
	}
	
}
