package com.updatetech.tripDodol.utility;


import com.updatetech.tripDodol.model.HotelStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class HotelStatusConverter implements AttributeConverter<HotelStatus, String> {
    @Override
    public String convertToDatabaseColumn(HotelStatus hotelStatus) {
        if (hotelStatus == null){
            return null;
        }
        return hotelStatus.getStatus();
    }

    @Override
    public HotelStatus convertToEntityAttribute(String hotelStatus) {
        if (hotelStatus == null){
            return null;
        }
        try {
            return HotelStatus.valueOf(hotelStatus);
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
