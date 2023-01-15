package com.ril.digital.salesforce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ril.digital.salesforce.domain.enumeration.IncentiveSchemeType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A IncentiveScheme.
 */
@Entity
@Table(name = "incentive_scheme")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class IncentiveScheme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private IncentiveSchemeType type;

    @Column(name = "min_achivement_percent")
    private Float minAchivementPercent;

    @Column(name = "max_achivement_percent")
    private Float maxAchivementPercent;

    @Column(name = "scheme_value")
    private Float schemeValue;

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

    @OneToMany(mappedBy = "scheme")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "scheme", "salesAgent", "approvedBy" }, allowSetters = true)
    private Set<Incentive> incentives = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "filters", "incentiveSchemes", "approvedBy" }, allowSetters = true)
    private SalesTarget target;

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

    public IncentiveScheme id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IncentiveSchemeType getType() {
        return this.type;
    }

    public IncentiveScheme type(IncentiveSchemeType type) {
        this.setType(type);
        return this;
    }

    public void setType(IncentiveSchemeType type) {
        this.type = type;
    }

    public Float getMinAchivementPercent() {
        return this.minAchivementPercent;
    }

    public IncentiveScheme minAchivementPercent(Float minAchivementPercent) {
        this.setMinAchivementPercent(minAchivementPercent);
        return this;
    }

    public void setMinAchivementPercent(Float minAchivementPercent) {
        this.minAchivementPercent = minAchivementPercent;
    }

    public Float getMaxAchivementPercent() {
        return this.maxAchivementPercent;
    }

    public IncentiveScheme maxAchivementPercent(Float maxAchivementPercent) {
        this.setMaxAchivementPercent(maxAchivementPercent);
        return this;
    }

    public void setMaxAchivementPercent(Float maxAchivementPercent) {
        this.maxAchivementPercent = maxAchivementPercent;
    }

    public Float getSchemeValue() {
        return this.schemeValue;
    }

    public IncentiveScheme schemeValue(Float schemeValue) {
        this.setSchemeValue(schemeValue);
        return this;
    }

    public void setSchemeValue(Float schemeValue) {
        this.schemeValue = schemeValue;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public IncentiveScheme isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public IncentiveScheme createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public IncentiveScheme updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public IncentiveScheme createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public IncentiveScheme updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Set<Incentive> getIncentives() {
        return this.incentives;
    }

    public void setIncentives(Set<Incentive> incentives) {
        if (this.incentives != null) {
            this.incentives.forEach(i -> i.setScheme(null));
        }
        if (incentives != null) {
            incentives.forEach(i -> i.setScheme(this));
        }
        this.incentives = incentives;
    }

    public IncentiveScheme incentives(Set<Incentive> incentives) {
        this.setIncentives(incentives);
        return this;
    }

    public IncentiveScheme addIncentives(Incentive incentive) {
        this.incentives.add(incentive);
        incentive.setScheme(this);
        return this;
    }

    public IncentiveScheme removeIncentives(Incentive incentive) {
        this.incentives.remove(incentive);
        incentive.setScheme(null);
        return this;
    }

    public SalesTarget getTarget() {
        return this.target;
    }

    public void setTarget(SalesTarget salesTarget) {
        this.target = salesTarget;
    }

    public IncentiveScheme target(SalesTarget salesTarget) {
        this.setTarget(salesTarget);
        return this;
    }

    public SalesForceUser getApprovedBy() {
        return this.approvedBy;
    }

    public void setApprovedBy(SalesForceUser salesForceUser) {
        this.approvedBy = salesForceUser;
    }

    public IncentiveScheme approvedBy(SalesForceUser salesForceUser) {
        this.setApprovedBy(salesForceUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IncentiveScheme)) {
            return false;
        }
        return id != null && id.equals(((IncentiveScheme) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IncentiveScheme{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", minAchivementPercent=" + getMinAchivementPercent() +
            ", maxAchivementPercent=" + getMaxAchivementPercent() +
            ", schemeValue=" + getSchemeValue() +
            ", isActive='" + getIsActive() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
