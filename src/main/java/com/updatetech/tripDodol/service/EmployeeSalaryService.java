package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.EmployeeSalary;

import java.util.List;

public interface EmployeeSalaryService {
    List<EmployeeSalary> findEmployeeSalaryByEmployeeId(Long id);
}
