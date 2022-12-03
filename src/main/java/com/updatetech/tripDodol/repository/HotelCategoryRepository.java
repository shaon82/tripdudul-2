package com.updatetech.tripDodol.repository;

import com.updatetech.tripDodol.model.HotelCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelCategoryRepository extends JpaRepository<HotelCategory, Long> {



    HotelCategory findByHotelCategoryName(String hotelCategoryName);

    HotelCategory findHotelCategoryByhotelCategoryId(Long hotelCategoryId);
}
