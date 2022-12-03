package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.RoomCategory;
import com.updatetech.tripDodol.repository.RoomCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomCategoryServiceImpl implements RoomCategoryService{

    @Autowired
    private RoomCategoryRepository roomCategoryRepository;

    @Override
    public boolean isRoomCategoryPresent(String roomCategoryName) {
        RoomCategory roomCategory = roomCategoryRepository.findRoomCategoryByRoomCategoryName(roomCategoryName);
        if (roomCategory != null){
            return true;
        }
        return false;
    }

    @Override
    public RoomCategory saveRoom(RoomCategory roomCategory) {
        return roomCategoryRepository.save(roomCategory);
    }

    @Override
    public List<RoomCategory> findAll() {
        return roomCategoryRepository.findAll();
    }

    @Override
    public RoomCategory findRoomCategoryByroomCategoryId(Long roomCategoryId) {
        return roomCategoryRepository.findRoomCategoryById(roomCategoryId);
    }

    @Override
    public void deleteRoomCategory(RoomCategory roomCategory) {
        roomCategoryRepository.delete(roomCategory);

    }

    @Override
    public List<RoomCategory> findRoomCategoryByHotelId(Long id) {
        return roomCategoryRepository.findRoomCategoryByHotelId(id);
    }


}
