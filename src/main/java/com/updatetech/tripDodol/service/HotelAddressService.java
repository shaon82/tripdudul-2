package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.HotelAddress;

import java.util.List;

public interface HotelAddressService {

    HotelAddress createHotelAddress(HotelAddress hotelAddress);

    boolean isAddressPresent(HotelAddress hotelAddress);

    HotelAddress saveHotelAddress(HotelAddress hotelAddress);

    List<HotelAddress> findAllAddress();

    HotelAddress findHotelAddressByHotelId(Long hotelId);

    void delete(HotelAddress hotelAddress);

    List<HotelAddress> findAddressByCity(String city);

    List<HotelAddress> saveAllAddress(List<HotelAddress> hotelAddresses);

    List<HotelAddress> findAddressByHotelId(Long hotelId);
}
