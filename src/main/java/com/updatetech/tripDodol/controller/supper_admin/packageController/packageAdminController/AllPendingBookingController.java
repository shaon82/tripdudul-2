package com.updatetech.tripDodol.controller.supper_admin.packageController.packageAdminController;


import com.updatetech.tripDodol.model.packageModel.PackageBooking;
import com.updatetech.tripDodol.service.package_service.PackageBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AllPendingBookingController {

    @Autowired
    private PackageBookingService packageBookingService;


    @GetMapping("/all/pending/booking/package")
    public String allPendingBooking(Model model){
        List<PackageBooking>packageBookings = packageBookingService.findAllPendingRequest();
        model.addAttribute("packageBookings", packageBookings);
        return "admin/all-pending-booking-list";
    }
}
