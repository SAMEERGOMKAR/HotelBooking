package com.Hotel_Booking2.service.Impl;

import com.Hotel_Booking2.entity.Hotels;
import com.Hotel_Booking2.entity.Room;
import com.Hotel_Booking2.exception.ResourceNotFoundException;
import com.Hotel_Booking2.payload.HotelsDTO;
import com.Hotel_Booking2.payload.RoomDTO;
import com.Hotel_Booking2.repository.HotelsRepository;
import com.Hotel_Booking2.repository.RoomRepository;
import com.Hotel_Booking2.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelsRepository hotelsRepository;

    private final String uploadDirectory = "src/main/resources/static/rooms_photoUrl/";

    public RoomServiceImpl(RoomRepository roomRepository,HotelsRepository hotelsRepository){
        this.roomRepository = roomRepository;
        this.hotelsRepository = hotelsRepository;
    }


    @Override
    public RoomDTO createRoom(Long hotelsId, RoomDTO roomDTO) {

        Room room = new Room();
        room .setId(roomDTO.getId());
        room.setRoomType(roomDTO.getRoomType());
        room.setRoomOption(roomDTO.getRoomOption());
        room.setAmenities(roomDTO.getAmenities());
        room.setDiscount(roomDTO.getDiscount());
        room.setOriginalPrice(roomDTO.getOriginalPrice());

        roomDTO.getTotalPrice();
        double discountAmount = roomDTO.getOriginalPrice() * (roomDTO.getDiscount() / 100);
        double totalPrice = roomDTO.getOriginalPrice() - discountAmount;
        room.setTotalPrice(totalPrice);

        MultipartFile imageUrl = roomDTO.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()){
            String image = saveImage(imageUrl);
            room.setPhotoUrl(image);
        }
        Hotels hotels = hotelsRepository.findById(hotelsId).orElseThrow(() -> new ResourceNotFoundException("Hotels", "id", hotelsId));
       room.setHotels(hotels);

        Room saveRoom = roomRepository.save(room);
        RoomDTO roomDTO1 = mapToDTO(saveRoom);
        return roomDTO1;

    }

    @Override
    public List<RoomDTO> getRoomByHotelsId(Long hotelsId) {

//        retrieve rooms by hotelsId
        List<Room> byHotelsId = roomRepository.findByHotelsId(hotelsId);
//        List<HotelsDTO> collect = byLocation.stream().map(hotels -> mapToDTO(hotels)).collect(Collectors.toList());
        List<RoomDTO> collect = byHotelsId.stream().map(room -> mapToDTO(room)).collect(Collectors.toList());

        return  collect;
    }

    private RoomDTO mapToDTO(Room saveRoom) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(saveRoom.getId());
        roomDTO.setRoomType(saveRoom.getRoomType());
        roomDTO.setRoomOption(saveRoom.getRoomOption());
        roomDTO.setAmenities(saveRoom.getAmenities());
        roomDTO.setDiscount(saveRoom.getDiscount());
        roomDTO.setOriginalPrice(saveRoom.getOriginalPrice());
        roomDTO.setTotalPrice(saveRoom.getTotalPrice());
        roomDTO.setPhotoUrl(saveRoom.getPhotoUrl());

        return roomDTO;
    }

    private String saveImage(MultipartFile imageUrl) {
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
