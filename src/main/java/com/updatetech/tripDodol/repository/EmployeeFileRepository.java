package com.updatetech.tripDodol.repository;

import com.updatetech.tripDodol.model.EmployeeFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeFileRepository extends JpaRepository<EmployeeFile, Long> {
    EmployeeFile findFileByEmployeeId(Long id);
}
