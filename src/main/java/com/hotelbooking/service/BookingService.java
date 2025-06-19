package com.hotelbooking.system.service;

import com.hotelbooking.system.model.Booking;
import com.hotelbooking.system.model.Guest;
import com.hotelbooking.system.model.Room;
import com.hotelbooking.system.repository.BookingRepository;
import com.hotelbooking.system.repository.GuestRepository;
import com.hotelbooking.system.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private GuestRepository guestRepository; // To fetch Guest by ID
    @Autowired
    private RoomRepository roomRepository;   // To fetch Room by ID

    // Create
    public Booking createBooking(Booking booking) {
        // Ensure associated Guest and Room exist before saving the booking
        if (booking.getGuest() != null && booking.getGuest().getId() != null) {
            Guest guest = guestRepository.findById(booking.getGuest().getId())
                    .orElseThrow(() -> new RuntimeException("Guest not found for ID: " + booking.getGuest().getId()));
            booking.setGuest(guest);
        } else {
             throw new RuntimeException("Booking must be associated with an existing Guest.");
        }

        if (booking.getRoom() != null && booking.getRoom().getId() != null) {
            Room room = roomRepository.findById(booking.getRoom().getId())
                    .orElseThrow(() -> new RuntimeException("Room not found for ID: " + booking.getRoom().getId()));
            booking.setRoom(room);
            // Optionally, update room availability here if your logic requires
            // room.setIsAvailable(false);
            // roomRepository.save(room);
        } else {
            throw new RuntimeException("Booking must be associated with an existing Room.");
        }

        return bookingRepository.save(booking);
    }

    // Read all
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Read by ID
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    // Update
    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id)); // Custom exception recommended

        // Update fields
        booking.setCheckInDate(bookingDetails.getCheckInDate());
        booking.setCheckOutDate(bookingDetails.getCheckOutDate());
        booking.setTotalPrice(bookingDetails.getTotalPrice());
        booking.setBookingStatus(bookingDetails.getBookingStatus());

        // Handle updates for associated Guest and Room if they are part of the update request
        if (bookingDetails.getGuest() != null && bookingDetails.getGuest().getId() != null) {
            Guest guest = guestRepository.findById(bookingDetails.getGuest().getId())
                    .orElseThrow(() -> new RuntimeException("Guest not found for ID: " + bookingDetails.getGuest().getId()));
            booking.setGuest(guest);
        }

        if (bookingDetails.getRoom() != null && bookingDetails.getRoom().getId() != null) {
            Room room = roomRepository.findById(bookingDetails.getRoom().getId())
                    .orElseThrow(() -> new RuntimeException("Room not found for ID: " + bookingDetails.getRoom().getId()));
            booking.setRoom(room);
            // Optionally update room availability if the room changes or booking status changes
        }

        return bookingRepository.save(booking);
    }

    // Delete
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Booking not found with id: " + id); // Custom exception recommended
        }
        // Optionally, update room availability back to true if the room was marked unavailable
        // Booking booking = bookingRepository.findById(id).orElse(null);
        // if (booking != null && booking.getRoom() != null) {
        //     Room room = booking.getRoom();
        //     room.setIsAvailable(true);
        //     roomRepository.save(room);
        // }
        bookingRepository.deleteById(id);
    }
}