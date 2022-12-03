package com.updatetech.tripDodol.controller.hotel.MerchantController;

import com.updatetech.tripDodol.model.*;
import com.updatetech.tripDodol.service.*;
import com.updatetech.tripDodol.utility.MailConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/merchant")
public class MerchantBookingController {

    @Autowired
    private HotelBookingService hotelBookingService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomCategoryService roomCategoryService;
    @Autowired
    private MailConstructor mailConstructor;
    @Autowired
    private GuestService guestService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserReportService userReportService;
    @Autowired
    private HotelPercentageService hotelPercentageService;


    @GetMapping("/booking/all")
    public String allBooking(Model model,Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        List<Booking>bookings = hotelBookingService.findBookingByHotelId(hotel.getId());
        model.addAttribute("bookings",bookings);
        return "merchant_web/hotel-booking-all";
    }

//    Search booking by date

    @GetMapping("/find/booking")
    public String findBookingByDate(Model model, @RequestParam("checkIn") Date fromDate, @RequestParam("checkOut") Date toDate, HttpSession session, HttpServletResponse response,Principal principal) throws ParseException {
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        List<Booking>bookings = hotelBookingService.findBookingByDate(hotel.getId(),fromDate, toDate);
        userReportService.generateHistoryReport(bookings,response,hotel.getHotelName());
        model.addAttribute("bookings",bookings);
        return "merchant_web/hotel-booking-all";
    }

//    add booking page

    @GetMapping("/add/booking")
    public String addBookingPage(Model model, Principal principal, HttpSession session) {
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        Hotel hotel = hotelService.findHotelByUser(user.getHotel().getId());
        session.setAttribute("hotelId", hotel.getId());
        List<RoomCategory>roomCategories = roomCategoryService.findRoomCategoryByHotelId(hotel.getId());
        List<Room>roomList = roomService.findRoomByHotelId(hotel.getId());
        Booking booking = new Booking();
        booking.getGuests().add(new Guest());
        model.addAttribute("roomCategories",roomCategories);
        model.addAttribute("roomList",roomList);
        model.addAttribute("booking",booking);
    	return "merchant_web/merchant-add-booking";
    }

//    save booking

    @PostMapping("/save/booking")
    public String saveBooking(Model model, HttpSession session, @Valid Booking booking, BindingResult result,
                              HttpServletRequest request){
        Long roomId = booking.getRoom().getId();
        Room room = roomService.findRoomByRoomId(roomId);
        room.setActive(false);
        booking.setHotelId((Long) session.getAttribute("hotelId"));
        booking.setStatus(Status.CONFIRM);
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "merchant_web/merchant-add-booking";
        }
        roomService.saveRoom(room);
        hotelBookingService.save(booking);
        guestService.saveAll(booking.getGuests());
//        String token = UUID.randomUUID().toString();
//        String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
//        SimpleMailMessage email= mailConstructor.bookingConfirmationTokenMail(appUrl,request.getLocale(), token,booking);
//        mailSender.send(email);

        model.addAttribute("success", true);

        return "redirect:/merchant/room-list";
    }

//    check out method

    @GetMapping("/check-out/booking/{id}")
    public String checkOutBooking(Model model, @PathVariable("id") Long id){
        Booking booking = hotelBookingService.findBookingById(id);
        Room room = roomService.findRoomByRoomId(booking.getRoom().getId());
        room.setActive(true);
        booking.setStatus(Status.DEFAULT);
        hotelBookingService.save(booking);
        roomService.saveRoom(room);
        return "redirect:/merchant/room-list";
    }

//    booking approved method

    @GetMapping("/booking/approved/{id}")
    public String approvedBooking(@PathVariable("id") Long id, HttpServletRequest request){
        Booking booking = hotelBookingService.findBookingById(id);
        Hotel hotel = hotelService.findHotelById(booking.getHotelId());
        HotelPercentage hotelPercentage = hotelPercentageService.findPercentageByHotelId(hotel.getId());
        double d = Double.parseDouble(hotelPercentage.getPercentage());
        double totalTaka = (d/100)*booking.getTotalAmount();
        booking.setPercentageAmount(totalTaka);
        booking.setStatus(Status.CONFIRM);
        String token = UUID.randomUUID().toString();
        String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        SimpleMailMessage email= mailConstructor.bookingMailConfirmation(appUrl,request.getLocale(),token,booking);
        mailSender.send(email);
        hotelBookingService.save(booking);
        return "redirect:/merchant/room-list";
    }

    // method for reject booking

    @GetMapping("/reject/booking/{bookingId}")
    public String rejectBooking(Model model, @PathVariable("bookingId") Long bookingId){
        Booking booking = hotelBookingService.findBookingById(bookingId);
        hotelBookingService.deleteBooking(booking);
        Room room = roomService.findRoomByRoomId(booking.getRoom().getId());
        room.setActive(true);
        roomService.saveRoom(room);
        return "redirect:/merchant/current/booking/information";
    }
}
