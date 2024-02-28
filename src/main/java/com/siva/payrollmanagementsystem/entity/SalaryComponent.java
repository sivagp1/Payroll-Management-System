package com.siva.payrollmanagementsystem.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SalaryComponent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double amount;
	private LocalDate month;
	private double percentageFromSalary;
	private String type;
	private Long employeeId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDate getMonth() {
		return month;
	}
	public void setMonth(LocalDate month) {
		this.month = month;
	}
	public double getPercentageFromSalary() {
		return percentageFromSalary;
	}
	public void setPercentageFromSalary(double percentageFromSalary) {
		this.percentageFromSalary = percentageFromSalary;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	
	
}
