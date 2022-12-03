package com.updatetech.tripDodol.repository;


import com.updatetech.tripDodol.model.HotelAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelAddressRepository extends JpaRepository<HotelAddress, Long> {
    HotelAddress findAddressByHotelAddressId(Long hotelAddressId);

    HotelAddress findAddressByHotelId(Long hotelId);

    List<HotelAddress> findAddressByCity(String city);

    List<HotelAddress> findHotelAddressListByHotelId(Long hotelId);
}
