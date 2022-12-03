package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForgotPassController {



    @GetMapping("/forgotPass")
    public String forgotPassPage(){
        return "web/forgot-pass";
    }
}
