package com.updatetech.tripDodol.model.packageModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class PackageCategory implements Serializable {
    private static final Long serialVersionUID = 1234L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Category name should not be empty!")
    @Size(min = 5, max = 30,message = "Category name should be at least 5 to 30 character!")
    private String categoryName;

    private Date createdDate;
    private boolean active=false;

    @OneToMany(mappedBy = "packageCategory",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TripPackage>tripPackages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<TripPackage> getTripPackages() {
        return tripPackages;
    }

    public void setTripPackages(List<TripPackage> tripPackages) {
        this.tripPackages = tripPackages;
    }
}
