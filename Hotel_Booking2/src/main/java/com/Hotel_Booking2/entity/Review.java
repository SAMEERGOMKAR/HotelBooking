package com.Hotel_Booking2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Rating rating;

    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotels_id",nullable = false)
    private Hotels hotels;
}
