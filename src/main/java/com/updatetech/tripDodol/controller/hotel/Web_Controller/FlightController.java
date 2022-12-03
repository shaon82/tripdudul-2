package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlightController {


    @GetMapping("/search/flight")
    public String flightHomePage(Model model){
        return "web/search-flight";
    }
}
