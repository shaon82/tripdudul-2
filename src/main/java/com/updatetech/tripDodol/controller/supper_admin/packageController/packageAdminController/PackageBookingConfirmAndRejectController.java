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

@Controller
@RequestMapping("/admin")
public class PackageBookingConfirmAndRejectController {

    @Autowired
    private PackageBookingService packageBookingService;

    @GetMapping("/package/booking-confirm/{id}")
    public String packageBookingConfirmation(@PathVariable("id") Long id, Model model){
        PackageBooking packageBooking = packageBookingService.findPackageBookingById(id);
        packageBooking.setPackageBookingStatus(PackageBookingStatus.CONFIRM);
        packageBookingService.saveBooking(packageBooking);
        return "redirect:/admin/find/all-package-booking";
    }


    @GetMapping("/reject/package-booking/{id}")
    public String rejectPackageBooking(@PathVariable("id") Long id, Model model){
        PackageBooking packageBooking = packageBookingService.findPackageBookingById(id);
        packageBookingService.deletePackageBookingById(packageBooking);
        return "redirect:/admin/all/pending/booking/package";
    }
}
