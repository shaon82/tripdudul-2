package com.updatetech.tripDodol.controller.supper_admin;


import com.updatetech.tripDodol.model.HotelContact;
import com.updatetech.tripDodol.service.HotelContactService;
import com.updatetech.tripDodol.service.HotelService;
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
public class HotelContactController {

    @Autowired
    private HotelContactService hotelContactService;
    @Autowired
    private HotelService hotelService;


    @GetMapping("/hotel-contact")
    public String showHotelPage(Model model){
        model.addAttribute("hotelList", hotelService.findAllHotel());
        return "admin/hotel-contact";
    }

    @PostMapping("/save-hotel-contact")
    public String saveContact(Model model, @Valid HotelContact hotelContact, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "admin/hotel-contact";
        }else if (hotelContactService.isHotelContactPresent(hotelContact)){
            model.addAttribute("hotelContactExist", true);
        }else {
            hotelContactService.saveContact(hotelContact);
            model.addAttribute("success", true);
            model.addAttribute("hotelContact", hotelContact);
        }

        return "redirect:/admin/hotel-link";
    }


}
