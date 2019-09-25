package com.ycar.passenger.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycar.passenger.dao.PassengerDaoImpl;
import com.ycar.passenger.entity.PRouteEntity;
import com.ycar.passenger.entity.PassengerEntity;

@RestController
@CrossOrigin
public class MypageController {
	
	@PersistenceContext
	EntityManager entityManager;
	
	private PassengerDaoImpl dao;
	
	@RequestMapping("/findEnv/{idx}")
	public PassengerEntity findEnvByIdx(@PathVariable("idx") long idx) {
		
		this.dao = new PassengerDaoImpl(entityManager);
		
		PassengerEntity entity = dao.findEnvByIdx(idx);
		System.out.println(entity);
		
		return entity;		
	}
	
	@RequestMapping("/findRoute/{idx}")
	public List<PRouteEntity> findRouteByIdx(@PathVariable("idx") int idx) {
		
		System.out.println(idx);
		
		this.dao = new PassengerDaoImpl(entityManager);
		
		List<PRouteEntity> entity = dao.findRouteByIdx(idx);
		System.out.println(entity);
		
		return entity;		
	}
	
}
