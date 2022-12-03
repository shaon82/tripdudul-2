package com.updatetech.tripDodol.repository;

import com.updatetech.tripDodol.model.Booking;
import com.updatetech.tripDodol.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Repository
public interface HotelBookingRepository extends JpaRepository<Booking, Long> {

    Booking findBookingByRoomId(Long roomId);

    Booking findBookingById(Long id);

    @Query(value = "select * from booking where hotel_id=? AND (status='PENDING' OR status='CONFIRM')", nativeQuery = true)
    List<Booking> findBookingWithStatus(Long hotelId);

    List<Booking> findBookingByHotelId(Long id);


    @Query(value = "select * from booking where hotel_id=? and (check_in between ? and ?)", nativeQuery = true)
    List<Booking> findBookingByDate(Long hotelId, Date fromDate, Date toDate);

    @Query(value = "select * from booking where hotel_id=? and check_in=?",nativeQuery = true)
    List<Booking>todaysIncome(Long hotelId, Date today);

}
