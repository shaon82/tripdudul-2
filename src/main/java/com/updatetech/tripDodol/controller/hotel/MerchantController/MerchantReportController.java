package com.updatetech.tripDodol.controller.hotel.MerchantController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/merchant")
public class MerchantReportController {



    @GetMapping("/booking/report")
    public String showReport(){
        return "merchant_web/merchant-invoice";
    }
}
