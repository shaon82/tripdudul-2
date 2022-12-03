package com.updatetech.tripDodol.controller.hotel.MerchantController;


import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.HotelExpense;
import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.service.HotelService;
import com.updatetech.tripDodol.service.MerchantHomeService;
import com.updatetech.tripDodol.service.UserReportService;
import com.updatetech.tripDodol.service.UserService;
import com.updatetech.tripDodol.utility.GenerateInvoiceNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/merchant")
public class MerchantExpenseController {

    @Autowired
    private MerchantHomeService merchantHomeService;
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserReportService userReportService;


    @GetMapping("/daily/expense/report")
    public void todaysExpenseReport(Model model, Principal principal, HttpServletResponse response)throws Exception{
        String username= principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        List<HotelExpense>hotelExpenses = merchantHomeService.findTodaysExpense(hotel.getId(),new Date());
        String invoiceNumber = GenerateInvoiceNumber.generateInvoiceNumberString();
        String reportName = "Daily Expense Report";
        userReportService.generateExpenseReport(hotel.getHotelName(),invoiceNumber,reportName,hotelExpenses,response);
    }

    @GetMapping("/weekly/expense/report")
    public void weeklyExpenseReport(Model model, Principal principal, HttpServletResponse response) throws Exception{
        String username= principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        List<HotelExpense> hotelExpenses = merchantHomeService.findWeeklyExpense(hotel.getId());
        String invoiceNumber = GenerateInvoiceNumber.generateInvoiceNumberString();
        String reportName = "Weekly Expense Report";
        userReportService.generateExpenseReport(hotel.getHotelName(),invoiceNumber,reportName,hotelExpenses,response);
    }


    @GetMapping("/monthly/expense/report")
    public void monthlyExpenseReport(Model model, Principal principal, HttpServletResponse response) throws Exception{
        String username= principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        List<HotelExpense> hotelExpenses = merchantHomeService.findMonthLyExpense(hotel.getId());
        String invoiceNumber = GenerateInvoiceNumber.generateInvoiceNumberString();
        String reportName = "Monthly Expense Report";
        userReportService.generateExpenseReport(hotel.getHotelName(),invoiceNumber,reportName,hotelExpenses,response);
    }

    @GetMapping("/yearly/expense/report")
    public void yearlyExpenseReport(Model model, Principal principal, HttpServletResponse response) throws Exception{
        String username= principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        List<HotelExpense> hotelExpenses = merchantHomeService.findYearlyExpense(hotel.getId());
        String invoiceNumber = GenerateInvoiceNumber.generateInvoiceNumberString();
        String reportName = "Yearly Expense Report";
        userReportService.generateExpenseReport(hotel.getHotelName(),invoiceNumber,reportName,hotelExpenses,response);
    }
}
