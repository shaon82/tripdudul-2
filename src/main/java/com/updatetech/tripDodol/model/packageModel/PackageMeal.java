package com.updatetech.tripDodol.model.packageModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class PackageMeal implements Serializable {
    private static final Long serialVersionUID = 152L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String mealName;
    private boolean status = false;


    @ManyToOne
    @JoinColumn(name = "trip_package_id")
    @JsonIgnore
    private TripPackage tripPackage;

    @OneToMany(mappedBy = "packageMeal", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MealItem>mealItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TripPackage getTripPackage() {
        return tripPackage;
    }

    public void setTripPackage(TripPackage tripPackage) {
        this.tripPackage = tripPackage;
    }

    public List<MealItem> getMealItems() {
        return mealItems;
    }

    public void setMealItems(List<MealItem> mealItems) {
        this.mealItems = mealItems;
    }
}
