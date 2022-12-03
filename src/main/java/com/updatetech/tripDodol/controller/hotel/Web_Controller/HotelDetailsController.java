package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import com.updatetech.tripDodol.model.*;
import com.updatetech.tripDodol.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HotelDetailsController {


    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelAddressService hotelAddressService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomFileService roomFileService;
    @Autowired
    private HotelBookingService hotelBookingService;
    @Autowired
    private HotelFileService hotelFileService;


//    @GetMapping("/hotelDetails/{hotelId}")
//    public String hotelDetailsPage(Model model, @PathVariable("hotelId") Long hotelId){
//        Hotel hotel = hotelService.findHotelById(hotelId);
//        HotelAddress hotelAddress = hotelAddressService.findHotelAddressByHotelId(hotelId);
//        List<Room>rooms=roomService.findRoomByHotelId(hotelId);
//        List<RoomFile>roomFiles = roomFileService.findAll();
//        model.addAttribute("hotel", hotel);
//        model.addAttribute("address", hotelAddress);
//        model.addAttribute("rooms", rooms);
//        model.addAttribute("roomFiles",roomFiles);
//
//        return "web/hotel-details";
//    }


    @GetMapping("/hotelDetails/{hotelId}")
    public String hotelDetails(Model model, @PathVariable("hotelId") Long hotelId, HttpSession session){
        session.setAttribute("hotelId", hotelId);
        Hotel hotel = hotelService.findHotelById(hotelId);
        List<HotelFile> hotelFiles = hotelFileService.findFilesByhotelId(hotelId);
        HotelAddress hotelAddress = hotelAddressService.findHotelAddressByHotelId(hotelId);
        List<Room>rooms=roomService.findRoomByHotelId(hotelId);
        List<Room>roomList = new ArrayList<>();
        List<RoomFile>roomFilesList= new ArrayList<>();

        for (Room room: rooms){
            if (room.isActive()== true){
                roomList.add(room);
            }
                List<RoomFile>roomFiles = roomFileService.findFileByRoomId(room.getId());
                roomFilesList.addAll(roomFiles);
        }
        model.addAttribute("hotel", hotel);
        model.addAttribute("address", hotelAddress);
        model.addAttribute("rooms", roomList);
        model.addAttribute("roomFiles",roomFilesList);
        return "web/hotel-details";
    }
}
