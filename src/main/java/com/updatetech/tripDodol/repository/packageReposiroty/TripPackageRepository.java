package com.updatetech.tripDodol.repository.packageReposiroty;

import com.updatetech.tripDodol.model.packageModel.TripPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TripPackageRepository extends JpaRepository<TripPackage, Long> {

    TripPackage findTripPackageByPackageName(String packageName);

    @Query(value = "select * from trip_package where package_name=? and destination=?", nativeQuery = true)
    List<TripPackage> searchPackageWithNameAndCity(String packageName, String city);

    List<TripPackage> findTripPackageByDestination(String destination);

    TripPackage findTripPackageById(Long id);
}
