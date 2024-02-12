package com.siva.payrollmanagementsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LeaveType {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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