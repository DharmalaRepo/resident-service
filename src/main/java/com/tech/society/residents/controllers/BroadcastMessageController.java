package com.tech.society.residents.controllers;

import com.tech.society.residents.dto.BroadcastMessageDTO;
import com.tech.society.residents.dto.RequestContext;
import com.tech.society.residents.models.BroadcastMessage;
import com.tech.society.residents.services.BroadcastMessageService;
import com.tech.society.residents.util.AppLogger;
import com.tech.society.residents.util.ApplicationUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/broadcasts")
public class BroadcastMessageController {

    @Autowired
    private BroadcastMessageService broadcastService;

    @PostMapping
    public ResponseEntity<?> sendBroadcast(@RequestBody BroadcastMessageDTO dto, HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            return ResponseEntity.ok(broadcastService.sendBroadcast(dto, ctx));
        } catch (Exception ex) {
            AppLogger.logError("sendBroadcast", ctx.getUserName(), "Broadcast failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send broadcast");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            return ResponseEntity.ok(broadcastService.getAllMessages(ctx));
        } catch (Exception ex) {
            AppLogger.logError("getAllBroadcasts", ctx.getUserName(), "Fetch failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch messages");
        }
    }

    @GetMapping("/flat/{flatNumber}")
    public ResponseEntity<?> getForFlat(@PathVariable String flatNumber, HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            return ResponseEntity.ok(broadcastService.getForFlat(flatNumber, ctx));
        } catch (Exception ex) {
            AppLogger.logError("getBroadcastsForFlat", ctx.getUserName(), "Fetch failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch messages for flat");
        }
    }
}