package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FamilyPackageController {



    @GetMapping("/familyPackage")
    public String familyPackagePage(){
        return "web/family-package";
    }
}
