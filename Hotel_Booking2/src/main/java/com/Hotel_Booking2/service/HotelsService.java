package com.Hotel_Booking2.service;

import com.Hotel_Booking2.payload.HotelResponse;
import com.Hotel_Booking2.payload.HotelsDTO;

public interface HotelsService {
    HotelsDTO createHotels(HotelsDTO hotelsDTO);


    public HotelResponse getAllHotels(int pageNum, int pageSize, String sortBy, String sortDir);

    HotelsDTO getHotelsById(Long id);

    HotelsDTO updateHotel(HotelsDTO hotelsDTO, Long id);

    void deleteHotelDetails(Long id);
}
