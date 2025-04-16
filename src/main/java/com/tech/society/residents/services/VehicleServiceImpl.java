package com.tech.society.residents.services;

import com.tech.society.residents.models.Vehicle;
import com.tech.society.residents.repositories.VehicleRepository;
import com.tech.society.residents.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getVehiclesByResidentId(String residentId) {
        return vehicleRepository.findByResidentId(residentId);
    }

    @Override
    public List<Vehicle> getVehiclesByFlatNumber(String flatNumber) {
        return vehicleRepository.findByFlatNumber(flatNumber);
    }

    @Override
    public Optional<Vehicle> getVehicleById(String id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(String id, Vehicle vehicle) {
        if (vehicleRepository.existsById(id)) {
            vehicle.setId(id);
            return vehicleRepository.save(vehicle);
        }
        return null;
    }

    @Override
    public void deleteVehicle(String id) {
        vehicleRepository.deleteById(id);
    }
}