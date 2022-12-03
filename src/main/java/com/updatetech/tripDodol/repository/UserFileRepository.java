package com.updatetech.tripDodol.repository;

import com.updatetech.tripDodol.model.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.FileNotFoundException;

public interface UserFileRepository extends JpaRepository<UserFile, Long> {

    UserFile findUserFileByUserId(Long id);
}
