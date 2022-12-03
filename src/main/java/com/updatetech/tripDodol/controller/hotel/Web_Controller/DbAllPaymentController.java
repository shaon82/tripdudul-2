package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DbAllPaymentController {


    @GetMapping("/dbAllPayment")
    public String dbAllPaymentPage(){
        return "web/db-all-payment";
    }
}
