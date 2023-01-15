package com.ril.digital.salesforce.service.dto;

import com.ril.digital.salesforce.domain.enumeration.IncentiveSchemeType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.ril.digital.salesforce.domain.IncentiveScheme} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class IncentiveSchemeDTO implements Serializable {

    private Long id;

    private IncentiveSchemeType type;

    private Float minAchivementPercent;

    private Float maxAchivementPercent;

    private Float value;

    private Boolean isActive;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String createdBy;

    private String updatedBy;

    private SalesTargetDTO target;

    private SalesForceUserDTO approvedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IncentiveSchemeType getType() {
        return type;
    }

    public void setType(IncentiveSchemeType type) {
        this.type = type;
    }

    public Float getMinAchivementPercent() {
        return minAchivementPercent;
    }

    public void setMinAchivementPercent(Float minAchivementPercent) {
        this.minAchivementPercent = minAchivementPercent;
    }

    public Float getMaxAchivementPercent() {
        return maxAchivementPercent;
    }

    public void setMaxAchivementPercent(Float maxAchivementPercent) {
        this.maxAchivementPercent = maxAchivementPercent;
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

    public SalesTargetDTO getTarget() {
        return target;
    }

    public void setTarget(SalesTargetDTO target) {
        this.target = target;
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
        if (!(o instanceof IncentiveSchemeDTO)) {
            return false;
        }

        IncentiveSchemeDTO incentiveSchemeDTO = (IncentiveSchemeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, incentiveSchemeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IncentiveSchemeDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", minAchivementPercent=" + getMinAchivementPercent() +
            ", maxAchivementPercent=" + getMaxAchivementPercent() +
            ", value=" + getValue() +
            ", isActive='" + getIsActive() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", target=" + getTarget() +
            ", approvedBy=" + getApprovedBy() +
            "}";
    }
}
