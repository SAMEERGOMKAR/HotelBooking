package com.Hotel_Booking2.service;

import com.Hotel_Booking2.payload.RoomDTO;

import java.util.List;

public interface RoomService {
    RoomDTO createRoom(Long hotelsId, RoomDTO roomDTO);

    List<RoomDTO> getRoomByHotelsId(Long hotelsId);

}