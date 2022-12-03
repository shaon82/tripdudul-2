package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.model.UserPasswordResetToken;

public interface MerchantPasswordResetService {
    void createPasswordResetTokenForUser(User user, String token);

    User validatePasswordResetToken(Long id, String token);

    UserPasswordResetToken findUserPasswordResetTokenByToken(String token);

    void deleteToken(UserPasswordResetToken userPasswordResetToken);
}
