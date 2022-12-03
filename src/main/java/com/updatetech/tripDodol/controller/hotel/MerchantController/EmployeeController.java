package com.updatetech.tripDodol.controller.hotel.MerchantController;


import com.updatetech.tripDodol.model.Employee;
import com.updatetech.tripDodol.model.EmployeeFile;
import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.service.EmployeeFileService;
import com.updatetech.tripDodol.service.EmployeeService;
import com.updatetech.tripDodol.service.HotelService;
import com.updatetech.tripDodol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/merchant")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private EmployeeFileService employeeFileService;


    @GetMapping("/get/all-employee")
    public String getAllEmployee(Model model, Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        List<Employee>employees = employeeService.findEmployeeByHotelId(hotel.getId());
        List<EmployeeFile>employeeFiles = new ArrayList<>();
        for (Employee employee: employees){
            EmployeeFile employeeFile = employeeFileService.findFileByEmployeeId(employee.getId());
            employeeFiles.add(employeeFile);
        }

        model.addAttribute("employees",employees);
        model.addAttribute("employeeFiles",employeeFiles);
        return "merchant_web/employee-all";
    }


    @GetMapping("/add/employee")
    public String addEmployeePage(Model model, Principal principal){
        String username = principal.getName();
        System.out.println("this is user name: "+username);
        model.addAttribute("employee", new Employee());
        return "merchant_web/employee-add";
    }


    @PostMapping("/add/employee")
    public String addEmployee(Model model, @Valid Employee employee, BindingResult result, Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        employee.setHotel(hotel);
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "merchant_web/employee-add";
        }
        else if (employeeService.isEmployeePresent(employee.getId())){
            model.addAttribute("employeeExist", true);
            return "merchant_web/employee-add";
        }else {
            employeeService.saveEmployee(employee);
            model.addAttribute("success", true);
        }
        return "redirect:/merchant/get/all-employee";
    }


    @GetMapping("/update/employee/{id}")
    public String updateEmployeePage(Model model, @PathVariable("id") Long id){
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        return "merchant_web/employee-update";
    }

    @PostMapping("/update/employee/{id}")
    public String updateEmployee(Model model,@PathVariable("id") Long id, @Valid Employee employee,
                                 BindingResult result, Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        employee.setHotel(hotel);

        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "merchant_web/employee-update";
        }else {
            employeeService.saveEmployee(employee);
            model.addAttribute("success", true);
        }
        return "redirect:/merchant/get/all-employee";
    }


    @GetMapping("/delete/employee/{id}")
    public String deleteEmployee(Model model, @PathVariable("id") Long id){
        Employee employee = employeeService.findEmployeeById(id);
        EmployeeFile employeeFile = employeeFileService.findFileByEmployeeId(employee.getId());
        File file = new File("/home/shaon/updateTech/tripDodol/src/main/webapp/employeeImage/"+employeeFile.getModifiedfileName());
        if (file.exists()){
            file.delete();
        }
        employeeFileService.deleteFile(employeeFile);
        employeeService.deleteEmployee(employee);
        return "redirect:/merchant/get/all-employee";
    }
}
