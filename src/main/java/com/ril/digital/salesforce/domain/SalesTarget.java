package com.ril.digital.salesforce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ril.digital.salesforce.domain.enumeration.SalesTargetType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SalesTarget.
 */
@Entity
@Table(name = "sales_target")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SalesTarget implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private SalesTargetType type;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotNull
    @Column(name = "target_value", nullable = false)
    private Float targetValue;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @OneToMany(mappedBy = "target")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "target" }, allowSetters = true)
    private Set<SalesTargetFilter> filters = new HashSet<>();

    @OneToMany(mappedBy = "target")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "incentives", "target", "approvedBy" }, allowSetters = true)
    private Set<IncentiveScheme> incentiveSchemes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(
        value = {
            "subordinates",
            "approvedSalesTargets",
            "approvedIncentiveSchemes",
            "retailerMappings",
            "approvedRetailerMappings",
            "achivementCaches",
            "incentives",
            "approvedIncentives",
            "manager",
        },
        allowSetters = true
    )
    private SalesForceUser approvedBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SalesTarget id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalesTargetType getType() {
        return this.type;
    }

    public SalesTarget type(SalesTargetType type) {
        this.setType(type);
        return this;
    }

    public void setType(SalesTargetType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public SalesTarget startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public SalesTarget endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Float getTargetValue() {
        return this.targetValue;
    }

    public SalesTarget targetValue(Float targetValue) {
        this.setTargetValue(targetValue);
        return this;
    }

    public void setTargetValue(Float targetValue) {
        this.targetValue = targetValue;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public SalesTarget isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public SalesTarget createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public SalesTarget updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public SalesTarget createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public SalesTarget updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Set<SalesTargetFilter> getFilters() {
        return this.filters;
    }

    public void setFilters(Set<SalesTargetFilter> salesTargetFilters) {
        if (this.filters != null) {
            this.filters.forEach(i -> i.setTarget(null));
        }
        if (salesTargetFilters != null) {
            salesTargetFilters.forEach(i -> i.setTarget(this));
        }
        this.filters = salesTargetFilters;
    }

    public SalesTarget filters(Set<SalesTargetFilter> salesTargetFilters) {
        this.setFilters(salesTargetFilters);
        return this;
    }

    public SalesTarget addFilters(SalesTargetFilter salesTargetFilter) {
        this.filters.add(salesTargetFilter);
        salesTargetFilter.setTarget(this);
        return this;
    }

    public SalesTarget removeFilters(SalesTargetFilter salesTargetFilter) {
        this.filters.remove(salesTargetFilter);
        salesTargetFilter.setTarget(null);
        return this;
    }

    public Set<IncentiveScheme> getIncentiveSchemes() {
        return this.incentiveSchemes;
    }

    public void setIncentiveSchemes(Set<IncentiveScheme> incentiveSchemes) {
        if (this.incentiveSchemes != null) {
            this.incentiveSchemes.forEach(i -> i.setTarget(null));
        }
        if (incentiveSchemes != null) {
            incentiveSchemes.forEach(i -> i.setTarget(this));
        }
        this.incentiveSchemes = incentiveSchemes;
    }

    public SalesTarget incentiveSchemes(Set<IncentiveScheme> incentiveSchemes) {
        this.setIncentiveSchemes(incentiveSchemes);
        return this;
    }

    public SalesTarget addIncentiveSchemes(IncentiveScheme incentiveScheme) {
        this.incentiveSchemes.add(incentiveScheme);
        incentiveScheme.setTarget(this);
        return this;
    }

    public SalesTarget removeIncentiveSchemes(IncentiveScheme incentiveScheme) {
        this.incentiveSchemes.remove(incentiveScheme);
        incentiveScheme.setTarget(null);
        return this;
    }

    public SalesForceUser getApprovedBy() {
        return this.approvedBy;
    }

    public void setApprovedBy(SalesForceUser salesForceUser) {
        this.approvedBy = salesForceUser;
    }

    public SalesTarget approvedBy(SalesForceUser salesForceUser) {
        this.setApprovedBy(salesForceUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesTarget)) {
            return false;
        }
        return id != null && id.equals(((SalesTarget) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesTarget{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", targetValue=" + getTargetValue() +
            ", isActive='" + getIsActive() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
