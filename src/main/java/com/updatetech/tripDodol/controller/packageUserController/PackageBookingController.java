package com.updatetech.tripDodol.controller.packageUserController;


import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.model.packageModel.*;
import com.updatetech.tripDodol.model.packageModel.dto.PackageUserDto;
import com.updatetech.tripDodol.service.UserService;
import com.updatetech.tripDodol.service.package_service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/package-booking")
public class PackageBookingController {

    @Autowired
    private PackageBookingService packageBookingService;

    @Autowired
    private TripPackageService tripPackageService;

    @Autowired
    private PackageFileService packageFileService;

    @Autowired
    private UserService userService;

    @Autowired
    private PackageUserService packageUserService;
    @Autowired
    private PackageUserAutoLoginService packageUserAutoLoginService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PackageReportService packageReportService;

    @GetMapping("/package-booking/{id}")
    public String packageBookingPage(@PathVariable Long id, Model model){

        List<PackageFile> packageFileList = new ArrayList<>();
        List<PackageFile>packageFiles = packageFileService.findPackageFileBytripPackageId(id);
        packageFileList.add(packageFiles.get(0));

        PackageBooking packageBooking = new PackageBooking();
        packageBooking.getPackageGuests().add(new PackageGuest());
        model.addAttribute("tripPackageId", id);
        model.addAttribute("packageBooking",packageBooking);
        model.addAttribute("packageFileList",packageFileList);
        return "web/package-booking";
    }


    @PostMapping("/booking/{tripPackageId}")
    public String packageBooking(@PathVariable Long tripPackageId, Model model, @Valid PackageBooking packageBooking,
                                 BindingResult result, HttpServletRequest request, HttpServletResponse response){
        TripPackage tripPackage = tripPackageService.findTripPackageById(tripPackageId);

        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "web/package-booking";
        }
        System.out.println("controller booking 1: "+packageBooking.getPhoneNumber());
        PackageUserDto packageUserDto = new PackageUserDto();
        BeanUtils.copyProperties(packageBooking, packageUserDto);

        User user = new User();
        BeanUtils.copyProperties(packageUserDto,user);

//        Map<String, String> loginDetails = new HashMap<>();
//        loginDetails.put("email",user.getEmail());
//        loginDetails.put("password", user.getPassword());

        List<PackageBooking>packageBookingList = new ArrayList<>();

        User user1 = packageUserService.SavePackageUser(user, request);
        packageBooking.setUser(user1);
        packageBooking.setPackageBookingStatus(PackageBookingStatus.PENDING);
        packageBooking.setPackagePrice(tripPackage.getPackagePrice());
        packageBooking.setTripPackage(tripPackage);

        System.out.println("controller booking 2: "+packageBooking.getPackagePrice());

        PackageBooking packageBooking1 = packageBookingService.saveBooking(packageBooking);

        packageBookingList.add(packageBooking1);
        for (PackageBooking packageBooking2: packageBookingList){
            System.out.println("package booking list: "+packageBooking2.getPhoneNumber());
        }
        packageReportService.createPackageBookingReport(packageBookingList, tripPackage.getPackageName());
//        authenticateUserAndSetSession(loginDetails, request);

        model.addAttribute("success", true);
        return "redirect:/web-user/profile";
    }

//    private void authenticateUserAndSetSession(Map loginDetails, HttpServletRequest request) {
//
//        SimpleGrantedAuthority auth = new SimpleGrantedAuthority("USER");
//        Collection<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
//        authorities.add(auth);
//
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDetails.get("email"), loginDetails.get("password"), authorities);
//        request.getSession();
//        token.setDetails(new WebAuthenticationDetails(request));
//        Authentication authentication = authenticationManager.authenticate(token);
//        if (authentication.isAuthenticated()){
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
//                    SecurityContextHolder.getContext());
//        }else {
//            System.out.println("not authenticate");
//        }
//
//
//    }
}
