package com.updatetech.tripDodol.controller.hotel.MerchantController;


import com.updatetech.tripDodol.model.Dto.UserDto;
import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.model.UserPasswordResetToken;
import com.updatetech.tripDodol.service.MerchantPasswordResetService;
import com.updatetech.tripDodol.service.UserService;
import com.updatetech.tripDodol.utility.MailConstructor;
import com.updatetech.tripDodol.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;
import java.util.UUID;

@Controller
@RequestMapping("/merchant")
public class MerchantPasswordResetController {
    @Autowired
    private UserService userService;
    @Autowired
    private MerchantPasswordResetService merchantPasswordResetService;
    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute("passwordResetForm")
    public  UserDto passwordReset(){
        return new UserDto();
    }

    @GetMapping("/user/password-reset")
    public String passwordResetPage(Model model){
        return "merchant_web/password-reset-page";
    }

    @PostMapping("/user/password-reset")
    public String passwordReset(@RequestParam("email") String email, HttpServletRequest request, Model model){
        User user = userService.findOneByEmail(email);
        if ((user == null)){
//            throw new UserServiceException(ErrorMessages.USER_NOT_FOUND.getErrorMessages());
            model.addAttribute("errorMessage","User Not Found");
            return "merchant_web/password-reset-page";
        }
        String token = UUID.randomUUID().toString();
        String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        merchantPasswordResetService.createPasswordResetTokenForUser(user, token);
        SimpleMailMessage simpleMailMessage = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(),token,user);
        mailSender.send(simpleMailMessage);
        return "merchant_web/password-reset-response-page";
    }


    @GetMapping("/user/changePassword")
    public String showChangePasswordPage(Locale locale, Model model, @RequestParam("id") Long id, @RequestParam("token") String token, HttpSession session){
        session.setAttribute("id",id);
        session.setAttribute("token",token);
        return "merchant_web/update-password";
    }


    @Transactional
    @PostMapping("/user/update-password")
    public String savePassword(Model model, @Valid UserDto userDto, BindingResult result,HttpSession session){
        Long id = (Long) session.getAttribute("id");
        String token = (String) session.getAttribute("token");
        User user = merchantPasswordResetService.validatePasswordResetToken(id, token);
        if (user != null){
            String newPassword = SecurityUtility.passwordEncoder().encode(userDto.getPassword());
            userService.updateUserPassword(newPassword, id);
            UserPasswordResetToken userPasswordResetToken =merchantPasswordResetService.findUserPasswordResetTokenByToken(token);
            merchantPasswordResetService.deleteToken(userPasswordResetToken);
        }
        return "redirect:/merchant/login";

    }

}
