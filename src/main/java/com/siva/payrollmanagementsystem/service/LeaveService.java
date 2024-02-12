package com.siva.payrollmanagementsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siva.payrollmanagementsystem.dto.LeaveTypeResponse;
import com.siva.payrollmanagementsystem.entity.LeaveType;
import com.siva.payrollmanagementsystem.exception.NoDataFoundException;
import com.siva.payrollmanagementsystem.repository.LeaveTypeRepository;

@Service
public class LeaveService {

	 @Autowired
     private LeaveTypeRepository leaveTypeRepository;

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
         response.setLeaveType(leaveType.getLeaveType());
         response.setEntitlementPerYear(leaveType.getEntitlementPerYear());
         return response;
     }
	    
}
