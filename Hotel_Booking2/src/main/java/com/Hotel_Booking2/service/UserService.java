package com.Hotel_Booking2.service;

import com.Hotel_Booking2.payload.HotelResponse;
import com.Hotel_Booking2.payload.HotelsDTO;

import java.util.List;

public interface UserService {
    HotelResponse getAllHotels(int pageNum, int pageSize, String sortBy, String sortDir);

    HotelsDTO getHotelsById(Long id);

    List<HotelsDTO> findByLocation(String location);
}

