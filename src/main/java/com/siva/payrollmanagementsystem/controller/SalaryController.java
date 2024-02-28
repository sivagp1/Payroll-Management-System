package com.siva.payrollmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siva.payrollmanagementsystem.dto.SalaryComponentRequest;
import com.siva.payrollmanagementsystem.dto.SalaryComponentResponse;
import com.siva.payrollmanagementsystem.dto.SalaryDistributionResponse;
import com.siva.payrollmanagementsystem.service.SalaryService;

@RestController
@RequestMapping("/salaryComponent")
public class SalaryController {

    @Autowired
    private SalaryService salaryComponentService;

    @PostMapping("/employee/{employeeId}/addComponent")
    public ResponseEntity<?> addSalaryComponent(@PathVariable Long employeeId, @RequestBody SalaryComponentRequest request) {
        
        SalaryComponentResponse response = salaryComponentService.addSalaryComponent(employeeId, request);
        return ResponseEntity.ok(response);
       
    }
    
    @GetMapping("/fetchAll")
    public ResponseEntity<?> fetchAllSalaryComponents() {
		
    	List<SalaryComponentResponse> response = salaryComponentService.getAllSalaryComponents();
     	return ResponseEntity.ok(response);
       
    }
    
    @GetMapping("/salaryOfEmployee/{employeeId}")
    public ResponseEntity<?> fetchEmployeeSalaryComponents(@PathVariable Long employeeId) {
		
    	SalaryDistributionResponse salaryDistributionResponse = salaryComponentService.getAllSalaryDistribution(employeeId);
    	return ResponseEntity.ok(salaryDistributionResponse);
    }
}
