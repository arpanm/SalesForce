package com.ril.digital.salesforce.service.dto;

import com.ril.digital.salesforce.domain.enumeration.Category;
import com.ril.digital.salesforce.domain.enumeration.OrderStatus;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.ril.digital.salesforce.domain.OrderCache} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderCacheDTO implements Serializable {

    private Long id;

    private String orderId;

    private OrderStatus orderStatus;

    private LocalDate orderDate;

    private String articleId;

    private Category category;

    private String brand;

    private String model;

    private Float articlePrice;

    private Long articleQty;

    private String retailerId;

    private String retailerState;

    private Boolean isActive;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String createdBy;

    private String updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
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

    public Float getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(Float articlePrice) {
        this.articlePrice = articlePrice;
    }

    public Long getArticleQty() {
        return articleQty;
    }

    public void setArticleQty(Long articleQty) {
        this.articleQty = articleQty;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderCacheDTO)) {
            return false;
        }

        OrderCacheDTO orderCacheDTO = (OrderCacheDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, orderCacheDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderCacheDTO{" +
            "id=" + getId() +
            ", orderId='" + getOrderId() + "'" +
            ", orderStatus='" + getOrderStatus() + "'" +
            ", orderDate='" + getOrderDate() + "'" +
            ", articleId='" + getArticleId() + "'" +
            ", category='" + getCategory() + "'" +
            ", brand='" + getBrand() + "'" +
            ", model='" + getModel() + "'" +
            ", articlePrice=" + getArticlePrice() +
            ", articleQty=" + getArticleQty() +
            ", retailerId='" + getRetailerId() + "'" +
            ", retailerState='" + getRetailerState() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
