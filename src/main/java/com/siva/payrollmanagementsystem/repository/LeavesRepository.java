package com.siva.payrollmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siva.payrollmanagementsystem.entity.Leaves;

public interface LeavesRepository extends JpaRepository<Leaves, Long> {
	
}
