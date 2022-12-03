package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.Exception.ErrorMessages;
import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.model.UserPasswordResetToken;
import com.updatetech.tripDodol.repository.MerchantPasswordResetRepository;
import com.updatetech.tripDodol.service.ExceptionService.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class MerchantPasswordResetServiceImpl implements MerchantPasswordResetService{

    @Autowired
    private MerchantPasswordResetRepository merchantPasswordResetRepository;

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        UserPasswordResetToken userPasswordResetToken = new UserPasswordResetToken(token, user);
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 15);
        Date expireTime = calendar.getTime();
        userPasswordResetToken.setExpiryDate(expireTime);
        merchantPasswordResetRepository.save(userPasswordResetToken);
    }

    @Override
    public User validatePasswordResetToken(Long id, String token) {
        UserPasswordResetToken userPasswordResetToken = merchantPasswordResetRepository.findUserPasswordResetTokenByToken(token);
        if (userPasswordResetToken == null || (userPasswordResetToken.getUser().getId() == id)){
            throw new UserServiceException(ErrorMessages.INVALID_TOKEN.getErrorMessages());
        }
        Calendar calendar = Calendar.getInstance();
        if ((userPasswordResetToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <=0){
            throw new UserServiceException(ErrorMessages.TOKEN_TIME_EXPIRED.getErrorMessages());
        }
        User user = userPasswordResetToken.getUser();
        return user;
    }

    @Override
    public UserPasswordResetToken findUserPasswordResetTokenByToken(String token) {
        return merchantPasswordResetRepository.findUserPasswordResetTokenByToken(token);
    }

    @Override
    public void deleteToken(UserPasswordResetToken userPasswordResetToken) {
        merchantPasswordResetRepository.delete(userPasswordResetToken);
    }

}
