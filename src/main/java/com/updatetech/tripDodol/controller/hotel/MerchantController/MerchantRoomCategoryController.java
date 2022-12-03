package com.updatetech.tripDodol.controller.hotel.MerchantController;


import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.RoomCategory;
import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.service.HotelService;
import com.updatetech.tripDodol.service.RoomCategoryService;
import com.updatetech.tripDodol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/merchant")
public class MerchantRoomCategoryController {

    @Autowired
    private RoomCategoryService roomCategoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;


    @GetMapping("/all/room-category")
    public String getAllRoomCategory(Model model, Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());

        System.out.println("this is hotel id: "+hotel.getId());
        List<RoomCategory>roomCategories = roomCategoryService.findRoomCategoryByHotelId(hotel.getId());
        model.addAttribute("roomCategories",roomCategories);
        return "merchant_web/room-cat-all";
    }



        @GetMapping("/add/room-category")
    public String merchantRoomCategoryPage(Model model){
            model.addAttribute("roomCategory", new RoomCategory());
            return "merchant_web/merchant-room-cat-add";
        }


        @PostMapping("/add/room-category")
    public String addRoomCategory(Model model, @Valid RoomCategory roomCategory, BindingResult result, Principal principal){
            String username = principal.getName();
            User user = userService.findUserByUsername(username);
            Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
            roomCategory.setHotel(hotel);
            if (result.hasErrors()){
                model.addAttribute("error", true);
                return "merchant_web/merchant-room-cat-add";
            }else if(roomCategoryService.isRoomCategoryPresent(roomCategory.getRoomCategoryName())){
                model.addAttribute("roomCategoryExist", true);
            }
            roomCategoryService.saveRoom(roomCategory);
            return "redirect:/merchant/all/room-category";
        }



        @GetMapping("/update/room-category/{roomCategoryId}")
    public String updateRoomCategory(Model model, @PathVariable("roomCategoryId") Long roomCategoryId){
        RoomCategory roomCategory = roomCategoryService.findRoomCategoryByroomCategoryId(roomCategoryId);
        model.addAttribute("roomCategory",roomCategory);
        return "merchant_web/merchant-room-cat-update";
        }


        @PostMapping("/update/room-category/{roomCategoryId}")
    public String updateRoomCategory(Model model, @PathVariable("roomCategoryId") Long roomCategoryId, RoomCategory roomCategory,
                                     BindingResult result, Principal principal){
            String username = principal.getName();
            User user = userService.findUserByUsername(username);
            Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
            roomCategory.setHotel(hotel);

            if (result.hasErrors()){
                model.addAttribute("error", true);
                return "merchant_web/merchant-room-cat-update";
            }else {
                roomCategoryService.saveRoom(roomCategory);
                model.addAttribute("success", true);
            }

            return "redirect:/merchant/all/room-category";
        }


        @GetMapping("/delete/room-category/{roomCategoryId}")
    public String deleteRoomCategory(@PathVariable("roomCategoryId") Long roomCategoryId){
        RoomCategory roomCategory = roomCategoryService.findRoomCategoryByroomCategoryId(roomCategoryId);
        roomCategoryService.deleteRoomCategory(roomCategory);
            return "redirect:/merchant/all/room-category";
        }
}
