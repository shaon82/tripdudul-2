package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.Booking;
import com.updatetech.tripDodol.model.HotelExpense;
import com.updatetech.tripDodol.repository.HotelBookingRepository;
import com.updatetech.tripDodol.repository.HotelExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MerchantHomeServiceImpl implements MerchantHomeService{
    @Autowired
    private HotelBookingRepository hotelBookingRepository;
    @Autowired
    private HotelExpenseRepository hotelExpenseRepository;


    @Override
    public double totalIncome(List<Booking> bookings) {
        double totalAmount=0;
        for (Booking booking: bookings){
            totalAmount += booking.getTotalAmount();
        }
        return totalAmount;
    }

    @Override
    public List<Booking> findCurrentMonthIncome(Long hotelId) {
        LocalDate today = LocalDate.now();
        Date start = java.sql.Date.valueOf(today.withDayOfMonth(1));
        Date end = java.sql.Date.valueOf(today.withDayOfMonth(today.lengthOfMonth()));
        return hotelBookingRepository.findBookingByDate(hotelId,start,end);
    }

    @Override
    public List<Booking> findCurrentYearIncome(Long hotelId) {
        LocalDate today = LocalDate.now();
        Date start = java.sql.Date.valueOf(today.withDayOfYear(1));
        Date end = java.sql.Date.valueOf(today.withDayOfYear(today.lengthOfYear()));
        return hotelBookingRepository.findBookingByDate(hotelId,start,end);
    }

    @Override
    public double totalExpense(List<HotelExpense> hotelExpenses) {
        double totalExpense=0;
        for (HotelExpense hotelExpense: hotelExpenses){
            totalExpense += hotelExpense.getAmount();
        }
        return totalExpense;
    }

    @Override
    public List<HotelExpense> findTodaysExpense(Long hotelId, Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date newDate = format.parse(format.format(date));
        System.out.println("this is today's income");
        return hotelExpenseRepository.findHotelExpenseByTodaysDate(hotelId, newDate);
    }

    @Override
    public List<HotelExpense> findWeeklyExpense(Long hotelId) { ;
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 7);
        Date start = c.getTime();
        c.add(Calendar.DATE, 6);
        Date end = c.getTime();
        System.out.println(start + " - " + end);
        return hotelExpenseRepository.findHotelExpenseByTwoDate(hotelId,start,end);
    }

    @Override
    public List<HotelExpense> findMonthLyExpense(Long hotelId) {
        LocalDate today = LocalDate.now();
        Date start = java.sql.Date.valueOf(today.withDayOfMonth(1));
        Date end = java.sql.Date.valueOf(today.withDayOfMonth(today.lengthOfMonth()));
        return hotelExpenseRepository.findHotelExpenseByTwoDate(hotelId,start,end);
    }

    @Override
    public List<HotelExpense> findYearlyExpense(Long hotelId) {
        LocalDate today = LocalDate.now();
        Date start = java.sql.Date.valueOf(today.withDayOfYear(1));
        Date end = java.sql.Date.valueOf(today.withDayOfYear(today.lengthOfYear()));
        return hotelExpenseRepository.findHotelExpenseByTwoDate(hotelId,start,end);
    }


}
