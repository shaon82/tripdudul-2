package com.updatetech.tripDodol.service.package_service;

import com.updatetech.tripDodol.model.packageModel.PackageFile;
import com.updatetech.tripDodol.repository.packageReposiroty.PackageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageFileServiceImpl implements PackageFileService{

    @Autowired
    private PackageFileRepository packageFileRepository;

    @Override
    public PackageFile saveFile(PackageFile packageFile) {
        return packageFileRepository.save(packageFile);
    }

    @Override
    public List<PackageFile> findPackageFileBytripPackageId(Long id) {
        return packageFileRepository.findPackageFileByTripPackageId(id);
    }
}
