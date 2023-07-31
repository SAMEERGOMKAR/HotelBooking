package com.Hotel_Booking2.repository;

import com.Hotel_Booking2.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

    List<Room> findByHotelsId(Long id);
}
