package com.updatetech.tripDodol.controller.hotel.Web_Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebHotelBookingInvoiceController {


    @GetMapping("/hotel/booking/invoice")
    public String showInvoice(Model model){
        return "web/web-hotel-booking-invoice";
    }
}
