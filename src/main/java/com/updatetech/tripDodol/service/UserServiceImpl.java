package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.Role;
import com.updatetech.tripDodol.model.User;
import com.updatetech.tripDodol.model.UserFile;
import com.updatetech.tripDodol.model.UserRole;
import com.updatetech.tripDodol.repository.RoleRepository;
import com.updatetech.tripDodol.repository.UserRepository;
import com.updatetech.tripDodol.repository.UserRoleRepository;
import com.updatetech.tripDodol.utility.SecurityUtility;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOG = LoggerFactory.getLogger(UserRepository.class);


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private FilePathService filePathService;
    @Autowired
    private UserFileService userFileService;
    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public User createUser(User user, Set<UserRole> userRoles)throws Exception {
        String filePath = "/home/shaon/updateTech/tripDodol/src/main/webapp/hotelImage/";
        System.out.print("this is user email: "+user.getEmail());
        User localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null){
            LOG.info("user {} already exists. Nothing will be done.",user.getUsername());
        }else {
            for (UserRole userRole: userRoles){
                roleRepository.save(userRole.getRole());

            }
            user.getUserRoles().addAll(userRoles);
            localUser = userRepository.save(user);
        }
        return localUser;
    }

    @Override
    public User createManager(User manager, Role role)throws Exception {
        User localUser = userRepository.findByUsername(manager.getUsername());
        if (localUser != null){
            LOG.info("user {} already exists. Nothing will be done.",manager.getUsername());
        }else {
            saveUser(manager,role);

            }
        if (localUser != null && localUser.getFiles() != null && localUser.getFiles().size() > 0) {
            for (MultipartFile file : localUser.getFiles()) {
                String fileName = file.getOriginalFilename();
                String fileExtension = FilenameUtils.getExtension(fileName);
                String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + localUser.getId() + "." + fileExtension;
                File storeFile = filePathService.getManagerFilePath(modifiedFileName, "manager");

                if (storeFile != null){
                    try {
                        FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                UserFile userFile = new UserFile();
                userFile.setFileName(fileName);
                userFile.setFileExtension(fileExtension);
                userFile.setModifiedfileName(modifiedFileName);
                userFile.setUser(localUser);

                userFileService.saveUserFile(userFile);
            }
        }
        return localUser;
    }

    @Override
    public List<User> findUserByHotelIdAndRole(Long id) {
        return userRepository.findUserByRoleAndHotelId(id);
    }

    @Override
    public void updateUserPassword(String newPassword, Long id) {
        userRepository.updatePasswordById(newPassword, id);
    }

    @Override
    public boolean isUserPresent(String email) {
        User user = userRepository.findOneByEmail(email);
        if (user != null){
            return true;
        }
        return false;
    }

    @Override
    public User saveUser(User user, Role role) {
        Role role1 = roleRepository.save(role);
        User user1 = userRepository.save(user);
        UserRole userRole = new UserRole();
        userRole.setRole(role1);
        userRole.setUser(user1);
        userRoleRepository.save(userRole);
        return user;
    }

    @Override
    public User findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
