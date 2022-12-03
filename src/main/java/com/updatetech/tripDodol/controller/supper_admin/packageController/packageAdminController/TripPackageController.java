package com.updatetech.tripDodol.controller.supper_admin.packageController.packageAdminController;


import com.updatetech.tripDodol.model.packageModel.TripPackage;
import com.updatetech.tripDodol.service.package_service.PackageCategoryService;
import com.updatetech.tripDodol.service.package_service.TripPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TripPackageController {

    @Autowired
    private TripPackageService tripPackageService;
    @Autowired
    private PackageCategoryService packageCategoryService;

    //Get All Package

    @GetMapping("/find/all-package")
    public String getAllPackage(Model model){
        model.addAttribute("packages",tripPackageService.findAllPackage());
        return "admin/package-all";
    }


    // Add package

    @GetMapping("/add-package")
    public String addPackagePage(Model model){
        model.addAttribute("tripPackage",new TripPackage());
        model.addAttribute("categories",packageCategoryService.findAll());
        return "admin/package-add";
    }

    @PostMapping("/add-package")
    public String addPackage(Model model, @Valid TripPackage tripPackage, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "admin/package-add";
        }else if (tripPackageService.isPackagePresent(tripPackage.getPackageName())){
            model.addAttribute("notice",true);
            return "admin/package-add";
        }
        tripPackageService.savePackage(tripPackage);
        model.addAttribute("success", true);
        return "admin/package-add";
    }
}
