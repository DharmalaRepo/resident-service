package com.tech.society.residents.services.impl;

import com.tech.society.residents.dto.AlertReminderDTO;
import com.tech.society.residents.dto.RequestContext;
import com.tech.society.residents.models.AlertReminder;
import com.tech.society.residents.repositories.AlertReminderRepository;
import com.tech.society.residents.services.AlertReminderService;
import com.tech.society.residents.util.AppLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlertReminderServiceImpl implements AlertReminderService {

    @Autowired
    private AlertReminderRepository repository;

    @Override
    public AlertReminderDTO createReminder(AlertReminderDTO dto, RequestContext ctx) {
        AlertReminder reminder = new AlertReminder();
        reminder.setReminderId("REM-" + UUID.randomUUID());
        reminder.setSocietyIdentifier(ctx.getSocietyIdentifier());
        reminder.setResidentId(dto.getResidentId());
        reminder.setTitle(dto.getTitle());
        reminder.setMessage(dto.getMessage());
        reminder.setTriggerDate(dto.getTriggerDate());
        reminder.setDeliveryMethods(dto.getDeliveryMethods());
        reminder.setActive(true);
        reminder.setSent(false);
        reminder.auditCreate(ctx.getUserName());

        AppLogger.log("createReminder", ctx.getUserName(), "Created reminder: " + reminder.getTitle());

        return mapToDTO(repository.save(reminder));
    }

    @Override
    public List<AlertReminderDTO> getUpcomingReminders(RequestContext ctx) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next12h = now.plusHours(12);
        return repository.findByTriggerDateBetweenAndSocietyIdentifier(now, next12h, ctx.getSocietyIdentifier())
                .stream()
                .filter(AlertReminder::isActive)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AlertReminderDTO> getAllReminders(RequestContext ctx) {
        return repository.findBySocietyIdentifier(ctx.getSocietyIdentifier())
                .stream()
                .filter(AlertReminder::isActive)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReminder(String reminderId, RequestContext ctx) {
        AlertReminder reminder = repository.findBySocietyIdentifier(ctx.getSocietyIdentifier())
                .stream()
                .filter(r -> r.getReminderId().equals(reminderId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Reminder not found"));

        reminder.setActive(false);
        reminder.auditUpdate(ctx.getUserName());
        repository.save(reminder);

        AppLogger.log("deleteReminder", ctx.getUserName(), "Soft-deleted reminder: " + reminderId);
    }

    private AlertReminderDTO mapToDTO(AlertReminder r) {
        AlertReminderDTO dto = new AlertReminderDTO();
        dto.setReminderId(r.getReminderId());
        dto.setResidentId(r.getResidentId());
        dto.setTitle(r.getTitle());
        dto.setMessage(r.getMessage());
        dto.setTriggerDate(r.getTriggerDate());
        dto.setDeliveryMethods(r.getDeliveryMethods());
        return dto;
    }
}