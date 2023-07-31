package com.Hotel_Booking2.service.Impl;

import com.Hotel_Booking2.entity.Hotels;
import com.Hotel_Booking2.entity.Review;
import com.Hotel_Booking2.exception.ResourceNotFoundException;
import com.Hotel_Booking2.exception.UserAPIException;
import com.Hotel_Booking2.payload.ReviewDTO;
import com.Hotel_Booking2.repository.HotelsRepository;
import com.Hotel_Booking2.repository.ReviewRepository;
import com.Hotel_Booking2.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final HotelsRepository hotelsRepository;

    public ReviewServiceImpl( ReviewRepository reviewRepository, HotelsRepository hotelsRepository){
        this.reviewRepository = reviewRepository;
        this.hotelsRepository = hotelsRepository;
    }

    @Override
    public ReviewDTO createReview(Long hotelsId, ReviewDTO reviewDTO) {

        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setRating(reviewDTO.getRating());
        review.setReview(reviewDTO.getReview());

        // retrieve hotel entity by id
        Hotels hotels = hotelsRepository.findById(hotelsId).orElseThrow(() -> new ResourceNotFoundException("Hotels", "id", hotelsId));
        review.setHotels(hotels);

        Review saveReview = reviewRepository.save(review);
        ReviewDTO reviewDTO1 = mapToDTO(saveReview);

        return reviewDTO1;
    }

//    @Override
//    public ReviewDTO updateReview(Long hotelsId, Long id, ReviewDTO reviewDTO) {
//
//       Review review = new Review();
//       review.setId(reviewDTO.getId());
//       review.setRating(reviewDTO.getRating());
//        review.setReview(reviewDTO.getReview());
//
//        // retrieve hotel entity by id
//        Hotels hotels = hotelsRepository.findById(hotelsId)
//                .orElseThrow(() -> new ResourceNotFoundException("Hotels", "id", hotelsId));
//       review.setHotels(hotels);
//
//        //retrieve review by id
//        Review review = reviewRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", id));
//
//      if (!review.getHotels().getId().equals(hotels.getId())){
//           throw  new UserAPIException(HttpStatus.BAD_REQUEST,"Comment does not belongs to post");
//     }
//
//        review.setId(reviewDTO.getId());
//        review.setRating(reviewDTO.getRating());
//        review.setReview(reviewDTO.getReview());
//        review.setHotels(hotels);
//
//        Review updateReview = reviewRepository.save(review);
//        ReviewDTO reviewDTO1 = mapToDTO(updateReview);
//        return reviewDTO1;
//    }

    private ReviewDTO mapToDTO(Review saveReview) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(saveReview.getId());
        reviewDTO.setRating(saveReview.getRating());
        reviewDTO.setReview(saveReview.getReview());
        return reviewDTO;
    }
}
