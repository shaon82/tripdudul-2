package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.EmployeeFile;

public interface EmployeeFileService {
    EmployeeFile saveFile(EmployeeFile employeeFile);

    EmployeeFile findFileByEmployeeId(Long id);

    void deleteFile(EmployeeFile employeeFile);
}
