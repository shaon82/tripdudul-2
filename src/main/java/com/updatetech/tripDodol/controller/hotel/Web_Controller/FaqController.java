package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaqController {



    @GetMapping("/faq")
    public String faqPage(){
        return "web/faq";
    }
}
