package com.updatetech.tripDodol.repository;


import com.updatetech.tripDodol.model.HotelExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HotelExpenseRepository extends JpaRepository<HotelExpense, Long> {
    List<HotelExpense> findHotelExpenseByHotelId(Long id);

    @Query(value = "select * from hotel_expense where hotel_id=? and date=?", nativeQuery = true)
    List<HotelExpense> findHotelExpenseByTodaysDate(Long hotelId, Date newDate);

    @Query(value = "select * from hotel_expense where hotel_id=? and (date between ? and ?)", nativeQuery = true)
    List<HotelExpense> findHotelExpenseByTwoDate(Long hotelId, Date start, Date end);


}
