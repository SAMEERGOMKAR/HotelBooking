package com.Hotel_Booking2.service;

import com.Hotel_Booking2.payload.ReviewDTO;

public interface ReviewService {
    ReviewDTO createReview(Long hotelsId, ReviewDTO reviewDTO);

//    ReviewDTO updateReview(Long hotelsId, Long id, ReviewDTO reviewDTO);

}
