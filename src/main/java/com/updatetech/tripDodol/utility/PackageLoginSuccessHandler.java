package com.updatetech.tripDodol.utility;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class PackageLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//    @Override
//    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        String targetUrl = determineTargetUrl(authentication);
//        if (response.isCommitted()){
//            System.out.println("Can't redirect");
//            return;
//        }
//        redirectStrategy.sendRedirect(request,response,targetUrl);
//    }
//
//    /*
//     * This method extracts the roles of currently logged-in user and returns
//     * appropriate URL according to his/her role.
//     */
//
//    protected String determineTargetUrl(Authentication authentication){
//        String url = "";
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        List<String> packageRoles = new ArrayList<String>();
//        for (GrantedAuthority authority: authorities){
//            packageRoles.add(authority.getAuthority());
//        }
//
//        if (isPackageAdmin(packageRoles)){
//            url = "/package/package-admin/home-page";
//        }else{
//            url = "/accessDenied";
//        }
//        return url;
//    }
//
//    private boolean isPackageAdmin(List<String> packageRoles) {
//        if (packageRoles.contains("PACKAGE_ADMIN")){
//            return true;
//        }
//        return false;
//    }
}
