package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.Booking;
import com.updatetech.tripDodol.model.HotelExpense;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserReportServiceImpl implements UserReportService{

    @Override
    public void generateReport(List<Booking> bookings, String hotelName, String roomNumber, String roomType, double roomPrice) {

            String filePath = "/home/shaon/updateTech/tripDodol/reports";

        try {
            JasperReport mainReport = JasperCompileManager.compileReport(filePath+"/"+"userHotelBooking.jrxml");
            JRBeanCollectionDataSource guestList = new JRBeanCollectionDataSource(bookings);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("hotelName",hotelName);
            parameters.put("roomNumber",roomNumber);
            parameters.put("roomPrice",roomPrice);
            parameters.put("roomType",roomType);

            JasperPrint jasperPrint = JasperFillManager.fillReport(mainReport, parameters,guestList);

            JasperExportManager.exportReportToPdfFile(jasperPrint,filePath+"/"+"HotelInvoice.pdf");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePdf(String filePath) {
        File file = new File(filePath+"HotelInvoice.pdf");
        if (file.exists()){
            file.delete();
        }
    }

    @Override
    public void generateHistoryReport(List<Booking>bookings, HttpServletResponse response, String hotelName) {
            String filePath = "/home/shaon/updateTech/tripDodol/reports/BookingHistoryReport/";

        try {
            JasperReport mainReport = JasperCompileManager.compileReport(filePath+"bookingHistoryReport.jrxml");
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bookings);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("hotelName",hotelName);
            JasperPrint jasperPrint = JasperFillManager.fillReport(mainReport,parameters,dataSource);
            response.setContentType("application/x-download");
            response.addHeader("Content-disposition", "attachment; filename=HistoryReport.pdf");
            OutputStream out = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint,out);


        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateTodaysReport(String hotelName, String invoiceNumber,String reportTitle, List<Booking> bookings, HttpServletResponse response) {
            String filePath = "/home/shaon/updateTech/tripDodol/reports/dailyIncomeReport/";

        try {
            JasperReport mainReport = JasperCompileManager.compileReport(filePath+"dailyIncomeReport.jrxml");
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bookings);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("hotelName",hotelName);
            parameters.put("invoiceNumber",invoiceNumber);
            parameters.put("reportTitle",reportTitle);
            JasperPrint jasperPrint = JasperFillManager.fillReport(mainReport,parameters,dataSource);
            response.setContentType("application/x-download");
            response.addHeader("Content-disposition", "attachment; filename=DailyHistoryReport.pdf");
            OutputStream out = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint,out);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateExpenseReport(String hotelName, String invoiceNumber, String reportName, List<HotelExpense> hotelExpenses, HttpServletResponse response) {

        String filePath = "/home/shaon/updateTech/tripDodol/reports/expenseReport/";

        try {
            JasperReport mainReport = JasperCompileManager.compileReport(filePath+"hotelExpenseReport.jrxml");
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(hotelExpenses);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("hotelName",hotelName);
            parameters.put("invoiceNumber",invoiceNumber);
            parameters.put("reportName",reportName);
            JasperPrint jasperPrint = JasperFillManager.fillReport(mainReport,parameters,dataSource);
            response.setContentType("application/x-download");
            response.addHeader("Content-disposition", "attachment; filename=DailyExpenseReport.pdf");
            OutputStream out = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint,out);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateBookingReport(String hotelName, String invoiceNumber, String reportName, List<Booking>bookings, HttpServletResponse response) {

        String filePath = "/home/shaon/updateTech/tripDodol/reports/BookingHistoryReport/";

        try {
            JasperReport mainReport = JasperCompileManager.compileReport(filePath+"dailyBookingHistory.jrxml");
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bookings);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("hotelName",hotelName);
            parameters.put("invoiceNumber",invoiceNumber);
            parameters.put("invoiceTitle",reportName);
            JasperPrint jasperPrint = JasperFillManager.fillReport(mainReport,parameters,dataSource);
            response.setContentType("application/x-download");
            response.addHeader("Content-disposition", "attachment; filename=BookingHistoryReport.pdf");
            OutputStream out = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint,out);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
