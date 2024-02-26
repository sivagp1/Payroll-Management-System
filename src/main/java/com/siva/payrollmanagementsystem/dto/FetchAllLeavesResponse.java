package com.siva.payrollmanagementsystem.dto;

public class FetchAllLeavesResponse {

	private Long leaveId;
    private Long employeeId;
    private String employeeName;
    private String employeeContact;
    private double employeeBasicSalary;
    private double employeeHra;
    private String startDate;
    private String endDate;
    private boolean halfDay;
    private Long leaveTypeId;
    private String leaveType;
    private int entitlementPerYear;
	public Long getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public boolean isHalfDay() {
		return halfDay;
	}
	public void setHalfDay(boolean halfDay) {
		this.halfDay = halfDay;
	}
	public Long getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(Long leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public int getEntitlementPerYear() {
		return entitlementPerYear;
	}
	public void setEntitlementPerYear(int entitlementPerYear) {
		this.entitlementPerYear = entitlementPerYear;
	}
}
