package com.joyintech.service.valuation.system.batchstart.dto;

import com.joyintech.framework.base.BaseEntity;

public class Product extends BaseEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1442385458962407765L;
    private String prodId;
    private String prodName;

    public String getProdId() {
        return this.prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return this.prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
}