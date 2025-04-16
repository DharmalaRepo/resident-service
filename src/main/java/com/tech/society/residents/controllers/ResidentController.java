package com.tech.society.residents.controllers;

import com.tech.society.residents.models.Resident;
import com.tech.society.residents.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    // Get all residents
    @GetMapping
    public ResponseEntity<List<Resident>> getAllResidents() {
        List<Resident> residents = residentService.getAllResidents();
        return ResponseEntity.ok(residents);
    }

    // Get a single resident by customId
    @GetMapping("/{customId}")
    public ResponseEntity<Resident> getResidentByCustomId(@PathVariable Long customId) {
        return residentService.getResidentByCustomId(customId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Insert a single resident
    @PostMapping
    public ResponseEntity<Resident> createResident(@RequestBody Resident resident) {
        Resident created = residentService.addResident(resident);
        return ResponseEntity.ok(created);
    }

    // Insert multiple residents
    @PostMapping("/bulk")
    public ResponseEntity<List<Resident>> createResidents(@RequestBody List<Resident> residents) {
        return ResponseEntity.ok(residentService.addResidents(residents));
    }

    // Update a single resident
    @PutMapping
    public ResponseEntity<Resident> updateResident(@RequestBody Resident resident) {
        try {
            Resident updated = residentService.updateResident(resident);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update multiple residents
    @PutMapping("/bulk")
    public ResponseEntity<List<Resident>> updateResidents(@RequestBody List<Resident> residents) {
        return ResponseEntity.ok(residentService.updateResidents(residents));
    }

    // Delete a single resident
    @DeleteMapping("/{customId}")
    public ResponseEntity<Void> deleteResident(@PathVariable Long customId) {
        try {
            residentService.deleteResident(customId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete multiple residents
    @DeleteMapping("/bulk")
    public ResponseEntity<Void> deleteResidents(@RequestBody List<Long> customIds) {
        residentService.deleteResidents(customIds);
        return ResponseEntity.noContent().build();
    }
}
