package com.tech.society.residents.repositories;

import com.tech.society.residents.models.BroadcastMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BroadcastMessageRepository extends MongoRepository<BroadcastMessage, String> {

    List<BroadcastMessage> findBySocietyIdentifier(String societyIdentifier);

    List<BroadcastMessage> findByFlatNumbersContainingAndSocietyIdentifier(String flatNumber, String societyIdentifier);

    List<BroadcastMessage> findByActiveTrueAndSocietyIdentifier(String societyIdentifier);
}