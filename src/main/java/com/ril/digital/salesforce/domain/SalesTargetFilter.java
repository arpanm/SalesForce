package com.ril.digital.salesforce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ril.digital.salesforce.domain.enumeration.Category;
import com.ril.digital.salesforce.domain.enumeration.FilterJoinType;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SalesTargetFilter.
 */
@Entity
@Table(name = "sales_target_filter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SalesTargetFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "article_id")
    private String articleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "article_min_price")
    private Float articleMinPrice;

    @Column(name = "article_max_price")
    private Float articleMaxPrice;

    @Column(name = "per_order_article_min_qty")
    private Long perOrderArticleMinQty;

    @Column(name = "per_order_article_max_qty")
    private Long perOrderArticleMaxQty;

    @Column(name = "per_retailer_article_min_qty")
    private Long perRetailerArticleMinQty;

    @Column(name = "per_retailer_article_max_qty")
    private Long perRetailerArticleMaxQty;

    @Column(name = "per_retailer_daily_article_min_qty")
    private Long perRetailerDailyArticleMinQty;

    @Column(name = "per_retailer_daily_article_max_qty")
    private Long perRetailerDailyArticleMaxQty;

    @Column(name = "state")
    private String state;

    @Column(name = "rigion")
    private String rigion;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_join")
    private FilterJoinType join;

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
    @JsonIgnoreProperties(value = { "filters", "incentiveSchemes", "approvedBy" }, allowSetters = true)
    private SalesTarget target;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SalesTargetFilter id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleId() {
        return this.articleId;
    }

    public SalesTargetFilter articleId(String articleId) {
        this.setArticleId(articleId);
        return this;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Category getCategory() {
        return this.category;
    }

    public SalesTargetFilter category(Category category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBrand() {
        return this.brand;
    }

    public SalesTargetFilter brand(String brand) {
        this.setBrand(brand);
        return this;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public SalesTargetFilter model(String model) {
        this.setModel(model);
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Float getArticleMinPrice() {
        return this.articleMinPrice;
    }

    public SalesTargetFilter articleMinPrice(Float articleMinPrice) {
        this.setArticleMinPrice(articleMinPrice);
        return this;
    }

    public void setArticleMinPrice(Float articleMinPrice) {
        this.articleMinPrice = articleMinPrice;
    }

    public Float getArticleMaxPrice() {
        return this.articleMaxPrice;
    }

    public SalesTargetFilter articleMaxPrice(Float articleMaxPrice) {
        this.setArticleMaxPrice(articleMaxPrice);
        return this;
    }

    public void setArticleMaxPrice(Float articleMaxPrice) {
        this.articleMaxPrice = articleMaxPrice;
    }

    public Long getPerOrderArticleMinQty() {
        return this.perOrderArticleMinQty;
    }

    public SalesTargetFilter perOrderArticleMinQty(Long perOrderArticleMinQty) {
        this.setPerOrderArticleMinQty(perOrderArticleMinQty);
        return this;
    }

    public void setPerOrderArticleMinQty(Long perOrderArticleMinQty) {
        this.perOrderArticleMinQty = perOrderArticleMinQty;
    }

    public Long getPerOrderArticleMaxQty() {
        return this.perOrderArticleMaxQty;
    }

    public SalesTargetFilter perOrderArticleMaxQty(Long perOrderArticleMaxQty) {
        this.setPerOrderArticleMaxQty(perOrderArticleMaxQty);
        return this;
    }

    public void setPerOrderArticleMaxQty(Long perOrderArticleMaxQty) {
        this.perOrderArticleMaxQty = perOrderArticleMaxQty;
    }

    public Long getPerRetailerArticleMinQty() {
        return this.perRetailerArticleMinQty;
    }

    public SalesTargetFilter perRetailerArticleMinQty(Long perRetailerArticleMinQty) {
        this.setPerRetailerArticleMinQty(perRetailerArticleMinQty);
        return this;
    }

    public void setPerRetailerArticleMinQty(Long perRetailerArticleMinQty) {
        this.perRetailerArticleMinQty = perRetailerArticleMinQty;
    }

    public Long getPerRetailerArticleMaxQty() {
        return this.perRetailerArticleMaxQty;
    }

    public SalesTargetFilter perRetailerArticleMaxQty(Long perRetailerArticleMaxQty) {
        this.setPerRetailerArticleMaxQty(perRetailerArticleMaxQty);
        return this;
    }

    public void setPerRetailerArticleMaxQty(Long perRetailerArticleMaxQty) {
        this.perRetailerArticleMaxQty = perRetailerArticleMaxQty;
    }

    public Long getPerRetailerDailyArticleMinQty() {
        return this.perRetailerDailyArticleMinQty;
    }

    public SalesTargetFilter perRetailerDailyArticleMinQty(Long perRetailerDailyArticleMinQty) {
        this.setPerRetailerDailyArticleMinQty(perRetailerDailyArticleMinQty);
        return this;
    }

    public void setPerRetailerDailyArticleMinQty(Long perRetailerDailyArticleMinQty) {
        this.perRetailerDailyArticleMinQty = perRetailerDailyArticleMinQty;
    }

    public Long getPerRetailerDailyArticleMaxQty() {
        return this.perRetailerDailyArticleMaxQty;
    }

    public SalesTargetFilter perRetailerDailyArticleMaxQty(Long perRetailerDailyArticleMaxQty) {
        this.setPerRetailerDailyArticleMaxQty(perRetailerDailyArticleMaxQty);
        return this;
    }

    public void setPerRetailerDailyArticleMaxQty(Long perRetailerDailyArticleMaxQty) {
        this.perRetailerDailyArticleMaxQty = perRetailerDailyArticleMaxQty;
    }

    public String getState() {
        return this.state;
    }

    public SalesTargetFilter state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRigion() {
        return this.rigion;
    }

    public SalesTargetFilter rigion(String rigion) {
        this.setRigion(rigion);
        return this;
    }

    public void setRigion(String rigion) {
        this.rigion = rigion;
    }

    public FilterJoinType getJoin() {
        return this.join;
    }

    public SalesTargetFilter join(FilterJoinType join) {
        this.setJoin(join);
        return this;
    }

    public void setJoin(FilterJoinType join) {
        this.join = join;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public SalesTargetFilter isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public SalesTargetFilter createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public SalesTargetFilter updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public SalesTargetFilter createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public SalesTargetFilter updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public SalesTarget getTarget() {
        return this.target;
    }

    public void setTarget(SalesTarget salesTarget) {
        this.target = salesTarget;
    }

    public SalesTargetFilter target(SalesTarget salesTarget) {
        this.setTarget(salesTarget);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesTargetFilter)) {
            return false;
        }
        return id != null && id.equals(((SalesTargetFilter) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesTargetFilter{" +
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
            "}";
    }
}
