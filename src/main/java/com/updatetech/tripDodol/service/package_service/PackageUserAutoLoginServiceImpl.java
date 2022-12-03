package com.updatetech.tripDodol.service.package_service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class PackageUserAutoLoginServiceImpl implements PackageUserAutoLoginService{

//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Override
//    public void authenticateUserAndSetSession(Map loginDetails, HttpServletRequest request) {
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDetails.get("email"), loginDetails.get("password"));
//        request.getSession();
//        token.setDetails(new WebAuthenticationDetails(request));
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
//                SecurityContextHolder.getContext());
//        System.out.println("login done");
//    }
}
