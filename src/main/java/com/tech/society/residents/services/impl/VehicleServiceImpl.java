package com.tech.society.residents.services.impl;

import com.tech.society.residents.dto.RequestContext;
import com.tech.society.residents.dto.VehicleDTO;
import com.tech.society.residents.dto.VehicleMatchResponseDTO;
import com.tech.society.residents.models.UserType;
import com.tech.society.residents.models.Vehicle;
import com.tech.society.residents.repositories.ResidentRepository;
import com.tech.society.residents.repositories.VehicleRepository;
import com.tech.society.residents.services.VehicleService;
import com.tech.society.residents.util.AppLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public VehicleDTO createVehicle(VehicleDTO dto, RequestContext ctx) {
        Vehicle vehicle = mapToEntity(dto);
        vehicle.setVehicleId("VEH-" + UUID.randomUUID());
        vehicle.setSocietyIdentifier(ctx.getSocietyIdentifier());
        vehicle.setResidentId(dto.getResidentId());
        vehicle.setFlatNumber(dto.getFlatNumber());
        vehicle.auditCreate(ctx.getUserName());
        Vehicle saved = vehicleRepository.save(vehicle);

        AppLogger.log("createVehicle", ctx.getUserName(), "Created vehicle for resident: " + saved.getResidentId());
        return mapToDTO(saved);
    }

    @Override
    public VehicleDTO updateVehicle(String vehicleId, VehicleDTO dto, RequestContext ctx) {
        Vehicle existing = findByScopedId(vehicleId, ctx);
        existing.setVehicleNumber(dto.getVehicleNumber());
        existing.setVehicleType(dto.getVehicleType());
        existing.setBrand(dto.getBrand());
        existing.setColor(dto.getColor());
        existing.setParkingSlot(dto.getParkingSlot());
        existing.setFlatNumber(dto.getFlatNumber());
        existing.setResidentId(dto.getResidentId());
        existing.auditUpdate(ctx.getUserName());
        return mapToDTO(vehicleRepository.save(existing));
    }

    @Override
    public List<VehicleDTO> getAllVehicles(RequestContext ctx) {
        if (ctx.getUserType().equals(UserType.STAFF)) {
            throw new SecurityException("STAFF is not allowed to view vehicle data.");
        }
        return vehicleRepository.findBySocietyIdentifier(ctx.getSocietyIdentifier())
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<VehicleDTO> getByResident(String residentId, RequestContext ctx) {
        // Allow only self or ADMIN
        if (!ctx.getUserType().equals(UserType.ADMIN) && !ctx.getUserId().equals(residentId)) {
            throw new SecurityException("Not authorized to view vehicles for resident: " + residentId);
        }
        return vehicleRepository.findByResidentIdAndSocietyIdentifier(residentId, ctx.getSocietyIdentifier())
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<VehicleDTO> getByFlat(String flatNumber, RequestContext ctx) {
        if (ctx.getUserType().equals(UserType.STAFF)) {
            throw new SecurityException("STAFF is not allowed to view vehicle data.");
        }
        return vehicleRepository.findByFlatNumberAndSocietyIdentifier(flatNumber, ctx.getSocietyIdentifier())
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteVehicle(String vehicleId, RequestContext ctx) {
        Vehicle vehicle = findByScopedId(vehicleId, ctx);
        vehicle.setActive(false);
        vehicle.auditUpdate(ctx.getUserName());
        vehicleRepository.save(vehicle);
        AppLogger.log("deleteVehicle", ctx.getUserName(), "Soft-deleted vehicle " + vehicleId);
    }

    private Vehicle findByScopedId(String vehicleId, RequestContext ctx) {
        return vehicleRepository.findBySocietyIdentifier(ctx.getSocietyIdentifier()).stream()
                .filter(v -> vehicleId.equals(v.getVehicleId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    private VehicleDTO mapToDTO(Vehicle v) {
        VehicleDTO dto = new VehicleDTO();
        dto.setVehicleId(v.getVehicleId());
        dto.setVehicleNumber(v.getVehicleNumber());
        dto.setVehicleType(v.getVehicleType());
        dto.setBrand(v.getBrand());
        dto.setColor(v.getColor());
        dto.setFlatNumber(v.getFlatNumber());
        dto.setParkingSlot(v.getParkingSlot());
        dto.setResidentId(v.getResidentId());
        return dto;
    }

    private Vehicle mapToEntity(VehicleDTO dto) {
        Vehicle v = new Vehicle();
        v.setVehicleNumber(dto.getVehicleNumber());
        v.setVehicleType(dto.getVehicleType());
        v.setBrand(dto.getBrand());
        v.setColor(dto.getColor());
        v.setFlatNumber(dto.getFlatNumber());
        v.setParkingSlot(dto.getParkingSlot());
        v.setResidentId(dto.getResidentId());
        return v;
    }

    @Override
    public VehicleMatchResponseDTO findResidentVehicleByLastDigits(String last4Digits, RequestContext ctx) {
        List<Vehicle> matches = vehicleRepository.findBySocietyIdentifier(ctx.getSocietyIdentifier())
                .stream()
                .filter(v -> {
                    String number = v.getVehicleNumber();
                    return number != null && number.replaceAll("\\s+", "").endsWith(last4Digits);
                })
                .collect(Collectors.toList());

        VehicleMatchResponseDTO response = new VehicleMatchResponseDTO();
        if (!matches.isEmpty()) {
            Vehicle v = matches.get(0); // take the first match
            response.setMatchFound(true);
            response.setVehicleNumber(v.getVehicleNumber());
            response.setVehicleType(v.getVehicleType());
            response.setBrand(v.getBrand());
            response.setFlatNumber(v.getFlatNumber());

            // Optional: Lookup resident name
            residentRepository.findByResidentIdAndSocietyIdentifier(v.getResidentId(), ctx.getSocietyIdentifier())
                    .ifPresent(r -> response.setResidentName(r.getName()));
        } else {
            response.setMatchFound(false);
        }

        return response;
    }

}