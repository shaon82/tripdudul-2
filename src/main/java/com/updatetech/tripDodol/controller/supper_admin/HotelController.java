package com.updatetech.tripDodol.controller.supper_admin;


import com.updatetech.tripDodol.model.*;
import com.updatetech.tripDodol.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes("hotelList")
public class    HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelCategoryService hotelCategoryService;
    @Autowired
    private HotelFileService hotelFileService;
    @Autowired
    private HotelAddressService hotelAddressService;
    @Autowired
    private HotelContactService hotelContactService;
    @Autowired
    private HotelLinkService hotelLinkService;
    @Autowired
    private RoomService roomService;


        @GetMapping("/allHotel")
    public String hotelAllPage(Model model){

        List<Hotel> hotelList = hotelService.findAllHotel();
        List<HotelFile> hotelFilesList= new ArrayList<>();
        for (Hotel hotel: hotelList){
            List<HotelFile>hotelFiles = hotelFileService.findFilesByhotelId(hotel.getId());
            hotelFilesList.add(hotelFiles.get(0));

        }
        model.addAttribute("hotelList",hotelList);
        model.addAttribute("hotelAddresses", hotelAddressService.findAllAddress());
        model.addAttribute("hotelContactList", hotelContactService.findAllContact());
        model.addAttribute("hotelFileList",hotelFilesList);
        return "admin/hotel-all";
    }


    @GetMapping("/addHotel")
    public String hotelAddPage(Model model, Hotel hotel){
        HotelCategory hotelCategory = new HotelCategory();
        model.addAttribute("hotel", hotel);
        model.addAttribute("hotelCategory", hotelCategory);
        model.addAttribute("hotelCategoryList", hotelCategoryService.findAll());
        List<Hotel>hotelList = hotelService.findAllHotel();
        model.addAttribute("hotelList", hotelList);
        return "admin/hotel-add";
    }

    @GetMapping("/all/pending/hotels")
    public String findAllPendingHotels(Model model){
        List<Hotel> hotels=hotelService.findAllPendingHotels();
        List<HotelFile>hotelFilesList = new ArrayList<>();
        for (Hotel hotel: hotels){
            List<HotelFile> hotelFiles = hotelFileService.findFilesByhotelId(hotel.getId());
            hotelFilesList.add(hotelFiles.get(0));
        }
        model.addAttribute("hotels",hotels);
        model.addAttribute("hotelFileList",hotelFilesList);
        return "admin/pending-hotels";
    }


    @PostMapping("/addHotel")
    public String saveHotel(Model model, @Valid Hotel hotel, BindingResult result){

        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "admin/hotel-add";
        }else if (hotelService.isHotelPresent(hotel.getHotelName())){
            model.addAttribute("hotelExist", true);
            return "admin/hotel-add";
        }

            hotelService.createHotel(hotel);
        model.addAttribute("hotelCategory","");
        return "redirect:/admin/hotel-contact";
    }


    @GetMapping("/delete-hotel/{hotelId}")
    public String deleteHotel(Model model, @PathVariable("hotelId") Long hotelId){
        Hotel hotel = hotelService.findHotelById(hotelId);
        List<HotelFile> hotelFiles = hotelFileService.findFilesByhotelId(hotelId);
        for (HotelFile files: hotelFiles){
            File file = new  File("/home/shaon/updateTech/tripDodol/src/main/webapp/hotelImage/"+files.getModifiedfileName());
            if (file.exists()){
                file.delete();
            }
            hotelFileService.deleteFile(files);
        }
        HotelContact hotelContact = hotelContactService.findHotelContactByHotelId(hotelId);
        HotelLink hotelLink = hotelLinkService.findHotelLinkByHotelId(hotelId);
        HotelAddress hotelAddress = hotelAddressService.findHotelAddressByHotelId(hotelId);
        hotelContactService.delete(hotelContact);
        hotelLinkService.deleteHotelLink(hotelLink);
        hotelAddressService.delete(hotelAddress);
        hotelService.deleteHotel(hotel);
        return "redirect:/admin/allHotel";
    }


    @GetMapping("/hotel/approve/{hotelId}")
    public String hotelApproveButton(Model model, @PathVariable(value = "hotelId") Long hotelId){
        Hotel hotel = hotelService.findHotelById(hotelId);
        hotel.setHotelStatus(HotelStatus.ACTIVE);
        hotelService.approvedHotel(hotel);
        return "redirect:/admin/allHotel";
    }

}
