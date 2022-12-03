package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeekendPageController {


    @GetMapping("/weekendPackage")
    public String weekendPackagePage(){
        return "web/weekend-package";
    }
}
