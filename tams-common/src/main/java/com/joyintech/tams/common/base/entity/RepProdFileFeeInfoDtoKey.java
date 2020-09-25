package com.joyintech.tams.common.base.entity;

public class RepProdFileFeeInfoDtoKey {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 费用品种代码
     */
    private String feeId;

    /**
     * 产品代码
     * @return PROD_ID 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 费用品种代码
     * @return FEE_ID 费用品种代码
     */
    public String getFeeId() {
        return feeId;
    }

    /**
     * 费用品种代码
     * @param feeId 费用品种代码
     */
    public void setFeeId(String feeId) {
        this.feeId = feeId == null ? null : feeId.trim();
    }
}