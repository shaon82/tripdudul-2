package com.updatetech.tripDodol.controller.packageUserController;


import com.updatetech.tripDodol.model.packageModel.PackageFile;
import com.updatetech.tripDodol.model.packageModel.TripPackage;
import com.updatetech.tripDodol.service.package_service.PackageFileService;
import com.updatetech.tripDodol.service.package_service.TripPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/package")
public class UserTripPackageController {

    @Autowired
    private TripPackageService tripPackageService;
    @Autowired
    private PackageFileService packageFileService;

    @GetMapping("/all-package")
    public String allPackage(Model model){
        List<PackageFile>packageFileList = new ArrayList<>();
        List<TripPackage> tripPackages = tripPackageService.findAllPackage();
        for (TripPackage tripPackage: tripPackages){
            List<PackageFile>packageFiles = packageFileService.findPackageFileBytripPackageId(tripPackage.getId());
            packageFileList.add(packageFiles.get(0));
        }
        model.addAttribute("tripPackages", tripPackages);
        model.addAttribute("packageFileList",packageFileList);
        return "web/all-package";
    }
}
