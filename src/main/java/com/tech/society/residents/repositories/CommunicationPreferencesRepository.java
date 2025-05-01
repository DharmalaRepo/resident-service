package com.tech.society.residents.repositories;


import com.tech.society.residents.models.CommunicationPreferences;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CommunicationPreferencesRepository extends MongoRepository<CommunicationPreferences, String> {

    Optional<CommunicationPreferences> findByResidentIdAndSocietyIdentifier(String residentId, String societyIdentifier);
}