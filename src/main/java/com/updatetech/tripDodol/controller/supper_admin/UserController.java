package com.updatetech.tripDodol.controller.supper_admin;


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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class UserController {


    @Autowired
    private UserService userService;



    @GetMapping("/add-user")
    public String addUserPage(){
        return "admin/user-add";
    }


    @PostMapping("/add-user")
    public String createUser(Model model, @Valid User user, BindingResult result) throws Exception {
        user.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));
        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "admin/user-add";
        }

        if (userService.isUserPresent(user.getEmail())){
            model.addAttribute("the User already exist!", true);
            return "admin/user-add";
        }

        userService.saveUser(user, role);
        model.addAttribute("done", true);
        return "admin/user-add";
    }



    @GetMapping("/alluser")
    public String allUserPage(Model model){
        model.addAttribute("users", userService.findAll());
        return "admin/user-all";
    }



    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model){
        User user = userService.findUserById(id);
        userService.delete(user);
        return "redirect:/admin/alluser";
    }
}
