package com.ril.digital.salesforce.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.ril.digital.salesforce.domain.Incentive} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class IncentiveDTO implements Serializable {

    private Long id;

    private Float achivementPercent;

    private Float value;

    private Boolean isActive;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String createdBy;

    private String updatedBy;

    private IncentiveSchemeDTO scheme;

    private SalesForceUserDTO salesAgent;

    private SalesForceUserDTO approvedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAchivementPercent() {
        return achivementPercent;
    }

    public void setAchivementPercent(Float achivementPercent) {
        this.achivementPercent = achivementPercent;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
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

    public IncentiveSchemeDTO getScheme() {
        return scheme;
    }

    public void setScheme(IncentiveSchemeDTO scheme) {
        this.scheme = scheme;
    }

    public SalesForceUserDTO getSalesAgent() {
        return salesAgent;
    }

    public void setSalesAgent(SalesForceUserDTO salesAgent) {
        this.salesAgent = salesAgent;
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
        if (!(o instanceof IncentiveDTO)) {
            return false;
        }

        IncentiveDTO incentiveDTO = (IncentiveDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, incentiveDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IncentiveDTO{" +
            "id=" + getId() +
            ", achivementPercent=" + getAchivementPercent() +
            ", value=" + getValue() +
            ", isActive='" + getIsActive() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", scheme=" + getScheme() +
            ", salesAgent=" + getSalesAgent() +
            ", approvedBy=" + getApprovedBy() +
            "}";
    }
}
