package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebEventController {



    @GetMapping("/webEvent")
    public String webEventPage(){
        return "web/events";
    }
}
