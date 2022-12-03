package com.updatetech.tripDodol.utility;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GenerateInvoiceNumber {

    private static final String SALT = "salt";

    @Bean
    public static String generateInvoiceNumberString(){
        String SALTCHARS = "ABCEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        StringBuilder salt = new StringBuilder();
        Random random = new Random();

        while (salt.length()<4){
            int index= (int) (random.nextFloat()*SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        String saltStr = salt.toString();
        return saltStr;
    }
}
