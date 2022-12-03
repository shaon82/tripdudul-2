package com.updatetech.tripDodol.repository;

import com.updatetech.tripDodol.model.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalary, Long> {
    List<EmployeeSalary> findEmployeeSalaryByEmployeeId(Long id);
}
