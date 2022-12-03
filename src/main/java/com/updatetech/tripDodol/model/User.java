package com.updatetech.tripDodol.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.updatetech.tripDodol.model.packageModel.PackageBooking;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;


@Entity

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @NotNull
//    @Size(min = 3,max = 10,message = "Length should be in between 3 to 10!")
    private String username;
//    @NotNull
//    @Size(min = 4,max = 10, message = "Password should be in between 4 to 10 character!")
    private String password;

    @Transient
    private String confirmPassword;
    private String nameInit;
//    @NotNull
//    @Size(min = 1,max = 10,message = "Length should be in between 3 to 10!")
    private String firstName;
//    @NotNull
//    @Size(min = 1,max = 10,message = "Length should be in between 3 to 10!")
    private String lastName;
    private String countryCode;
//    @Pattern(regexp = "[7-9][0-9]{9}")
//    @Size(max = 11, message = "Digits should be 11!")
    private String phoneNumber;
    private String city;
    private String country;
//    @Column(name = "email", nullable = false, updatable = false, unique = true)
    @NotEmpty(message = "Email should be not empty!")
//    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
    private String email;
    private String passPortNumberOrNIDNumber;
    private boolean enabled=true;

    @Nullable
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Hotel hotel;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @Nullable
    @JsonIgnore
    private UserPasswordResetToken userPasswordResetToken;


    @Transient
    private List<MultipartFile> files = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Room> rooms;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_category_id")
    private UserCategory userCategory;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private PackageBooking packageBooking;


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority>authorities = new HashSet<>();
        userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
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

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Nullable
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(@Nullable Hotel hotel) {
        this.hotel = hotel;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }

    @Nullable
    public UserPasswordResetToken getUserPasswordResetToken() {
        return userPasswordResetToken;
    }

    public void setUserPasswordResetToken(@Nullable UserPasswordResetToken userPasswordResetToken) {
        this.userPasswordResetToken = userPasswordResetToken;
    }

    public String getNameInit() {
        return nameInit;
    }

    public void setNameInit(String nameInit) {
        this.nameInit = nameInit;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPassPortNumberOrNIDNumber() {
        return passPortNumberOrNIDNumber;
    }

    public void setPassPortNumberOrNIDNumber(String passPortNumberOrNIDNumber) {
        this.passPortNumberOrNIDNumber = passPortNumberOrNIDNumber;
    }

    public PackageBooking getPackageBooking() {
        return packageBooking;
    }

    public void setPackageBooking(PackageBooking packageBooking) {
        this.packageBooking = packageBooking;
    }
}
