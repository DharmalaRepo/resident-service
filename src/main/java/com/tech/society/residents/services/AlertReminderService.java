package com.tech.society.residents.services;

import com.tech.society.residents.dto.AlertReminderDTO;
import com.tech.society.residents.dto.RequestContext;
import com.tech.society.residents.models.AlertReminder;
import com.tech.society.residents.repositories.AlertReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface AlertReminderService {

    AlertReminderDTO createReminder(AlertReminderDTO dto, RequestContext ctx);
    List<AlertReminderDTO> getUpcomingReminders(RequestContext ctx);
    List<AlertReminderDTO> getAllReminders(RequestContext ctx);
    void deleteReminder(String reminderId, RequestContext ctx);
}