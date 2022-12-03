package com.updatetech.tripDodol.service.package_service;


import com.updatetech.tripDodol.model.Guest;
import com.updatetech.tripDodol.model.packageModel.PackageBooking;
import com.updatetech.tripDodol.model.packageModel.PackageGuest;
import com.updatetech.tripDodol.repository.packageReposiroty.PackageBookingRepository;
import com.updatetech.tripDodol.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PackageBookingServiceImpl implements PackageBookingService{

    @Autowired
    private PackageBookingRepository packageBookingRepository;
    @Autowired
    private PackageGuestService packageGuestService;



    @Override
    public PackageBooking saveBooking(PackageBooking packageBooking) {
        for (PackageGuest guest: packageBooking.getPackageGuests()){
            guest.setPackageBooking(packageBooking);
            packageGuestService.save(guest);
        }
        packageBookingRepository.save(packageBooking);
        System.out.println("Service Package Booking: "+packageBooking.getPhoneNumber());
        return packageBooking;
    }

    @Override
    public List<PackageBooking> findAll() {
        return packageBookingRepository.findAll();
    }

    @Override
    public List<PackageBooking> findAllPendingRequest() {
        return packageBookingRepository.findAllPendingRequest();
    }

    @Override
    public PackageBooking findPackageBookingById(Long id) {
        return packageBookingRepository.findPackageBookingById(id);
    }

    @Override
    public void deletePackageBookingById(PackageBooking packageBooking) {
        packageBookingRepository.delete(packageBooking);
    }

    @Override
    public List<PackageBooking> findPackageBookingHistory() {
        return packageBookingRepository.findPackageBookingHistory();
    }

    @Override
    public List<PackageBooking> findCurrentPackageBooking() {
        return packageBookingRepository.findCurrentPackageBooking();
    }

    @Override
    public List<PackageBooking> findPackageBookingWeekly() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 7);
        Date start = c.getTime();
        c.add(Calendar.DATE, 6);
        Date end = c.getTime();
        System.out.println(start + " - " + end);
        return packageBookingRepository.findPackageBookingByWeekly(start, end);
    }

    @Override
    public List<PackageBooking> findCurrentMonthlyIncome() {
        LocalDate today = LocalDate.now();
        Date start = java.sql.Date.valueOf(today.withDayOfMonth(1));
        Date end = java.sql.Date.valueOf(today.withDayOfMonth(today.lengthOfMonth()));
        return packageBookingRepository.findCurrentMonthBooking(start, end);
    }

    @Override
    public List<PackageBooking> findYearlyIncome() {
        LocalDate today = LocalDate.now();
        Date start = java.sql.Date.valueOf(today.withDayOfYear(1));
        Date end = java.sql.Date.valueOf(today.withDayOfYear(today.lengthOfYear()));
        return packageBookingRepository.findCurrentMonthBooking(start, end);
    }
}
