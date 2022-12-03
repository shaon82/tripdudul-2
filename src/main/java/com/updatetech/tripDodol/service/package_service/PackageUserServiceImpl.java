package com.updatetech.tripDodol.service.package_service;


import com.updatetech.tripDodol.model.Role;
import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.repository.UserRepository;
import com.updatetech.tripDodol.service.UserService;
import com.updatetech.tripDodol.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class PackageUserServiceImpl implements PackageUserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PackageUserAutoLoginService packageUserAutoLoginService;

    @Override
    public User SavePackageUser(User user, HttpServletRequest request) {
        user.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));
        Role role = new Role();
        role.setRoleId(5);
        role.setName("ROLE_USER");
        userService.saveUser(user,role);
        return user;
    }
}
