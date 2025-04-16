package com.tech.society.residents.controllers;

import com.tech.society.residents.models.Poll;
import com.tech.society.residents.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    @GetMapping
    public List<Poll> getOpenPolls() {
        return pollService.getOpenPolls();
    }

    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        return pollService.createPoll(poll);
    }
}