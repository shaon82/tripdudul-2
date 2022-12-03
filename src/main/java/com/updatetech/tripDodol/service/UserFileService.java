package com.updatetech.tripDodol.service;

import com.updatetech.tripDodol.model.UserFile;

import java.io.FileNotFoundException;

public interface UserFileService {
    UserFile saveUserFile(UserFile userFile);

    UserFile findFileByUserId(Long id)throws FileNotFoundException;
}
