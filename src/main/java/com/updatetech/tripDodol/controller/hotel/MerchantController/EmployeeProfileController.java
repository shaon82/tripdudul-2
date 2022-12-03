package com.updatetech.tripDodol.controller.hotel.MerchantController;

import com.updatetech.tripDodol.model.Employee;
import com.updatetech.tripDodol.model.EmployeeFile;
import com.updatetech.tripDodol.model.EmployeeSalary;
import com.updatetech.tripDodol.service.EmployeeFileService;
import com.updatetech.tripDodol.service.EmployeeSalaryService;
import com.updatetech.tripDodol.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/merchant")
public class EmployeeProfileController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeFileService employeeFileService;
	@Autowired
	private EmployeeSalaryService employeeSalaryService;
	
	@GetMapping("/view/employee-profile/{id}")
	public String employeeProfilePage(Model model, @PathVariable("id") Long id) {
		Employee employee = employeeService.findEmployeeById(id);
		EmployeeFile employeeFile = employeeFileService.findFileByEmployeeId(id);
		List<EmployeeSalary> employeeSalaries = employeeSalaryService.findEmployeeSalaryByEmployeeId(id);
		model.addAttribute("employee",employee);
		model.addAttribute("employeeFile",employeeFile);
		model.addAttribute("employeeSalaries",employeeSalaries);
		return "merchant_web/view-employee-profile";
	}

}
