package com.updatetech.tripDodol.controller.hotel.MerchantController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/package")
public class Testcontroller {

    @GetMapping("/testing")
    public String shoPage(){
        return "test";
    }
}
