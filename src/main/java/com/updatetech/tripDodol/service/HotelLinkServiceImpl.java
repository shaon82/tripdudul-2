package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.HotelLink;
import com.updatetech.tripDodol.repository.HotelLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelLinkServiceImpl implements HotelLinkService{

    @Autowired
    private HotelLinkRepository hotelLinkRepository;

    @Override
    public boolean isHotelLinkPresent(HotelLink hotelLink) {
        HotelLink hotelLink1 = hotelLinkRepository.findHotelLinkByHotelLinkId(hotelLink.getHotelLinkId());
        if (hotelLink1 != null){
            return true;
        }
        return false;
    }

    @Override
    public HotelLink saveHotel(HotelLink hotelLink) {

        return hotelLinkRepository.save(hotelLink);
    }

    @Override
    public HotelLink findHotelLinkByHotelId(Long hotelId) {
        return hotelLinkRepository.findLinkByHotelId(hotelId);
    }

    @Override
    public void deleteHotelLink(HotelLink hotelLink) {
        hotelLinkRepository.delete(hotelLink);
    }

    @Override
    public List<HotelLink> saveAllLink(List<HotelLink> hotelLinks) {
        return hotelLinkRepository.saveAll(hotelLinks);
    }
}
