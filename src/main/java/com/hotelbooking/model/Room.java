package com.hotelbooking.system.model;

import com.hotelbooking.system.model.Hotel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomNumber;
    private String roomType; 
    private Double price;
    private Boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel; 

    public Room(Hotel hotel, Long id, Boolean isAvailable, Double price, String roomNumber, String roomType) {
        this.hotel = hotel;
        this.id = id;
        this.isAvailable = isAvailable;
        this.price = price;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }
