package com.Hotel_Booking2.controller;

import com.Hotel_Booking2.payload.ReviewDTO;
import com.Hotel_Booking2.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }


    @PostMapping("/hotels/{hotelsId}/review")
    public ResponseEntity<ReviewDTO> createReview(
            @PathVariable("hotelsId")Long hotelsId,
            @RequestBody ReviewDTO reviewDTO
    ){

        ReviewDTO review = reviewService.createReview(hotelsId, reviewDTO);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

//    @PutMapping("/hotels/{hotelsId}/review/{id}")
//    public ResponseEntity<ReviewDTO>updateReview(
//            @PathVariable("hotelsId")Long hotelsId,
//            @PathVariable("id")Long id,
//            @RequestBody ReviewDTO reviewDTO
//    ){
//        ReviewDTO reviewDTO1 = reviewService.updateReview(hotelsId, id, reviewDTO);
//        return new ResponseEntity<>(reviewDTO1,HttpStatus.OK);
//    }


}

