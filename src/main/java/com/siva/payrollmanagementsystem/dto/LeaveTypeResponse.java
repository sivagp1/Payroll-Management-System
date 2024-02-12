package com.siva.payrollmanagementsystem.dto;

public class LeaveTypeResponse {

	private Long leaveTypeId;
    private String leaveType;
    private int entitlementPerYear;
    
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
