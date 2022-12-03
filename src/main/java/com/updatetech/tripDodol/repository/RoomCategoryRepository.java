package com.updatetech.tripDodol.repository;


import com.updatetech.tripDodol.model.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Long> {
    RoomCategory findRoomCategoryByRoomCategoryName(String roomCategoryName);

    RoomCategory findRoomCategoryById(Long id);

    List<RoomCategory> findRoomCategoryByHotelId(Long id);
}
