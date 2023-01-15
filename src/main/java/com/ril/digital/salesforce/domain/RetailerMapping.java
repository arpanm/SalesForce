package com.ril.digital.salesforce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RetailerMapping.
 */
@Entity
@Table(name = "retailer_mapping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RetailerMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "prm_id")
    private String prmId;

    @Column(name = "agent_code")
    private String agentCode;

    @Column(name = "pincode")
    private Long pincode;

    @Column(name = "state")
    private String state;

    @Column(name = "region")
    private String region;

    @Column(name = "registed_on")
    private LocalDate registedOn;

    @Column(name = "mapped_on")
    private LocalDate mappedOn;

    @Column(name = "mapped_till")
    private LocalDate mappedTill;

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
    private SalesForceUser relationshipManager;

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

    public RetailerMapping id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public RetailerMapping name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrmId() {
        return this.prmId;
    }

    public RetailerMapping prmId(String prmId) {
        this.setPrmId(prmId);
        return this;
    }

    public void setPrmId(String prmId) {
        this.prmId = prmId;
    }

    public String getAgentCode() {
        return this.agentCode;
    }

    public RetailerMapping agentCode(String agentCode) {
        this.setAgentCode(agentCode);
        return this;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public Long getPincode() {
        return this.pincode;
    }

    public RetailerMapping pincode(Long pincode) {
        this.setPincode(pincode);
        return this;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return this.state;
    }

    public RetailerMapping state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegion() {
        return this.region;
    }

    public RetailerMapping region(String region) {
        this.setRegion(region);
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public LocalDate getRegistedOn() {
        return this.registedOn;
    }

    public RetailerMapping registedOn(LocalDate registedOn) {
        this.setRegistedOn(registedOn);
        return this;
    }

    public void setRegistedOn(LocalDate registedOn) {
        this.registedOn = registedOn;
    }

    public LocalDate getMappedOn() {
        return this.mappedOn;
    }

    public RetailerMapping mappedOn(LocalDate mappedOn) {
        this.setMappedOn(mappedOn);
        return this;
    }

    public void setMappedOn(LocalDate mappedOn) {
        this.mappedOn = mappedOn;
    }

    public LocalDate getMappedTill() {
        return this.mappedTill;
    }

    public RetailerMapping mappedTill(LocalDate mappedTill) {
        this.setMappedTill(mappedTill);
        return this;
    }

    public void setMappedTill(LocalDate mappedTill) {
        this.mappedTill = mappedTill;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public RetailerMapping isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public RetailerMapping createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public RetailerMapping updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public RetailerMapping createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public RetailerMapping updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public SalesForceUser getRelationshipManager() {
        return this.relationshipManager;
    }

    public void setRelationshipManager(SalesForceUser salesForceUser) {
        this.relationshipManager = salesForceUser;
    }

    public RetailerMapping relationshipManager(SalesForceUser salesForceUser) {
        this.setRelationshipManager(salesForceUser);
        return this;
    }

    public SalesForceUser getApprovedBy() {
        return this.approvedBy;
    }

    public void setApprovedBy(SalesForceUser salesForceUser) {
        this.approvedBy = salesForceUser;
    }

    public RetailerMapping approvedBy(SalesForceUser salesForceUser) {
        this.setApprovedBy(salesForceUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RetailerMapping)) {
            return false;
        }
        return id != null && id.equals(((RetailerMapping) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RetailerMapping{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", prmId='" + getPrmId() + "'" +
            ", agentCode='" + getAgentCode() + "'" +
            ", pincode=" + getPincode() +
            ", state='" + getState() + "'" +
            ", region='" + getRegion() + "'" +
            ", registedOn='" + getRegistedOn() + "'" +
            ", mappedOn='" + getMappedOn() + "'" +
            ", mappedTill='" + getMappedTill() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
