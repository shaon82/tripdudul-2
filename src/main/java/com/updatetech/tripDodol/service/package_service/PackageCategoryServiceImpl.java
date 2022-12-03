package com.updatetech.tripDodol.service.package_service;


import com.updatetech.tripDodol.model.packageModel.PackageCategory;
import com.updatetech.tripDodol.repository.packageReposiroty.PackageCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageCategoryServiceImpl implements PackageCategoryService{

    @Autowired
    private PackageCategoryRepository packageCategoryRepository;


    @Override
    public PackageCategory savePackageCategory(PackageCategory packageCategory) {
        return packageCategoryRepository.save(packageCategory);
    }

    @Override
    public boolean isPackageCategoryPresent(String packageCategoryName) {
        PackageCategory packageCategory = packageCategoryRepository.findPackageCategoryByCategoryName(packageCategoryName);
        if (packageCategory != null){
            return true;
        }
        return false;
    }

    @Override
    public List<PackageCategory> findAll() {
        return packageCategoryRepository.findAll();
    }

    @Override
    public PackageCategory findPackageCategoryById(Long id) {
        return packageCategoryRepository.findPackageCategoryById(id);
    }

    @Override
    public void deletePackageCategory(PackageCategory packageCategory) {
        packageCategoryRepository.delete(packageCategory);
    }
}
