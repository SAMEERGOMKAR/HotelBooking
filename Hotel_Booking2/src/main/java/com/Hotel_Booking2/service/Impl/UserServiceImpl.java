package com.Hotel_Booking2.service.Impl;

import com.Hotel_Booking2.entity.Hotels;
import com.Hotel_Booking2.exception.ResourceNotFoundException;
import com.Hotel_Booking2.payload.HotelResponse;
import com.Hotel_Booking2.payload.HotelsDTO;
import com.Hotel_Booking2.repository.UsersRepository;
import com.Hotel_Booking2.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }


    @Override
    public HotelResponse getAllHotels(int pageNum, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?

                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        PageRequest pageable = PageRequest.of(pageNum, pageSize, sort);
        Page<Hotels> hotels = usersRepository.findAll(pageable);
        List<Hotels> content = hotels.getContent();
        List<HotelsDTO> collect = content.stream().map(hotels1 -> mapToDTO(hotels1)).collect(Collectors.toList());

        HotelResponse hotelResponse = new HotelResponse();
        hotelResponse.setContent(collect);
        hotelResponse.setPageNo(hotels.getNumber());
        hotelResponse.setPageSize(hotels.getSize());
        hotelResponse.setTotalElement(hotels.getTotalElements());
        hotelResponse.setTotalPages(hotels.getTotalPages());
        hotelResponse.setLast(hotels.isLast());


        return hotelResponse;
    }

    @Override
    public HotelsDTO getHotelsById(Long id) {
        //get hotels from database
        Hotels hotels = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        return mapToDTO(hotels);
    }

    @Override
    public List<HotelsDTO> findByLocation(String location) {
        List<Hotels> byLocation = usersRepository.findByLocation(location);

        List<HotelsDTO> collect = byLocation.stream().map(hotels -> mapToDTO(hotels)).collect(Collectors.toList());


        return collect;
    }

    private HotelsDTO mapToDTO(Hotels hotels1) {

        HotelsDTO hotelsDTO = new HotelsDTO();
        hotelsDTO.setId(hotels1.getId());
        hotelsDTO.setHotelName(hotels1.getHotelName());
        hotelsDTO.setStays(hotels1.getStays());
        hotelsDTO.setAmenities(hotels1.getAmenities());
        hotelsDTO.setAvailability(hotels1.getAvailability());
        hotelsDTO.setLocation(hotels1.getLocation());
        hotelsDTO.setDescription(hotels1.getDescription());
        hotelsDTO.setDiscount(hotels1.getDiscount());
        hotelsDTO.setTotalPrice(hotels1.getTotalPrice());
        hotelsDTO.setOriginalPrice(hotels1.getOriginalPrice());
        hotelsDTO.setPhotoUrl(hotels1.getPhotoUrl());

        return hotelsDTO;

    }
}
