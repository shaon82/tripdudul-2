package com.updatetech.tripDodol.repository;


import com.updatetech.tripDodol.model.HotelPercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelPercentageRepository extends JpaRepository<HotelPercentage, Long> {
    HotelPercentage findPercentageByHotelId(Long id);
}
