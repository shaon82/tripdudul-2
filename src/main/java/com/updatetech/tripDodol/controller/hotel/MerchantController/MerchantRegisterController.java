package com.updatetech.tripDodol.controller.hotel.MerchantController;


import com.updatetech.tripDodol.model.Role;
import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.model.UserRole;
import com.updatetech.tripDodol.service.UserService;
import com.updatetech.tripDodol.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/merchant")
public class MerchantRegisterController {

    @Autowired
    private UserService userService;


    @GetMapping("/merchant-register")
    public String registerPage(Model model){
        return "merchant_web/merchant_register";
    }





    @PostMapping("/merchant-register")
    public String register(Model model, @Valid User user, BindingResult result) throws Exception {
        user.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));
        Role role = new Role();
        role.setRoleId(2);
        role.setName("ROLE_MERCHANT");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "web/register";
        }

        if (userService.isUserPresent(user.getEmail())){
            model.addAttribute("the User already exist!", true);
            return "web/register";
        }

        userService.createUser(user, userRoles);
        model.addAttribute("done", true);
        return "web/register";
    }
}
