package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import com.updatetech.tripDodol.model.*;
import com.updatetech.tripDodol.repository.RoleRepository;
import com.updatetech.tripDodol.service.HotelCategoryService;
import com.updatetech.tripDodol.service.HotelService;
import com.updatetech.tripDodol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/merchant")
public class MerchentRegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private HotelCategoryService hotelCategoryService;

    @GetMapping("/merchant-registration")
    public String merchantAdminRegistration(Model model){
        Hotel hotel = new Hotel();
        hotel.getUsers().add(new User());
        hotel.getHotelContacts().add(new HotelContact());
        hotel.getHotelLinks().add(new HotelLink());
        hotel.getHotelAddresses().add(new HotelAddress());
        model.addAttribute("hotel",hotel);
        model.addAttribute("hotelCategories",hotelCategoryService.findAll());
        return "web/web-merchant-registration-test";
    }



    @PostMapping("/add-merchant")
    public String saveMerchant(Model model, @Valid Hotel hotel, BindingResult result) throws Exception{

        if (result.hasErrors()){
            model.addAttribute("error", true);
            System.out.println("Error");
            return "web/web-merchant-registration-test";
        }

        model.addAttribute("done", true);

        hotelService.saveHotel(hotel);
    	System.out.println("Hotel Info: " + hotel);
        return "web/merchant-register-confirmation";
    }


    @GetMapping("/register/confirmation")
    public String merchantRegisterconfirmation(Model model){
        return "web/merchant-register-confirmation";
    }
}
