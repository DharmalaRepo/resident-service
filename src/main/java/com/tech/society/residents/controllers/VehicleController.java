package com.tech.society.residents.controllers;

import com.tech.society.residents.models.Vehicle;
import com.tech.society.residents.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/resident/{residentId}")
    public List<Vehicle> getByResidentId(@PathVariable String residentId) {
        return vehicleService.getVehiclesByResidentId(residentId);
    }

    @GetMapping("/flat/{flatNumber}")
    public List<Vehicle> getByFlatNumber(@PathVariable String flatNumber) {
        return vehicleService.getVehiclesByFlatNumber(flatNumber);
    }

    @GetMapping("/{id}")
    public Optional<Vehicle> getVehicleById(@PathVariable String id) {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable String id, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(id, vehicle);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable String id) {
        vehicleService.deleteVehicle(id);
    }
}