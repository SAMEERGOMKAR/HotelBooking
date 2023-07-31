package com.Hotel_Booking2.controller;

import com.Hotel_Booking2.payload.RoomDTO;
import com.Hotel_Booking2.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomsController {

    private final RoomService roomService;

    public RoomsController(RoomService roomService){
        this.roomService = roomService;
    }

    @PostMapping("/hotels/{hotelsId}/rooms")
    public ResponseEntity<RoomDTO> createRoom(

            @PathVariable("hotelsId") Long hotelsId,
            @RequestParam("roomType")String roomType,
            @RequestParam("amenities")String amenities,
            @RequestParam("roomOption")String roomOption,
            @RequestParam("originalPrice")Double originalPrice,
            @RequestParam("discount")Double discount,
            @RequestParam("photoUrl") MultipartFile imageUrl

    ){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomType(roomType);
        roomDTO.setRoomOption(roomOption);
        roomDTO.setAmenities(amenities);
        roomDTO.setOriginalPrice(originalPrice);
        roomDTO.setDiscount(discount);
        roomDTO.setImageUrl(imageUrl);

        RoomDTO room = roomService.createRoom(hotelsId,roomDTO);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }


    @GetMapping("/hotels/{hotelsId}/rooms")
    public List<RoomDTO> getRoomByHotelsId(
            @PathVariable("hotelsId")Long hotelsId
    ){
        List<RoomDTO> roomByHotelsId = roomService.getRoomByHotelsId(hotelsId);
        return  roomByHotelsId;

    }

}

