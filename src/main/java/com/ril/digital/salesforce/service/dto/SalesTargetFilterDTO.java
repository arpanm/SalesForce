package com.ril.digital.salesforce.service.dto;

import com.ril.digital.salesforce.domain.enumeration.Category;
import com.ril.digital.salesforce.domain.enumeration.FilterJoinType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.ril.digital.salesforce.domain.SalesTargetFilter} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SalesTargetFilterDTO implements Serializable {

    private Long id;

    private String articleId;

    private Category category;

    private String brand;

    private String model;

    private Float articleMinPrice;

    private Float articleMaxPrice;

    private Long perOrderArticleMinQty;

    private Long perOrderArticleMaxQty;

    private Long perRetailerArticleMinQty;

    private Long perRetailerArticleMaxQty;

    private Long perRetailerDailyArticleMinQty;

    private Long perRetailerDailyArticleMaxQty;

    private String state;

    private String rigion;

    private FilterJoinType join;

    private Boolean isActive;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String createdBy;

    private String updatedBy;

    private SalesTargetDTO target;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Float getArticleMinPrice() {
        return articleMinPrice;
    }

    public void setArticleMinPrice(Float articleMinPrice) {
        this.articleMinPrice = articleMinPrice;
    }

    public Float getArticleMaxPrice() {
        return articleMaxPrice;
    }

    public void setArticleMaxPrice(Float articleMaxPrice) {
        this.articleMaxPrice = articleMaxPrice;
    }

    public Long getPerOrderArticleMinQty() {
        return perOrderArticleMinQty;
    }

    public void setPerOrderArticleMinQty(Long perOrderArticleMinQty) {
        this.perOrderArticleMinQty = perOrderArticleMinQty;
    }

    public Long getPerOrderArticleMaxQty() {
        return perOrderArticleMaxQty;
    }

    public void setPerOrderArticleMaxQty(Long perOrderArticleMaxQty) {
        this.perOrderArticleMaxQty = perOrderArticleMaxQty;
    }

    public Long getPerRetailerArticleMinQty() {
        return perRetailerArticleMinQty;
    }

    public void setPerRetailerArticleMinQty(Long perRetailerArticleMinQty) {
        this.perRetailerArticleMinQty = perRetailerArticleMinQty;
    }

    public Long getPerRetailerArticleMaxQty() {
        return perRetailerArticleMaxQty;
    }

    public void setPerRetailerArticleMaxQty(Long perRetailerArticleMaxQty) {
        this.perRetailerArticleMaxQty = perRetailerArticleMaxQty;
    }

    public Long getPerRetailerDailyArticleMinQty() {
        return perRetailerDailyArticleMinQty;
    }

    public void setPerRetailerDailyArticleMinQty(Long perRetailerDailyArticleMinQty) {
        this.perRetailerDailyArticleMinQty = perRetailerDailyArticleMinQty;
    }

    public Long getPerRetailerDailyArticleMaxQty() {
        return perRetailerDailyArticleMaxQty;
    }

    public void setPerRetailerDailyArticleMaxQty(Long perRetailerDailyArticleMaxQty) {
        this.perRetailerDailyArticleMaxQty = perRetailerDailyArticleMaxQty;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRigion() {
        return rigion;
    }

    public void setRigion(String rigion) {
        this.rigion = rigion;
    }

    public FilterJoinType getJoin() {
        return join;
    }

    public void setJoin(FilterJoinType join) {
        this.join = join;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesTargetFilterDTO)) {
            return false;
        }

        SalesTargetFilterDTO salesTargetFilterDTO = (SalesTargetFilterDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, salesTargetFilterDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesTargetFilterDTO{" +
            "id=" + getId() +
            ", articleId='" + getArticleId() + "'" +
            ", category='" + getCategory() + "'" +
            ", brand='" + getBrand() + "'" +
            ", model='" + getModel() + "'" +
            ", articleMinPrice=" + getArticleMinPrice() +
            ", articleMaxPrice=" + getArticleMaxPrice() +
            ", perOrderArticleMinQty=" + getPerOrderArticleMinQty() +
            ", perOrderArticleMaxQty=" + getPerOrderArticleMaxQty() +
            ", perRetailerArticleMinQty=" + getPerRetailerArticleMinQty() +
            ", perRetailerArticleMaxQty=" + getPerRetailerArticleMaxQty() +
            ", perRetailerDailyArticleMinQty=" + getPerRetailerDailyArticleMinQty() +
            ", perRetailerDailyArticleMaxQty=" + getPerRetailerDailyArticleMaxQty() +
            ", state='" + getState() + "'" +
            ", rigion='" + getRigion() + "'" +
            ", join='" + getJoin() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", target=" + getTarget() +
            "}";
    }
}
