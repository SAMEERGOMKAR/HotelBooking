package com.Hotel_Booking2.payload;

import lombok.Data;

import javax.persistence.Column;

@Data
public class RegisterDto {

    private Long id;


    private String firstName ;

    private String lastName ;

    private String username;

    private String email;

    private String password;
}
