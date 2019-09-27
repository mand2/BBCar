package com.ny.driver.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ny.driver.entity.ReservationEntity;
import com.ny.driver.repository.ReservRepository;

@RestController
public class ChatController {
	
//	@PersistenceContext
//	EntityManager manager;
	
	@Autowired
	private ReservRepository repository;
	
	@GetMapping("{d_idx}")
	public ResponseEntity<List<ReservationEntity>> getLists(@PathVariable("d_idx") String d_idx){
		System.out.println("들어오나? 01     " + d_idx);
		
		List<ReservationEntity> list = repository.getlists(d_idx);
		
		System.out.println("들어오나? 02     "+ list);
		
		return new ResponseEntity<List<ReservationEntity>>(list, HttpStatus.OK);
	}
	
//	@GetMapping("/chat/{d_idx")
//	public ResponseEntity<List<ReservationEntity>> getChatFriends(@PathVariable("d_idx")String d_idx){
//		
//		
//		
//		return new ResponseEntity<List<ReservationEntity>>(list, HttpStatus.OK);
//	}
	
}
