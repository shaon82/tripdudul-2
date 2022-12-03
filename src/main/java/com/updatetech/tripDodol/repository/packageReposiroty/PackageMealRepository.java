package com.updatetech.tripDodol.repository.packageReposiroty;

import com.updatetech.tripDodol.model.packageModel.PackageMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageMealRepository extends JpaRepository<PackageMeal, Long> {
}
