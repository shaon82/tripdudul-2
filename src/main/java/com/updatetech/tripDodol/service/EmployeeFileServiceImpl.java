package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.EmployeeFile;
import com.updatetech.tripDodol.repository.EmployeeFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeFileServiceImpl implements EmployeeFileService{

    @Autowired
    private EmployeeFileRepository employeeFileRepository;


    @Override
    public EmployeeFile saveFile(EmployeeFile employeeFile) {
        return employeeFileRepository.save(employeeFile);
    }

    @Override
    public EmployeeFile findFileByEmployeeId(Long id) {
        return employeeFileRepository.findFileByEmployeeId(id);
    }

    @Override
    public void deleteFile(EmployeeFile employeeFile) {
        employeeFileRepository.delete(employeeFile);
    }
}
