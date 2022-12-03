package com.updatetech.tripDodol.controller.packageUserController;


import com.updatetech.tripDodol.model.packageModel.PackageFile;
import com.updatetech.tripDodol.model.packageModel.TripPackage;
import com.updatetech.tripDodol.service.package_service.PackageFileService;
import com.updatetech.tripDodol.service.package_service.TripPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/package")
public class PackageDetailsController {

    @Autowired
    private TripPackageService tripPackageService;
    @Autowired
    private PackageFileService packageFileService;

    @GetMapping("/package-details/{id}")
    public String packageDetailsPage(Model model, @PathVariable Long id){
        TripPackage tripPackage = tripPackageService.findTripPackageById(id);
        List<PackageFile>packageFiles = packageFileService.findPackageFileBytripPackageId(tripPackage.getId());
        model.addAttribute("tripPackage",tripPackage);
        model.addAttribute("packageFiles",packageFiles);

        return "web/package-details";
    }
}
