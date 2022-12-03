package com.updatetech.tripDodol.service.package_service;

import com.updatetech.tripDodol.model.packageModel.PackageCategory;

import java.util.List;

public interface PackageCategoryService {

    PackageCategory savePackageCategory(PackageCategory packageCategory);

    boolean isPackageCategoryPresent(String packageCategoryName);

    List<PackageCategory> findAll();

    PackageCategory findPackageCategoryById(Long id);

    void deletePackageCategory(PackageCategory packageCategory);
}
