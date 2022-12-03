package com.updatetech.tripDodol.controller.hotel.MerchantController;


import com.updatetech.tripDodol.model.*;
import com.updatetech.tripDodol.service.*;
import com.updatetech.tripDodol.utility.GenerateInvoiceNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/merchant")
public class MerchantHomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelBookingService hotelBookingService;
    @Autowired
    private UserReportService userReportService;
    @Autowired
    private MerchantHomeService merchantHomeService;
    @Autowired
    private UserFileService userFileService;


    @GetMapping(value = "/merchant-home")
    public String adminIndexPage(Model model, Principal principal, HttpSession session) throws Exception{
        String username= principal.getName();
        User user = userService.findUserByUsername(username);
        UserFile userFile = userFileService.findFileByUserId(user.getId());
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        session.setAttribute("hotelId", hotel.getId());
        System.out.println("this is hotel name: "+hotel.getHotelName());
        session.setAttribute("hotelId",hotel.getId());
        List<Booking>bookings= null;
        try {
            bookings = hotelBookingService.findBookingByTodaysDate(hotel.getId(), new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double totalIncome = merchantHomeService.totalIncome(bookings);
        List<Booking>weeklyBooking = hotelBookingService.findBookingByHotelIdAndWeekly(hotel.getId());
        double weeklyTotalIncome = merchantHomeService.totalIncome(weeklyBooking);

        List<Booking>monthlyBooking = merchantHomeService.findCurrentMonthIncome(hotel.getId());
        double monthlyIncome = merchantHomeService.totalIncome(monthlyBooking);

        List<Booking>yearlyTotalIncome= merchantHomeService.findCurrentYearIncome(hotel.getId());
        double totalYearlyIncome = merchantHomeService.totalIncome(yearlyTotalIncome);

        List<HotelExpense>todaysExpense= merchantHomeService.findTodaysExpense(hotel.getId(), new Date());
        double todaysTotalExpense = merchantHomeService.totalExpense(todaysExpense);

        List<HotelExpense>weeklyExpense = merchantHomeService.findWeeklyExpense(hotel.getId());
        double weeklyTotalExpense = merchantHomeService.totalExpense(weeklyExpense);

        List<HotelExpense>monthlyExpense = merchantHomeService.findMonthLyExpense(hotel.getId());
        double monthlyTotalExpense = merchantHomeService.totalExpense(monthlyExpense);

        List<HotelExpense>yearlyExpense = merchantHomeService.findYearlyExpense(hotel.getId());
        double yearlyTotalExpense = merchantHomeService.totalExpense(yearlyExpense);

        List<Booking>todaysBookingList= hotelBookingService.findBookingByTodaysDate(hotel.getId(),new Date());
        List<Booking>weeklyBookingList = hotelBookingService.findBookingByHotelIdAndWeekly(hotel.getId());
        List<Booking>monthLyBookingList= hotelBookingService.findBookingByMonthly(hotel.getId());
        List<Booking>yearlyBookingList = hotelBookingService.findBookingByYearly(hotel.getId());
        List<Booking>bookingList = hotelBookingService.findBookingByHotelId(hotel.getId());

        model.addAttribute("userFile",userFile);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("hotelId", hotel.getId());
        model.addAttribute("weeklyTotalIncome",weeklyTotalIncome);
        model.addAttribute("monthlyIncome", monthlyIncome);
        model.addAttribute("totalYearlyIncome",totalYearlyIncome);
        model.addAttribute("todaysTotalExpense",todaysTotalExpense);
        model.addAttribute("weeklyTotalExpense",weeklyTotalExpense);
        model.addAttribute("monthlyTotalExpense",monthlyTotalExpense);
        model.addAttribute("yearlyTotalExpense",yearlyTotalExpense);
        model.addAttribute("todaysBookingList",todaysBookingList.size());
        model.addAttribute("weeklyBookingList",weeklyBookingList.size());
        model.addAttribute("monthLyBookingList",monthLyBookingList.size());
        model.addAttribute("yearlyBookingList",yearlyBookingList.size());
        model.addAttribute("bookings", bookingList);
        model.addAttribute("hotel",hotel);
        return "merchant_web/index";
    }

    @GetMapping("/report/today/income")
    public void todaysIncome(Model model,Principal principal, HttpServletResponse response){
        String username= principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        List<Booking>bookings=null;
        try {
            bookings = hotelBookingService.findBookingByTodaysDate(hotel.getId(), new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String invoiceNumber = GenerateInvoiceNumber.generateInvoiceNumberString();
        String reportTitle = "Daily Revenue";
        System.out.println("this is invoice number: "+invoiceNumber);
        userReportService.generateTodaysReport(hotel.getHotelName(), invoiceNumber,reportTitle, bookings, response);
    }

    @GetMapping("/report/week/income")
    public void incomeReportforThisWeek(Model model,Principal principal, HttpServletResponse response)throws Exception{
        String username= principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        List<Booking>bookings=hotelBookingService.findBookingByHotelIdAndWeekly(hotel.getId());
        String invoiceNumber = GenerateInvoiceNumber.generateInvoiceNumberString();
        String reportTitle= "Weekly Revenue";
        userReportService.generateTodaysReport(hotel.getHotelName(),invoiceNumber,reportTitle,bookings, response);
    }

    @GetMapping("/monthly/income/report")
    public void monthlyIncomeReport(Model model,Principal principal, HttpServletResponse response){
        String username= principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        List<Booking>bookings =merchantHomeService.findCurrentMonthIncome(hotel.getId());
        String invoiceNumber = GenerateInvoiceNumber.generateInvoiceNumberString();
        String reportTitle="Monthly Revenue";
        userReportService.generateTodaysReport(hotel.getHotelName(),invoiceNumber,reportTitle,bookings,response);
     }

     @GetMapping("/yearly/income/report")
     public void yearlyIncomeReport(Model model, Principal principal, HttpServletResponse response)throws Exception{
         String username= principal.getName();
         User user = userService.findUserByUsername(username);
         Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
         List<Booking>bookings =merchantHomeService.findCurrentYearIncome(hotel.getId());
         String invoiceNumber = GenerateInvoiceNumber.generateInvoiceNumberString();
         String reportTitle="Yearly Revenue";
         userReportService.generateTodaysReport(hotel.getHotelName(),invoiceNumber,reportTitle,bookings,response);
     }
}
