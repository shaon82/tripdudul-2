package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DbTravelBookingController {



    @GetMapping("/dbTravelBooking")
    public String dbTravelBookingPage(){
        return "web/db-travel-booking";
    }


    @GetMapping("/dbTravelDetails")
    public String dbTravelDetailsPage(){
        return "web/db-travel-details";
    }

}
