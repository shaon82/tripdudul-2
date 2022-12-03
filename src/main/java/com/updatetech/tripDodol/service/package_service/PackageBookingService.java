package com.updatetech.tripDodol.service.package_service;

import com.updatetech.tripDodol.model.packageModel.PackageBooking;

import java.util.List;

public interface PackageBookingService {
    PackageBooking saveBooking(PackageBooking packageBooking);

    List<PackageBooking> findAll();

    List<PackageBooking> findAllPendingRequest();

    PackageBooking findPackageBookingById(Long id);

    void deletePackageBookingById(PackageBooking packageBooking);

    List<PackageBooking> findPackageBookingHistory();

    List<PackageBooking> findCurrentPackageBooking();

    List<PackageBooking> findPackageBookingWeekly();

    List<PackageBooking> findCurrentMonthlyIncome();

    List<PackageBooking> findYearlyIncome();
}
