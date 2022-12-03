package com.updatetech.tripDodol.repository.packageReposiroty;

import com.updatetech.tripDodol.model.packageModel.PackageCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PackageCategoryRepository extends JpaRepository<PackageCategory, Long> {

    PackageCategory findPackageCategoryByCategoryName(String packageCategoryName);

    PackageCategory findPackageCategoryById(Long id);

//    PackageCategory updatePackageCategory(Long id);
}
