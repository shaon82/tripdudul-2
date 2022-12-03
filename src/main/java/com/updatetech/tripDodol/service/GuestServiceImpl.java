package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.Guest;
import com.updatetech.tripDodol.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImpl implements GuestService{

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @Override
    public Iterable<Guest> saveAll(List<Guest> guests) {
        return guestRepository.saveAll(guests);
    }
}
