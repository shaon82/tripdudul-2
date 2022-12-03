package com.updatetech.tripDodol.model.packageModel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.updatetech.tripDodol.model.User;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class PackageBooking implements Serializable {
    private static final long SerialVersionUID = 87L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameInit;
    @Transient
    private String firstName;
    @Transient
    private String lastName;
    @NotEmpty(message = "Email should be not empty!")
    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
    private String email;
    @Transient
//    @NotNull
//    @Size(min = 4,max = 10, message = "Password should be in between 4 to 10 character!")
    private String password;
    @Transient
    private String ConfirmPassword;
    private String countryCode;
    @Pattern(regexp = "[0][0-9]{10}",message = "Invalid mobile number!.")
    @Size(max = 11, message = "Digits should be 11!")
    private String phoneNumber;
    private String city;
    private String country;
    private String passPortNumberOrNIDNumber;
    private Date fromDate;
    private Date toDate;
    private double PackagePrice;
    private PackageBookingStatus packageBookingStatus;

    @OneToOne(cascade = CascadeType.ALL)
    private TripPackage tripPackage;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<PackageGuest>packageGuests;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getPassPortNumberOrNIDNumber() {
        return passPortNumberOrNIDNumber;
    }

    public void setPassPortNumberOrNIDNumber(String passPortNumberOrNIDNumber) {
        this.passPortNumberOrNIDNumber = passPortNumberOrNIDNumber;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public PackageBookingStatus getPackageBookingStatus() {
        return packageBookingStatus;
    }

    public void setPackageBookingStatus(PackageBookingStatus packageBookingStatus) {
        this.packageBookingStatus = packageBookingStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PackageGuest> getPackageGuests() {
        if (packageGuests == null){
            packageGuests = new ArrayList<PackageGuest>();
        }
        return packageGuests;
    }

    public void setPackageGuests(List<PackageGuest> packageGuests) {
        this.packageGuests = packageGuests;
    }

    public TripPackage getTripPackage() {
        return tripPackage;
    }

    public void setTripPackage(TripPackage tripPackage) {
        this.tripPackage = tripPackage;
    }

    public double getPackagePrice() {
        return PackagePrice;
    }

    public void setPackagePrice(double packagePrice) {
        PackagePrice = packagePrice;
    }
}
