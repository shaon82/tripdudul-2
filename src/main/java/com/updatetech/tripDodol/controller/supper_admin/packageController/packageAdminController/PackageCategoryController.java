package com.updatetech.tripDodol.controller.supper_admin.packageController.packageAdminController;

import com.updatetech.tripDodol.model.packageModel.PackageCategory;
import com.updatetech.tripDodol.service.package_service.PackageCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class PackageCategoryController {

    @Autowired
    private PackageCategoryService packageCategoryService;

    //find all category

    @GetMapping("/all-category-package")
    public String showAllPackageCategory(Model model){
        model.addAttribute("categories", packageCategoryService.findAll());
        return "admin/package-cat-all";

    }

    //Add package category

    @GetMapping("/add-package-Category")
    public String packageCategoryAddPage(Model model){
        model.addAttribute("packageCategory",new PackageCategory());
        return "admin/package-cat-add";
    }

    @PostMapping("/add-package-Category")
    public String savePackageCategory(Model model, @Valid PackageCategory packageCategory, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "admin/package-cat-add";
        }else if (packageCategoryService.isPackageCategoryPresent(packageCategory.getCategoryName())){
            model.addAttribute("categoryExist", true);
            return "admin/package-cat-add";
        }
        packageCategory.setActive(true);
        packageCategory.setCreatedDate(new Date());
        packageCategoryService.savePackageCategory(packageCategory);
        System.out.println("Package Category name: "+packageCategory.getCategoryName());
        model.addAttribute("success", true);
        return "redirect:/admin/all-category-package";
    }

    //update package category

    @GetMapping("/update/package-category/{id}")
    public String updatePackageCategoryPage(Model model, @PathVariable("id") Long id){
        PackageCategory packageCategory = packageCategoryService.findPackageCategoryById(id);
        model.addAttribute("packageCategory",packageCategory);
        return "admin/update-package-cat";
    }

    @PostMapping("/update/package-category/{id}")
    public String updatePackageCategory(Model model, @PathVariable("id") Long id,@Valid PackageCategory packageCategory,BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "admin/update-package-cat";
        }else if (packageCategoryService.isPackageCategoryPresent(packageCategory.getCategoryName())){
            model.addAttribute("categoryExist", true);
            return "admin/update-package-cat";
        }
        packageCategory.setCreatedDate(new Date());
        packageCategory.setActive(true);
        packageCategoryService.savePackageCategory(packageCategory);
        model.addAttribute("success", true);
        return "redirect:/admin/all-category-package";
    }

    // Delete package category

    @GetMapping("/delete/package-category/{id}")
    public String deletePackageCategory(@PathVariable("id") Long id){
        PackageCategory packageCategory = packageCategoryService.findPackageCategoryById(id);
        packageCategoryService.deletePackageCategory(packageCategory);
        return "redirect:/admin/all-category-package";
    }
}
