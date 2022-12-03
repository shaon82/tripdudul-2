package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.HotelContact;
import com.updatetech.tripDodol.repository.HotelContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelContactServiceImpl implements HotelContactService{

    @Autowired
    private HotelContactRepository hotelContactRepository;

    @Override
    public boolean isHotelContactPresent(HotelContact hotelContact) {
        HotelContact hotelContact1 = hotelContactRepository.findHotelContactByHotelContactId(hotelContact.getHotelContactId());
        if (hotelContact1 != null){
            return true;
        }
        return false;
    }

    @Override
    public HotelContact saveContact(HotelContact hotelContact) {
        return hotelContactRepository.save(hotelContact);
    }

    @Override
    public List<HotelContact> findAllContact() {
        return hotelContactRepository.findAll();
    }

    @Override
    public HotelContact findHotelContactByHotelId(Long hotelId) {
        return hotelContactRepository.findContactByHotelId(hotelId);
    }

    @Override
    public void delete(HotelContact hotelContact) {
        hotelContactRepository.delete(hotelContact);
    }

    @Override
    public List<HotelContact> saveAllContact(List<HotelContact> hotelContacts) {
        return hotelContactRepository.saveAll(hotelContacts);
    }

    @Override
    public List<HotelContact> findContactByHotelId(Long hotelId) {
        return hotelContactRepository.findHotelContactListByHotelId(hotelId);
    }
}
