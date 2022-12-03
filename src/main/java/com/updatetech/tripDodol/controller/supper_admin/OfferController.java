package com.updatetech.tripDodol.controller.supper_admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class OfferController {


    @GetMapping("/offers")
    public String offersPage(){
        return "admin/offers";
    }


    @GetMapping("/addOffers")
    public String offersAddPage(){
        return "admin/offers-add";
    }
}
