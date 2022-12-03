package com.updatetech.tripDodol.controller.hotel.MerchantController;


import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.model.UserCategory;
import com.updatetech.tripDodol.service.HotelService;
import com.updatetech.tripDodol.service.UserCategoryService;
import com.updatetech.tripDodol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/merchant")
public class MerchantUserCategoryController {

    @Autowired
    private UserCategoryService userCategoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;


    @GetMapping("/all-employee-category")
    public String getAllEmployeeCategory(Model model, Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        List<UserCategory> userCategories = userCategoryService.findUserCategoryByHotelId(hotel.getId());
        for (UserCategory userCategory: userCategories){
            System.out.println(userCategory);
        }
        model.addAttribute("userCategories", userCategories);
        return "merchant_web/user-cat-all";
    }



    @GetMapping("/add-merchant-category")
    public String addMerchantUserCategoryPage(Model model, HttpSession session){
        model.addAttribute("userCategory", new UserCategory());
        return "merchant_web/merchant-user-cat-add";
    }


    @PostMapping("/add-merchant-category")
    public String addMerchantUserCategory(Model model, @Valid UserCategory userCategory, BindingResult result,
                                          HttpSession session, Principal principal){

        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        System.out.println("this is hotel id: "+hotel.getId());
        userCategory.setHotel(hotel);

        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "merchant_web/merchant-user-cat-add";
        }
        else if (userCategoryService.isUserCategoryPresent(userCategory.getCategoryName())){
                model.addAttribute("categoryExist", true);
            return "merchant_web/merchant-user-cat-add";
        }

        userCategoryService.save(userCategory);
        return "redirect:/merchant/all-employee-category";

    }


    @GetMapping("/update/user-category/{id}")
    public String updateUserCategoryPage(Model model, @PathVariable("id") Long id){
        UserCategory userCategory = userCategoryService.findUserCategoryById(id);
        model.addAttribute("userCategory", userCategory);
        return "merchant_web/merchant-user-cat-update";
    }

    @PostMapping("/update/user-category/{id}")
    public String updateUserCategory(@PathVariable("id") Long id, @Valid UserCategory userCategory, BindingResult result,
                                     Model model, Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        userCategory.setHotel(hotel);
        if (result.hasErrors()){
            userCategory.setId(id);
            model.addAttribute("error", true);
            return "merchant_web/merchant-user-cat-update";
        }

        userCategoryService.save(userCategory);
        return "redirect:/merchant/all-employee-category";
    }



}
