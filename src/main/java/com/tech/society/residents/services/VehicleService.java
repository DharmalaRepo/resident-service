package com.tech.society.residents.services;

import com.tech.society.residents.models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    List<Vehicle> getAllVehicles();
    List<Vehicle> getVehiclesByResidentId(String residentId);
    List<Vehicle> getVehiclesByFlatNumber(String flatNumber);
    Optional<Vehicle> getVehicleById(String id);
    Vehicle createVehicle(Vehicle vehicle);
    Vehicle updateVehicle(String id, Vehicle vehicle);
    void deleteVehicle(String id);
}