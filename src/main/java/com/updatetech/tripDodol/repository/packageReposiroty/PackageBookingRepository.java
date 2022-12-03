package com.updatetech.tripDodol.repository.packageReposiroty;

import com.updatetech.tripDodol.model.packageModel.PackageBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PackageBookingRepository extends JpaRepository<PackageBooking, Long> {

    @Query(value = "select * from package_booking where package_booking_status='PENDING'", nativeQuery = true)
    List<PackageBooking> findAllPendingRequest();

    PackageBooking findPackageBookingById(Long id);

    @Query(value = "select * from package_booking where package_booking_status='DEFAULT'", nativeQuery = true)
    List<PackageBooking> findPackageBookingHistory();


    @Query(value = "select * from package_booking where package_booking_status='CONFIRM'", nativeQuery = true)
    List<PackageBooking> findCurrentPackageBooking();

    @Query(value = "select * from package_booking where package_booking_status='CONFIRM' AND from_date = ?", nativeQuery = true)
    List<PackageBooking> packageBookingTodaysIncome(Date today);

    @Query(value = "select * from package_booking where package_booking_status='CONFIRM' AND (from_date between ? and ?)", nativeQuery = true)
    List<PackageBooking> findPackageBookingByWeekly(Date start, Date end);

    @Query(value = "select * from package_booking where package_booking_status='CONFIRM' AND (from_date between ? and ?)", nativeQuery = true)
    List<PackageBooking> findCurrentMonthBooking(Date start, Date end);
}
