package com.siva.payrollmanagementsystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siva.payrollmanagementsystem.dto.SalaryComponentRequest;
import com.siva.payrollmanagementsystem.dto.SalaryComponentResponse;
import com.siva.payrollmanagementsystem.dto.SalaryDistributionComponent;
import com.siva.payrollmanagementsystem.dto.SalaryDistributionResponse;
import com.siva.payrollmanagementsystem.entity.Employee;
import com.siva.payrollmanagementsystem.entity.SalaryComponent;
import com.siva.payrollmanagementsystem.exception.BadRequestException;
import com.siva.payrollmanagementsystem.exception.NoDataFoundException;
import com.siva.payrollmanagementsystem.repository.EmployeeRepository;
import com.siva.payrollmanagementsystem.repository.SalaryComponentRepository;

@Service
public class SalaryService {
	
	@Autowired
    private SalaryComponentRepository salaryComponentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public SalaryComponentResponse addSalaryComponent(Long employeeId, SalaryComponentRequest request) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoDataFoundException("Employee not found with id: " + employeeId));

        validateInput(request);

        double amount = calculateAmount(request.getType(), request.getPercentageFromSalary(), employee.getBasicSalary());

        SalaryComponent salaryComponent = new SalaryComponent();
        salaryComponent.setEmployeeId(employee.getId());
        salaryComponent.setType(request.getType());
        salaryComponent.setPercentageFromSalary(request.getPercentageFromSalary());
        salaryComponent.setAmount(amount);
        salaryComponent.setMonth(LocalDate.parse(request.getMonth()));

        SalaryComponent savedComponent = salaryComponentRepository.save(salaryComponent);

        return mapToSalaryComponentResponse(savedComponent, employee);
    }

    private void validateInput(SalaryComponentRequest request) {
        if (!request.getType().equalsIgnoreCase("Allowance") && !request.getType().equalsIgnoreCase("Appraisal") &&
                !request.getType().equalsIgnoreCase("Deduction")) {
            throw new BadRequestException("Invalid type. Type must be one of: Allowance, Appraisal, Deduction");
        }
        double maxPercentage;
        if (request.getType().equalsIgnoreCase("Allowance") || request.getType().equalsIgnoreCase("Appraisal")) {
            maxPercentage = 5.0;
        } else {
            maxPercentage = 0.5;
        }

        if (request.getPercentageFromSalary() > maxPercentage) {
            throw new BadRequestException("Percentage from salary exceeds the maximum limit for the type");
        }
    }

    private double calculateAmount(String type, double percentageFromSalary, double basicSalary) {
        double amount;
        if (percentageFromSalary != 0) {
            amount = basicSalary * (percentageFromSalary/100.0);
        } else {
            amount = 0; // Assuming amount is zero if percentage is not provided
        }
        return amount;
    }

    private SalaryComponentResponse mapToSalaryComponentResponse(SalaryComponent salaryComponent, Employee employee) {
        SalaryComponentResponse response = new SalaryComponentResponse();
        response.setSalaryComponentId(salaryComponent.getId());
        response.setEmployeeId(employee.getId());
        response.setEmployeeName(employee.getName());
        response.setEmployeeContact(employee.getContact());
        response.setEmployeeBasicSalary(employee.getBasicSalary());
        response.setEmployeeHra(employee.getHra());
        response.setType(salaryComponent.getType());
        response.setPercentageFromSalary(salaryComponent.getPercentageFromSalary());
        response.setAmount(salaryComponent.getAmount());
        response.setMonth(salaryComponent.getMonth().toString());
        return response;
    }
    
    public List<SalaryComponentResponse> getAllSalaryComponents() {
        List<SalaryComponent> salaryComponents = salaryComponentRepository.findAll();
        if (salaryComponents.isEmpty()) {
            throw new NoDataFoundException("Salary Component not found  in the  table");
        } else {
            return salaryComponents.stream()
                    .map(this::mapToSalaryComponentResponse)
                    .collect(Collectors.toList());
        }
    }

    private SalaryComponentResponse mapToSalaryComponentResponse(SalaryComponent salaryComponent) {
        SalaryComponentResponse response = new SalaryComponentResponse();
        response.setSalaryComponentId(salaryComponent.getId());
        response.setEmployeeId(salaryComponent.getEmployeeId());
        
        Employee employee = employeeRepository.findById(response.getEmployeeId()).orElse(null);
        if (employee != null) {
            response.setEmployeeName(employee.getName());
            response.setEmployeeContact(employee.getContact());
            response.setEmployeeBasicSalary(employee.getBasicSalary());
            response.setEmployeeHra(employee.getHra());
        }
        
        response.setType(salaryComponent.getType());
        response.setPercentageFromSalary(salaryComponent.getPercentageFromSalary());
        response.setAmount(salaryComponent.getAmount());
        response.setMonth(salaryComponent.getMonth().toString());
        return response;
    }
    
    public SalaryDistributionResponse getAllSalaryDistribution(Long employeeId) {
    	
    	Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoDataFoundException("Employee not found with id: " + employeeId));
    	
        List<SalaryComponent> salaryComponents = salaryComponentRepository.findByEmployeeId(employeeId);
        return mapToSalaryComponentDistribution(salaryComponents, employee);
    }

    private SalaryDistributionResponse mapToSalaryComponentDistribution(List<SalaryComponent> salaryComponents, Employee employee) {
    	
    	
    	SalaryDistributionResponse response = new SalaryDistributionResponse();
    	response.setEmployeeId(employee.getId());
    	response.setEmployeeName(employee.getName());
    	response.setEmployeeContact(employee.getContact());
    	response.setEmployeeBasicSalary(employee.getBasicSalary());
    	response.setEmployeeHra(employee.getHra());
    	
        double totalAllowancesAndAppraisals = calculateTotalAllowancesAndAppraisals(employee.getId(), LocalDate.now());
        double totalDeductions = calculateTotalDeductions(employee.getId(), LocalDate.now());
        double salaryForMonth = employee.getBasicSalary() + employee.getHra() + totalAllowancesAndAppraisals - totalDeductions;
        response.setSalaryForMonth(salaryForMonth);
        
        List<SalaryComponent> salaryComponentsOfEmpInMonth = findSalaryComponentsForEmployeeInMonth(employee.getId(), LocalDate.now());
        if(salaryComponents.isEmpty())
        	response.setSalaryDistributions(new ArrayList<>());
        else	{
        	response.setSalaryDistributions(salaryComponentsOfEmpInMonth.stream()
        									.map(this::SalaryDistributionComponentResp)
        									.collect(Collectors.toList()));
        }
        	
        return response;
    }
    
    private double calculateTotalAllowancesAndAppraisals(Long employeeId, LocalDate currentDate) {
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();
        return salaryComponentRepository.calculateTotalAllowancesAndAppraisals(employeeId, month, year);
    }

    private double calculateTotalDeductions(Long employeeId, LocalDate currentDate) {
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();
        return salaryComponentRepository.calculateTotalDeductions(employeeId, month, year);
    }
    
    private List<SalaryComponent> findSalaryComponentsForEmployeeInMonth(Long employeeId, LocalDate currentDate) {
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();
        return salaryComponentRepository.findSalaryComponentsForEmployeeInMonth(employeeId, month, year);
    }
    
    private SalaryDistributionComponent SalaryDistributionComponentResp(SalaryComponent salaryComponent)	{
    	
    	SalaryDistributionComponent salaryDistributionComponent = new SalaryDistributionComponent();
    	salaryDistributionComponent.setSalaryComponentId(salaryComponent.getId());
    	salaryDistributionComponent.setType(salaryComponent.getType());
    	salaryDistributionComponent.setMonth(salaryComponent.getMonth().getMonth().toString());
    	salaryDistributionComponent.setPercentageFromSalary(salaryComponent.getPercentageFromSalary());
    	salaryDistributionComponent.setAmount(salaryComponent.getAmount());
    	
    	return salaryDistributionComponent;
    }

}
