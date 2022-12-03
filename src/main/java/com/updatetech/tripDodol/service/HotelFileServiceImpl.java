package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.HotelFile;
import com.updatetech.tripDodol.repository.HotelFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class HotelFileServiceImpl implements HotelFileService{

    @Autowired
    private HotelFileRepository hotelFileRepository;


    @Override
    public HotelFile saveHotel(HotelFile hotelFile) {
        return hotelFileRepository.save(hotelFile);
    }

    @Override
    public List<HotelFile> findFilesByhotelId(Long hotelId) {
        return hotelFileRepository.findFilesByHotelId(hotelId);
    }

    @Override
    public void deleteFile(HotelFile files) {
        hotelFileRepository.delete(files);
    }

    @Override
    public List<HotelFile> findAllFile() {
        return hotelFileRepository.findAll();
    }

    @Override
    public HotelFile findHotelFileById(Long id) {
        return hotelFileRepository.findHotelFileById(id);
    }

}
