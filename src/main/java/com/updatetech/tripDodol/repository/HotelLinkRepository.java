package com.updatetech.tripDodol.repository;

import com.updatetech.tripDodol.model.HotelLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelLinkRepository extends JpaRepository<HotelLink, Long> {
    HotelLink findHotelLinkByHotelLinkId(Long hotelLinkId);

    HotelLink findLinkByHotelId(Long hotelId);
}
