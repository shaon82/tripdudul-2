package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DbEventBookingController {


    @GetMapping("/dbEventBooking")
    public String dbeventBookingPage(){
        return "web/db-event-booking";
    }


    @GetMapping("/dbHotelBooking")
    public String dbHotelBookingPage(){
        return "web/db-hotel-booking";
    }


    @GetMapping("/dbEventDetail")
    public String dbeventDetailPage(){
        return "web/db-event-details";
    }



    @GetMapping("/dbHotelDetail")
    public String dbHotelDetailPage(){
        return "web/db-hotel-details";
    }


    @GetMapping("/dbMyProfile")
    public String dbMyProfilePage(){
        return "web/db-my-profile";
    }


    @GetMapping("/dbMyProfileEdit")
    public String dbMyProfileEditPage(){
        return "web/db-my-profile-edit";
    }



    @GetMapping("/dbPayment")
    public String dbPaymentPage(){
        return "web/db-payment";
    }


    @GetMapping("/dbRefund")
    public String dbRefundPage(){
        return "web/db-refund";
    }




}
