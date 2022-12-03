package com.updatetech.tripDodol.controller.supper_admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class SightController {



    @GetMapping("/allSightSee")
    public String sightSeeAllPage(){
        return "admin/sight-see-all";
    }


    @GetMapping("/addSightSee")
    public String sightSeeAddPage(){
        return "admin/sight-see-add";
    }
}
