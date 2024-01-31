package com.example.chargingstation.controller;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.chargingstation.model.CharginStation;
import com.example.chargingstation.repository.ChargingStationRepository;
import com.example.chargingstation.service.ChargingStationService;

@RestController
@RequestMapping("/charge")
public class MainController {
	
	@Autowired
	ChargingStationRepository crepo;
	
	@Autowired
	ChargingStationService cservice;
	
	@PostMapping("/add")
	public ResponseEntity<?> AddStation(@RequestBody CharginStation station) {
		crepo.save(station);
		return ResponseEntity.ok("Ok");
	}
	
//	@PostMapping("start")
//	public ResponseEntity<?> StartCharging()
	
	@GetMapping("stations")
	public ResponseEntity<List<CharginStation>> Display(){
		return new ResponseEntity<List<CharginStation>>( crepo.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("start")
	public ResponseEntity<?> Start(@RequestParam Long Id, @RequestParam Long vehicleBatteryCapacity, @RequestParam Long currentVehicleCharge){
		CharginStation cstation = crepo.getById(Id);
		Instant currentInstant = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .withZone(ZoneOffset.UTC);
        String formattedDateTime = formatter.format(currentInstant);
        cstation.setChargingStartTime(formattedDateTime);
        cstation.setVehicleBatteryCapacity(vehicleBatteryCapacity);
        cstation.setCurrentVehicleCharge(currentVehicleCharge);
        Long Hours = (cstation.getVehicleBatteryCapacity()-cstation.getCurrentVehicleCharge())/cstation.getEnergyOutput();
		
		int extraHours = Hours.intValue();
//		Instant currentInstant = Instant.now();
        
        
        Instant updatedInstant = currentInstant.plus(Duration.ofHours(extraHours));

        
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
//                .withZone(ZoneOffset.UTC);
        
        formattedDateTime = formatter.format(updatedInstant);
        cstation.setAvailabilityTime(formattedDateTime);
                crepo.save(cstation);
        return ResponseEntity.ok("Ok");
        
	}
	
	@GetMapping("available")
	public ResponseEntity<List<Map<String, Object>>> Available(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<CharginStation> all = crepo.findAll();
		for(CharginStation cstation : all) {
			if(cstation.getAvailabilityTime()!=null) {
			String formattedDateTime = cstation.getAvailabilityTime();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
	                .withZone(ZoneOffset.UTC);

	        Instant updatedInstant = Instant.from(formatter.parse(formattedDateTime));

	        
	        Instant currentInstant = Instant.now();

	        
	        int comparisonResult = currentInstant.compareTo(updatedInstant);

	        if (comparisonResult > 0) {
	            cstation.setAvailabilityTime(null);
	            cstation.setChargingStartTime(null);
	            cstation.setCurrentVehicleCharge(null);
	            cstation.setVehicleBatteryCapacity(null);
	            crepo.save(cstation);
	        }}
			if(cstation.getChargingStartTime()==null) {
				Map<String, Object> selectedColumns = new HashMap<>();
				selectedColumns.put("Station Id", cstation.getStationID() );
				selectedColumns.put("Energy Output", cstation.getEnergyOutput()+"kWh" );
				selectedColumns.put("Type", cstation.getType() );
				list.add(selectedColumns);
			}
		}
		return new ResponseEntity<List<Map<String, Object>>>(list,HttpStatus.OK);
	}
	
	
//	@PostMapping("later")
//	public ResponseEntity<?> Availability(){
//		List<CharginStation> all = crepo.findAll();
//		for(CharginStation cstation : all) {
//			if(cstation.getChargingStartTime()!=null) {
//				Long Hours = (cstation.getVehicleBatteryCapacity()-cstation.getCurrentVehicleCharge())/cstation.getEnergyOutput();
//				
//				int extraHours = Hours.intValue();
//				Instant currentInstant = Instant.now();
//		        
//		        
//		        Instant updatedInstant = currentInstant.plus(Duration.ofHours(extraHours));
//
//		        
//		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
//		                .withZone(ZoneOffset.UTC);
//		        
//		        String formattedDateTime = formatter.format(updatedInstant);
//		        cstation.setAvailabilityTime(formattedDateTime);
//		        crepo.save(cstation);
//			}
//		}
//		return ResponseEntity.ok("Done");
//	https://places.ls.hereapi.com/places/v1/discover/explore

//	}
	
	
	
	@GetMapping("availablity")
	public ResponseEntity<List<Map<String, Object>>> AvailableStart(){
		List<CharginStation> all = crepo.findAll();
		List<Map<String, Object>> list = new ArrayList();
		for(CharginStation cstation : all) {
			if(cstation.getAvailabilityTime()!=null) {
				Map<String, Object> selectedColumns = new HashMap<>();
				selectedColumns.put("Station Id", cstation.getStationID() );
				selectedColumns.put("Energy Output", cstation.getEnergyOutput()+"kWh" );
				selectedColumns.put("Type", cstation.getType() );
				selectedColumns.put("Availability time", cstation.getAvailabilityTime() );
				list.add(selectedColumns);
			}
		}
		return new ResponseEntity<List<Map<String, Object>>>(list,HttpStatus.OK);

	}
}
