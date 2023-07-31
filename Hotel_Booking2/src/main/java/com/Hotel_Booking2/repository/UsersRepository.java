package com.Hotel_Booking2.repository;

import com.Hotel_Booking2.entity.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Hotels,Long> {

    List<Hotels> findByLocation(String location);
}
