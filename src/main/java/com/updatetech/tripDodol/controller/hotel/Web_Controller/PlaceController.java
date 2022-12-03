package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaceController {


    @GetMapping("/place")
    public String placePage(){
        return "web/places";
    }



    @GetMapping("/place1")
    public String place1Page(){
        return "web/places-1";
    }



    @GetMapping("/place2")
    public String place2Page(){
        return "web/places-2";
    }
}
