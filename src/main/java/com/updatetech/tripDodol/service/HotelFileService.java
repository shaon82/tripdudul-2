package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.HotelFile;

import java.util.List;

public interface HotelFileService {
    HotelFile saveHotel(HotelFile hotelFile);

    List<HotelFile> findFilesByhotelId(Long hotelId);

    void deleteFile(HotelFile files);

    List<HotelFile> findAllFile();

    HotelFile findHotelFileById(Long id);
}
