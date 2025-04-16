package com.tech.society.residents.repositories;

import com.tech.society.residents.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
