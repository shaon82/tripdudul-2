package com.updatetech.tripDodol.service.package_service;


import com.updatetech.tripDodol.model.packageModel.PackageBooking;
import com.updatetech.tripDodol.repository.packageReposiroty.PackageBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PackageAdminHomeServiceImpl implements PackageAdminHomeService{

    @Autowired
    private PackageBookingRepository packageBookingRepository;
    @Autowired
    private PackageBookingService packageBookingService;


    @Override
    public double findTodaysIncome(Date date) {
        double todayTotalIncome=0;
        List<PackageBooking>packageBookings = packageBookingRepository.packageBookingTodaysIncome(date);
        for (PackageBooking packageBooking: packageBookings){
            todayTotalIncome += packageBooking.getPackagePrice();
        }
        return todayTotalIncome;
    }

    @Override
    public double findWeeklyIncome() {
        double weeklyTotalIncome = 0;
        List<PackageBooking>packageBookings = packageBookingService.findPackageBookingWeekly();
        for (PackageBooking packageBooking: packageBookings){
            System.out.println("booking id: "+packageBooking.getId());
            weeklyTotalIncome += packageBooking.getPackagePrice();
        }
        return weeklyTotalIncome;
    }

    @Override
    public double findMonthlyIncome() {
        double monthlyIncome = 0;
        List<PackageBooking> packageBookings = packageBookingService.findCurrentMonthlyIncome();
        for (PackageBooking packageBooking: packageBookings){
            monthlyIncome += packageBooking.getPackagePrice();
        }
        return monthlyIncome;
    }

    @Override
    public double findIncomeYearly() {
        double yearlyIncome =0;
        List<PackageBooking>packageBookings = packageBookingService.findYearlyIncome();
        for (PackageBooking packageBooking: packageBookings){
            yearlyIncome += packageBooking.getPackagePrice();
        }
        return yearlyIncome;
    }
}
