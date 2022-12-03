package com.updatetech.tripDodol.service.package_service;

import java.util.Date;

public interface PackageAdminHomeService {
    double findTodaysIncome(Date date);

    double findWeeklyIncome();

    double findMonthlyIncome();

    double findIncomeYearly();
}
