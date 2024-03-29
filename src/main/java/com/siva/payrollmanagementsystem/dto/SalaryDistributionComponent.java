package com.siva.payrollmanagementsystem.dto;

public class SalaryDistributionComponent {

	private Long salaryComponentId;
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
