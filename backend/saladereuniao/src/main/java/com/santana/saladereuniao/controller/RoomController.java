package com.santana.saladereuniao.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santana.saladereuniao.exceptions.ResourceNotFoundException;
import com.santana.saladereuniao.model.Room;
import com.santana.saladereuniao.repositories.RoomRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomController {

	@Autowired
	private RoomRepository roomRepository;
	
	@GetMapping("/rooms")
	public List<Room> getAllRooms(){
		return roomRepository.findAll();
	}
	
	@GetMapping("/rooms/{id}")
	public ResponseEntity<Room> getRoomById(@PathVariable Long id) throws ResourceNotFoundException{
		
		Room room = findRoom(id);		
		return ResponseEntity.ok().body(room);		
	}
	
	@PostMapping("/rooms")
	public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room) {
		Room roomSaved = roomRepository.save(room);
		return ResponseEntity.status(HttpStatus.CREATED).body(roomSaved);
	}
	
	@PutMapping("/rooms/{id}")
	public ResponseEntity<Room> updateRoom(@PathVariable Long id, @Valid @RequestBody Room roomDetails) 
			throws ResourceNotFoundException{
		
		Room room = findRoom(id);
		
		room.setName(roomDetails.getName());
		room.setDate(roomDetails.getDate());
		room.setStartHour(roomDetails.getStartHour());
		room.setEndHour(roomDetails.getEndHour());
		
		Room updateRoom = roomRepository.save(room);
		
		return ResponseEntity.status(HttpStatus.OK).body(updateRoom);
	}
	
	@DeleteMapping("/rooms/{id}")
	public Map<String, Boolean> deleteRoom(@PathVariable Long id) throws ResourceNotFoundException {
		
		Room room = findRoom(id);
		roomRepository.delete(room);
		
		Map<String, Boolean> response = new HashMap<>();
		
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
	
	//Find Room
	private Room findRoom(long roomId) throws ResourceNotFoundException {
		return roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found " + roomId));
	}
	
}


