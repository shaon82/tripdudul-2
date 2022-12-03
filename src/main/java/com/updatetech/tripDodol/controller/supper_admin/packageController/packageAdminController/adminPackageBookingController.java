package com.updatetech.tripDodol.controller.supper_admin.packageController.packageAdminController;


import com.updatetech.tripDodol.model.packageModel.PackageBooking;
import com.updatetech.tripDodol.model.packageModel.PackageBookingStatus;
import com.updatetech.tripDodol.service.package_service.PackageBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminPackageBookingController {

    @Autowired
    private PackageBookingService packageBookingService;


    @GetMapping("/find/all-package-booking")
    public String findAllPackageBooking(Model model){
        List<PackageBooking>packageBookings= packageBookingService.findAll();
        model.addAttribute("packageBookings", packageBookings);
        return "admin/admin-package-booking-all";
    }


    @GetMapping("/package-booking-history")
    public String packageBookingHistory(Model model){
        List<PackageBooking>packageBookings = packageBookingService.findPackageBookingHistory();
        model.addAttribute("packageBookings",packageBookings);
        return "admin/package-booking-history";
    }


    @GetMapping("/package-booking-current/information")
    public String currentPackageBooking(Model model){
        List<PackageBooking>packageBookings = packageBookingService.findCurrentPackageBooking();
        model.addAttribute("packageBookings",packageBookings);
        return "admin/package-booking-current-info";
    }


    @GetMapping("/package/booking-check-out/{id}")
    public String packageBookingCheckOut(@PathVariable("id") Long id, Model model){
        PackageBooking packageBooking = packageBookingService.findPackageBookingById(id);
        packageBooking.setPackageBookingStatus(PackageBookingStatus.DEFAULT);
        packageBookingService.saveBooking(packageBooking);
        return "redirect:/admin/package-booking-current/information";
    }


}
