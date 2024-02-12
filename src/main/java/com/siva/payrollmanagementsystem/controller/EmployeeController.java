package com.siva.payrollmanagementsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siva.payrollmanagementsystem.dto.IncrementRequest;
import com.siva.payrollmanagementsystem.dto.IncrementResponse;
import com.siva.payrollmanagementsystem.entity.Employee;
import com.siva.payrollmanagementsystem.repository.EmployeeRepository;
import com.siva.payrollmanagementsystem.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	Logger log = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/fetchAll")
	public ResponseEntity<List<Employee>> getEmployee()	{
		
		List<Employee> employees = employeeRepository.findAll();
		if(!employees.isEmpty()) {
			return ResponseEntity.ok(employees);
		}
		else	{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}
	
	@PostMapping("/incrementbasicsalary/{employeeId}")
	public ResponseEntity<?> incrementEmployeeSalary(@PathVariable Long employeeId, @RequestBody IncrementRequest request) {
    	Employee responseBody = employeeService.incrementEmployeeSalary(employeeId, request);
    	return ResponseEntity.ok(responseBody);
	}
}