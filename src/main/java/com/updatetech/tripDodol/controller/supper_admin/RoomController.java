package com.updatetech.tripDodol.controller.supper_admin;


import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.Room;
import com.updatetech.tripDodol.model.RoomCategory;
import com.updatetech.tripDodol.service.HotelService;
import com.updatetech.tripDodol.service.RoomCategoryService;
import com.updatetech.tripDodol.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomCategoryService roomCategoryService;


    @GetMapping("/allTypeHotelRoom")
    public String hotelRoomTypeAllPage(Model model){
        model.addAttribute("roomList", roomService.findAllRoom());
        return "admin/hotel-room-type-all";
    }


    @GetMapping("/addHotelRoomType")
    public String hotelRoomTypeAddPage(Model model, Hotel hotel, RoomCategory roomCategory){
        model.addAttribute("hotel", hotel);
        model.addAttribute("roomCategory", roomCategory);
        model.addAttribute("hotelList", hotelService.findAllHotel());
        model.addAttribute("roomCategoryList", roomCategoryService.findAll());
        return "admin/hotel-room-type-add";
    }


    @PostMapping("/save-room")
    public String saveRoom(Model model, @Valid Room room, BindingResult result){
        room.setActive(true);
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "admin/hotel-room-type-add";
        }else if (roomService.isRoomPresent(room.getId())){
            model.addAttribute("roomExist", true);
        }else {
            roomService.saveRoom(room);
            model.addAttribute("success", room);
        }
        return "redirect:/admin/allTypeHotelRoom";
    }
}
