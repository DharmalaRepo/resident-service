package com.tech.society.residents.dto;

import com.tech.society.residents.models.FamilyMember;
import com.tech.society.residents.models.ResidencyStatus;
import com.tech.society.residents.models.ResidentType;

import java.time.LocalDateTime;
import java.util.List;

public class ResidentDTO {
    private String residentId;
    private String name;
    private String blockNumber;
    private String flatNumber;
    private String mobileNumber;
    private String email;
    private String whatsappNumber;
    private ResidentType residentType;
    private ResidencyStatus residencyStatus;
    private LocalDateTime moveInDate;
    private LocalDateTime moveOutDate;
    private boolean showInDirectory;
    private List<FamilyMember> familyMembers;

    public ResidentDTO() {
    }

    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public ResidentType getResidentType() {
        return residentType;
    }

    public void setResidentType(ResidentType residentType) {
        this.residentType = residentType;
    }

    public ResidencyStatus getResidencyStatus() {
        return residencyStatus;
    }

    public void setResidencyStatus(ResidencyStatus residencyStatus) {
        this.residencyStatus = residencyStatus;
    }

    public LocalDateTime getMoveInDate() {
        return moveInDate;
    }

    public void setMoveInDate(LocalDateTime moveInDate) {
        this.moveInDate = moveInDate;
    }

    public LocalDateTime getMoveOutDate() {
        return moveOutDate;
    }

    public void setMoveOutDate(LocalDateTime moveOutDate) {
        this.moveOutDate = moveOutDate;
    }

    public boolean isShowInDirectory() {
        return showInDirectory;
    }

    public void setShowInDirectory(boolean showInDirectory) {
        this.showInDirectory = showInDirectory;
    }

    public List<FamilyMember> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<FamilyMember> familyMembers) {
        this.familyMembers = familyMembers;
    }
}
