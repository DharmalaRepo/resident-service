package com.tech.society.residents.repositories;

import com.tech.society.residents.models.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {

    List<Vehicle> findByResidentIdAndSocietyIdentifier(String residentId, String societyIdentifier);

    List<Vehicle> findByFlatNumberAndSocietyIdentifier(String flatNumber, String societyIdentifier);

    List<Vehicle> findBySocietyIdentifier(String societyIdentifier);

    List<Vehicle> findByActiveTrueAndSocietyIdentifier(String societyIdentifier);
}