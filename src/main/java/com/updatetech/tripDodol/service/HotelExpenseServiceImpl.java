package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.HotelExpense;
import com.updatetech.tripDodol.repository.HotelExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelExpenseServiceImpl implements HotelExpenseService{

    @Autowired
    private HotelExpenseRepository hotelExpenseRepository;

    @Override
    public HotelExpense saveExpense(HotelExpense hotelExpense) {
        return hotelExpenseRepository.save(hotelExpense);
    }

    @Override
    public List<HotelExpense> findHotelExpenseByHotelId(Long id) {
        return hotelExpenseRepository.findHotelExpenseByHotelId(id);
    }
}
