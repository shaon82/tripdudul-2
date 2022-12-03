package com.updatetech.tripDodol.repository;


import com.updatetech.tripDodol.model.HotelFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelFileRepository extends JpaRepository<HotelFile, Long> {
    List<HotelFile> findFilesByHotelId(Long hotelId);

    HotelFile findHotelFileById(Long id);
}
