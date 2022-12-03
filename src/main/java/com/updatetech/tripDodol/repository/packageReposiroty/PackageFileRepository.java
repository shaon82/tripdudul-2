package com.updatetech.tripDodol.repository.packageReposiroty;

import com.updatetech.tripDodol.model.packageModel.PackageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageFileRepository extends JpaRepository<PackageFile, Long> {
    List<PackageFile> findPackageFileByTripPackageId(Long id);
}
