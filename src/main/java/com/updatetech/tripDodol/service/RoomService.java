package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.Room;

import java.util.List;

public interface RoomService {
    boolean isRoomPresent(Long roomId);

    Room saveRoom(Room room);

    List<Room> findAllRoom();

    List<Room> findRoomByHotelId(Long hotelId);

    Room findRoomByRoomId(Long roomId);

    List<Room> findRoomByRoomCategoryId(Long roomCategoryId);

    void deleteRoom(Room room);
}
