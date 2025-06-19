package com.hotelbooking.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelbooking.system.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}