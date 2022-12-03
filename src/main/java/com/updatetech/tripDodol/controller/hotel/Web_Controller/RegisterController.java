package com.updatetech.tripDodol.controller.hotel.Web_Controller;


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

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String registerPage(Model model){
        return "web/register";
    }


    @PostMapping("/register")
    public String register(Model model, @Valid User user, BindingResult result) throws Exception {
        user.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));
        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
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
