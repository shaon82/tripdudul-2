package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.Booking;
import com.updatetech.tripDodol.model.Guest;
import com.updatetech.tripDodol.model.Status;
import com.updatetech.tripDodol.repository.HotelBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HotelBookingServiceImpl implements HotelBookingService{

    @Autowired
    private HotelBookingRepository hotelBookingRepository;
    @Autowired
    private GuestService guestService;

    @Override
    public Booking save(Booking booking) {
        long days=(booking.getCheckOut().getTime() - booking.getCheckIn().getTime())/(24*3600*1000);
        System.out.println("this is day's: "+days);
        booking.setTotalAmount(days*booking.getRoom().getRoomPrice());
        return hotelBookingRepository.save(booking);
    }

    @Override
    public Booking findBookingByRoomId(Long roomId) {
        return hotelBookingRepository.findBookingByRoomId(roomId);
    }

    @Override
    public boolean roomIsNotpresent(Long id) {
        Booking booking = hotelBookingRepository.findBookingByRoomId(id);
        if (booking == null){
            return true;
        }
        return false;
    }

    @Override
    public List<Booking> findAll() {
        return hotelBookingRepository.findAll();
    }

    @Override
    public Booking findBookingById(Long id) {
        return hotelBookingRepository.findBookingById(id);
    }

    @Override
    public List<Booking> findBookingWithStatus(Long hotelId) {
        return hotelBookingRepository.findBookingWithStatus(hotelId);
    }

    @Override
    public List<Booking> findBookingByHotelId(Long id) {
        return hotelBookingRepository.findBookingByHotelId(id);
    }

    @Override
    public List<Booking> findBookingByDate(Long hotelId, Date fromDate, Date toDate) {
        return hotelBookingRepository.findBookingByDate(hotelId,fromDate, toDate);
    }

    @Override
    public List<Booking> findBookingByTodaysDate(Long hotelId, Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date newDate = format.parse(format.format(date));
        return hotelBookingRepository.todaysIncome(hotelId,newDate);
    }

    @Override
    public List<Booking> findBookingByHotelIdAndWeekly(Long hotelId) {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 7);
        Date start = c.getTime();
        c.add(Calendar.DATE, 6);
        Date end = c.getTime();
        System.out.println(start + " - " + end);
        return hotelBookingRepository.findBookingByDate(hotelId,start,end);
    }

    @Override
    public List<Booking> findBookingByMonthly(Long hotelId) {
        LocalDate today = LocalDate.now();
        Date start = java.sql.Date.valueOf(today.withDayOfMonth(1));
        Date end = java.sql.Date.valueOf(today.withDayOfMonth(today.lengthOfMonth()));
        return hotelBookingRepository.findBookingByDate(hotelId,start,end);
    }

    @Override
    public List<Booking> findBookingByYearly(Long hotelId) {
        LocalDate today = LocalDate.now();
        Date start = java.sql.Date.valueOf(today.withDayOfYear(1));
        Date end = java.sql.Date.valueOf(today.withDayOfYear(today.lengthOfYear()));
        return hotelBookingRepository.findBookingByDate(hotelId,start,end);
    }

    @Override
    public void deleteBooking(Booking booking) {
        hotelBookingRepository.delete(booking);
    }


}
