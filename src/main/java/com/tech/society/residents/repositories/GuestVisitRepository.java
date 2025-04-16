package com.tech.society.residents.repositories;


import com.tech.society.residents.models.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface GuestVisitRepository extends MongoRepository<GuestVisit, String> {
    List<GuestVisit> findByVisitingResidentId(int residentId);
    List<GuestVisit> findByFlatNumber(String flatNumber);
}