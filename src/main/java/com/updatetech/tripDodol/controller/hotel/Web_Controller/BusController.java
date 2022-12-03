package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BusController {


    @GetMapping("/search/bus")
    public String flightHomePage(Model model){
        return "web/search-bus";
    }
}
