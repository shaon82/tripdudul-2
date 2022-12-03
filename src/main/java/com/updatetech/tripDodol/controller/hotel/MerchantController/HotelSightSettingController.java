package com.updatetech.tripDodol.controller.hotel.MerchantController;


import com.updatetech.tripDodol.model.*;
import com.updatetech.tripDodol.service.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/merchant")
public class HotelSightSettingController {
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelFileService hotelFileService;
    @Autowired
    private RoomFileService roomFileService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private FilePathService filePathService;


    @GetMapping("/all/hotel-photo")
    public String allHotelPhoto(Model model, Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        List<HotelFile>hotelFiles = hotelFileService.findFilesByhotelId(hotel.getId());
        model.addAttribute("hotelFiles",hotelFiles);
        return "merchant_web/hotel-all-photo";
    }

    @GetMapping("/add/hotel/photo")
    private String addHotelPhotoPage(Model model){
        return "merchant_web/add-hotel-photo";
    }


    @PostMapping("/add/hotel/photo")
    private String addHotelPhoto(Model model, Principal principal, @Valid Hotel hotel, BindingResult result){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel1 = hotelService.findHotelByUser(user.getHotel().getId());
        hotel1.setFiles(hotel.getFiles());
        hotelService.createHotel(hotel1);
        return "redirect:/merchant/merchant-home";
    }

    @GetMapping("/edit/hotel/photo/{id}")
    public String editHotelPhoto(Model model, @PathVariable("id") Long id){
        HotelFile hotelFile = hotelFileService.findHotelFileById(id);
        model.addAttribute("hotelFile",hotelFile);
        return "merchant_web/edit-hotel-photo";
    }


    @GetMapping("/delete/hotel-photo/{id}")
    public String deleteHotelPhoto(Model model, @PathVariable("id") Long id){
        String filePath = "/home/shaon/updateTech/tripDodol/src/main/webapp/hotelImage/";
        HotelFile hotelFile = hotelFileService.findHotelFileById(id);
        File file = new File(filePath+hotelFile.getModifiedfileName());
        if (file.exists()){
            file.delete();
        }
        hotelFileService.deleteFile(hotelFile);
        model.addAttribute("hotelFile",hotelFile);
        return "redirect:/merchant/all/hotel-photo";
    }


    @GetMapping("/hotel/room/photo/all")
    public String allRoomPhoto(Model model, Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel1 = hotelService.findHotelByUser(user.getHotel().getId());
        List<Room>rooms = roomService.findRoomByHotelId(hotel1.getId());
        List<RoomFile>roomFileList = new ArrayList<>();
        for (Room room: rooms){
            List<RoomFile>roomFiles = roomFileService.findFileByRoomId(room.getId());
            roomFileList.addAll(roomFiles);
        }
        model.addAttribute("roomFileList", roomFileList);
        return "merchant_web/room-photo-all";
    }

    @GetMapping("/change/room/image/{id}")
    public String changeRoomPhoto(Model model, @PathVariable("id") Long id, HttpSession session){
        String filePath = "/home/shaon/updateTech/tripDodol/src/main/webapp/roomImage/";
        RoomFile roomFile = roomFileService.findFileById(id);
        Room room = roomService.findRoomByRoomId(roomFile.getRoom().getId());
        session.setAttribute("roomId",room.getId());
        File file= new File(filePath+roomFile.getModifiedfileName());
        if (file.exists()){
            file.delete();
        }
        roomFileService.deleteFile(roomFile);
        model.addAttribute("room",room);
        return "merchant_web/add-room-file";
    }
    @PostMapping("/save/room/file")
    public String savePhoto(Model model, @Valid MultipartFile file, BindingResult result, HttpSession session){
        Long roomId = (Long) session.getAttribute("roomId");
        Room room = roomService.findRoomByRoomId(roomId);
        String fileName = file.getOriginalFilename();
        String fileExtension = FilenameUtils.getExtension(fileName);
        String modifiedFileName = "RoomImage"+"_"+room.getId()+"."+fileExtension;
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
        roomFile.setRoom(room);
        roomFileService.saveFile(roomFile);
        return "redirect:/merchant/hotel/room/photo/all";
    }

}
