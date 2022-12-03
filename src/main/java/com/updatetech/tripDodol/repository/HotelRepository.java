package com.updatetech.tripDodol.repository;


import com.updatetech.tripDodol.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Hotel findHotelByHotelName(String hotelName);

    Hotel findHotelById(Long hotelId);

    @Query(value = "select * from hotel h " +
            "join hotel_hotel_addresses b " +
            "   on h.id = b.hotel_id " +
            "join hotel_address c " +
            "   on c.hotel_address_id = b.hotel_addresses_hotel_address_id " +
            "where c.city = :city", nativeQuery = true)
    List<Hotel>searchHotelWithCity(String city);

    @Query(value = "select * from hotel where hotel_status='ACTIVE'", nativeQuery = true)
    List<Hotel> findAllHotelByStatus();

    @Query(value = "select * from hotel where hotel_status='PENDING'", nativeQuery = true)
    List<Hotel>findAllPendingHotels();
}
