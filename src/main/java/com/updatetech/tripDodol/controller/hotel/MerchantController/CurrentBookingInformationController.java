package com.updatetech.tripDodol.controller.hotel.MerchantController;


import com.updatetech.tripDodol.model.Booking;
import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.service.HotelBookingService;
import com.updatetech.tripDodol.service.HotelService;
import com.updatetech.tripDodol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/merchant-manager")
public class CurrentBookingInformationController {

    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelBookingService hotelBookingService;


    @GetMapping("/current/booking/information")
    public String bookingInfoPage(Model model, Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        List<Booking> bookings = hotelBookingService.findBookingWithStatus(hotel.getId());
        model.addAttribute("bookings",bookings);
        return "merchant_web/merchant-hotel-room-booking-info";
    }
}
