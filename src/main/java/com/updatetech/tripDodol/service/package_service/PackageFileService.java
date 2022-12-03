package com.updatetech.tripDodol.service.package_service;

import com.updatetech.tripDodol.model.packageModel.PackageFile;

import java.util.List;

public interface PackageFileService {

    PackageFile saveFile(PackageFile packageFile);

    List<PackageFile> findPackageFileBytripPackageId(Long id);
}
