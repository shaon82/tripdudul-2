package com.updatetech.tripDodol.controller.supper_admin;


import com.updatetech.tripDodol.model.*;
import com.updatetech.tripDodol.repository.RoleRepository;
import com.updatetech.tripDodol.service.HotelCategoryService;
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
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminMerchentController {

    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private HotelCategoryService hotelCategoryService;

    @GetMapping("/add-merchant")
    public String addMerchantPage(Model model){
        Hotel hotel = new Hotel();
        hotel.getUsers().add(new User());
        hotel.getHotelContacts().add(new HotelContact());
        hotel.getHotelLinks().add(new HotelLink());
        hotel.getHotelAddresses().add(new HotelAddress());
        model.addAttribute("hotel",hotel);
        model.addAttribute("hotelCategories",hotelCategoryService.findAll());
        return "admin/merchant-add";
    }



    @PostMapping("/add-merchant")
    public String saveMerchant(Model model, @Valid Hotel hotel, BindingResult result) throws Exception{
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "admin/merchant-add";
        } else {

            model.addAttribute("done", true);
            hotelService.saveHotel(hotel);
        }
        return "admin/merchant-add";
    }
}
