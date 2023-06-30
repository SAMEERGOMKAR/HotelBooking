package com.Hotel_Booking2.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class HotelsDTO {


    private Long id;

    private String hotelName;

    private String location;

    private String description;

    private String stays;

    private String amenities;

    private String availability;

    private Double originalPrice;

    private Double discountPrice;

    private String photoUrl;

    private MultipartFile imageUrl;

}
