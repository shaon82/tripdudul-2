package com.updatetech.tripDodol.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String nameInit;
    @NotEmpty
    @Size(min = 1,max = 10,message = "First Name should be in between 1 to 10!")
    private String firstName;
    @NotEmpty
    @Size(min = 1,max = 10,message = "Last Name should be in between 1 to 10!")
    private String lastName;

    @NotEmpty(message = "Email should be not empty!")
    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
    private String email;

    private String numberInit;

    @Pattern(regexp = "[0][0-9]{10}",message = "Invalid mobile number!.")
    @Size(max = 11, message = "Digits should be 11!")
    private String number;

    private Date checkIn;

    private Date checkOut;
    private double totalAmount;
    @Nullable
    private Double percentageAmount;
    private Long hotelId;
    private Status status;

    @OneToOne
    @JsonIgnore
    private Room room;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "booking")
    @JsonIgnore
    @Nullable
    private HotelPercentage hotelPercentage;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Guest>guests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameInit() {
        return nameInit;
    }

    public void setNameInit(String nameInit) {
        this.nameInit = nameInit;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberInit() {
        return numberInit;
    }

    public void setNumberInit(String numberInit) {
        this.numberInit = numberInit;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Guest> getGuests() {
        if (guests == null){
            guests= new ArrayList<Guest>();
        }
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }


    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Nullable
    public Double getPercentageAmount() {
        return percentageAmount;
    }

    public void setPercentageAmount(@Nullable Double percentageAmount) {
        this.percentageAmount = percentageAmount;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public HotelPercentage getHotelPercentage() {
        return hotelPercentage;
    }

    public void setHotelPercentage(HotelPercentage hotelPercentage) {
        this.hotelPercentage = hotelPercentage;
    }
}
