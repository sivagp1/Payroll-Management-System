package com.siva.payrollmanagementsystem.dto;

public class LeaveResponse {
    private Long leaveId;
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
