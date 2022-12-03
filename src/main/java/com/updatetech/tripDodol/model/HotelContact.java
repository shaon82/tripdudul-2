package com.updatetech.tripDodol.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class HotelContact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotelContactId;
//    @Pattern(regexp = "[7-9][0-9]{9}")
//    @Size(max = 11, message = "Mobile Number should be 11!")
    private String phoneNumber;
//    @NotEmpty(message = "Email should be not empty!")
//    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
    private String hotelEmail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    public Long getHotelContactId() {
        return hotelContactId;
    }

    public void setHotelContactId(Long hotelContactId) {
        this.hotelContactId = hotelContactId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
