package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.Guest;

import java.util.List;

public interface GuestService {
    Guest save(Guest guest);

    List<Guest> findAll();

    Iterable<Guest> saveAll(List<Guest> guests);
}
