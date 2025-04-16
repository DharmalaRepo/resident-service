package com.tech.society.residents.services;

import com.tech.society.residents.models.BroadcastMessage;
import com.tech.society.residents.repositories.BroadcastMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadcastMessageService {

    @Autowired
    private BroadcastMessageRepository repository;

    public List<BroadcastMessage> getAll() {
        return repository.findAll();
    }

    public BroadcastMessage send(BroadcastMessage message) {
        return repository.save(message);
    }
}