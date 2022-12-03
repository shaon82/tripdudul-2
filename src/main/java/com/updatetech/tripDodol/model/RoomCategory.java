package com.updatetech.tripDodol.model;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class RoomCategory implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roomCategoryName;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Room>rooms;

    @ManyToOne
    @JsonIgnore
    private Hotel hotel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomCategoryName() {
        return roomCategoryName;
    }

    public void setRoomCategoryName(String roomCategoryName) {
        this.roomCategoryName = roomCategoryName;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
