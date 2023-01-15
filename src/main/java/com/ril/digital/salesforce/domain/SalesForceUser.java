package com.ril.digital.salesforce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ril.digital.salesforce.domain.enumeration.Role;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SalesForceUser.
 */
@Entity
@Table(name = "sales_force_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SalesForceUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Pattern(regexp = "^[^@\\s]+@ril\\.[^@\\s]+$")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Min(value = 1000000000L)
    @Max(value = 9999999999L)
    @Column(name = "phone", nullable = false)
    private Long phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role userRole;

    @Column(name = "date_of_joining")
    private LocalDate dateOfJoining;

    @Column(name = "date_of_exit")
    private LocalDate dateOfExit;

    @Column(name = "state")
    private String state;

    @Column(name = "region")
    private String region;

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

    @OneToMany(mappedBy = "manager")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    private Set<SalesForceUser> subordinates = new HashSet<>();

    @OneToMany(mappedBy = "approvedBy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "filters", "incentiveSchemes", "approvedBy" }, allowSetters = true)
    private Set<SalesTarget> approvedSalesTargets = new HashSet<>();

    @OneToMany(mappedBy = "approvedBy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "incentives", "target", "approvedBy" }, allowSetters = true)
    private Set<IncentiveScheme> approvedIncentiveSchemes = new HashSet<>();

    @OneToMany(mappedBy = "relationshipManager")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "relationshipManager", "approvedBy" }, allowSetters = true)
    private Set<RetailerMapping> retailerMappings = new HashSet<>();

    @OneToMany(mappedBy = "approvedBy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "relationshipManager", "approvedBy" }, allowSetters = true)
    private Set<RetailerMapping> approvedRetailerMappings = new HashSet<>();

    @OneToMany(mappedBy = "salesAgent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "salesAgent" }, allowSetters = true)
    private Set<AchivementCache> achivementCaches = new HashSet<>();

    @OneToMany(mappedBy = "salesAgent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "scheme", "salesAgent", "approvedBy" }, allowSetters = true)
    private Set<Incentive> incentives = new HashSet<>();

    @OneToMany(mappedBy = "approvedBy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "scheme", "salesAgent", "approvedBy" }, allowSetters = true)
    private Set<Incentive> approvedIncentives = new HashSet<>();

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
    private SalesForceUser manager;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SalesForceUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public SalesForceUser name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public SalesForceUser email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return this.phone;
    }

    public SalesForceUser phone(Long phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Role getUserRole() {
        return this.userRole;
    }

    public SalesForceUser userRole(Role userRole) {
        this.setUserRole(userRole);
        return this;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public LocalDate getDateOfJoining() {
        return this.dateOfJoining;
    }

    public SalesForceUser dateOfJoining(LocalDate dateOfJoining) {
        this.setDateOfJoining(dateOfJoining);
        return this;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public LocalDate getDateOfExit() {
        return this.dateOfExit;
    }

    public SalesForceUser dateOfExit(LocalDate dateOfExit) {
        this.setDateOfExit(dateOfExit);
        return this;
    }

    public void setDateOfExit(LocalDate dateOfExit) {
        this.dateOfExit = dateOfExit;
    }

    public String getState() {
        return this.state;
    }

    public SalesForceUser state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegion() {
        return this.region;
    }

    public SalesForceUser region(String region) {
        this.setRegion(region);
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public SalesForceUser isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public SalesForceUser createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public SalesForceUser updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public SalesForceUser createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public SalesForceUser updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Set<SalesForceUser> getSubordinates() {
        return this.subordinates;
    }

    public void setSubordinates(Set<SalesForceUser> salesForceUsers) {
        if (this.subordinates != null) {
            this.subordinates.forEach(i -> i.setManager(null));
        }
        if (salesForceUsers != null) {
            salesForceUsers.forEach(i -> i.setManager(this));
        }
        this.subordinates = salesForceUsers;
    }

    public SalesForceUser subordinates(Set<SalesForceUser> salesForceUsers) {
        this.setSubordinates(salesForceUsers);
        return this;
    }

    public SalesForceUser addSubordinates(SalesForceUser salesForceUser) {
        this.subordinates.add(salesForceUser);
        salesForceUser.setManager(this);
        return this;
    }

    public SalesForceUser removeSubordinates(SalesForceUser salesForceUser) {
        this.subordinates.remove(salesForceUser);
        salesForceUser.setManager(null);
        return this;
    }

    public Set<SalesTarget> getApprovedSalesTargets() {
        return this.approvedSalesTargets;
    }

    public void setApprovedSalesTargets(Set<SalesTarget> salesTargets) {
        if (this.approvedSalesTargets != null) {
            this.approvedSalesTargets.forEach(i -> i.setApprovedBy(null));
        }
        if (salesTargets != null) {
            salesTargets.forEach(i -> i.setApprovedBy(this));
        }
        this.approvedSalesTargets = salesTargets;
    }

    public SalesForceUser approvedSalesTargets(Set<SalesTarget> salesTargets) {
        this.setApprovedSalesTargets(salesTargets);
        return this;
    }

    public SalesForceUser addApprovedSalesTargets(SalesTarget salesTarget) {
        this.approvedSalesTargets.add(salesTarget);
        salesTarget.setApprovedBy(this);
        return this;
    }

    public SalesForceUser removeApprovedSalesTargets(SalesTarget salesTarget) {
        this.approvedSalesTargets.remove(salesTarget);
        salesTarget.setApprovedBy(null);
        return this;
    }

    public Set<IncentiveScheme> getApprovedIncentiveSchemes() {
        return this.approvedIncentiveSchemes;
    }

    public void setApprovedIncentiveSchemes(Set<IncentiveScheme> incentiveSchemes) {
        if (this.approvedIncentiveSchemes != null) {
            this.approvedIncentiveSchemes.forEach(i -> i.setApprovedBy(null));
        }
        if (incentiveSchemes != null) {
            incentiveSchemes.forEach(i -> i.setApprovedBy(this));
        }
        this.approvedIncentiveSchemes = incentiveSchemes;
    }

    public SalesForceUser approvedIncentiveSchemes(Set<IncentiveScheme> incentiveSchemes) {
        this.setApprovedIncentiveSchemes(incentiveSchemes);
        return this;
    }

    public SalesForceUser addApprovedIncentiveSchemes(IncentiveScheme incentiveScheme) {
        this.approvedIncentiveSchemes.add(incentiveScheme);
        incentiveScheme.setApprovedBy(this);
        return this;
    }

    public SalesForceUser removeApprovedIncentiveSchemes(IncentiveScheme incentiveScheme) {
        this.approvedIncentiveSchemes.remove(incentiveScheme);
        incentiveScheme.setApprovedBy(null);
        return this;
    }

    public Set<RetailerMapping> getRetailerMappings() {
        return this.retailerMappings;
    }

    public void setRetailerMappings(Set<RetailerMapping> retailerMappings) {
        if (this.retailerMappings != null) {
            this.retailerMappings.forEach(i -> i.setRelationshipManager(null));
        }
        if (retailerMappings != null) {
            retailerMappings.forEach(i -> i.setRelationshipManager(this));
        }
        this.retailerMappings = retailerMappings;
    }

    public SalesForceUser retailerMappings(Set<RetailerMapping> retailerMappings) {
        this.setRetailerMappings(retailerMappings);
        return this;
    }

    public SalesForceUser addRetailerMappings(RetailerMapping retailerMapping) {
        this.retailerMappings.add(retailerMapping);
        retailerMapping.setRelationshipManager(this);
        return this;
    }

    public SalesForceUser removeRetailerMappings(RetailerMapping retailerMapping) {
        this.retailerMappings.remove(retailerMapping);
        retailerMapping.setRelationshipManager(null);
        return this;
    }

    public Set<RetailerMapping> getApprovedRetailerMappings() {
        return this.approvedRetailerMappings;
    }

    public void setApprovedRetailerMappings(Set<RetailerMapping> retailerMappings) {
        if (this.approvedRetailerMappings != null) {
            this.approvedRetailerMappings.forEach(i -> i.setApprovedBy(null));
        }
        if (retailerMappings != null) {
            retailerMappings.forEach(i -> i.setApprovedBy(this));
        }
        this.approvedRetailerMappings = retailerMappings;
    }

    public SalesForceUser approvedRetailerMappings(Set<RetailerMapping> retailerMappings) {
        this.setApprovedRetailerMappings(retailerMappings);
        return this;
    }

    public SalesForceUser addApprovedRetailerMappings(RetailerMapping retailerMapping) {
        this.approvedRetailerMappings.add(retailerMapping);
        retailerMapping.setApprovedBy(this);
        return this;
    }

    public SalesForceUser removeApprovedRetailerMappings(RetailerMapping retailerMapping) {
        this.approvedRetailerMappings.remove(retailerMapping);
        retailerMapping.setApprovedBy(null);
        return this;
    }

    public Set<AchivementCache> getAchivementCaches() {
        return this.achivementCaches;
    }

    public void setAchivementCaches(Set<AchivementCache> achivementCaches) {
        if (this.achivementCaches != null) {
            this.achivementCaches.forEach(i -> i.setSalesAgent(null));
        }
        if (achivementCaches != null) {
            achivementCaches.forEach(i -> i.setSalesAgent(this));
        }
        this.achivementCaches = achivementCaches;
    }

    public SalesForceUser achivementCaches(Set<AchivementCache> achivementCaches) {
        this.setAchivementCaches(achivementCaches);
        return this;
    }

    public SalesForceUser addAchivementCaches(AchivementCache achivementCache) {
        this.achivementCaches.add(achivementCache);
        achivementCache.setSalesAgent(this);
        return this;
    }

    public SalesForceUser removeAchivementCaches(AchivementCache achivementCache) {
        this.achivementCaches.remove(achivementCache);
        achivementCache.setSalesAgent(null);
        return this;
    }

    public Set<Incentive> getIncentives() {
        return this.incentives;
    }

    public void setIncentives(Set<Incentive> incentives) {
        if (this.incentives != null) {
            this.incentives.forEach(i -> i.setSalesAgent(null));
        }
        if (incentives != null) {
            incentives.forEach(i -> i.setSalesAgent(this));
        }
        this.incentives = incentives;
    }

    public SalesForceUser incentives(Set<Incentive> incentives) {
        this.setIncentives(incentives);
        return this;
    }

    public SalesForceUser addIncentives(Incentive incentive) {
        this.incentives.add(incentive);
        incentive.setSalesAgent(this);
        return this;
    }

    public SalesForceUser removeIncentives(Incentive incentive) {
        this.incentives.remove(incentive);
        incentive.setSalesAgent(null);
        return this;
    }

    public Set<Incentive> getApprovedIncentives() {
        return this.approvedIncentives;
    }

    public void setApprovedIncentives(Set<Incentive> incentives) {
        if (this.approvedIncentives != null) {
            this.approvedIncentives.forEach(i -> i.setApprovedBy(null));
        }
        if (incentives != null) {
            incentives.forEach(i -> i.setApprovedBy(this));
        }
        this.approvedIncentives = incentives;
    }

    public SalesForceUser approvedIncentives(Set<Incentive> incentives) {
        this.setApprovedIncentives(incentives);
        return this;
    }

    public SalesForceUser addApprovedIncentives(Incentive incentive) {
        this.approvedIncentives.add(incentive);
        incentive.setApprovedBy(this);
        return this;
    }

    public SalesForceUser removeApprovedIncentives(Incentive incentive) {
        this.approvedIncentives.remove(incentive);
        incentive.setApprovedBy(null);
        return this;
    }

    public SalesForceUser getManager() {
        return this.manager;
    }

    public void setManager(SalesForceUser salesForceUser) {
        this.manager = salesForceUser;
    }

    public SalesForceUser manager(SalesForceUser salesForceUser) {
        this.setManager(salesForceUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesForceUser)) {
            return false;
        }
        return id != null && id.equals(((SalesForceUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesForceUser{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone=" + getPhone() +
            ", userRole='" + getUserRole() + "'" +
            ", dateOfJoining='" + getDateOfJoining() + "'" +
            ", dateOfExit='" + getDateOfExit() + "'" +
            ", state='" + getState() + "'" +
            ", region='" + getRegion() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
