package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.Role;
import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.model.UserRole;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface UserService {

    boolean isUserPresent(String email);

    User saveUser(User user,Role role);

    User findOneByEmail(String email);

    List<User> findAll();

    User findUserById(long id);

    void delete(User user);

    User findUserByUsername(String username);

    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    User createManager(User manager, Role role) throws Exception;

    List<User> findUserByHotelIdAndRole(Long id);

    void updateUserPassword(String newPassword, Long id);
}
