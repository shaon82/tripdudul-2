package com.updatetech.tripDodol.controller.hotel.Web_Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPackageController {
	
	@GetMapping("/search/package")
    public String hotelHomePage(Model model){
        return "web/search-package";
    }

}
