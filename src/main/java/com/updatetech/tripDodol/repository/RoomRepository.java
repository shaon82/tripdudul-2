package com.updatetech.tripDodol.repository;


import com.updatetech.tripDodol.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findRoomById(Long id);

    List<Room> findRoomByHotelId(Long hotelId);

    List<Room> findRoomByRoomCategoryId(Long id);
}
