package com.updatetech.tripDodol.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class HotelPercentage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String percentage;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Hotel hotel;


    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Booking booking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "HotelPercentage{" +
                "id=" + id +
                ", percentage='" + percentage + '\'' +
                ", hotel=" + hotel +
                ", booking=" + booking +
                '}';
    }
}
