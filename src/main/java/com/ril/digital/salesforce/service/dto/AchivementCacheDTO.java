package com.ril.digital.salesforce.service.dto;

import com.ril.digital.salesforce.domain.enumeration.Category;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.ril.digital.salesforce.domain.AchivementCache} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AchivementCacheDTO implements Serializable {

    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String articleId;

    private Category category;

    private String brand;

    private String model;

    private String retailerId;

    private String retailerState;

    private Long orderQty;

    private Float totalOrderValue;

    private Boolean isActive;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String createdBy;

    private String updatedBy;

    private SalesForceUserDTO salesAgent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

    public String getRetailerState() {
        return retailerState;
    }

    public void setRetailerState(String retailerState) {
        this.retailerState = retailerState;
    }

    public Long getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Long orderQty) {
        this.orderQty = orderQty;
    }

    public Float getTotalOrderValue() {
        return totalOrderValue;
    }

    public void setTotalOrderValue(Float totalOrderValue) {
        this.totalOrderValue = totalOrderValue;
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

    public SalesForceUserDTO getSalesAgent() {
        return salesAgent;
    }

    public void setSalesAgent(SalesForceUserDTO salesAgent) {
        this.salesAgent = salesAgent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AchivementCacheDTO)) {
            return false;
        }

        AchivementCacheDTO achivementCacheDTO = (AchivementCacheDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, achivementCacheDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AchivementCacheDTO{" +
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
            ", salesAgent=" + getSalesAgent() +
            "}";
    }
}
