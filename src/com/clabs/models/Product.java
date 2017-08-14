package com.clabs.models;

import java.util.Date;

final public class Product {

    public String id;

    public String name;

    public String productType;

    public String accountId;

    public String description;

    public Float adjustmentFactor;

    public String productRefId;

    public Date created;

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setProductType(String productType) {
        this.productType = productType;
        return this;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Product setAdjustmentFactor(Float adjustmentFactor) {
        this.adjustmentFactor = adjustmentFactor;
        return this;
    }

    public Product setProductRefId(String externalProductRefId) {
        this.productRefId = externalProductRefId;
        return this;
    }
}
