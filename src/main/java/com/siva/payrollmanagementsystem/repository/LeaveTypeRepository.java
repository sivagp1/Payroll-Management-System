package com.siva.payrollmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siva.payrollmanagementsystem.entity.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {
	
}
