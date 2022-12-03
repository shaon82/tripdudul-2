package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.HotelExpense;

import java.util.List;

public interface HotelExpenseService {
    HotelExpense saveExpense(HotelExpense hotelExpense);

    List<HotelExpense> findHotelExpenseByHotelId(Long id);
}
