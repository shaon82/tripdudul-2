package com.updatetech.tripDodol.model.packageModel.dto;

import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

public class PackageUserDto {

    private String password;
    private String nameInit;
    private String firstName;
    private String lastName;
    private String countryCode;
    private String phoneNumber;
    private String city;
    private String country;
    private String email;
    private String passPortNumberOrNIDNumber;

    public PackageUserDto() {
    }

    public PackageUserDto(String password, String nameInit, String firstName, String lastName, String countryCode, String phoneNumber, String city, String country, String email, String passPortNumberOrNIDNumber) {
        this.password = password;
        this.nameInit = nameInit;
        this.firstName = firstName;
        this.lastName = lastName;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.country = country;
        this.email = email;
        this.passPortNumberOrNIDNumber = passPortNumberOrNIDNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassPortNumberOrNIDNumber() {
        return passPortNumberOrNIDNumber;
    }

    public void setPassPortNumberOrNIDNumber(String passPortNumberOrNIDNumber) {
        this.passPortNumberOrNIDNumber = passPortNumberOrNIDNumber;
    }
}
