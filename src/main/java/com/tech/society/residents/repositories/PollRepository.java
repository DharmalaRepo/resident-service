package com.tech.society.residents.repositories;

import com.tech.society.residents.models.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface PollRepository extends MongoRepository<Poll, String> {
    List<Poll> findByStatus(String status);
}