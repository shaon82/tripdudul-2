package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.Booking;
import com.updatetech.tripDodol.model.HotelExpense;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface MerchantHomeService {

    double totalIncome(List<Booking>bookings);

    List<Booking> findCurrentMonthIncome(Long HotelId);

    List<Booking> findCurrentYearIncome(Long hotelId);

    double totalExpense(List<HotelExpense>hotelExpenses);

    List<HotelExpense> findTodaysExpense(Long hotelId, Date date) throws ParseException;

    List<HotelExpense> findWeeklyExpense(Long hotelId);

    List<HotelExpense> findMonthLyExpense(Long hotelId);

    List<HotelExpense> findYearlyExpense(Long hotelId);

}
