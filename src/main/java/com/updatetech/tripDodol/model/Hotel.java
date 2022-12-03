package com.updatetech.tripDodol.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hotel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @NotNull
//    @Size(min = 3,max = 20,message = "Hotel name should be in between 3 to 20!")
    private String hotelName;
    private String description;
    @ElementCollection(targetClass = String.class)
    private List<String> amenities = new ArrayList<>();

    @Transient
    private List<MultipartFile> files = new ArrayList<MultipartFile>();
    @Transient
    private List<String> removeImage = new ArrayList<String>();


    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users;


        @ManyToOne
        @JoinColumn(name = "hotel_category_id")
        private HotelCategory hotelCategory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    private List<HotelContact>hotelContacts;


    @OneToMany(cascade = CascadeType.ALL)
    private List<HotelAddress> hotelAddresses;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    @Nullable
    private List<HotelLink> hotelLinks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
    @JsonIgnore
    private List<Room> rooms;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    @JsonIgnore
    private List<UserCategory>userCategories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    @JsonIgnore
    private List<Employee>employees;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    @JsonIgnore
    private List<RoomCategory>roomCategories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    @JsonIgnore
    private List<HotelExpense>hotelExpenses;

    @OneToOne(mappedBy = "hotel",cascade = CascadeType.ALL)
    @JsonIgnore
    @Nullable
    private HotelPercentage hotelPercentage;

    private HotelStatus hotelStatus;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public List<String> getRemoveImage() {
        return removeImage;
    }

    public void setRemoveImage(List<String> removeImage) {
        this.removeImage = removeImage;
    }

    public List<HotelAddress> getHotelAddresses() {
        if (hotelAddresses == null){
            hotelAddresses = new ArrayList<HotelAddress>();
        }
        return hotelAddresses;
    }

    public void setHotelAddresses(List<HotelAddress> hotelAddresses) {
        this.hotelAddresses = hotelAddresses;
    }

    public HotelCategory getHotelCategory() {
        return hotelCategory;
    }

    public void setHotelCategory(HotelCategory hotelCategory) {
        this.hotelCategory = hotelCategory;
    }

    @Nullable
    public List<HotelLink> getHotelLinks() {
        if (hotelLinks == null){
            hotelLinks = new ArrayList<HotelLink>();
        }
        return hotelLinks;
    }

    public void setHotelLinks(@Nullable List<HotelLink> hotelLinks) {
        this.hotelLinks = hotelLinks;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public HotelStatus getHotelStatus() {
        return hotelStatus;
    }

    public void setHotelStatus(HotelStatus hotelStatus) {
        this.hotelStatus = hotelStatus;
    }

    public List<User> getUsers() {
        if (users == null){
            users=new ArrayList<User>();
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<HotelContact> getHotelContacts() {
        if (hotelContacts == null){
            hotelContacts = new ArrayList<HotelContact>();
        }
        return hotelContacts;
    }

    public void setHotelContacts(List<HotelContact> hotelContacts) {
        this.hotelContacts = hotelContacts;
    }

    public List<UserCategory> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(List<UserCategory> userCategories) {
        this.userCategories = userCategories;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<RoomCategory> getRoomCategories() {
        return roomCategories;
    }

    public void setRoomCategories(List<RoomCategory> roomCategories) {
        this.roomCategories = roomCategories;
    }

    public List<HotelExpense> getHotelExpenses() {
        return hotelExpenses;
    }

    public void setHotelExpenses(List<HotelExpense> hotelExpenses) {
        this.hotelExpenses = hotelExpenses;
    }

    public HotelPercentage getHotelPercentage() {
        return hotelPercentage;
    }

    public void setHotelPercentage(HotelPercentage hotelPercentage) {
        this.hotelPercentage = hotelPercentage;
    }
}
