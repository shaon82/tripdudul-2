package com.updatetech.tripDodol.repository;


import com.updatetech.tripDodol.model.HotelContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelContactRepository extends JpaRepository<HotelContact, Long> {
    HotelContact findHotelContactByHotelContactId(Long hotelContactId);

    HotelContact findContactByHotelId(Long hotelId);

    List<HotelContact> findHotelContactListByHotelId(Long hotelId);
}
