package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.Employee;

import java.util.List;

public interface EmployeeService {
    boolean isEmployeePresent(Long id);

    Employee saveEmployee(Employee employee);

    List<Employee> findEmployeeByHotelId(Long id);

    Employee findEmployeeById(Long id);

    void deleteEmployee(Employee employee);
}
