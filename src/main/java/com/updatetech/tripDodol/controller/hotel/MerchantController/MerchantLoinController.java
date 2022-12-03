package com.updatetech.tripDodol.controller.hotel.MerchantController;


import com.updatetech.tripDodol.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/merchant")
public class MerchantLoinController {

    @GetMapping("/merchant-login")
    public String merchantLoginPage(Model model, User user){
        return "merchant_web/merchant-login";
    }

}
