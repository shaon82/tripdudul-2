package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.RoomCategory;

import java.util.List;

public interface RoomCategoryService {
    boolean isRoomCategoryPresent(String roomCategoryName);

    RoomCategory saveRoom(RoomCategory roomCategory);

    List<RoomCategory> findAll();

    RoomCategory findRoomCategoryByroomCategoryId(Long roomCategoryId);

    void deleteRoomCategory(RoomCategory roomCategory);

    List<RoomCategory> findRoomCategoryByHotelId(Long id);
}
