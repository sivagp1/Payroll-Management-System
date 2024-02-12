package com.siva.payrollmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siva.payrollmanagementsystem.dto.LeaveTypeResponse;
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
}
