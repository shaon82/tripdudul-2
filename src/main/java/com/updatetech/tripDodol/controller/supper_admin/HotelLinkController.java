package com.updatetech.tripDodol.controller.supper_admin;


import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.HotelLink;
import com.updatetech.tripDodol.service.HotelLinkService;
import com.updatetech.tripDodol.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
@SessionAttributes("hotelList")
public class HotelLinkController {

    @Autowired
    private HotelLinkService hotelLinkService;
    @Autowired
    private HotelService hotelService;


    @GetMapping("/hotel-link")
    public String hotelLinkPage(Model model, Hotel hotel){
        model.addAttribute("hotelList", hotelService.findAllHotel());
        return "admin/hotel-link";
    }


    @PostMapping("/save-hotel-link")
    public String saveHotelLink(Model model, @Valid HotelLink hotelLink, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "hotel-add";
        }

        if (hotelLinkService.isHotelLinkPresent(hotelLink)){
            model.addAttribute("linkExist", true);
        }else {
            hotelLinkService.saveHotel(hotelLink);
            model.addAttribute("success", true);
            model.addAttribute("hotelLink", hotelLink);
        }
        return "redirect:/admin/hotel-address";
    }



}
