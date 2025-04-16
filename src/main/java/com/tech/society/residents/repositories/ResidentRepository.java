package com.tech.society.residents.repositories;

import com.tech.society.residents.models.Resident;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResidentRepository extends MongoRepository<Resident, String> {

    // Get resident by customId
    Optional<Resident> findByCustomId(Long customId);

    // Delete one resident by customId
    void deleteByCustomId(Long customId);

    // Find all residents by list of custom IDs
    List<Resident> findByCustomIdIn(List<Long> customIds);

    // Delete all residents by list of custom IDs
    void deleteByCustomIdIn(List<Long> customIds);

    // Check existence by customId
    boolean existsByCustomId(Long customId);
}