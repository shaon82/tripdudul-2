package com.updatetech.tripDodol.model.packageModel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TripPackage implements Serializable {

    private static final Long serialVersionUID= 12345L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Package name should not be empty!")
    private String packageName;
    private double packagePrice;
    @NotEmpty(message = "Package duration should not be empty!")
    private String packageDuration;
    @NotEmpty(message = "Package description should not be empty!")
    private String description;
    @ElementCollection(targetClass = String.class)
    private List<String>amenities;
    @Column(name = "value_from")
    private String from;
    private String destination;
    private boolean active=false;

    @ManyToOne
    @JoinColumn(name = "package_category_id")
    @JsonIgnore
    private PackageCategory packageCategory;

    @OneToOne(mappedBy = "tripPackage")
    private PackageBooking packageBooking;

    @Transient
    private List<MultipartFile>files = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getPackageDuration() {
        return packageDuration;
    }

    public void setPackageDuration(String packageDuration) {
        this.packageDuration = packageDuration;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public PackageCategory getPackageCategory() {
        return packageCategory;
    }

    public void setPackageCategory(PackageCategory packageCategory) {
        this.packageCategory = packageCategory;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public PackageBooking getPackageBooking() {
        return packageBooking;
    }

    public void setPackageBooking(PackageBooking packageBooking) {
        this.packageBooking = packageBooking;
    }
}
