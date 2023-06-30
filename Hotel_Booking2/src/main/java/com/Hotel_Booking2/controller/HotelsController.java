package com.Hotel_Booking2.controller;

import com.Hotel_Booking2.payload.HotelResponse;
import com.Hotel_Booking2.payload.HotelsDTO;
import com.Hotel_Booking2.service.HotelsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/hotels")
public class HotelsController {

    private final HotelsService hotelsService;

    public HotelsController(HotelsService hotelsService){
        this.hotelsService = hotelsService;
    }
@PostMapping("/add")
@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HotelsDTO> createHotels(

        @RequestParam("hotelName") String hotelName,
        @RequestParam("amenities") String amenities,
        @RequestParam("stays") String stays,
        @RequestParam("availability") String availability,
        @RequestParam("location") String location,
        @RequestParam("description") String description,
        @RequestParam("discountPrice") Double discountPrice,
        @RequestParam("originalPrice") Double originalPrice,
        @RequestParam("photoUrl") MultipartFile imageUrl
){
        HotelsDTO hotelsDTO = new HotelsDTO();
    hotelsDTO.setHotelName(hotelName);
    hotelsDTO.setAmenities(amenities);
    hotelsDTO.setStays(stays);
    hotelsDTO.setAvailability(availability);
    hotelsDTO.setLocation(location);
    hotelsDTO.setDescription(description);
    hotelsDTO.setDiscountPrice(discountPrice);
    hotelsDTO.setOriginalPrice(originalPrice);
    hotelsDTO.setImageUrl(imageUrl);

    HotelsDTO hotels = hotelsService.createHotels(hotelsDTO);
    return new ResponseEntity<>(hotels, HttpStatus.CREATED);


    }
    //http://localhost:8080/api/hotels?pageNum=0&pageSize=5&sortBy=id&sortDir=asc

    @GetMapping
    public ResponseEntity<HotelResponse> getAllHotels(
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {

        HotelResponse allHotels = hotelsService.getAllHotels(pageNum, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allHotels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelsDTO>getHotelsById(@PathVariable(name = "id")Long id){
        HotelsDTO hotels = hotelsService.getHotelsById(id);
        return new ResponseEntity<>(hotels,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HotelsDTO> updateByID(
            @PathVariable(name = "id")Long id,
            @RequestParam("hotelName") String hotelName,
            @RequestParam("amenities") String amenities,
            @RequestParam("stays") String stays,
            @RequestParam("availability") String availability,
            @RequestParam("location") String location,
            @RequestParam("description") String description,
            @RequestParam("discountPrice") Double discountPrice,
            @RequestParam("originalPrice") Double originalPrice,
            @RequestParam("photoUrl") MultipartFile imageUrl
    ){
        HotelsDTO hotelsDTO = new HotelsDTO();
        hotelsDTO.setId(id);
        hotelsDTO.setHotelName(hotelName);
        hotelsDTO.setAmenities(amenities);
        hotelsDTO.setStays(stays);
        hotelsDTO.setAvailability(availability);
        hotelsDTO.setLocation(location);
        hotelsDTO.setDescription(description);
        hotelsDTO.setDiscountPrice(discountPrice);
        hotelsDTO.setOriginalPrice(originalPrice);
        hotelsDTO.setImageUrl(imageUrl);
        HotelsDTO hotelsDTO1 = hotelsService.updateHotel(hotelsDTO, id);

        return new ResponseEntity<>(hotelsDTO1, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteHotels(@PathVariable(name = "id")Long id){
        hotelsService.deleteHotelDetails(id);
        return new ResponseEntity<>("Hotel Details Delete Successfully",HttpStatus.OK);
    }


}
