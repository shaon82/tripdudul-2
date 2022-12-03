package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import com.updatetech.tripDodol.model.Hotel;
import com.updatetech.tripDodol.model.HotelAddress;
import com.updatetech.tripDodol.model.HotelFile;
import com.updatetech.tripDodol.service.HotelAddressService;
import com.updatetech.tripDodol.service.HotelFileService;
import com.updatetech.tripDodol.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HotelListController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelFileService hotelFileService;
    @Autowired
    private HotelAddressService hotelAddressService;


    @GetMapping("/hotelList")
    public String hotelListPage(Model model){
        List<Hotel>hotelList = hotelService.findAllHotel();
        List<HotelFile>hotelFileList = new ArrayList<>();
        for (Hotel hotel: hotelList){
            List<HotelFile>hotelFiles = hotelFileService.findFilesByhotelId(hotel.getId());
            hotelFileList.add(hotelFiles.get(0));
        }
        model.addAttribute("hotelList",hotelList);
        model.addAttribute("hotelFileList",hotelFileList);
        return "web/hotels-list";
    }


    @GetMapping("/search/hotel/{checkIn}/{checkOut}/{city}")
    public String searchedHotelList(Model model, @RequestParam("checkIn") Date checkIn, @RequestParam("checkOut") Date checkOut, @RequestParam("city") String city, HttpSession session){
        session.setAttribute("checkIn", checkIn);
        session.setAttribute("checkOut", checkOut);
        List<HotelAddress>hotelAddresses = hotelAddressService.findAddressByCity(city);
        List<Hotel>hotels = new ArrayList<>();
        List<HotelFile>hotelFileList= new ArrayList<>();
        for (HotelAddress address: hotelAddresses){
            Hotel hotel = hotelService.findHotelById(address.getHotel().getId());
            hotels.add(hotel);
            List<HotelFile>hotelFiles = hotelFileService.findFilesByhotelId(hotel.getId());
            hotelFileList.add(hotelFiles.get(0));
        }
        model.addAttribute("hotels", hotels);
        model.addAttribute("hotelFileList",hotelFileList);
        return "web/searched-hotels-list";
    }

}
