package com.tech.society.residents.services.impl;


import com.tech.society.residents.dto.BroadcastMessageDTO;
import com.tech.society.residents.dto.RequestContext;
import com.tech.society.residents.models.BroadcastMessage;
import com.tech.society.residents.repositories.BroadcastMessageRepository;
import com.tech.society.residents.services.BroadcastMessageService;
import com.tech.society.residents.util.AppLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BroadcastMessageServiceImpl implements BroadcastMessageService {

    @Autowired
    private BroadcastMessageRepository broadcastMessageRepository;

    @Override
    public BroadcastMessageDTO sendBroadcast(BroadcastMessageDTO dto, RequestContext ctx) {
        BroadcastMessage msg = new BroadcastMessage();
        msg.setBroadcastId("BCAST-" + UUID.randomUUID());
        msg.setSocietyIdentifier(ctx.getSocietyIdentifier());
        msg.setTitle(dto.getTitle());
        msg.setMessage(dto.getMessage());
        msg.setAudience(dto.getAudience());
        msg.setFlatNumbers(dto.getFlatNumbers());
        msg.setDeliveryMethods(dto.getDeliveryMethods());
        msg.setSentBy(ctx.getUserName());
        msg.setSentAt(LocalDateTime.now());
        msg.setActive(true);
        msg.auditCreate(ctx.getUserName());

        AppLogger.log("sendBroadcast", ctx.getUserName(), "Broadcast sent to: " + dto.getAudience());

        return mapToDTO(broadcastMessageRepository.save(msg));
    }

    @Override
    public List<BroadcastMessageDTO> getAllMessages(RequestContext ctx) {
        return broadcastMessageRepository.findByActiveTrueAndSocietyIdentifier(ctx.getSocietyIdentifier())
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<BroadcastMessageDTO> getForFlat(String flatNumber, RequestContext ctx) {
        return broadcastMessageRepository.findByFlatNumbersContainingAndSocietyIdentifier(flatNumber, ctx.getSocietyIdentifier())
                .stream().filter(BroadcastMessage::isActive)
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    private BroadcastMessageDTO mapToDTO(BroadcastMessage msg) {
        BroadcastMessageDTO dto = new BroadcastMessageDTO();
        dto.setBroadcastId(msg.getBroadcastId());
        dto.setTitle(msg.getTitle());
        dto.setMessage(msg.getMessage());
        dto.setAudience(msg.getAudience());
        dto.setFlatNumbers(msg.getFlatNumbers());
        dto.setDeliveryMethods(msg.getDeliveryMethods());
        return dto;
    }
}