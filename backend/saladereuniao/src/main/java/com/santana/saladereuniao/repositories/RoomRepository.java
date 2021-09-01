package com.santana.saladereuniao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santana.saladereuniao.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

}
