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
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()){
            System.out.println("Can't redirect");
            return;
        }
        redirectStrategy.sendRedirect(request,response,targetUrl);
    }

    /*
     * This method extracts the roles of currently logged-in user and returns
     * appropriate URL according to his/her role.
     */

    protected String determineTargetUrl(Authentication authentication){
        String url = "";
        Collection<? extends GrantedAuthority>authorities = authentication.getAuthorities();
        List<String>roles = new ArrayList<String>();
        for (GrantedAuthority authority: authorities){
            roles.add(authority.getAuthority());
        }

        if (isMerchantAdmin(roles)){
            url = "/merchant/merchant-home";
        }else if (isMerchantManager(roles)){
            url = "/merchant-manager/current/booking/information";
        }else{
            url = "/accessDenied";
        }
        return url;
    }

    private boolean isMerchantManager(List<String> roles) {
        if (roles.contains("ROLE_MERCHANT_MANAGER") || roles.contains("ROLE_MERCHANT_ADMIN")){
            return true;
        }
        return false;
    }

    private boolean isMerchantAdmin(List<String> roles) {
        if (roles.contains("ROLE_MERCHANT_ADMIN")){
            return true;
        }
        return false;
    }
}
