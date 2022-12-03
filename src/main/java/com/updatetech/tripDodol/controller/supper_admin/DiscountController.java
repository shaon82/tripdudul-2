package com.updatetech.tripDodol.controller.supper_admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DiscountController {


    @GetMapping("/discount")
    public String discountPage(){
        return "admin/discount";
    }



    @GetMapping("/addDiscount")
    public String discountAddPage(){
        return "admin/discount-add";
    }
}
