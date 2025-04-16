package com.tech.society.residents.controllers;

import com.tech.society.residents.models.AlertReminder;
import com.tech.society.residents.services.AlertReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class AlertReminderController {

    @Autowired
    private AlertReminderService alertReminderService;

    @PostMapping
    public AlertReminder createReminder(@RequestBody AlertReminder reminder) {
        return alertReminderService.createReminder(reminder);
    }

    @GetMapping("/upcoming")
    public List<AlertReminder> getUpcomingReminders() {
        return alertReminderService.getUpcomingReminders();
    }
}