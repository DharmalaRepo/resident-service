package com.tech.society.residents.services;


import com.tech.society.residents.models.Resident;
import com.tech.society.residents.repositories.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    // Insert a single resident
    public Resident addResident(Resident resident) {
        return residentRepository.save(resident);
    }

    // Insert multiple residents
    public List<Resident> addResidents(List<Resident> residents) {
        return residentRepository.saveAll(residents);
    }

    // Get all residents
    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }

    // Get resident by customId
    public Optional<Resident> getResidentByCustomId(Long customId) {
        return residentRepository.findByCustomId(customId);
    }

    // Update a single resident
    public Resident updateResident(Resident updatedResident) {
        Optional<Resident> existing = residentRepository.findByCustomId(updatedResident.getCustomId());
        if (existing.isPresent()) {
            Resident resident = existing.get();
            resident.setName(updatedResident.getName());
            resident.setFlatNumber(updatedResident.getFlatNumber());
            resident.setMobileNumber(updatedResident.getMobileNumber());
            resident.setEmail(updatedResident.getEmail());
            resident.setWhatsappNumber(updatedResident.getWhatsappNumber());
            resident.setVehicles(updatedResident.getVehicles());
            resident.setModifiedDate(updatedResident.getModifiedDate());
            resident.setModifiedBy(updatedResident.getModifiedBy());
            return residentRepository.save(resident);
        } else {
            throw new RuntimeException("Resident not found with customId: " + updatedResident.getCustomId());
        }
    }

    // Update multiple residents (based on incoming list)
    public List<Resident> updateResidents(List<Resident> updatedResidents) {
        return updatedResidents.stream()
                .map(this::updateResident)
                .toList();
    }

    // Delete a single resident by customId
    public void deleteResident(Long customId) {
        if (residentRepository.existsByCustomId(customId)) {
            residentRepository.deleteByCustomId(customId);
        } else {
            throw new RuntimeException("Resident not found with customId: " + customId);
        }
    }

    // Delete multiple residents by list of customIds
    public void deleteResidents(List<Long> customIds) {
        residentRepository.deleteByCustomIdIn(customIds);
    }
}