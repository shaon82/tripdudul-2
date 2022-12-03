package com.updatetech.tripDodol.service.package_service;


import com.updatetech.tripDodol.model.packageModel.PackageFile;
import com.updatetech.tripDodol.model.packageModel.TripPackage;
import com.updatetech.tripDodol.repository.packageReposiroty.TripPackageRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class TripPackageServiceImpl implements TripPackageService{

    @Autowired
    private TripPackageRepository tripPackageRepository;
    @Autowired
    private PackageFilePathService packageFilePathService;
    @Autowired
    private PackageFileService packageFileService;

    @Override
    public boolean isPackagePresent(String packageName) {
        TripPackage tripPackage = tripPackageRepository.findTripPackageByPackageName(packageName);
        if (tripPackage != null){
            return true;
        }
        return false;
    }

    @Override
    public TripPackage savePackage(TripPackage tripPackage) {
        tripPackage.setActive(true);
        TripPackage localPackage = tripPackageRepository.save(tripPackage);
        if (localPackage != null && localPackage.getFiles() != null && localPackage.getFiles().size() > 0){
            for (MultipartFile file: localPackage.getFiles()){
                String fileName = file.getOriginalFilename();
                String fileExtension = FilenameUtils.getExtension(fileName);
                String modifiedFileName = "packagePhoto"+"_"+localPackage.getId()+"."+fileExtension;
                File storeFile = packageFilePathService.getFilePath(modifiedFileName, "packageImage");
                if (storeFile != null){
                    try {
                        FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                PackageFile packageFile = new PackageFile();
                packageFile.setFileName(fileName);
                packageFile.setModifiedfileName(modifiedFileName);
                packageFile.setFileExtension(fileExtension);
                packageFile.setTripPackage(localPackage);
                packageFileService.saveFile(packageFile);
            }
        }
        return localPackage;
    }

    @Override
    public List<TripPackage> findAllPackage() {
        return tripPackageRepository.findAll();
    }

    @Override
    public List<TripPackage> searchPackageWithNameAndCity(String packageName, String city) {
        return tripPackageRepository.searchPackageWithNameAndCity(packageName,city);
    }

    @Override
    public List<TripPackage> findTripPackageByDestination(String destination) {
        return tripPackageRepository.findTripPackageByDestination(destination);
    }

    @Override
    public TripPackage findTripPackageById(Long id) {
        return tripPackageRepository.findTripPackageById(id);
    }
}
