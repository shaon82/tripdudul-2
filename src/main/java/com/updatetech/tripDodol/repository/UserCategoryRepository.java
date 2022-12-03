package com.updatetech.tripDodol.repository;

import com.updatetech.tripDodol.model.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
    UserCategory findByCategoryName(String categoryName);

    List<UserCategory> findUserCategoryByHotelId(Long id);

    UserCategory findUserCategoryById(Long id);
}
