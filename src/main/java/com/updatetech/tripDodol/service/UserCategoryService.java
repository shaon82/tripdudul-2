package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.UserCategory;

import java.util.List;

public interface UserCategoryService {
    boolean isUserCategoryPresent(String categoryName);

    UserCategory save(UserCategory userCategory);

    List<UserCategory> findUserCategoryByHotelId(Long id);

    UserCategory findUserCategoryById(Long id);
}
