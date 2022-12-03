package com.updatetech.tripDodol.repository;

import com.updatetech.tripDodol.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findOneByEmail(String email);

    User findUserById(long id);

    @Query(value = "select * from user u JOIN user_role ur ON u.id = ur.user_id " +
            "JOIN role r ON ur.role_id = r.role_id where u.hotel_id=? AND r.name ='ROLE_MERCHANT_MANAGER'", nativeQuery = true)
    List<User> findUserByRoleAndHotelId(Long hotelId);

    @Modifying
    @Query(value = "update user set password=? where id=?", nativeQuery = true)
    void updatePasswordById(String password, Long id);

    User findUserByEmail(String email);
}
