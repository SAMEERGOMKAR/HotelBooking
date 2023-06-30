package com.Hotel_Booking2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotels {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotelName")
    private String hotelName;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "stays")
    private String stays;

    @Column(name = "amenities")
    private String amenities;

    @Column(name = "availability")
    private String availability;

    @Column(name = "originalPrice")
    private Double originalPrice;

    @Column(name = "discountPrice")
    private Double discountPrice;

    @Column(name = "image")
    private String photoUrl;

}
