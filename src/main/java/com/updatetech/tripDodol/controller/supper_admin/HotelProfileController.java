package com.updatetech.tripDodol.controller.supper_admin;


import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.HotelAddress;
import com.updatetech.tripDodol.model.HotelContact;
import com.updatetech.tripDodol.model.Room;
import com.updatetech.tripDodol.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class HotelProfileController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelCategoryService hotelCategoryService;
    @Autowired
    private HotelFileService hotelFileService;
    @Autowired
    private HotelAddressService hotelAddressService;
    @Autowired
    private HotelContactService hotelContactService;
    @Autowired
    private HotelLinkService hotelLinkService;
    @Autowired
    private RoomService roomService;


    @GetMapping("/hotel/profile/{id}")
    public String hotelProfile(Model model, @PathVariable("id") Long hotelId){
        Hotel hotel = hotelService.findHotelById(hotelId);
        List<HotelContact> hotelContactList = hotelContactService.findContactByHotelId(hotelId);
        List<HotelAddress>hotelAddressList = hotelAddressService.findAddressByHotelId(hotelId);
        List<Room>roomList = roomService.findRoomByHotelId(hotelId);
        model.addAttribute("hotel", hotel);
        model.addAttribute("hotelContactList",hotelContactList);
        model.addAttribute("hotelAddressList",hotelAddressList);
        model.addAttribute("roomList",roomList);

        return "admin/hotel-profile";
    }
}
