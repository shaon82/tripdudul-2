package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebLoginController {


    @GetMapping("/webLogin")
    public String customerLoginPage(){
        return "web/login";
    }
}
