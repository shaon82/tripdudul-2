package com.updatetech.tripDodol.repository.packageReposiroty;


import com.updatetech.tripDodol.model.packageModel.PackageGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageGuestRepository extends JpaRepository<PackageGuest, Long> {
}
