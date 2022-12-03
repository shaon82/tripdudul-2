package com.updatetech.tripDodol.controller.packageUserController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/package")
public class PackageUserLoginController {

    @GetMapping("/package-login")
    public String packageUserLoginPage(){
        return "web/package-login";
    }
}
