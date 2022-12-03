package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.Employee;
import com.updatetech.tripDodol.model.EmployeeFile;
import com.updatetech.tripDodol.repository.EmployeeRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private FilePathService filePathService;
    @Autowired
    private EmployeeFileService employeeFileService;


    @Override
    public boolean isEmployeePresent(Long id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee != null){
            return true;
        }
        return false;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Employee employee1 = employeeRepository.save(employee);
        if (employee1.getFile() != null && employee1.getFile().getSize()>0){
            MultipartFile file = employee1.getFile();

            String fileName = file.getOriginalFilename();
            String fileExtension = FilenameUtils.getExtension(fileName);
            String modifiedFileName = FilenameUtils.getBaseName(fileName)+"_"+employee1.getId()+"."+fileExtension;
            File storeRoomFile = filePathService.getRoomFilePath(modifiedFileName, "employeeImage");

            if (storeRoomFile != null){
                try {
                    FileUtils.writeByteArrayToFile(storeRoomFile, file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            EmployeeFile employeeFile = new EmployeeFile();
            employeeFile.setModifiedfileName(modifiedFileName);
            employeeFile.setFileExtension(fileExtension);
            employeeFile.setFileName(fileName);
            employeeFile.setEmployee(employee1);

            employeeFileService.saveFile(employeeFile);
        }
        return employee1;
    }

    @Override
    public List<Employee> findEmployeeByHotelId(Long id) {
        return employeeRepository.findEmployeeByHotelId(id);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }
}
