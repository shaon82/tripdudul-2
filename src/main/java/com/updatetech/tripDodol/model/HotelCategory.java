package com.updatetech.tripDodol.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
public class HotelCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotelCategoryId;
    @NotNull
    @Size(min = 3, max = 15, message = "Category Name should be 3 to 15 character!")
    private String hotelCategoryName;


    @OneToMany(mappedBy = "hotelCategory", cascade = CascadeType.ALL)
    private Set<Hotel> hotels;


    public Long getHotelCategoryId() {
        return hotelCategoryId;
    }

    public void setHotelCategoryId(Long hotelCategoryId) {
        this.hotelCategoryId = hotelCategoryId;
    }

    public String getHotelCategoryName() {
        return hotelCategoryName;
    }

    public void setHotelCategoryName(String hotelCategoryName) {
        this.hotelCategoryName = hotelCategoryName;
    }

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }
}
