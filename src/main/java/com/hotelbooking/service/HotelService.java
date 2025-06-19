package com.hotelbooking.system.service;

import com.hotelbooking.system.model.Hotel;
import com.hotelbooking.system.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    // Create
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // Read all
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // Read by ID
    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    // Update
    public Hotel updateHotel(Long id, Hotel hotelDetails) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id)); // You can create a custom exception here

        hotel.setName(hotelDetails.getName());
        hotel.setAddress(hotelDetails.getAddress());
        hotel.setCity(hotelDetails.getCity());
        hotel.setCountry(hotelDetails.getCountry());
        hotel.setPhoneNumber(hotelDetails.getPhoneNumber());
        hotel.setEmail(hotelDetails.getEmail());

        return hotelRepository.save(hotel);
    }

    // Delete
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}