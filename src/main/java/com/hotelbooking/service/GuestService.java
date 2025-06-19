package com.hotelbooking.system.service;

import com.hotelbooking.system.model.Guest;
import com.hotelbooking.system.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    // Create
    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    // Read all
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    // Read by ID
    public Optional<Guest> getGuestById(Long id) {
        return guestRepository.findById(id);
    }

    // Update
    public Guest updateGuest(Long id, Guest guestDetails) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id)); // Custom exception recommended

        guest.setFirstName(guestDetails.getFirstName());
        guest.setLastName(guestDetails.getLastName());
        guest.setEmail(guestDetails.getEmail());
        guest.setPhoneNumber(guestDetails.getPhoneNumber());
        guest.setAddress(guestDetails.getAddress());

        return guestRepository.save(guest);
    }

    // Delete
    public void deleteGuest(Long id) {
        if (!guestRepository.existsById(id)) {
            throw new RuntimeException("Guest not found with id: " + id); // Custom exception recommended
        }
        guestRepository.deleteById(id);
    }
}