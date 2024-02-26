package com.siva.payrollmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siva.payrollmanagementsystem.entity.Leaves;

public interface LeavesRepository extends JpaRepository<Leaves, Long> {

	List<Leaves> findByEmployeeId(Long employeeId);
	
}
