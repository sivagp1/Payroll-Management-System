package com.siva.payrollmanagementsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siva.payrollmanagementsystem.dto.EmployeesResponse;
import com.siva.payrollmanagementsystem.dto.IncrementRequest;
import com.siva.payrollmanagementsystem.dto.LeaveTypeResponse;
import com.siva.payrollmanagementsystem.entity.Employee;
import com.siva.payrollmanagementsystem.entity.LeaveType;
import com.siva.payrollmanagementsystem.exception.EmployeeNotFoundException;
import com.siva.payrollmanagementsystem.exception.InvalidIncrementPercentageException;
import com.siva.payrollmanagementsystem.exception.NoDataFoundException;
import com.siva.payrollmanagementsystem.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;

	
	public List<EmployeesResponse> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new NoDataFoundException("No employee list found, please add.");
        }
        return employees.stream()
        		.map(this::mapToEmployeesResponse)
                .collect(Collectors.toList());
     }
	
	private EmployeesResponse mapToEmployeesResponse(Employee employee) {
        EmployeesResponse response = new EmployeesResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setHra(employee.getHra());
        response.setBasicSalary(employee.getBasicSalary());
        response.setContact(employee.getContact());
        return response;
    }
	
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
