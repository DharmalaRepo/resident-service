package com.tech.society.residents.repositories;

import com.tech.society.residents.models.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    List<Vehicle> findByResidentId(String residentId);
    List<Vehicle> findByFlatNumber(String flatNumber);
}