package com.example.chargingstation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CharginStation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long stationID;
	
	
	
	private Long energyOutput;
	private String type;
	private String chargingStartTime;
	private String availabilityTime;
	private Long vehicleBatteryCapacity;
	private Long currentVehicleCharge;
	
	public String getChargingStartTime() {
		return chargingStartTime;
	}
	public void setChargingStartTime(String chargingStartTime) {
		this.chargingStartTime = chargingStartTime;
	}
	public String getAvailabilityTime() {
		return availabilityTime;
	}
	public void setAvailabilityTime(String availabilityTime) {
		this.availabilityTime = availabilityTime;
	}
	public Long getVehicleBatteryCapacity() {
		return vehicleBatteryCapacity;
	}
	public void setVehicleBatteryCapacity(Long vehicleBatteryCapacity) {
		this.vehicleBatteryCapacity = vehicleBatteryCapacity;
	}
	public Long getCurrentVehicleCharge() {
		return currentVehicleCharge;
	}
	public void setCurrentVehicleCharge(Long currentVehicleCharge) {
		this.currentVehicleCharge = currentVehicleCharge;
	}
	public Long getStationID() {
		return stationID;
	}
	public void setStationID(Long stationID) {
		this.stationID = stationID;
	}
	public Long getEnergyOutput() {
		return energyOutput;
	}
	public void setEnergyOutput(Long energyOutput) {
		this.energyOutput = energyOutput;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "CharginStation [stationID=" + stationID + ", energyOutput=" + energyOutput + ", type=" + type
				+ ", chargingStartTime=" + chargingStartTime + ", availabilityTime=" + availabilityTime
				+ ", vehicleBatteryCapacity=" + vehicleBatteryCapacity + ", currentVehicleCharge="
				+ currentVehicleCharge + "]";
	}
	
	
	

}
