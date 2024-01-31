package com.example.chargingstation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chargingstation.model.CharginStation;

public interface ChargingStationRepository extends JpaRepository<CharginStation, Long> {
	long count();
}
