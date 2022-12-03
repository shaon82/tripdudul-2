package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.HotelCategory;
import com.updatetech.tripDodol.repository.HotelCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelCategoryServiceImpl implements HotelCategoryService{

    @Autowired
    private HotelCategoryRepository hotelCategoryRepository;

    @Override
    public boolean isHotelCategoryPresent(String hotelCategoryName) {
        HotelCategory hotelCategory = hotelCategoryRepository.findByHotelCategoryName(hotelCategoryName);
        if (hotelCategory != null){
            return true;
        }
        return false;
    }

    @Override
    public List<HotelCategory> findAll() {
        return hotelCategoryRepository.findAll();
    }

    @Override
    public HotelCategory findHotelCategoryByhotelCategoryId(Long hotelCategoryId) {
        Optional<HotelCategory> hotelCategory = hotelCategoryRepository.findById(hotelCategoryId);
        if (hotelCategory.isPresent()){
            return hotelCategory.get();
        }
        return null;
    }

    @Override
    public void delete(HotelCategory hotelCategory) {
        hotelCategoryRepository.delete(hotelCategory);
    }


    @Override
    public HotelCategory createHotelCategory(HotelCategory hotelCategory) {
        return hotelCategoryRepository.save(hotelCategory);
    }
}
