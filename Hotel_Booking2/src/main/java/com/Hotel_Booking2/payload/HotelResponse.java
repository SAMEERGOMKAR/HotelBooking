package com.Hotel_Booking2.payload;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class HotelResponse {


    private List<HotelsDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean last;
}
