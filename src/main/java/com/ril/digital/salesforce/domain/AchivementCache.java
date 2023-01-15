package com.ril.digital.salesforce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ril.digital.salesforce.domain.enumeration.Category;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AchivementCache.
 */
@Entity
@Table(name = "achivement_cache")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AchivementCache implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "article_id")
    private String articleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "retailer_id")
    private String retailerId;

    @Column(name = "retailer_state")
    private String retailerState;

    @Column(name = "order_qty")
    private Long orderQty;

    @Column(name = "total_order_value")
    private Float totalOrderValue;

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
    private SalesForceUser salesAgent;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AchivementCache id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public AchivementCache startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public AchivementCache endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getArticleId() {
        return this.articleId;
    }

    public AchivementCache articleId(String articleId) {
        this.setArticleId(articleId);
        return this;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Category getCategory() {
        return this.category;
    }

    public AchivementCache category(Category category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBrand() {
        return this.brand;
    }

    public AchivementCache brand(String brand) {
        this.setBrand(brand);
        return this;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public AchivementCache model(String model) {
        this.setModel(model);
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRetailerId() {
        return this.retailerId;
    }

    public AchivementCache retailerId(String retailerId) {
        this.setRetailerId(retailerId);
        return this;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

    public String getRetailerState() {
        return this.retailerState;
    }

    public AchivementCache retailerState(String retailerState) {
        this.setRetailerState(retailerState);
        return this;
    }

    public void setRetailerState(String retailerState) {
        this.retailerState = retailerState;
    }

    public Long getOrderQty() {
        return this.orderQty;
    }

    public AchivementCache orderQty(Long orderQty) {
        this.setOrderQty(orderQty);
        return this;
    }

    public void setOrderQty(Long orderQty) {
        this.orderQty = orderQty;
    }

    public Float getTotalOrderValue() {
        return this.totalOrderValue;
    }

    public AchivementCache totalOrderValue(Float totalOrderValue) {
        this.setTotalOrderValue(totalOrderValue);
        return this;
    }

    public void setTotalOrderValue(Float totalOrderValue) {
        this.totalOrderValue = totalOrderValue;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public AchivementCache isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public AchivementCache createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public AchivementCache updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public AchivementCache createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public AchivementCache updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public SalesForceUser getSalesAgent() {
        return this.salesAgent;
    }

    public void setSalesAgent(SalesForceUser salesForceUser) {
        this.salesAgent = salesForceUser;
    }

    public AchivementCache salesAgent(SalesForceUser salesForceUser) {
        this.setSalesAgent(salesForceUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AchivementCache)) {
            return false;
        }
        return id != null && id.equals(((AchivementCache) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AchivementCache{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", articleId='" + getArticleId() + "'" +
            ", category='" + getCategory() + "'" +
            ", brand='" + getBrand() + "'" +
            ", model='" + getModel() + "'" +
            ", retailerId='" + getRetailerId() + "'" +
            ", retailerState='" + getRetailerState() + "'" +
            ", orderQty=" + getOrderQty() +
            ", totalOrderValue=" + getTotalOrderValue() +
            ", isActive='" + getIsActive() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
