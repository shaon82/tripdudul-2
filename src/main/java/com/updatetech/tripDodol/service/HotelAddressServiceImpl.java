package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.HotelAddress;
import com.updatetech.tripDodol.repository.HotelAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelAddressServiceImpl implements HotelAddressService{

    @Autowired
    private HotelAddressRepository hotelAddressRepository;

    @Override
    public HotelAddress createHotelAddress(HotelAddress hotelAddress) {
        return hotelAddressRepository.save(hotelAddress);
    }

    @Override
    public boolean isAddressPresent(HotelAddress hotelAddress) {
        HotelAddress hotelAddress1 = hotelAddressRepository.findAddressByHotelAddressId(hotelAddress.getHotelAddressId());
        if (hotelAddress1 != null){
            return true;
        }
        return false;
    }

    @Override
    public HotelAddress saveHotelAddress(HotelAddress hotelAddress) {
        return hotelAddressRepository.save(hotelAddress);
    }

    @Override
    public List<HotelAddress> findAllAddress() {
        return hotelAddressRepository.findAll();
    }

    @Override
    public HotelAddress findHotelAddressByHotelId(Long hotelId) {
        return hotelAddressRepository.findAddressByHotelId(hotelId);
    }

    @Override
    public void delete(HotelAddress hotelAddress) {
        hotelAddressRepository.delete(hotelAddress);
    }

    @Override
    public List<HotelAddress> findAddressByCity(String city) {
        return hotelAddressRepository.findAddressByCity(city);
    }

    @Override
    public List<HotelAddress> saveAllAddress(List<HotelAddress> hotelAddresses) {
        return hotelAddressRepository.saveAll(hotelAddresses);
    }

    @Override
    public List<HotelAddress> findAddressByHotelId(Long hotelId) {
        return hotelAddressRepository.findHotelAddressListByHotelId(hotelId);
    }

}
