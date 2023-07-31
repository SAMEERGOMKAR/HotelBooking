package com.Hotel_Booking2.repository;

import com.Hotel_Booking2.entity.Hotels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelsRepository extends JpaRepository<Hotels,Long> {
}
