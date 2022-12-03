package com.updatetech.tripDodol.controller.hotel.MerchantController;


import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.HotelExpense;
import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.service.HotelExpenseService;
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
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/merchant")
public class HotelExpenseController {

    @Autowired
    private HotelExpenseService hotelExpenseService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;

//    expense list
    @GetMapping("/expense/list")
    public String expenseList(Model model,Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        List<HotelExpense>hotelExpenses = hotelExpenseService.findHotelExpenseByHotelId(hotel.getId());
        model.addAttribute("hotelExpenses",hotelExpenses);
        return "merchant_web/hotel-expense-all";
    }

//    add expense page
    @GetMapping("/add/expense")
    public String addExpensePage(Model model, Principal principal){
        return "merchant_web/hotel-expense-add";
    }

//    Save expense method
    @PostMapping("/save/expense")
    public String addExpense(Model model, @Valid HotelExpense hotelExpense, Principal principal, BindingResult result){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        hotelExpense.setHotel(hotel);
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "merchant_web/hotel-expense-add";
        }
        hotelExpenseService.saveExpense(hotelExpense);
        model.addAttribute("success", true);
        return "merchant_web/hotel-expense-add";
    }

}
