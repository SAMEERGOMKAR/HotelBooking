package com.Hotel_Booking2.payload;

import com.Hotel_Booking2.entity.Rating;
import lombok.Data;

@Data
public class ReviewDTO {

    private Long id;
    private Rating rating;
    private String review;
}
