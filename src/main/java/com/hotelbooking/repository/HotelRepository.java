package com.hotelbooking.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelbooking.system.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}