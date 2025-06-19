package com.hotelbooking.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelbooking.system.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}