package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.EmployeeSalary;
import com.updatetech.tripDodol.repository.EmployeeSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSalaryServiceImpl implements EmployeeSalaryService{

    @Autowired
    private EmployeeSalaryRepository employeeSalaryRepository;

    @Override
    public List<EmployeeSalary> findEmployeeSalaryByEmployeeId(Long id) {
        return employeeSalaryRepository.findEmployeeSalaryByEmployeeId(id);
    }
}
