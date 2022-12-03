package com.updatetech.tripDodol.model;


import com.fasterxml.jackson.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roomNumber;
    private String roomType;
    private double roomPrice;
    @ElementCollection(targetClass = String.class)
    @JsonIgnore
    private List<String> roomAmenities = new ArrayList<>();
    private String roomDescription;
    private boolean active=true;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_category_id")
    @JsonIgnore
    private RoomCategory roomCategory;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonIgnore
    private Hotel hotel;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "room")
    @JsonIgnore
    private Booking booking;

    @JsonIgnore
    @Transient
    private List<MultipartFile> roomFiles = new ArrayList<MultipartFile>();
    @JsonIgnore
    @Transient
    private List<String> removeRoomImage = new ArrayList<String>();


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public List<String> getRoomAmenities() {
        return roomAmenities;
    }

    public void setRoomAmenities(List<String> roomAmenities) {
        this.roomAmenities = roomAmenities;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<MultipartFile> getRoomFiles() {
        return roomFiles;
    }

    public void setRoomFiles(List<MultipartFile> roomFiles) {
        this.roomFiles = roomFiles;
    }

    public List<String> getRemoveRoomImage() {
        return removeRoomImage;
    }

    public void setRemoveRoomImage(List<String> removeRoomImage) {
        this.removeRoomImage = removeRoomImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }


    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
