package com.updatetech.tripDodol.controller.supper_admin.packageController.packageAdminController;


import com.updatetech.tripDodol.model.packageModel.TripPackage;
import com.updatetech.tripDodol.service.package_service.TripPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class PackageSearchControllerTwo {

    @Autowired
    private TripPackageService tripPackageService;

    @GetMapping("/search/package/{packageName}/{city}")
    public String searchPackageWithNameAndCity(String packageName, String city){
        List<TripPackage>tripPackages = tripPackageService.searchPackageWithNameAndCity(packageName,city);
        return null;
    }
}
