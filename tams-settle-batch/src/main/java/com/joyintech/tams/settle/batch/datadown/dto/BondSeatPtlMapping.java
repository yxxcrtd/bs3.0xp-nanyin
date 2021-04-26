package com.joyintech.tams.settle.batch.datadown.dto;

/**
 * @author 何阳阳
 * @date 2020/7/6
 * @see BondSeatPtlMapping
 * @since 1.0
 */
public class BondSeatPtlMapping {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 证券账号
     */
    private String bondId;

    /**
     * 深交所席位号
     */
    private String shSeatNo;

    /**
     * 上交所席位号
     */
    private String szSeatNo;

    public BondSeatPtlMapping() {
    }

    public BondSeatPtlMapping(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getBondId() {
        return bondId;
    }

    public void setBondId(String bondId) {
        this.bondId = bondId;
    }

    public String getShSeatNo() {
        return shSeatNo;
    }

    public void setShSeatNo(String shSeatNo) {
        this.shSeatNo = shSeatNo;
    }

    public String getSzSeatNo() {
        return szSeatNo;
    }

    public void setSzSeatNo(String szSeatNo) {
        this.szSeatNo = szSeatNo;
    }
}
