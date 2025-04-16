package com.tech.society.residents.controllers;

import com.tech.society.residents.models.BroadcastMessage;
import com.tech.society.residents.services.BroadcastMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/broadcasts")
public class BroadcastMessageController {

    @Autowired
    private BroadcastMessageService broadcastMessageService;

    @GetMapping
    public List<BroadcastMessage> getAll() {
        return broadcastMessageService.getAll();
    }

    @PostMapping
    public BroadcastMessage send(@RequestBody BroadcastMessage message) {
        return broadcastMessageService.send(message);
    }
}