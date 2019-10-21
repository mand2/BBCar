package com.ycar.passenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ycar.passenger.entity.PassengerEntity;

public interface PassengerRepository extends JpaRepository<PassengerEntity, Integer> {

}
