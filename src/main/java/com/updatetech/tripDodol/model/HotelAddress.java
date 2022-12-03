package com.updatetech.tripDodol.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class HotelAddress implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotelAddressId;
    @NotNull
    private String streeAddress;
    @NotNull
    private String block;
    @NotNull
    private String city;
    @NotNull
    private String country;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    public Long getHotelAddressId() {
        return hotelAddressId;
    }

    public void setHotelAddressId(Long hotelAddressId) {
        this.hotelAddressId = hotelAddressId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getStreeAddress() {
        return streeAddress;
    }

    public void setStreeAddress(String streeAddress) {
        this.streeAddress = streeAddress;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
