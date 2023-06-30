package com.Hotel_Booking2.service;

import com.Hotel_Booking2.entity.User;
import com.Hotel_Booking2.payload.LoginDto;
import com.Hotel_Booking2.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    User register(RegisterDto registerDto);
}

