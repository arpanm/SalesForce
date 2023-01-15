package com.ril.digital.salesforce.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.ril.digital.salesforce.domain.RetailerMapping} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RetailerMappingDTO implements Serializable {

    private Long id;

    private String name;

    private String prmId;

    private String agentCode;

    private Long pincode;

    private String state;

    private LocalDate registedOn;

    private LocalDate mappedOn;

    private LocalDate mappedTill;

    private Boolean isActive;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String createdBy;

    private String updatedBy;

    private SalesForceUserDTO relationshipManager;

    private SalesForceUserDTO approvedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrmId() {
        return prmId;
    }

    public void setPrmId(String prmId) {
        this.prmId = prmId;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getRegistedOn() {
        return registedOn;
    }

    public void setRegistedOn(LocalDate registedOn) {
        this.registedOn = registedOn;
    }

    public LocalDate getMappedOn() {
        return mappedOn;
    }

    public void setMappedOn(LocalDate mappedOn) {
        this.mappedOn = mappedOn;
    }

    public LocalDate getMappedTill() {
        return mappedTill;
    }

    public void setMappedTill(LocalDate mappedTill) {
        this.mappedTill = mappedTill;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public SalesForceUserDTO getRelationshipManager() {
        return relationshipManager;
    }

    public void setRelationshipManager(SalesForceUserDTO relationshipManager) {
        this.relationshipManager = relationshipManager;
    }

    public SalesForceUserDTO getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(SalesForceUserDTO approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RetailerMappingDTO)) {
            return false;
        }

        RetailerMappingDTO retailerMappingDTO = (RetailerMappingDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, retailerMappingDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RetailerMappingDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", prmId='" + getPrmId() + "'" +
            ", agentCode='" + getAgentCode() + "'" +
            ", pincode=" + getPincode() +
            ", state='" + getState() + "'" +
            ", registedOn='" + getRegistedOn() + "'" +
            ", mappedOn='" + getMappedOn() + "'" +
            ", mappedTill='" + getMappedTill() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", relationshipManager=" + getRelationshipManager() +
            ", approvedBy=" + getApprovedBy() +
            "}";
    }
}
