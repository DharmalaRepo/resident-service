package com.tech.society.residents.repositories;

import com.tech.society.residents.models.*;
        import org.springframework.data.mongodb.repository.MongoRepository;
        import java.util.List;

public interface BroadcastMessageRepository extends MongoRepository<BroadcastMessage, String> {
    List<BroadcastMessage> findByIsActive(int isActive);
}