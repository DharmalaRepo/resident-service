package com.tech.society.residents.services;

import com.tech.society.residents.dto.BroadcastMessageDTO;
import com.tech.society.residents.dto.RequestContext;
import com.tech.society.residents.models.BroadcastMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BroadcastMessageService {

    BroadcastMessageDTO sendBroadcast(BroadcastMessageDTO dto, RequestContext ctx);
    List<BroadcastMessageDTO> getAllMessages(RequestContext ctx);
    List<BroadcastMessageDTO> getForFlat(String flatNumber, RequestContext ctx);
}