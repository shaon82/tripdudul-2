package com.updatetech.tripDodol.controller.packageUserController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/web-user")
public class PackageUserProfileController {

    @GetMapping("/profile")
    public String packageUserProfilePage(Model model, Principal principal){
        String username = principal.getName();
        model.addAttribute("username", username);
        return "web/package-user-profile";
    }
}
