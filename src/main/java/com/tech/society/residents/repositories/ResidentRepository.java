package com.tech.society.residents.repositories;

import com.tech.society.residents.models.Resident;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ResidentRepository extends MongoRepository<Resident, String> {

    Optional<Resident> findByResidentIdAndSocietyIdentifier(String residentId, String societyIdentifier);

    List<Resident> findBySocietyIdentifier(String societyIdentifier);

    List<Resident> findByFlatNumberAndSocietyIdentifier(String flatNumber, String societyIdentifier);

    List<Resident> findByBlockNumberAndSocietyIdentifier(String blockNumber, String societyIdentifier);

    Optional<Resident> findByEncryptedMobileNumberAndSocietyIdentifier(String encryptedMobile, String societyIdentifier);

    List<Resident> findByActiveTrueAndSocietyIdentifier(String societyIdentifier);
}