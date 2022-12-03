package com.updatetech.tripDodol.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class HotelLink implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotelLinkId;
    private String facebookLink;
    private String twitterLink;
    private String LinkedinLink;
    private String instagramLink;
    private String whatsAppNumber;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    public Long getHotelLinkId() {
        return hotelLinkId;
    }

    public void setHotelLinkId(Long hotelLinkId) {
        this.hotelLinkId = hotelLinkId;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getLinkedinLink() {
        return LinkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        LinkedinLink = linkedinLink;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    public String getWhatsAppNumber() {
        return whatsAppNumber;
    }

    public void setWhatsAppNumber(String whatsAppNumber) {
        this.whatsAppNumber = whatsAppNumber;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
