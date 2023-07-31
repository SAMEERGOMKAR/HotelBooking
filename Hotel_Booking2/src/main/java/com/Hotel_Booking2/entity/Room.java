package com.Hotel_Booking2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roomType")
    private String roomType;

    @Column(name = "amenities")
    private String amenities;

    @Column(name = "roomOption")
    private String roomOption;

    @Column(name = "originalPrice")
    private  Double originalPrice;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "totalPrice")
    private Double totalPrice;

    @Column(name = "image")
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotels_id",nullable = false)
    private Hotels hotels;

}
