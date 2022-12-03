package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.Booking;
import com.updatetech.tripDodol.model.Status;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface HotelBookingService {
    Booking save(Booking booking);

    Booking findBookingByRoomId(Long roomId);

    boolean roomIsNotpresent(Long id);

    List<Booking> findAll();

    Booking findBookingById(Long id);

    List<Booking> findBookingWithStatus(Long hotelId);

    List<Booking> findBookingByHotelId(Long id);

    List<Booking> findBookingByDate(Long hotelId,Date fromDate, Date toDate);

    List<Booking> findBookingByTodaysDate(Long hotelId, Date date) throws ParseException;

    List<Booking>findBookingByHotelIdAndWeekly(Long hotelId);

    List<Booking>findBookingByMonthly(Long hotelId);

    List<Booking>findBookingByYearly(Long hotelId);

    void deleteBooking(Booking booking);


//    List<Booking> findBookingByStatus(Status pending);

    
}
