package com.siva.payrollmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.siva.payrollmanagementsystem.entity.SalaryComponent;

public interface SalaryComponentRepository extends JpaRepository<SalaryComponent, Long> {
	
	List<SalaryComponent> findByEmployeeId(Long employeeId);
	
	 @Query("SELECT COALESCE(SUM(sc.amount), 0) FROM SalaryComponent sc WHERE sc.employeeId = :employeeId AND sc.type IN ('Allowance', 'Appraisal') AND MONTH(sc.month) = :month AND YEAR(sc.month) = :year")
    double calculateTotalAllowancesAndAppraisals(@Param("employeeId") Long employeeId, @Param("month") int month, @Param("year") int year);

    @Query("SELECT COALESCE(SUM(sc.amount), 0) FROM SalaryComponent sc WHERE sc.employeeId = :employeeId AND sc.type = 'Deduction' AND MONTH(sc.month) = :month AND YEAR(sc.month) = :year")
    double calculateTotalDeductions(@Param("employeeId") Long employeeId, @Param("month") int month, @Param("year") int year);

    @Query("SELECT sc FROM SalaryComponent sc WHERE sc.employeeId = :employeeId AND MONTH(sc.month) = :month AND YEAR(sc.month) = :year")
    List<SalaryComponent> findSalaryComponentsForEmployeeInMonth(@Param("employeeId") Long employeeId, @Param("month") int month, @Param("year") int year);

}
