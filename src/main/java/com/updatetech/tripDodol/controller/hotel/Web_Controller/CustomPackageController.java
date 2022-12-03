package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomPackageController {



    @GetMapping("/customPackage")
    public String customPackagePage(){
        return "web/custom-package";
    }
}
