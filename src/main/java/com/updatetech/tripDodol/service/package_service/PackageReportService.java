package com.updatetech.tripDodol.service.package_service;

import com.updatetech.tripDodol.model.packageModel.PackageBooking;

import java.util.List;

public interface PackageReportService {
    void createPackageBookingReport(List<PackageBooking> packageBookingList, String packageName);
}
