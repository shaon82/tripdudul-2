package com.updatetech.tripDodol.controller.hotel.Web_Controller;


import com.updatetech.tripDodol.model.*;
import com.updatetech.tripDodol.service.*;
import com.updatetech.tripDodol.utility.MailConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/hotel-booking")
public class BookingController {

    @Autowired
    private HotelBookingService hotelBookingService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomFileService roomFileService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private GuestService guestService;
    @Autowired
    private MailConstructor mailConstructor;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserReportService userReportService;


    @GetMapping("/booking/room/{roomId}")
    public String bookingPage(Model model, @PathVariable Long roomId, HttpSession session, Principal principal) {
        session.setAttribute("roomId", roomId);
        String sessionId = session.getId();
        Room room = roomService.findRoomByRoomId(roomId);
        List<RoomFile> files = roomFileService.findFileByRoomId(room.getId());
        Hotel hotel = hotelService.findHotelById(room.getHotel().getId());
        session.setAttribute("hotelId", hotel.getId());

        Booking booking = new Booking();
        booking.getGuests().add(new Guest());

        model.addAttribute("room", room);
        model.addAttribute("files", files);
        model.addAttribute("hotel", hotel);
        model.addAttribute("booking", booking);
        return "web/cart";
    }

    @PostMapping("/room/save-booking")
    public String createBooking(Model model, @Valid Booking booking, BindingResult result, HttpSession session, HttpServletRequest request,
                                Principal principal) {
        Long hotelId = (Long) session.getAttribute("hotelId");
        Hotel hotel = hotelService.findHotelById(hotelId);
        if (result.hasErrors()) {
            model.addAttribute("hotel", hotel.getHotelName());
            model.addAttribute("error", true);
            return "web/cart";
        }
        Long roomId = (Long) session.getAttribute("roomId");
        Date checkin = (Date) session.getAttribute("checkIn");
        Date checkout = (Date) session.getAttribute("checkOut");
        Room room = roomService.findRoomByRoomId(roomId);
        room.setActive(false);
        booking.setRoom(room);
        booking.setCheckIn(checkin);
        booking.setCheckOut(checkout);

        System.out.println("post hotel name: " + hotel.getHotelName());
        booking.setHotelId(hotel.getId());
        booking.setStatus(Status.PENDING);

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        roomService.saveRoom(room);
        hotelBookingService.save(booking);
        guestService.saveAll(booking.getGuests());
        userReportService.generateReport(bookings, hotel.getHotelName(), room.getRoomNumber(), room.getRoomType(), room.getRoomPrice());
        String token = UUID.randomUUID().toString();
        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        SimpleMailMessage email = mailConstructor.bookingConfirmationTokenMail(request.getLocale(), booking);
        mailSender.send(email);
        System.out.println("Booking : " + booking.getFirstName());

        model.addAttribute("success", true);
        return "redirect:/";
    }


    @GetMapping("/download-pdf")
    public void downloadPdf(HttpServletResponse response) throws FileNotFoundException {
        File file = new File("reports");
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=/"
                + "HotelInvoice.pdf" + "/");

        try {
            FileInputStream fileInputStream = new FileInputStream(file + "/" + "HotelInvoice.pdf");
            int i;
            while ((i = fileInputStream.read()) != -1) {
                response.getWriter().write(i);
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        userReportService.deletePdf(file + "/");

    }


    @GetMapping("/bookingAll")
    public String bookingAllPage() {
        return "web/booking-all";
    }


    @GetMapping("/bookingCarRental")
    public String bookingCarRentalPage() {
        return "web/booking-car-rentals";
    }


    @GetMapping("/bookingFlight")
    public String bookingFlightPage() {
        return "web/booking-flight";
    }


    @GetMapping("/bookingHotel")
    public String bookingHotelPage() {
        return "web/booking-hotel";
    }


    @GetMapping("/bookingSlider")
    public String bookingSliderPage() {
        return "web/booking-slider";
    }

    @GetMapping("/bookingTourPackage")
    public String bookingTourPackagePage() {
        return "web/booking-tour-package";
    }
}
