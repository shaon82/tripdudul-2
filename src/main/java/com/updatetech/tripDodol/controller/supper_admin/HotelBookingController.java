package com.updatetech.tripDodol.controller.supper_admin;


import com.updatetech.tripDodol.model.Booking;
import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.Room;
import com.updatetech.tripDodol.service.HotelBookingService;
import com.updatetech.tripDodol.service.HotelService;
import com.updatetech.tripDodol.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class HotelBookingController {


    @Autowired
    private HotelBookingService hotelBookingService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;


    @GetMapping("/all-hotel-booking")
    public String hotelBookingPageAllPage(Model model, HttpSession session){
        List<Booking>bookings = hotelBookingService.findAll();
        Set<Hotel>hotels = new HashSet<>();
        for (Booking booking: bookings){
            Hotel hotel= hotelService.findHotelById(booking.getHotelId());
            hotels.add(hotel);
        }
        model.addAttribute("bookings", bookings);
        model.addAttribute("hotels", hotels);
        return "admin/hotel-booking-all";
    }


    @GetMapping("/packageBookingAll")
    public String packageBookingAllPage(){
        return "admin/package-booking-all";
    }


    @GetMapping("/sightSeeBookingAll")
    public String sightSeeBookingAllPage(){
        return "admin/sight-see-booking-all";
    }



    @GetMapping("/eventBookingAll")
    public String eventBookingAllPage(){
        return "admin/event-booking-all";
    }
}
