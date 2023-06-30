package com.Hotel_Booking2.service.Impl;

import com.Hotel_Booking2.entity.Hotels;
import com.Hotel_Booking2.exception.ResourceNotFoundException;
import com.Hotel_Booking2.payload.HotelResponse;
import com.Hotel_Booking2.payload.HotelsDTO;
import com.Hotel_Booking2.repository.HotelsRepository;
import com.Hotel_Booking2.service.HotelsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelsServiceImpl implements HotelsService {

    private final HotelsRepository hotelsRepository;

    private final String uploadDirectory = "src/main/resources/static/hotelPhotoUrl/";

public HotelsServiceImpl(HotelsRepository hotelsRepository){
    this.hotelsRepository = hotelsRepository;
}

    @Override
    public HotelsDTO createHotels(HotelsDTO hotelsDTO) {
        Hotels hotels = new Hotels();
        hotels.setId(hotelsDTO.getId());
        hotels.setHotelName(hotelsDTO.getHotelName());
        hotels.setStays(hotelsDTO.getStays());
        hotels.setAmenities(hotelsDTO.getAmenities());
        hotels.setAvailability(hotelsDTO.getAvailability());
        hotels.setLocation(hotelsDTO.getLocation());
        hotels.setDescription(hotelsDTO.getDescription());
        hotels.setDiscountPrice(hotelsDTO.getDiscountPrice());
        hotels.setOriginalPrice(hotelsDTO.getOriginalPrice());

        MultipartFile imageUrl = hotelsDTO.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()){
            String image = saveImage(imageUrl);
            hotels.setPhotoUrl(image);
        }

        Hotels saveHotel = hotelsRepository.save(hotels);
        HotelsDTO hotelsDTO1 = mapToDTO(saveHotel);
        return hotelsDTO1;
    }

    @Override
    public HotelResponse getAllHotels(int pageNum, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?

                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        PageRequest pageable = PageRequest.of(pageNum, pageSize, sort);
        Page<Hotels> hotels = hotelsRepository.findAll(pageable);
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
        Hotels hotels = hotelsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));


        return mapToDTO(hotels);
    }

    @Override
    public HotelsDTO updateHotel(HotelsDTO hotelsDTO, Long id) {
        //get hotels from database
        Hotels hotels = hotelsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        hotels.setId(hotelsDTO.getId());
        hotels.setHotelName(hotelsDTO.getHotelName());
        hotels.setStays(hotelsDTO.getStays());
        hotels.setAmenities(hotelsDTO.getAmenities());
        hotels.setAvailability(hotelsDTO.getAvailability());
        hotels.setLocation(hotelsDTO.getLocation());
        hotels.setDescription(hotelsDTO.getDescription());
        hotels.setDiscountPrice(hotelsDTO.getDiscountPrice());
        hotels.setOriginalPrice(hotelsDTO.getOriginalPrice());

        MultipartFile imageUrl = hotelsDTO.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()){
            String image = saveImage(imageUrl);
            hotels.setPhotoUrl(image);
        }
        Hotels saveHotel = hotelsRepository.save(hotels);
        HotelsDTO hotelsDTO1 = mapToDTO(saveHotel);
        return hotelsDTO1;
    }

    @Override
    public void deleteHotelDetails(Long id) {
        //get all hotels from database
        Hotels hotels = hotelsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        hotelsRepository.delete(hotels);
    }

    public HotelsDTO mapToDTO(Hotels saveHotel) {

    HotelsDTO hotelsDTO = new HotelsDTO();
        hotelsDTO.setId(saveHotel.getId());
        hotelsDTO.setHotelName(saveHotel.getHotelName());
        hotelsDTO.setStays(saveHotel.getStays());
        hotelsDTO.setAmenities(saveHotel.getAmenities());
        hotelsDTO.setAvailability(saveHotel.getAvailability());
        hotelsDTO.setLocation(saveHotel.getLocation());
        hotelsDTO.setDescription(saveHotel.getDescription());
        hotelsDTO.setDiscountPrice(saveHotel.getDiscountPrice());
        hotelsDTO.setOriginalPrice(saveHotel.getOriginalPrice());
        hotelsDTO.setPhotoUrl(saveHotel.getPhotoUrl());

        return hotelsDTO;
    }

     public String saveImage(MultipartFile imageUrl) {
        try {
            byte[] bytes = imageUrl.getBytes();
            String originalFileName = imageUrl.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
            String baseFileName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
            String uniqueFileName = baseFileName + "_" + System.currentTimeMillis() + fileExtension;
            Path path = Paths.get(uploadDirectory + uniqueFileName);
            Files.write(path, bytes);

            return uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save profile image", e);
        }


    }
    }

