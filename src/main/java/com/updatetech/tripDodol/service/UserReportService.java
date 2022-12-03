package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.Booking;
import com.updatetech.tripDodol.model.Guest;
import com.updatetech.tripDodol.model.HotelExpense;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserReportService {


    void generateReport(List<Booking> bookings, String hotelName, String roomNumber, String roomType, double roomPrice);

    void deletePdf(String filePath);

    void generateHistoryReport(List<Booking>bookings, HttpServletResponse response,String hotelName);

    void generateTodaysReport(String hotelName, String invoiceNumber,String reportTitle, List<Booking> bookings,HttpServletResponse response);

    void generateExpenseReport(String hotelName, String invoiceNumber, String reportName, List<HotelExpense> hotelExpenses, HttpServletResponse response);

    void generateBookingReport(String hotelName, String invoiceNumber, String reportName, List<Booking>bookings , HttpServletResponse response);
}
