package com.siva.payrollmanagementsystem.dto;

public class LeaveApplicationRequest {

	private String startDate;
    private String endDate;
    private boolean isHalfDay;
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
		return isHalfDay;
	}
	public void setHalfDay(boolean isHalfDay) {
		this.isHalfDay = isHalfDay;
	}
}
