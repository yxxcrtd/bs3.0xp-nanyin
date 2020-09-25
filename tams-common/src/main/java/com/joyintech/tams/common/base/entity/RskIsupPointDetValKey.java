package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class RskIsupPointDetValKey extends BaseEntity {
    /**
     * 组合或组合包代码

     */
    private String portfolioId;

    /**
     * 监督指标编号
     */
    private String indId;

    /**
     * 监督日期
     */
    private Date supDate;

    /**
     * 监督频率(日间，日终)
     */
    private String supFrequency;

    /**
     * 资产代码
     */
    private String assetCode;

    /**
     * 取得 组合或组合包代码

     * @return 组合或组合包代码

     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合或组合包代码

     * @param portfolioId 组合或组合包代码

     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 监督指标编号
     * @return 监督指标编号
     */
    public String getIndId() {
        return indId;
    }

    /**
     * 设置 监督指标编号
     * @param indId 监督指标编号
     */
    public void setIndId(String indId) {
        this.indId = indId == null ? null : indId.trim();
    }

    /**
     * 取得 监督日期
     * @return 监督日期
     */
    public Date getSupDate() {
        return supDate;
    }

    /**
     * 设置 监督日期
     * @param supDate 监督日期
     */
    public void setSupDate(Date supDate) {
        this.supDate = supDate;
    }

    /**
     * 取得 监督频率(日间，日终)
     * @return 监督频率(日间，日终)
     */
    public String getSupFrequency() {
        return supFrequency;
    }

    /**
     * 设置 监督频率(日间，日终)
     * @param supFrequency 监督频率(日间，日终)
     */
    public void setSupFrequency(String supFrequency) {
        this.supFrequency = supFrequency == null ? null : supFrequency.trim();
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 资产代码
     * @param assetCode 资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }
}