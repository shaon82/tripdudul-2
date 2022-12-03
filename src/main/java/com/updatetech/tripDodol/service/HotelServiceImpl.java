package com.updatetech.tripDodol.service;


import com.updatetech.tripDodol.model.*;
import com.updatetech.tripDodol.repository.HotelRepository;
import com.updatetech.tripDodol.utility.SecurityUtility;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class HotelServiceImpl implements HotelService{


    private static String UPLOAD_FOLDER="resources/static/projectFile/hotelPhoto/";

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelFileService hotelFileService;

    @Autowired
    private FilePathService filePathService;
    @Autowired
    private UserService userService;
    @Autowired
    private HotelContactService hotelContactService;
    @Autowired
    private HotelLinkService hotelLinkService;
    @Autowired
    private HotelAddressService hotelAddressService;


    @Override
    public Hotel createHotel(Hotel hotel) {
        hotel.setHotelStatus(HotelStatus.PENDING);
        Hotel hotel1 = hotelRepository.save(hotel);

        if (hotel1 != null && hotel1.getFiles() != null && hotel1.getFiles().size() > 0){
            for (MultipartFile file: hotel1.getFiles()){
                String fileName = file.getOriginalFilename();
                String fileExtension = FilenameUtils.getExtension(fileName);
                String modifiedFileName = "HotelPhoto"+"_"+hotel1.getId()+"."+fileExtension;
                File storeFile = filePathService.getFilePath(modifiedFileName, "hotelImage");

                if (storeFile != null){
                    try {
                        FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                HotelFile hotelFile = new HotelFile();
                hotelFile.setFileName(fileName);
                hotelFile.setFileExtension(fileExtension);
                hotelFile.setModifiedfileName(modifiedFileName);
                hotelFile.setHotel(hotel1);

                hotelFileService.saveHotel(hotelFile);
            }
        }
        return hotel1;
    }

    @Override
    public boolean isHotelPresent(String hotelName) {
        Hotel hotel = hotelRepository.findHotelByHotelName(hotelName);
        if (hotel != null){
            return true;
        }
        return false;
    }

    @Override
    public Hotel findHotelById(Long hotelId) {

        return hotelRepository.findHotelById(hotelId);
    }

    @Override
    public void deleteHotel(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

    @Override
    public List<Hotel> findAllHotel() {
        return hotelRepository.findAllHotelByStatus();
    }

    @Override
    public List<Hotel> searchHotelWithCity(String city) {
        return hotelRepository.searchHotelWithCity(city);
    }

    @Override
    public Hotel findHotelByUser(Long id) {
        return findHotelById(id);
    }

    @Override
    public List<Hotel> findAllPendingHotels() {
        return hotelRepository.findAllPendingHotels();
    }

    @Override
    public Hotel approvedHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel saveHotel(Hotel hotel)throws Exception {
    	System.out.println("Hotel Info: " + hotel);
        hotel.setHotelStatus(HotelStatus.PENDING);
        Hotel localHotel = hotelRepository.save(hotel);
        List<User>users = hotel.getUsers();
        for(User user: users){
            user.setHotel(hotel);
            user.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));
            Role role = new Role();
            role.setRoleId(2);
            role.setName("ROLE_MERCHANT_ADMIN");
            userService.saveUser(user,role);

        }

        if (localHotel != null && localHotel.getFiles() != null && localHotel.getFiles().size() > 0){
            for (MultipartFile file: localHotel.getFiles()){
                String fileName = file.getOriginalFilename();
                String fileExtension = FilenameUtils.getExtension(fileName);
                String modifiedFileName = "HotelPhoto"+"_"+localHotel.getId()+"."+fileExtension;
                File storeFile = filePathService.getFilePath(modifiedFileName, "hotelImage");

                if (storeFile != null){
                    try {
                        FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                HotelFile hotelFile = new HotelFile();
                hotelFile.setFileName(fileName);
                hotelFile.setFileExtension(fileExtension);
                hotelFile.setModifiedfileName(modifiedFileName);
                hotelFile.setHotel(localHotel);

                hotelFileService.saveHotel(hotelFile);
            }
        }


        for (HotelAddress hotelAddress:  hotel.getHotelAddresses()){
            hotelAddress.setHotel(hotel);
            hotelAddressService.saveHotelAddress(hotelAddress);
        }
        for (HotelLink hotelLink: hotel.getHotelLinks()){
            hotelLink.setHotel(hotel);
            hotelLinkService.saveHotel(hotelLink);
        }
        for (HotelContact hotelContact: hotel.getHotelContacts()){
            hotelContact.setHotel(hotel);
            hotelContactService.saveContact(hotelContact);
        }
        return localHotel;
    }

}
