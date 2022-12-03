package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel);

    boolean isHotelPresent(String hotelName);

    Hotel findHotelById(Long hotelId);

    void deleteHotel(Hotel hotel);

    List<Hotel> findAllHotel();

    List<Hotel> searchHotelWithCity(String city);

    Hotel findHotelByUser(Long id);



    List<Hotel> findAllPendingHotels();

    Hotel approvedHotel(Hotel hotel);

    Hotel saveHotel(Hotel hotel)throws Exception;
}