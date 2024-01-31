package com.example.chargingstation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.chargingstation.repository.ChargingStationRepository;

@Service
public class ChargingStationService {
	
	@Autowired
	ChargingStationRepository crepo;
	
	public long CountTable() {
		return crepo.count();
	}

}
