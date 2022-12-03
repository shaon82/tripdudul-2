package com.updatetech.tripDodol.repository.packageReposiroty;

import com.updatetech.tripDodol.model.packageModel.MealItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealItemRepository extends JpaRepository<MealItem, Long> {
}
