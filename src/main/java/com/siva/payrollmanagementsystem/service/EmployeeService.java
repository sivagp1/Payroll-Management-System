package com.siva.payrollmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siva.payrollmanagementsystem.dto.IncrementRequest;
import com.siva.payrollmanagementsystem.entity.Employee;
import com.siva.payrollmanagementsystem.exception.EmployeeNotFoundException;
import com.siva.payrollmanagementsystem.exception.InvalidIncrementPercentageException;
import com.siva.payrollmanagementsystem.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee incrementEmployeeSalary(Long employeeId, IncrementRequest request) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
            new EmployeeNotFoundException("Employee not found with id: " + employeeId));

        double incrementPercentage = request.getIncrementPercentage();
        if (incrementPercentage > 10.0) {
            throw new InvalidIncrementPercentageException("Invalid increment percentage. Increment should be <= 10%.");
        }
        
        double currentSalary = employee.getBasicSalary();
        double newSalary = currentSalary + ((currentSalary * incrementPercentage) / 100);
        employee.setBasicSalary(newSalary);
        employeeRepository.save(employee);
		return employee;
    }
	
}
