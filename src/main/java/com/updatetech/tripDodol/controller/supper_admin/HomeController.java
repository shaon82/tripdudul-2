package com.updatetech.tripDodol.controller.supper_admin;


import com.updatetech.tripDodol.service.package_service.PackageAdminHomeService;
import com.updatetech.tripDodol.service.package_service.PackageBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/admin")
public class HomeController {

    @Autowired
    private PackageAdminHomeService packageAdminHomeService;

    @GetMapping(value = "/adminhome")
    public String adminIndexPage(Model model){
        double todayIncome = packageAdminHomeService.findTodaysIncome(new Date());
        double weeklyTotalIncome = packageAdminHomeService.findWeeklyIncome();
        double currentMonthlyTotalIncome = packageAdminHomeService.findMonthlyIncome();
        double yearlyIncome = packageAdminHomeService.findIncomeYearly();
        model.addAttribute("todayIncome", todayIncome);
        model.addAttribute("weeklyTotalIncome", weeklyTotalIncome);
        model.addAttribute("currentMonthlyTotalIncome", currentMonthlyTotalIncome);
        model.addAttribute("yearlyIncome",yearlyIncome);
        return "admin/index";
    }



}
