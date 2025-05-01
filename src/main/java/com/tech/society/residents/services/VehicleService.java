package com.tech.society.residents.services;

import com.tech.society.residents.dto.RequestContext;
import com.tech.society.residents.dto.VehicleDTO;
import com.tech.society.residents.dto.VehicleMatchResponseDTO;
import com.tech.society.residents.models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    VehicleDTO createVehicle(VehicleDTO dto, RequestContext ctx);
    VehicleDTO updateVehicle(String vehicleId, VehicleDTO dto, RequestContext ctx);
    List<VehicleDTO> getAllVehicles(RequestContext ctx);
    List<VehicleDTO> getByResident(String residentId, RequestContext ctx);
    List<VehicleDTO> getByFlat(String flatNumber, RequestContext ctx);
    void deleteVehicle(String vehicleId, RequestContext ctx);
    VehicleMatchResponseDTO findResidentVehicleByLastDigits(String last4Digits, RequestContext ctx);

}