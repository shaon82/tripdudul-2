package com.updatetech.tripDodol.configuration;

import com.updatetech.tripDodol.service.MerchantSecurityService;
import com.updatetech.tripDodol.service.UserSecurityService;
import com.updatetech.tripDodol.service.WebSecurityService;
import com.updatetech.tripDodol.service.adminService.AdminSecurityService;
import com.updatetech.tripDodol.service.package_service.PackageSecurityService;
import com.updatetech.tripDodol.utility.CustomLoginSuccessHandler;
import com.updatetech.tripDodol.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHER ={
            "/web_asset/css/**",
            "/web_asset/js/**",
            "/resources/**",
            "/webjars/**",
            "/web_asset/images/**",
            "/web_asset/fonts/**",
            "/register",
            "/admin_asset/css/**",
            "/home/shaon/updateTech/tripDodol/src/main/resources/static/projectFile/hotelPhoto/**",
            "/admin_asset/js/**",
            "/admin_asset/images/**",
            "/admin_asset/fonts/**",
            "/admin/super-admin/login",
            "/admin/add-user",
            "/admin/forgetPassword",
            "/merchant/merchant-login",
            "/merchant/user/password-reset",
            "/merchant/add-merchant"
//            "/web-user/profile"
    };


    @Configuration
    @EnableWebSecurity
    @Order(1)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{

        @Autowired
        private AdminSecurityService adminSecurityService;

        private BCryptPasswordEncoder passwordEncoder(){
            return SecurityUtility.passwordEncoder();
        }


//        @Override
//        public void configure(WebSecurity web) throws Exception {
//            web
//                    .ignoring().antMatchers(PUBLIC_MATCHER);
//        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable().cors().disable()
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers(PUBLIC_MATCHER).permitAll()
                    .and()
                    .formLogin().failureUrl("/admin/login?error")
                    .defaultSuccessUrl("/admin/adminhome")
                    .loginPage("/admin/super-admin/login").permitAll()

                    .and()
                    .formLogin().loginPage("/admin/super-admin/login").defaultSuccessUrl("/admin/adminhome")
                    .usernameParameter("username") .passwordParameter("password")
                    .failureUrl("/admin/super-admin/login?error=true").permitAll()
                    .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
                    .logoutSuccessUrl("/admin/super-admin/login?logout")
                    .deleteCookies("remember-me").permitAll()
                    .and()
                    .rememberMe();

        }


        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
            auth.userDetailsService(adminSecurityService).passwordEncoder(passwordEncoder());
        }

    }



    @Configuration
    @EnableWebSecurity
    @Order(2)
    public static class MerchantSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private MerchantSecurityService merchantSecurityService;
        @Autowired
        private CustomLoginSuccessHandler customLoginSuccessHandler;

        private BCryptPasswordEncoder passwordEncoder() {
            return SecurityUtility.passwordEncoder();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web
                    .ignoring().antMatchers("/merchant/merchant-registration","/merchant/add-merchant");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable().cors().disable()
                    .antMatcher("/merchant/**")
                    .authorizeRequests()
                    .antMatchers(PUBLIC_MATCHER).permitAll()
                    .antMatchers("/merchant/**").access("hasRole('MERCHANT_ADMIN')")
                    .antMatchers("/merchant-manager/**").access("hasAnyRole('MERCHANT_MANAGER','MERCHANT_ADMIN')")
                    .and()
                    .formLogin().loginPage("/merchant/merchant-login").permitAll()
                    .failureUrl("/merchant/merchant-login?error")
                    .successHandler(customLoginSuccessHandler)
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/merchant/logout"))
                    .logoutSuccessUrl("/merchant/merchant-login?logout")
                    .deleteCookies("remember-me").permitAll()
                    .and()
                    .rememberMe();
        }


        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(merchantSecurityService).passwordEncoder(passwordEncoder());
        }

    }


    @Configuration
    @EnableWebSecurity
    @Order(3)
    public static class webSecurityConfig extends WebSecurityConfigurerAdapter{

        @Autowired
        private UserSecurityService userSecurityService;

        private BCryptPasswordEncoder passwordEncoder() {
            return SecurityUtility.passwordEncoder();
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean()throws Exception{
            return super.authenticationManagerBean();
        }


//        @Override
//        public void configure(WebSecurity web) throws Exception {
//            web
//                    .ignoring().antMatchers("/webLogin","/resources/**");
//        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable().cors().disable()
                    .authorizeRequests().antMatchers(PUBLIC_MATCHER).permitAll()
                    .antMatchers("/web-user/**").access("hasRole('USER')")
//                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("/webLogin").permitAll().usernameParameter("email").passwordParameter("password")
                    .defaultSuccessUrl("/web-user/profile")
                    .failureUrl("/webLogin?error")
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/web/logout"))
                    .logoutSuccessUrl("/webLogin?logout")
                    .deleteCookies("remember-me").permitAll()
                    .and()
                    .rememberMe();

        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
            authenticationManagerBuilder.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
        }
    }

}
