package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PrdIssuStrategyDetailKey extends BaseEntity {
    /**
     * 产品发行规划编号
     */
    private String prdIssueplanCode;

    /**
     * 产品规划系列编号
     */
    private String prdPlanSerisId;

    /**
     * 策略代码
     */
    private String strategyId;

    /**
     * 资产大类
     */
    private String assetType;

    /**
     * 取得 产品发行规划编号
     * @return 产品发行规划编号
     */
    public String getPrdIssueplanCode() {
        return prdIssueplanCode;
    }

    /**
     * 设置 产品发行规划编号
     * @param prdIssueplanCode 产品发行规划编号
     */
    public void setPrdIssueplanCode(String prdIssueplanCode) {
        this.prdIssueplanCode = prdIssueplanCode == null ? null : prdIssueplanCode.trim();
    }

    /**
     * 取得 产品规划系列编号
     * @return 产品规划系列编号
     */
    public String getPrdPlanSerisId() {
        return prdPlanSerisId;
    }

    /**
     * 设置 产品规划系列编号
     * @param prdPlanSerisId 产品规划系列编号
     */
    public void setPrdPlanSerisId(String prdPlanSerisId) {
        this.prdPlanSerisId = prdPlanSerisId == null ? null : prdPlanSerisId.trim();
    }

    /**
     * 取得 策略代码
     * @return 策略代码
     */
    public String getStrategyId() {
        return strategyId;
    }

    /**
     * 设置 策略代码
     * @param strategyId 策略代码
     */
    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId == null ? null : strategyId.trim();
    }

    /**
     * 取得 资产大类
     * @return 资产大类
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产大类
     * @param assetType 资产大类
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }
}