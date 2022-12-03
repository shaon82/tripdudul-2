package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.Room;
import com.updatetech.tripDodol.model.RoomFile;
import com.updatetech.tripDodol.repository.RoomFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomFileServiceImpl implements RoomFileService{

    @Autowired
    private RoomFileRepository roomFileRepository;



    @Override
    public RoomFile saveFile(RoomFile roomFile) {
        return roomFileRepository.save(roomFile);
    }

    @Override
    public List<RoomFile> findAll() {
        return roomFileRepository.findAll();
    }

    @Override
    public List<RoomFile> findFileByRoomId(Long roomId) {
        return roomFileRepository.findFilesByRoomId(roomId);
    }

    @Override
    public void deleteFile(RoomFile roomFile) {
        roomFileRepository.delete(roomFile);
    }

    @Override
    public RoomFile findFileById(Long id) {
        return roomFileRepository.findFileById(id);
    }

}
