package com.updatetech.tripDodol.service.package_service;

import com.updatetech.tripDodol.model.User;

import javax.servlet.http.HttpServletRequest;

public interface PackageUserService {
    User SavePackageUser(User user, HttpServletRequest request);
}
