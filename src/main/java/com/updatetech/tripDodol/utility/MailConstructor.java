package com.updatetech.tripDodol.utility;

import com.updatetech.tripDodol.model.Booking;
import com.updatetech.tripDodol.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Component
public class MailConstructor {

    @Autowired
    private Environment environment;

    public SimpleMailMessage bookingConfirmationTokenMail(Locale locale, Booking booking){
//        String url = contextPath+"/room/save-booking?token="+token;
        String message = "\nPlease wait some moment. We will send You confirmation message. thank you.";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(booking.getEmail());
        email.setSubject("Request for booking.");
        email.setText(message);
        email.setFrom(environment.getProperty("support.email"));
        return email;
    }


    public SimpleMailMessage bookingMailConfirmation(String contextPath, Locale locale,String token, Booking booking){
        String url = contextPath+"/download-pdf?token="+token;
        String message = "Your booking is confirmed. Please click the link for download the invoice. Bring this invoice for entering the hotel. Thanks.\n";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(booking.getEmail());
        email.setSubject("Booking confirmation.");
        email.setText(message+url);
        email.setFrom(environment.getProperty("support.email"));
        return email;
    }


    public SimpleMailMessage constructResetTokenEmail( String contextPath, Locale locale, String token, User user){
        String url = contextPath+"/merchant/user/changePassword?id="+user.getId()+"&token="+token;
        String message = "Please click this link for reset your password. thanks.\n";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("For reset password.");
        email.setText(message+url);
        email.setFrom(environment.getProperty("support.email"));
        return email;
    }
}
