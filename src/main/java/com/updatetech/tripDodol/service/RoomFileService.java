package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.RoomFile;

import java.util.List;

public interface RoomFileService {

    RoomFile saveFile(RoomFile roomFile);


    List<RoomFile> findAll();

    List<RoomFile> findFileByRoomId(Long roomId);

    void deleteFile(RoomFile roomFile);

    RoomFile findFileById(Long id);
}
