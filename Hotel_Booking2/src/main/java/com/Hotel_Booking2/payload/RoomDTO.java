package com.Hotel_Booking2.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

@Data
public class RoomDTO {

    private Long id;

    private String roomType;

    private String amenities;

    private String roomOption;

    private  Double originalPrice;

    private Double discount;

    private Double totalPrice;

    private String photoUrl;

    private MultipartFile imageUrl;

}
