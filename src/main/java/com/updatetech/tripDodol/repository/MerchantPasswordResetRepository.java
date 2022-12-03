package com.updatetech.tripDodol.repository;

import com.updatetech.tripDodol.model.UserPasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantPasswordResetRepository extends JpaRepository<UserPasswordResetToken, Long> {
    UserPasswordResetToken findByToken(String token);

    UserPasswordResetToken findUserPasswordResetTokenByToken(String token);
}
