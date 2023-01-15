package com.ril.digital.salesforce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Incentive.
 */
@Entity
@Table(name = "incentive")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Incentive implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "achivement_percent")
    private Float achivementPercent;

    @Column(name = "value")
    private Float value;

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

    @ManyToOne
    @JsonIgnoreProperties(value = { "incentives", "target", "approvedBy" }, allowSetters = true)
    private IncentiveScheme scheme;

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
    private SalesForceUser salesAgent;

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

    public Incentive id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAchivementPercent() {
        return this.achivementPercent;
    }

    public Incentive achivementPercent(Float achivementPercent) {
        this.setAchivementPercent(achivementPercent);
        return this;
    }

    public void setAchivementPercent(Float achivementPercent) {
        this.achivementPercent = achivementPercent;
    }

    public Float getValue() {
        return this.value;
    }

    public Incentive value(Float value) {
        this.setValue(value);
        return this;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Incentive isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public Incentive createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public Incentive updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Incentive createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Incentive updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public IncentiveScheme getScheme() {
        return this.scheme;
    }

    public void setScheme(IncentiveScheme incentiveScheme) {
        this.scheme = incentiveScheme;
    }

    public Incentive scheme(IncentiveScheme incentiveScheme) {
        this.setScheme(incentiveScheme);
        return this;
    }

    public SalesForceUser getSalesAgent() {
        return this.salesAgent;
    }

    public void setSalesAgent(SalesForceUser salesForceUser) {
        this.salesAgent = salesForceUser;
    }

    public Incentive salesAgent(SalesForceUser salesForceUser) {
        this.setSalesAgent(salesForceUser);
        return this;
    }

    public SalesForceUser getApprovedBy() {
        return this.approvedBy;
    }

    public void setApprovedBy(SalesForceUser salesForceUser) {
        this.approvedBy = salesForceUser;
    }

    public Incentive approvedBy(SalesForceUser salesForceUser) {
        this.setApprovedBy(salesForceUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Incentive)) {
            return false;
        }
        return id != null && id.equals(((Incentive) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Incentive{" +
            "id=" + getId() +
            ", achivementPercent=" + getAchivementPercent() +
            ", value=" + getValue() +
            ", isActive='" + getIsActive() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
