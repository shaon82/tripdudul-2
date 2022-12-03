package com.updatetech.tripDodol.controller.hotel.MerchantController;


import com.updatetech.tripDodol.model.Booking;
import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.service.HotelBookingService;
import com.updatetech.tripDodol.service.HotelService;
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
public class BookingHistoryReportController {


    @Autowired
    private UserReportService userReportService;
    @Autowired
    private HotelBookingService hotelBookingService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;

    @GetMapping("/daily/booking/report")
    public void dailyBookingHistoryReport(Model model, Principal principal, HttpServletResponse response)throws Exception{
        String username= principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        String invoiceNumber= GenerateInvoiceNumber.generateInvoiceNumberString();
        String invoiceTitle = "Daily Booking History";
        List<Booking>bookings = hotelBookingService.findBookingByTodaysDate(hotel.getId(), new Date());
        userReportService.generateBookingReport(hotel.getHotelName(),invoiceNumber,invoiceTitle,bookings,response);
    }

    @GetMapping("/weekly/booking/report")
    public void weekBookingHistoryReport(Model model, Principal principal, HttpServletResponse response){
        String username= principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        String invoiceNumber= GenerateInvoiceNumber.generateInvoiceNumberString();
        String invoiceTitle = "Weekly Booking History";
        List<Booking>bookings = hotelBookingService.findBookingByHotelIdAndWeekly(hotel.getId());
        userReportService.generateBookingReport(hotel.getHotelName(),invoiceNumber,invoiceTitle,bookings,response);
    }

    @GetMapping("/monthly/booking/report")
    public void monthlyBookingHistoryReport(Model model, Principal principal, HttpServletResponse response){
        String username= principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        String invoiceNumber= GenerateInvoiceNumber.generateInvoiceNumberString();
        String invoiceTitle = "Monthly Booking History";
        List<Booking>bookings = hotelBookingService.findBookingByMonthly(hotel.getId());
        userReportService.generateBookingReport(hotel.getHotelName(),invoiceNumber,invoiceTitle,bookings,response);
    }

    @GetMapping("/yearly/booking/report")
    public void yearlyBookingHistoryReport(Model model, Principal principal, HttpServletResponse response){
        String username= principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        String invoiceNumber= GenerateInvoiceNumber.generateInvoiceNumberString();
        String invoiceTitle = "yearly Booking History";
        List<Booking>bookings = hotelBookingService.findBookingByYearly(hotel.getId());
        userReportService.generateBookingReport(hotel.getHotelName(),invoiceNumber,invoiceTitle,bookings,response);
    }
}
