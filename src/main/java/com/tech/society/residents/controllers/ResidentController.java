package com.tech.society.residents.controllers;

import com.tech.society.residents.dto.RequestContext;
import com.tech.society.residents.dto.ResidentDTO;
import com.tech.society.residents.models.Resident;
import com.tech.society.residents.services.ResidentService;
import com.tech.society.residents.util.AppLogger;
import com.tech.society.residents.util.ApplicationUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ResidentDTO dto, HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            return ResponseEntity.ok(residentService.createResident(dto, ctx));
        } catch (Exception ex) {
            AppLogger.logError("createResident", ctx.getUserName(), "Resident creation failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create resident");
        }
    }

    @PutMapping("/{residentId}")
    public ResponseEntity<?> update(@PathVariable String residentId, @RequestBody ResidentDTO dto, HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            return ResponseEntity.ok(residentService.updateResident(residentId, dto, ctx));
        } catch (Exception ex) {
            AppLogger.logError("updateResident", ctx.getUserName(), "Resident update failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update resident");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            return ResponseEntity.ok(residentService.getAllResidents(ctx));
        } catch (Exception ex) {
            AppLogger.logError("getAllResidents", ctx.getUserName(), "Fetch failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch residents");
        }
    }

    @GetMapping("/directory")
    public ResponseEntity<?> getDirectory(HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            return ResponseEntity.ok(residentService.getDirectoryResidents(ctx));
        } catch (Exception ex) {
            AppLogger.logError("getDirectoryResidents", ctx.getUserName(), "Fetch failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch public directory");
        }
    }

    @PatchMapping("/{residentId}/deactivate")
    public ResponseEntity<?> deactivate(@PathVariable String residentId, HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            residentService.deactivateResident(residentId, ctx);
            return ResponseEntity.ok("Resident deactivated");
        } catch (Exception ex) {
            AppLogger.logError("deactivateResident", ctx.getUserName(), "Failed to deactivate", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to deactivate resident");
        }
    }

    @PatchMapping("/{residentId}/activate")
    public ResponseEntity<?> activate(@PathVariable String residentId, HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            residentService.activateResident(residentId, ctx);
            return ResponseEntity.ok("Resident activated");
        } catch (Exception ex) {
            AppLogger.logError("activateResident", ctx.getUserName(), "Failed to activate", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to activate resident");
        }
    }
}