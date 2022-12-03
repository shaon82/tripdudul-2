package com.updatetech.tripDodol.controller.supper_admin;


import com.updatetech.tripDodol.model.HotelCategory;
import com.updatetech.tripDodol.model.RoomCategory;
import com.updatetech.tripDodol.service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class RoomCategoryController {

    @Autowired
    private RoomCategoryService roomCategoryService;


    @GetMapping("/add-room-category")
    public String addRoomCategoryPage(Model model){
        model.addAttribute("roomCategory", new RoomCategory());
        return "admin/room-cat-add";
    }



    @PostMapping("/add-room-category")
    public String addRoomCategory(Model model, @Valid RoomCategory roomCategory, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "admin/room-cat-add";
        }else if(roomCategoryService.isRoomCategoryPresent(roomCategory.getRoomCategoryName())){
            model.addAttribute("roomCategoryExist", true);
        }
        roomCategoryService.saveRoom(roomCategory);
        return "redirect:/admin/room-cat-all";
    }


    @GetMapping("/room-cat-all")
    public String roomCategoryAllPage(Model model){
        model.addAttribute("roomAllCategory", roomCategoryService.findAll());
        return "admin/room-cat-all";
    }


    @GetMapping("/delete-room-category/{roomCategoryId}")
    public String deleteRoomCategory(Model model,@PathVariable("roomCategoryId") Long roomCategoryId){
        RoomCategory roomCategory = roomCategoryService.findRoomCategoryByroomCategoryId(roomCategoryId);
        model.addAttribute("roomCategory", roomCategory);
        roomCategoryService.deleteRoomCategory(roomCategory);
        return "redirect:/admin/room-cat-all";
    }
}
