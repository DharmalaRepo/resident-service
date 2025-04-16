package com.tech.society.residents.repositories;


import com.tech.society.residents.models.*;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface AlertReminderRepository extends MongoRepository<AlertReminder, String> {
    List<AlertReminder> findByTriggerDateBefore(LocalDateTime date);
}