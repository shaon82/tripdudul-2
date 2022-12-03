package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.HotelPercentage;

public interface HotelPercentageService {
    HotelPercentage savePercentage(HotelPercentage hotelPercentage);

    HotelPercentage findPercentageByHotelId(Long id);
}
