package com.tech.society.residents.services;


import com.tech.society.residents.dto.RequestContext;
import com.tech.society.residents.dto.ResidentDTO;
import com.tech.society.residents.models.Resident;
import com.tech.society.residents.repositories.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ResidentService {

    ResidentDTO createResident(ResidentDTO dto, RequestContext ctx);
    ResidentDTO updateResident(String residentId, ResidentDTO dto, RequestContext ctx);
    ResidentDTO getResidentById(String residentId, RequestContext ctx);
    List<ResidentDTO> getAllResidents(RequestContext ctx);
    List<ResidentDTO> getDirectoryResidents(RequestContext ctx);

    void deactivateResident(String residentId, RequestContext ctx);
    void activateResident(String residentId, RequestContext ctx);

    String decryptEmailByResidentId(String residentId, RequestContext ctx); // for internal use
}