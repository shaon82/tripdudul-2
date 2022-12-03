package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.HotelLink;

import java.util.List;

public interface HotelLinkService {
    boolean isHotelLinkPresent(HotelLink hotelLink);

    HotelLink saveHotel(HotelLink hotelLink);

    HotelLink findHotelLinkByHotelId(Long hotelId);
    void deleteHotelLink(HotelLink hotelLink);

    List<HotelLink> saveAllLink(List<HotelLink> hotelLinks);
}
