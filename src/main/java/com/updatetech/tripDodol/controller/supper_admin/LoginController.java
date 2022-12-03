package com.updatetech.tripDodol.controller.supper_admin;


import com.updatetech.tripDodol.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class LoginController {


    @GetMapping("/super-admin/login")
        public String loginPage(Model model, User user){
            return "admin/login";
    }

}
