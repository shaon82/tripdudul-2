package com.updatetech.tripDodol.controller.supper_admin;


import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.HotelPercentage;
import com.updatetech.tripDodol.service.HotelPercentageService;
import com.updatetech.tripDodol.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminPercentageController {
    @Autowired
    private HotelPercentageService hotelPercentageService;
    @Autowired
    private HotelService hotelService;

    @PostMapping("/add/hotel/percentage/{hotelId}")
    public String addHotelPercentage(Model model, @Valid HotelPercentage hotelPercentage, @PathVariable("hotelId") Long hotelId, BindingResult result){
        Hotel hotel = hotelService.findHotelById(hotelId);
        hotelPercentage.setHotel(hotel);
        hotelPercentageService.savePercentage(hotelPercentage);
        return "redirect:/admin/allHotel";
    }
}
