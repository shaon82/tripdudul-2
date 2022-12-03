package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.Room;
import com.updatetech.tripDodol.model.RoomCategory;
import com.updatetech.tripDodol.model.RoomFile;
import com.updatetech.tripDodol.repository.RoomRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{


    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomFileService roomFileService;
    @Autowired
    private FilePathService filePathService;
    @Autowired
    private RoomCategoryService roomCategoryService;

    @Override
    public boolean isRoomPresent(Long roomId) {
        Room room = roomRepository.findRoomById(roomId);
        if (room != null){
            return true;
        }
        return false;
    }

    @Override
    public Room saveRoom(Room room) {
        Room room1 = roomRepository.save(room);
        if (room1.getRoomFiles() != null && room1.getRoomFiles().size()>0) {
            for (MultipartFile file : room1.getRoomFiles()) {
                String fileName = file.getOriginalFilename();
                String fileExtension = FilenameUtils.getExtension(fileName);
                String modifiedFileName = FilenameUtils.getBaseName(fileName)+"_"+room1.getId()+"."+fileExtension;
                File storeRoomFile = filePathService.getRoomFilePath(modifiedFileName, "roomImage");

                if (storeRoomFile != null){
                    try {
                        FileUtils.writeByteArrayToFile(storeRoomFile, file.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                RoomFile roomFile = new RoomFile();
                roomFile.setModifiedfileName(modifiedFileName);
                roomFile.setFileExtension(fileExtension);
                roomFile.setFileName(fileName);
                roomFile.setRoom(room1);

                roomFileService.saveFile(roomFile);
            }
        }

        return room1;
    }

    @Override
    public List<Room> findAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> findRoomByHotelId(Long hotelId) {
        return roomRepository.findRoomByHotelId(hotelId);
    }

    @Override
    public Room findRoomByRoomId(Long roomId) {
        return roomRepository.findRoomById(roomId);
    }

    @Override
    public List<Room> findRoomByRoomCategoryId(Long roomCategoryId) {
        RoomCategory roomCategory = roomCategoryService.findRoomCategoryByroomCategoryId(roomCategoryId);
        return roomRepository.findRoomByRoomCategoryId(roomCategory.getId());
    }

    @Override
    public void deleteRoom(Room room) {
        roomRepository.delete(room);
    }
}
