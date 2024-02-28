package com.siva.payrollmanagementsystem.dto;


public class SalaryComponentRequest {

	private String type;
    private double percentageFromSalary;
    private String month;
    
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
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
    
    
}
