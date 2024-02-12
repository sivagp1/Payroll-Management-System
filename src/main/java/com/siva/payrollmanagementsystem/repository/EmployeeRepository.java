package com.siva.payrollmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siva.payrollmanagementsystem.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	
}
