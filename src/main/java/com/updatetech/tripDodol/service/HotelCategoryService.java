package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.HotelCategory;

import java.util.List;

public interface HotelCategoryService {

    HotelCategory createHotelCategory(HotelCategory hotelCategory);

    boolean isHotelCategoryPresent(String hotelCategoryName);


    List<HotelCategory> findAll();

    HotelCategory findHotelCategoryByhotelCategoryId(Long hotelCategoryId);

    void delete(HotelCategory hotelCategory);
}
