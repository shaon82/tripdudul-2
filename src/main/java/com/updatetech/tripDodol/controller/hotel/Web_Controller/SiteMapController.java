package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteMapController {


    @GetMapping("/siteMap")
    public String siteMapPage(){
        return "web/sitemap";
    }
}
