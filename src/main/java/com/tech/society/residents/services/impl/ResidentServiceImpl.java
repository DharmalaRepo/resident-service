package com.tech.society.residents.services.impl;



import com.tech.society.residents.dto.RequestContext;
import com.tech.society.residents.dto.ResidentDTO;
import com.tech.society.residents.models.Resident;
import com.tech.society.residents.models.User;
import com.tech.society.residents.models.UserType;
import com.tech.society.residents.repositories.ResidentRepository;
import com.tech.society.residents.services.EncryptionService;
import com.tech.society.residents.services.ResidentService;
import com.tech.society.residents.services.UserServiceClient;
import com.tech.society.residents.util.AppLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResidentServiceImpl implements ResidentService {

    private static final Logger logger = LoggerFactory.getLogger(ResidentServiceImpl.class);

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public ResidentDTO createResident(ResidentDTO dto, RequestContext ctx) {
        Resident resident = mapToEntity(dto);
        resident.setSocietyIdentifier(ctx.getSocietyIdentifier());
        resident.setResidentId(generateCustomResidentId());

        // 🔐 Encrypt sensitive fields
        resident.setEncryptedMobileNumber(encryptionService.encrypt(dto.getMobileNumber()));
        resident.setEncryptedEmail(encryptionService.encrypt(dto.getEmail()));
        resident.setEncryptedWhatsappNumber(encryptionService.encrypt(dto.getWhatsappNumber()));

        // 📝 Audit Trail
        resident.auditCreate(ctx.getUserName());

        // Save
        Resident saved = residentRepository.save(resident);

        // 👤 Trigger login creation

        User user = new User();
        user.setUsername(dto.getEmail());
        user.setEmail(dto.getEmail());
        user.setMobileNumber(dto.getMobileNumber());
        user.setSocietyId(ctx.getSocietyIdentifier());
        user.setUserType("RESIDENT");
        user.setRoles(List.of("RESIDENT"));
        user.setPassword("pass@123"); // 🔒 set securely in future
        user.setCreatedBy(ctx.getUserName());
        user.setCreatedDate(LocalDateTime.now());
        user.setFirstLogin(true);
        user.setActive(true);

        // Call user registration API
        boolean loginCreated = userServiceClient.registerUser(user, ctx);

        saved.setLoginCreated(loginCreated);
        residentRepository.save(saved); // Save login flag update

        AppLogger.log("createResident", ctx.getUserName(), "Created resident with ID: " + saved.getResidentId());

        return mapToDTO(saved);
    }

    @Override
    public ResidentDTO updateResident(String residentId, ResidentDTO dto, RequestContext ctx) {
        Resident existing = residentRepository
                .findByResidentIdAndSocietyIdentifier(residentId, ctx.getSocietyIdentifier())
                .orElseThrow(() -> new RuntimeException("Resident not found"));

        // 🔐 Update encrypted fields
        existing.setEncryptedMobileNumber(encryptionService.encrypt(dto.getMobileNumber()));
        existing.setEncryptedEmail(encryptionService.encrypt(dto.getEmail()));
        existing.setEncryptedWhatsappNumber(encryptionService.encrypt(dto.getWhatsappNumber()));

        existing.setName(dto.getName());
        existing.setBlockNumber(dto.getBlockNumber());
        existing.setFlatNumber(dto.getFlatNumber());
        existing.setResidentType(dto.getResidentType());
        existing.setResidencyStatus(dto.getResidencyStatus());
        existing.setMoveInDate(dto.getMoveInDate());
        existing.setMoveOutDate(dto.getMoveOutDate());
        existing.setShowInDirectory(dto.isShowInDirectory());
        existing.setFamilyMembers(dto.getFamilyMembers());

        existing.auditUpdate(ctx.getUserName());

        return mapToDTO(residentRepository.save(existing));
    }

    @Override
    public ResidentDTO getResidentById(String residentId, RequestContext ctx) {
        Resident r = residentRepository
                .findByResidentIdAndSocietyIdentifier(residentId, ctx.getSocietyIdentifier())
                .orElseThrow(() -> new RuntimeException("Resident not found"));

        // 🔐 Access control
        if (!ctx.getUserType().equals(UserType.ADMIN) && !ctx.getUserId().equals(r.getResidentId())) {
            throw new SecurityException("Access denied");
        }

        return mapToDTO(r);
    }

    @Override
    public List<ResidentDTO> getAllResidents(RequestContext ctx) {
        if (ctx.getUserType().equals(UserType.STAFF)) {
            throw new SecurityException("Access denied for STAFF");
        }

        return residentRepository.findByActiveTrueAndSocietyIdentifier(ctx.getSocietyIdentifier())
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ResidentDTO> getDirectoryResidents(RequestContext ctx) {
        return residentRepository.findByActiveTrueAndSocietyIdentifier(ctx.getSocietyIdentifier())
                .stream().filter(Resident::isShowInDirectory)
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void deactivateResident(String residentId, RequestContext ctx) {
        Resident r = findByResidentScoped(residentId, ctx);
        r.setActive(false);
        r.auditUpdate(ctx.getUserName());
        residentRepository.save(r);
    }

    @Override
    public void activateResident(String residentId, RequestContext ctx) {
        Resident r = findByResidentScoped(residentId, ctx);
        r.setActive(true);
        r.auditUpdate(ctx.getUserName());
        residentRepository.save(r);
    }

    @Override
    public String decryptEmailByResidentId(String residentId, RequestContext ctx) {
        Resident r = findByResidentScoped(residentId, ctx);
        return encryptionService.decrypt(r.getEncryptedEmail());
    }

    // 🔎 Utility
    private Resident findByResidentScoped(String residentId, RequestContext ctx) {
        return residentRepository
                .findByResidentIdAndSocietyIdentifier(residentId, ctx.getSocietyIdentifier())
                .orElseThrow(() -> new RuntimeException("Resident not found"));
    }

    private ResidentDTO mapToDTO(Resident r) {
        ResidentDTO dto = new ResidentDTO();
        dto.setResidentId(r.getResidentId());
        dto.setName(r.getName());
        dto.setBlockNumber(r.getBlockNumber());
        dto.setFlatNumber(r.getFlatNumber());
        dto.setMobileNumber(encryptionService.decrypt(r.getEncryptedMobileNumber()));
        dto.setEmail(encryptionService.decrypt(r.getEncryptedEmail()));
        dto.setWhatsappNumber(encryptionService.decrypt(r.getEncryptedWhatsappNumber()));
        dto.setResidentType(r.getResidentType());
        dto.setResidencyStatus(r.getResidencyStatus());
        dto.setMoveInDate(r.getMoveInDate());
        dto.setMoveOutDate(r.getMoveOutDate());
        dto.setShowInDirectory(r.isShowInDirectory());
        dto.setFamilyMembers(r.getFamilyMembers());
        return dto;
    }

    private Resident mapToEntity(ResidentDTO dto) {
        Resident r = new Resident();
        r.setName(dto.getName());
        r.setBlockNumber(dto.getBlockNumber());
        r.setFlatNumber(dto.getFlatNumber());
        r.setResidentType(dto.getResidentType());
        r.setResidencyStatus(dto.getResidencyStatus());
        r.setMoveInDate(dto.getMoveInDate());
        r.setMoveOutDate(dto.getMoveOutDate());
        r.setShowInDirectory(dto.isShowInDirectory());
        r.setFamilyMembers(dto.getFamilyMembers());
        return r;
    }

    private String generateCustomResidentId() {
        return "RES-" + System.currentTimeMillis();
    }
}