package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.UserFile;
import com.updatetech.tripDodol.repository.UserFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class UserFileServiceImpl implements UserFileService{

    @Autowired
    private UserFileRepository userFileRepository;


    @Override
    public UserFile saveUserFile(UserFile userFile) {
        return userFileRepository.save(userFile);
    }

    @Override
    public UserFile findFileByUserId(Long id)throws FileNotFoundException {
        return userFileRepository.findUserFileByUserId(id);
    }
}
