package com.updatetech.tripDodol.controller.hotel.MerchantController;


import com.updatetech.tripDodol.model.*;
import com.updatetech.tripDodol.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/merchant")
public class MerchantRoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomCategoryService roomCategoryService;
    @Autowired
    private RoomFileService roomFileService;
    @Autowired
    private HotelBookingService hotelBookingService;
    @Autowired
    private UserService userService;


    @GetMapping("/room-list")
    public String hotelRoomTypeAllPage(Model model, HttpSession session, Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        Long hotelId= hotel.getId();
        List<Room>rooms = roomService.findRoomByHotelId(hotelId);
        List<RoomFile>roomFiles = new ArrayList<>();
        for (Room room: rooms){
            List<RoomFile>roomFileList = roomFileService.findFileByRoomId(room.getId());
            roomFiles.add(roomFileList.get(0));
        }
        model.addAttribute("roomList",rooms);
        model.addAttribute("roomFiles", roomFiles);
        return "merchant_web/merchant-hotel-room-all";
    }


    @GetMapping("/add/room")
    public String hotelRoomTypeAddPage(Model model, Hotel hotel, RoomCategory roomCategory,Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel1 = hotelService.findHotelByUser(user.getHotel().getId());
        List<RoomCategory>roomCategories = roomCategoryService.findRoomCategoryByHotelId(hotel1.getId());
        model.addAttribute("hotel", hotel);
        model.addAttribute("roomCategory", roomCategory);
        model.addAttribute("hotelList", hotelService.findAllHotel());
        model.addAttribute("roomCategoryList",roomCategories );
        return "merchant_web/merchant-hotel-room-add";
    }


    @PostMapping("/save-room")
    public String saveRoom(Model model, @Valid Room room, BindingResult result, HttpSession session){
        Long hotelId = (Long) session.getAttribute("hotelId");
        Hotel hotel = hotelService.findHotelById(hotelId);
        System.out.println("this is hotel name: "+hotel.getHotelName());
        room.setHotel(hotel);
        room.setActive(true);
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "merchant_web/merchant-hotel-room-add";
        }else if (roomService.isRoomPresent(room.getId())){
            model.addAttribute("roomExist", true);
        }else {
            roomService.saveRoom(room);
            model.addAttribute("success", room);
        }
        return "redirect:/merchant/room-list";
    }

    @GetMapping("/update/room/{id}")
    public String updateRoomPage(Model model, @PathVariable("id") Long id){
        Room room = roomService.findRoomByRoomId(id);
        model.addAttribute("room",room);
        model.addAttribute("roomCategoryList", roomCategoryService.findAll());
        return "merchant_web/merchant-hotel-room-update";
    }


    @PostMapping("/update/room/{id}")
    public String updateRoom(Model model, @Valid Room room, BindingResult result, HttpSession session){
        Long hotelId = (Long) session.getAttribute("hotelId");
        Hotel hotel = hotelService.findHotelById(hotelId);
        room.setHotel(hotel);
        room.setActive(true);
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "merchant_web/merchant-hotel-room-add";
        }else if (roomService.isRoomPresent(room.getId())){
            model.addAttribute("roomExist", true);
        }else {
            roomService.saveRoom(room);
            model.addAttribute("success", room);
        }
        return "redirect:/merchant/room-list";
    }


    @GetMapping("/delete/room/{id}")
    public String deleteRoom(Model model, @PathVariable("id") Long id){
        Room room = roomService.findRoomByRoomId(id);
        List<RoomFile>files = roomFileService.findFileByRoomId(id);
        for (RoomFile roomFile: files){
            File file = new File("/home/shaon/updateTech/tripDodol/src/main/webapp/roomImage/"+roomFile.getModifiedfileName());
            if (file.exists()){
                file.delete();
            }
            roomFileService.deleteFile(roomFile);
        }
        roomService.deleteRoom(room);

        return "redirect:/merchant/room-list";
    }


}
