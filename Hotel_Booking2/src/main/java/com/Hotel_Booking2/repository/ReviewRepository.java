package com.Hotel_Booking2.repository;

import com.Hotel_Booking2.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
