package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.HotelPercentage;
import com.updatetech.tripDodol.repository.HotelPercentageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelPercentageServiceImpl implements HotelPercentageService{
    @Autowired
    private HotelPercentageRepository hotelPercentageRepository;

    @Override
    public HotelPercentage savePercentage(HotelPercentage hotelPercentage) {
        return hotelPercentageRepository.save(hotelPercentage);
    }

    @Override
    public HotelPercentage findPercentageByHotelId(Long id) {
        return hotelPercentageRepository.findPercentageByHotelId(id);
    }
}
