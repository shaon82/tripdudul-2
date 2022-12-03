package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllPackageController {


    @GetMapping("/allPackage")
    public String allPackagePage(){
        return "web/all-package";
    }
}
