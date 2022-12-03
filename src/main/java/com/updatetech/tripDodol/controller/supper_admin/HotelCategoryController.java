package com.updatetech.tripDodol.controller.supper_admin;


import com.updatetech.tripDodol.model.HotelCategory;
import com.updatetech.tripDodol.service.HotelCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class HotelCategoryController {

    @Autowired
    private HotelCategoryService hotelCategoryService;


    @GetMapping("/add-hotel-category")
    public String addHotelCategoryPage(Model model, HotelCategory hotelCategory){
        model.addAttribute("hotelCategory", hotelCategory);
        model.addAttribute("isAdd", true);
        return "admin/hotel-cat-add";
    }


    @PostMapping("/add-hotel-category")
    public String addHotelCategory(Model model, @Valid HotelCategory hotelCategory, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "admin/hotel-cat-add";
        }
        if (hotelCategoryService.isHotelCategoryPresent(hotelCategory.getHotelCategoryName())){
            model.addAttribute("hotelCategoryExist",hotelCategory);
            return "admin/hotel-cat-add";
        }
        hotelCategoryService.createHotelCategory(hotelCategory);
        model.addAttribute("hotelCategory", hotelCategory);
        model.addAttribute("success", true);
        return "redirect:/admin/hotel-category-list";
    }



    @GetMapping("/hotel-category-list")
    public String hotelCategoryList(Model model){
        model.addAttribute("hotelCategoryList", hotelCategoryService.findAll());
        return "admin/hotel-cat-all";
    }



    @GetMapping("/edit-hotel-category/{hotelCategoryId}")
    public String editHotelCategory(@PathVariable("hotelCategoryId") Long hotelCategoryId, Model model){
        HotelCategory hotelCategory = hotelCategoryService.findHotelCategoryByhotelCategoryId(hotelCategoryId);
        model.addAttribute("hotelCategory", hotelCategory);
        return "admin/hotel-cat-edit";
    }

    @PostMapping("/edit-hotel-category/{hotelCategoryId}")
    public String updateHotelCategory(Model model, @PathVariable("hotelCategoryId") Long hotelCategoryId, @Valid HotelCategory hotelCategory, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "admin/hotel-cat-edit";
        }
        hotelCategoryService.createHotelCategory(hotelCategory);
        return "redirect:/admin/hotel-category-list";
    }


    @GetMapping("/delete-category/{hotelCategoryId}")
    public String deleteHotelCategory(Model model, @PathVariable("hotelCategoryId") Long hotelCategoryId){
        HotelCategory hotelCategory = hotelCategoryService.findHotelCategoryByhotelCategoryId(hotelCategoryId);
        hotelCategoryService.delete(hotelCategory);
        return "redirect:/admin/hotel-category-list";
    }



}
