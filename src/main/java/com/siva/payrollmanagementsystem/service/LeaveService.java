package com.siva.payrollmanagementsystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siva.payrollmanagementsystem.dto.EmployeeLeavesResponse;
import com.siva.payrollmanagementsystem.dto.FetchAllLeavesResponse;
import com.siva.payrollmanagementsystem.dto.LeaveApplicationRequest;
import com.siva.payrollmanagementsystem.dto.LeaveApplicationResponse;
import com.siva.payrollmanagementsystem.dto.LeaveResponse;
import com.siva.payrollmanagementsystem.dto.LeaveTypeResponse;
import com.siva.payrollmanagementsystem.dto.UpdateLeaveRequest;
import com.siva.payrollmanagementsystem.entity.Employee;
import com.siva.payrollmanagementsystem.entity.LeaveType;
import com.siva.payrollmanagementsystem.entity.Leaves;
import com.siva.payrollmanagementsystem.exception.BadRequestException;
import com.siva.payrollmanagementsystem.exception.NoDataFoundException;
import com.siva.payrollmanagementsystem.repository.EmployeeRepository;
import com.siva.payrollmanagementsystem.repository.LeaveTypeRepository;
import com.siva.payrollmanagementsystem.repository.LeavesRepository;

@Service
public class LeaveService {

	 @Autowired
     private LeaveTypeRepository leaveTypeRepository;
	 
	 @Autowired
     private LeavesRepository leavesRepository;
	 
	 @Autowired
	 private EmployeeRepository employeeRepository;

     public List<LeaveTypeResponse> getAllLeaveTypes() {
        List<LeaveType> leaveTypes = leaveTypeRepository.findAll();
        if (leaveTypes.isEmpty()) {
            throw new NoDataFoundException("No Leave Type list found, please add.");
        }
        return leaveTypes.stream()
        		.map(this::mapToLeaveTypeResponse)
                .collect(Collectors.toList());
     }
     
     private LeaveTypeResponse mapToLeaveTypeResponse(LeaveType leaveType) {
         LeaveTypeResponse response = new LeaveTypeResponse();
         response.setLeaveTypeId(leaveType.getLeaveTypeId());
         response.setLeaveType(leaveType.getType());
         response.setEntitlementPerYear(leaveType.getEntitlementPerYear());
         return response;
     }
     
     public LeaveApplicationResponse applyForLeave(Long employeeId, Long leaveTypeId, LeaveApplicationRequest request) {
         Employee employee = findEmployeeById(employeeId);
         LeaveType leaveType = findLeaveTypeById(leaveTypeId);
         if (leaveType == null) {
             throw new NoDataFoundException("Leave type not found with id: " + leaveTypeId);
         }

         LocalDate startDate = LocalDate.parse(request.getStartDate());
         LocalDate endDate = LocalDate.parse(request.getEndDate());
         if (startDate.isBefore(LocalDate.now())) {
             throw new BadRequestException("Leave dates should not be in the past.");
         }
         if (endDate.isBefore(startDate)) {
             throw new BadRequestException("End date should be on or after the start date.");
         }

         Leaves leaveApplication = new Leaves();
         leaveApplication.setEmployeeId(employeeId);
         leaveApplication.setLeaveTypeId(leaveTypeId);
         leaveApplication.setStartDate(LocalDate.parse(request.getStartDate()));
         leaveApplication.setEndDate(LocalDate.parse(request.getEndDate()));
         leaveApplication.setHalfDay(request.isHalfDay());

         Leaves savedLeaves = leavesRepository.save(leaveApplication);
         return mapToLeaveApplicationResponse(employee, savedLeaves, leaveType);
     }

     private Employee findEmployeeById(Long employeeId) {
         return employeeRepository.findById(employeeId)
        		 .orElseThrow(() -> new NoDataFoundException("Employee not found with id: " + employeeId));
     }

     private LeaveType findLeaveTypeById(Long leaveTypeId) {
         return leaveTypeRepository.findById(leaveTypeId).orElse(null);
     }

     private LeaveApplicationResponse mapToLeaveApplicationResponse(Employee employee, Leaves savedLeaves, LeaveType leaveType) {
         LeaveApplicationResponse response = new LeaveApplicationResponse();
         response.setLeaveId(savedLeaves.getLeaveId());
         response.setEmployeeId(employee.getId());
         response.setEmployeeName(employee.getName());
         response.setEmployeeContact(employee.getContact());
         response.setEmployeeBasicSalary(employee.getBasicSalary());
         response.setEmployeeHra(employee.getHra());
         response.setLeaveTypeId(savedLeaves.getLeaveTypeId());
         response.setLeaveType(leaveType.getType());
         response.setEntitlementPerYear(leaveType.getEntitlementPerYear());
         response.setStartDate(savedLeaves.getStartDate().toString());
         response.setEndDate(savedLeaves.getEndDate().toString());
         response.setHalfDay(savedLeaves.isHalfDay());
         return response;
     }
     
     
     public EmployeeLeavesResponse fetchLeavesByEmployeeId(Long employeeId) {
         Employee employee = findEmployeeById(employeeId);
         List<Leaves> leaves = leavesRepository.findByEmployeeId(employeeId);
         if(leaves == null)
        	 throw new NoDataFoundException("Leave not found for employee " +employeeId);
         
         Map<Long, LeaveType> leaveTypeMap = leaveTypeRepository.findAll().stream()
                 .collect(Collectors.toMap(LeaveType::getLeaveTypeId, leaveType -> leaveType));
         
         List<LeaveResponse> leaveResponses = leaves.stream()
                 .map(leave -> {
                     LeaveType leaveType = leaveTypeMap.get(leave.getLeaveTypeId());
                     if (leaveType == null) {
                         throw new NoDataFoundException("Leave type not found with id: " + leave.getLeaveTypeId());
                     }

                     LeaveResponse response = new LeaveResponse();
                     response.setLeaveId(leave.getLeaveId());
                     response.setStartDate(leave.getStartDate().toString());
                     response.setEndDate(leave.getEndDate().toString());
                     response.setHalfDay(leave.isHalfDay());
                     response.setLeaveTypeId(leaveType.getLeaveTypeId());
                     response.setLeaveType(leaveType.getType());
                     response.setEntitlementPerYear(leaveType.getEntitlementPerYear());
                     return response;
                 })
                 .collect(Collectors.toList());

         return new EmployeeLeavesResponse(employee, leaveResponses);
         
     }
	    
     
     public List<FetchAllLeavesResponse> getAllLeaves() {
         List<Leaves> leaves = leavesRepository.findAll();
         return leaves.stream()
                 .map(this::mapToLeaveResponse)
                 .collect(Collectors.toList());
     }

     private FetchAllLeavesResponse mapToLeaveResponse(Leaves leave) {
    	 FetchAllLeavesResponse response = new FetchAllLeavesResponse();
         response.setLeaveId(leave.getLeaveId());
         
         Employee employee = employeeRepository.findById(leave.getEmployeeId())
                 .orElseThrow(() -> new NoDataFoundException("Employee not found with id: " + leave.getEmployeeId()));
         
         response.setEmployeeId(employee.getId());
         response.setEmployeeName(employee.getName());
         response.setEmployeeContact(employee.getContact());
         response.setEmployeeBasicSalary(employee.getBasicSalary());
         response.setEmployeeHra(employee.getHra());
         
         response.setStartDate(leave.getStartDate().toString());
         response.setEndDate(leave.getEndDate().toString());
         response.setHalfDay(leave.isHalfDay());
         
         LeaveType leaveType = leaveTypeRepository.findById(leave.getLeaveTypeId())
                 .orElseThrow(() -> new NoDataFoundException("Leave type not found with id: " + leave.getLeaveTypeId()));
         
         response.setLeaveTypeId(leaveType.getLeaveTypeId());
         response.setLeaveType(leaveType.getType());
         response.setEntitlementPerYear(leaveType.getEntitlementPerYear());
         
         return response;
     }
     
     
     public FetchAllLeavesResponse updateLeave(Long leaveId, Long leaveTypeId, UpdateLeaveRequest request) {
         Leaves leave = leavesRepository.findById(leaveId)
                 .orElseThrow(() -> new NoDataFoundException("Leave not found with id: " + leaveId));

         LeaveType leaveType = leaveTypeRepository.findById(leaveTypeId)
                 .orElseThrow(() -> new NoDataFoundException("Leave type not found with id: " + leaveTypeId));

         Employee employee = employeeRepository.findById(leave.getEmployeeId())
                 .orElseThrow(() -> new NoDataFoundException("Employee not found with id: " + leave.getEmployeeId()));

         LocalDate startDate = LocalDate.parse(request.getStartDate());
         LocalDate endDate = LocalDate.parse(request.getEndDate());

         if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())) {
             throw new BadRequestException("Leave dates should not be in the past.");
         }

         if (endDate.isBefore(startDate)) {
             throw new BadRequestException("End date should be after the start date.");
         }

         leave.setStartDate(startDate);
         leave.setEndDate(endDate);
         leave.setHalfDay(request.isHalfDay());

         leavesRepository.save(leave);

         return mapToLeaveResponse(leave, employee, leaveType);
     }
     
     private FetchAllLeavesResponse mapToLeaveResponse(Leaves leave, Employee employee, LeaveType leaveType) {
    	 FetchAllLeavesResponse response = new FetchAllLeavesResponse();
         response.setLeaveId(leave.getLeaveId());
         response.setEmployeeId(employee.getId());
         response.setEmployeeName(employee.getName());
         response.setEmployeeContact(employee.getContact());
         response.setEmployeeBasicSalary(employee.getBasicSalary());
         response.setEmployeeHra(employee.getHra());
         response.setLeaveTypeId(leaveType.getLeaveTypeId());
         response.setLeaveType(leaveType.getType());
         response.setEntitlementPerYear(leaveType.getEntitlementPerYear());
         response.setStartDate(leave.getStartDate().toString());
         response.setEndDate(leave.getEndDate().toString());
         response.setHalfDay(leave.isHalfDay());
         return response;
     }
     
     public void deleteLeave(Long leaveId) {
         Leaves leave = leavesRepository.findById(leaveId)
                 .orElseThrow(() -> new NoDataFoundException("Leave not found with id: " + leaveId));

         if (leave.getEndDate().isBefore(LocalDate.now())) {
             throw new BadRequestException("Cannot delete leave as the leave date is in the past");
         }

         leavesRepository.delete(leave);
     }

}
