package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import com.updatetech.tripDodol.service.package_service.TripPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebHomeController {

    @Autowired
    private TripPackageService tripPackageService;

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("packageList", tripPackageService.findAllPackage());
        return "web/main";
    }
}
