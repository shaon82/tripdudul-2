package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.HotelContact;

import java.util.List;

public interface HotelContactService {
    boolean isHotelContactPresent(HotelContact hotelContact);

    HotelContact saveContact(HotelContact hotelContact);

    List<HotelContact> findAllContact();

    HotelContact findHotelContactByHotelId(Long hotelId);

    void delete(HotelContact hotelContact);

    List<HotelContact> saveAllContact(List<HotelContact> hotelContacts);

    List<HotelContact> findContactByHotelId(Long hotelId);
}
