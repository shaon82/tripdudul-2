package com.updatetech.tripDodol.controller.supper_admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class EventController {


    @GetMapping("/allEvent")
    public String eventAllPage(){
        return "admin/event-all";
    }



    @GetMapping("/addEvent")
    public String eventAddPage(){
        return "admin/event-add";
    }
}
