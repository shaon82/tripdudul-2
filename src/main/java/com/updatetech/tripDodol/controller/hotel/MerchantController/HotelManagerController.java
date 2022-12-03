package com.updatetech.tripDodol.controller.hotel.MerchantController;


import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.Role;
import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.service.HotelService;
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
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/merchant")
public class HotelManagerController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;


    @GetMapping("/hotel/manager-list")
    public String managerList(Model model, Principal principal){
        String username = principal.getName();
        User user1 = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user1.getHotel().getId());
        List<User>users = userService.findUserByHotelIdAndRole(hotel.getId());
        model.addAttribute("users",users);
        return "merchant_web/manager-list";
    }

    @GetMapping("/add/hotel/manager")
    public String addHotelMerchantManagerPage(Model model){
        return "merchant_web/add-hotel-manager";
    }


    @PostMapping("/add/hotel/manager")
    public String addHotelManager(Model model, @Valid User manager, Principal principal, BindingResult result)throws Exception{
        String username = principal.getName();
        User user1 = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user1.getHotel().getId());
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "merchant_web/add-hotel-manager";
        }else if (userService.isUserPresent(manager.getEmail())){
            model.addAttribute("messes","the user has already been registered!");
        }

        System.out.println("this is hotel id: "+hotel.getId());
        manager.setPassword(SecurityUtility.passwordEncoder().encode(manager.getPassword()));
        manager.setHotel(hotel);
        Role role = new Role();
        role.setRoleId(3);
        role.setName("ROLE_MERCHANT_MANAGER");
            userService.createManager(manager,role);
            model.addAttribute("done", true);
        return "redirect:/merchant/merchant-home";
    }


}
