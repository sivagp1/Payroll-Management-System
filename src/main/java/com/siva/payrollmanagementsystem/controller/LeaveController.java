package com.siva.payrollmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siva.payrollmanagementsystem.dto.EmployeeLeavesResponse;
import com.siva.payrollmanagementsystem.dto.FetchAllLeavesResponse;
import com.siva.payrollmanagementsystem.dto.LeaveApplicationRequest;
import com.siva.payrollmanagementsystem.dto.LeaveApplicationResponse;
import com.siva.payrollmanagementsystem.dto.LeaveTypeResponse;
import com.siva.payrollmanagementsystem.exception.SuccessResponse;
import com.siva.payrollmanagementsystem.service.LeaveService;

@RestController
@RequestMapping("/leave")
public class LeaveController {

	@Autowired
    private LeaveService leaveService;

    @GetMapping("/leaveType")
    public ResponseEntity<List<LeaveTypeResponse>> getAllLeaveTypes() {
     
        List<LeaveTypeResponse> leaveTypes = leaveService.getAllLeaveTypes();
        return ResponseEntity.ok(leaveTypes);
        
    }
    
    
    @PostMapping("/add/{employeeId}/{leaveTypeId}")
    public ResponseEntity<?> applyForLeave(@PathVariable Long employeeId,
                                           @PathVariable Long leaveTypeId,
                                           @RequestBody LeaveApplicationRequest request) {
    	
            LeaveApplicationResponse response = leaveService.applyForLeave(employeeId, leaveTypeId, request);
            return ResponseEntity.ok(response);
        
    }
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> fetchLeavesByEmployeeId(@PathVariable Long employeeId) {
    	
    	EmployeeLeavesResponse response = leaveService.fetchLeavesByEmployeeId(employeeId);
            return ResponseEntity.ok(response);
        
    }
    
    @GetMapping("/fetchAll")
    public ResponseEntity<?> fetchAllLeaves()	{
    	
    	List<FetchAllLeavesResponse> response = leaveService.getAllLeaves();
    	return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/delete/{leaveId}")
    public ResponseEntity<?> deleteLeave(@PathVariable Long leaveId) {
    	
        leaveService.deleteLeave(leaveId);
        return ResponseEntity.ok(new SuccessResponse(true));
    }
}
