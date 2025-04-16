package com.tech.society.residents.services;

import com.tech.society.residents.models.Poll;
import com.tech.society.residents.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService {

    @Autowired
    private PollRepository repository;

    public List<Poll> getOpenPolls() {
        return repository.findByStatus("OPEN");
    }

    public Poll createPoll(Poll poll) {
        return repository.save(poll);
    }
}