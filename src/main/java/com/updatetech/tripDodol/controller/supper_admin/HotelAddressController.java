package com.updatetech.tripDodol.controller.supper_admin;


import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.HotelAddress;
import com.updatetech.tripDodol.service.HotelAddressService;
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
public class HotelAddressController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelAddressService hotelAddressService;


    @GetMapping("/hotel-address")
    public String hotelAddressPage(Model model, Hotel hotel){
        model.addAttribute("hotel", hotel);
        model.addAttribute("hotelList", hotelService.findAllHotel());
        return "admin/hotel-address";
    }


    @PostMapping("/save-hotel-address")
    public String saveHotelAddress(Model model, @Valid HotelAddress hotelAddress, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "admin/hotel-address";
        }else if (hotelAddressService.isAddressPresent(hotelAddress)){
            model.addAttribute("hotelAddressExist", true);
        }else {
            hotelAddressService.saveHotelAddress(hotelAddress);
            model.addAttribute("success", true);
        }
        return "redirect:/admin/allHotel";
    }


}
