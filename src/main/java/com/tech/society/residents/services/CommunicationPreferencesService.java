package com.tech.society.residents.services;

import com.tech.society.residents.dto.CommunicationPreferencesDTO;
import com.tech.society.residents.dto.RequestContext;

public interface CommunicationPreferencesService {

    CommunicationPreferencesDTO getPreferences(RequestContext ctx);
    CommunicationPreferencesDTO updatePreferences(CommunicationPreferencesDTO dto, RequestContext ctx);
}