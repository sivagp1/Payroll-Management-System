package com.siva.payrollmanagementsystem.dto;

import java.time.LocalDate;

public class SalaryComponentResponse {
	
	    private Long salaryComponentId;
	    private Long employeeId;
	    private String employeeName;
	    private String employeeContact;
	    private double employeeBasicSalary;
	    private double employeeHra;
	    private String type;
	    private double percentageFromSalary;
	    private double amount;
	    private String month;
	    
		public Long getSalaryComponentId() {
			return salaryComponentId;
		}
		public void setSalaryComponentId(Long salaryComponentId) {
			this.salaryComponentId = salaryComponentId;
		}
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
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public double getPercentageFromSalary() {
			return percentageFromSalary;
		}
		public void setPercentageFromSalary(double percentageFromSalary) {
			this.percentageFromSalary = percentageFromSalary;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getMonth() {
			return month;
		}
		public void setMonth(String month) {
			this.month = month;
		}


	    

}
