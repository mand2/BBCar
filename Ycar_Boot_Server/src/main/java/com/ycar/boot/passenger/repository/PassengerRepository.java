package com.ycar.boot.passenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ycar.boot.passenger.entity.PassengerEntity;


public interface PassengerRepository extends JpaRepository<PassengerEntity, Integer> {

}
