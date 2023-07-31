package com.Hotel_Booking2.controller;

import com.Hotel_Booking2.payload.HotelResponse;
import com.Hotel_Booking2.payload.HotelsDTO;
import com.Hotel_Booking2.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    //http://localhost:8080/api/users?pageNum=0&pageSize=5&sortBy=id&sortDir=asc

    @GetMapping
    public ResponseEntity<HotelResponse> getAllHotels(
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {

        HotelResponse allHotels = userService.getAllHotels(pageNum, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allHotels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelsDTO>getHotelsById(@PathVariable(name = "id")Long id){
        HotelsDTO hotels = userService.getHotelsById(id);
        return new ResponseEntity<>(hotels,HttpStatus.OK);
    }

    //http://localhost:8080/api/details/search?hotelName=ABC
    @GetMapping("/search")
    public ResponseEntity<List<HotelsDTO>> findByLocation(
            @RequestParam("location") String location
    ) {
        List<HotelsDTO> byLocation = userService.findByLocation(location);
        return new ResponseEntity<>(byLocation, HttpStatus.OK);
    }


}
