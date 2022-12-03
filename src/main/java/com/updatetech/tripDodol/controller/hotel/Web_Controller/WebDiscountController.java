package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebDiscountController {



    @GetMapping("/webDiscount")
    public String webDiscountPage(){
        return "web/discount";
    }
}
