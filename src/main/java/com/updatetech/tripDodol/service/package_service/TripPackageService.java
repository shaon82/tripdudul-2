package com.updatetech.tripDodol.service.package_service;

import com.updatetech.tripDodol.model.packageModel.TripPackage;

import java.util.List;

public interface TripPackageService {

    boolean isPackagePresent(String packageName);

    TripPackage savePackage(TripPackage tripPackage);

    List<TripPackage> findAllPackage();

    List<TripPackage> searchPackageWithNameAndCity(String packageName, String city);

    List<TripPackage> findTripPackageByDestination(String destination);

    TripPackage findTripPackageById(Long id);
}
