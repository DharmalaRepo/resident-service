package com.tech.society.residents.controllers;

import com.tech.society.residents.dto.RequestContext;
import com.tech.society.residents.dto.VehicleDTO;
import com.tech.society.residents.dto.VehicleMatchResponseDTO;
import com.tech.society.residents.models.Vehicle;
import com.tech.society.residents.services.VehicleService;
import com.tech.society.residents.util.AppLogger;
import com.tech.society.residents.util.ApplicationUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VehicleDTO dto, HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            return ResponseEntity.ok(vehicleService.createVehicle(dto, ctx));
        } catch (Exception ex) {
            AppLogger.logError("createVehicle", ctx.getUserName(), "Vehicle creation failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create vehicle");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            return ResponseEntity.ok(vehicleService.getAllVehicles(ctx));
        } catch (Exception ex) {
            AppLogger.logError("getAllVehicles", ctx.getUserName(), "Fetch failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch vehicles");
        }
    }

    @GetMapping("/resident/{residentId}")
    public ResponseEntity<?> getByResident(@PathVariable String residentId, HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            return ResponseEntity.ok(vehicleService.getByResident(residentId, ctx));
        } catch (Exception ex) {
            AppLogger.logError("getVehiclesByResident", ctx.getUserName(), "Fetch failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch vehicles for resident");
        }
    }

    @GetMapping("/flat/{flatNumber}")
    public ResponseEntity<?> getByFlat(@PathVariable String flatNumber, HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            return ResponseEntity.ok(vehicleService.getByFlat(flatNumber, ctx));
        } catch (Exception ex) {
            AppLogger.logError("getVehiclesByFlat", ctx.getUserName(), "Fetch failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch vehicles for flat");
        }
    }

    @GetMapping("/verify/metadata/{last4Digits}")
    public ResponseEntity<?> findResidentVehicle(@PathVariable String last4Digits, HttpServletRequest request) {
        RequestContext ctx = ApplicationUtils.getRequestContext(request);
        try {
            return ResponseEntity.ok(vehicleService.findResidentVehicleByLastDigits(last4Digits, ctx));
        } catch (Exception ex) {
            AppLogger.logError("findResidentVehicle", ctx.getUserName(), "Vehicle match lookup failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Vehicle verification failed");
        }
    }
}