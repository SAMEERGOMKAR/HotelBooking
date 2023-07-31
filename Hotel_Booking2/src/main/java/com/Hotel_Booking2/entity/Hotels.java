package com.Hotel_Booking2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotels")
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

    @Column(name = "discount")
    private Double discount;

    @Column(name = "totalPrice")
    private Double totalPrice;

    @Column(name = "image")
    private String photoUrl;


   @OneToMany(mappedBy = "hotels", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Room>rooms = new HashSet<>();

    @OneToMany(mappedBy = "hotels", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review>reviews = new HashSet<>();

}
