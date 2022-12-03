package com.updatetech.tripDodol.controller.supper_admin.packageController.packageAdminController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class TripPackageProfileController {

    @GetMapping("/package-profile/{id}")
    public String packageProfilePage(@PathVariable("id") Long id){
        return "admin/package-profile";
    }
}
