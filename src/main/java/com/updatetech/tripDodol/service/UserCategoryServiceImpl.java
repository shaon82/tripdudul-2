package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.UserCategory;
import com.updatetech.tripDodol.repository.UserCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCategoryServiceImpl implements UserCategoryService{

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @Override
    public boolean isUserCategoryPresent(String categoryName) {
        UserCategory userCategory = userCategoryRepository.findByCategoryName(categoryName);
        if (userCategory != null){
            return true;
        }
        return false;
    }

    @Override
    public UserCategory save(UserCategory userCategory) {
        return userCategoryRepository.save(userCategory);
    }

    @Override
    public List<UserCategory> findUserCategoryByHotelId(Long id) {
        return userCategoryRepository.findUserCategoryByHotelId(id);
    }

    @Override
    public UserCategory findUserCategoryById(Long id) {
        return userCategoryRepository.findUserCategoryById(id);
    }
}
