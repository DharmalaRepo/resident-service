package com.tech.society.residents.controllers;

import com.tech.society.residents.dto.AlertReminderDTO;
import com.tech.society.residents.dto.RequestContext;
import com.tech.society.residents.models.AlertReminder;
import com.tech.society.residents.services.AlertReminderService;
import com.tech.society.residents.util.AppLogger;
import com.tech.society.residents.util.ApplicationUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class AlertReminderController {

    @Autowired
    private AlertReminderService reminderService;

    @PostMapping
    public ResponseEntity<?> createReminder(@RequestBody AlertReminderDTO dto, HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            AlertReminderDTO result = reminderService.createReminder(dto, ctx);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            AppLogger.logError("createReminder", ctx.getUserName(), "Failed to create reminder", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create reminder");
        }
    }

    @GetMapping("/upcoming")
    public ResponseEntity<?> getUpcomingReminders(HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            return ResponseEntity.ok(reminderService.getUpcomingReminders(ctx));
        } catch (Exception ex) {
            AppLogger.logError("getUpcomingReminders", ctx.getUserName(), "Failed to fetch reminders", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch upcoming reminders");
        }
    }
}