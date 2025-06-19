package com.hotelbooking.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelbooking.system.model.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}