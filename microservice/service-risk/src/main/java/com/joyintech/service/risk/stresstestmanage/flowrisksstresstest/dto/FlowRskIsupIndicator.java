package com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dto;

import com.joyintech.framework.base.BaseEntity;

public class FlowRskIsupIndicator extends BaseEntity {
    /**
     * 监督指标编号
     */
    private String indId;

    /**
     * 监督指标名称
     */
    private String indName;

    /**
     * 指标类型
     */
    private String indType;

    /**
     * 表达式
     */
    private String expr;

    /**
     * 监督类型(SUPERVISION:监管,INTERNAL:内控)
     */
    private String supType;

    /**
     * 监督时点(委托监控,交易监控,事后监控),多个用逗号分隔
     */
    private String timePoint;

    /**
     * 监督频率(DAILY:每日,WEEKLY:每周,MONTHLY:每月,QUARTERLY:每季度,SEMIANNUALLY:每半年,YEARLY:每年)
     */
    private String supFrequency;

    /**
     * 预警条件
     */
    private String warnCond;

    /**
     * 违规条件
     */
    private String foulCond;

    /**
     * 是否启用(是，否)，默认为是
     */
    private String isEnabled;

    /**
     * 资产类型
     */
    private String assetType;

    /**
     * 资产二级类型
     */
    private String assetSecType;

    /**
     * 备注
     */
    private String memo;

    /**
     * 资产三级类型
     */
    private String assetThrType;

    /**
     * 投资性质
     */
    private String investProporty;

    /**
     * 指标分类维度
     */
    private String supDisplayClassify;

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
     * 取得 监督指标名称
     * @return 监督指标名称
     */
    public String getIndName() {
        return indName;
    }

    /**
     * 设置 监督指标名称
     * @param indName 监督指标名称
     */
    public void setIndName(String indName) {
        this.indName = indName == null ? null : indName.trim();
    }

    /**
     * 取得 指标类型
     * @return 指标类型
     */
    public String getIndType() {
        return indType;
    }

    /**
     * 设置 指标类型
     * @param indType 指标类型
     */
    public void setIndType(String indType) {
        this.indType = indType == null ? null : indType.trim();
    }

    /**
     * 取得 表达式
     * @return 表达式
     */
    public String getExpr() {
        return expr;
    }

    /**
     * 设置 表达式
     * @param expr 表达式
     */
    public void setExpr(String expr) {
        this.expr = expr == null ? null : expr.trim();
    }

    /**
     * 取得 监督类型(SUPERVISION:监管,INTERNAL:内控)
     * @return 监督类型(SUPERVISION:监管,INTERNAL:内控)
     */
    public String getSupType() {
        return supType;
    }

    /**
     * 设置 监督类型(SUPERVISION:监管,INTERNAL:内控)
     * @param supType 监督类型(SUPERVISION:监管,INTERNAL:内控)
     */
    public void setSupType(String supType) {
        this.supType = supType == null ? null : supType.trim();
    }

    /**
     * 取得 监督时点(委托监控,交易监控,事后监控),多个用逗号分隔
     * @return 监督时点(委托监控,交易监控,事后监控),多个用逗号分隔
     */
    public String getTimePoint() {
        return timePoint;
    }

    /**
     * 设置 监督时点(委托监控,交易监控,事后监控),多个用逗号分隔
     * @param timePoint 监督时点(委托监控,交易监控,事后监控),多个用逗号分隔
     */
    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint == null ? null : timePoint.trim();
    }

    /**
     * 取得 监督频率(DAILY:每日,WEEKLY:每周,MONTHLY:每月,QUARTERLY:每季度,SEMIANNUALLY:每半年,YEARLY:每年)
     * @return 监督频率(DAILY:每日,WEEKLY:每周,MONTHLY:每月,QUARTERLY:每季度,SEMIANNUALLY:每半年,YEARLY:每年)
     */
    public String getSupFrequency() {
        return supFrequency;
    }

    /**
     * 设置 监督频率(DAILY:每日,WEEKLY:每周,MONTHLY:每月,QUARTERLY:每季度,SEMIANNUALLY:每半年,YEARLY:每年)
     * @param supFrequency 监督频率(DAILY:每日,WEEKLY:每周,MONTHLY:每月,QUARTERLY:每季度,SEMIANNUALLY:每半年,YEARLY:每年)
     */
    public void setSupFrequency(String supFrequency) {
        this.supFrequency = supFrequency == null ? null : supFrequency.trim();
    }

    /**
     * 取得 预警条件
     * @return 预警条件
     */
    public String getWarnCond() {
        return warnCond;
    }

    /**
     * 设置 预警条件
     * @param warnCond 预警条件
     */
    public void setWarnCond(String warnCond) {
        this.warnCond = warnCond == null ? null : warnCond.trim();
    }

    /**
     * 取得 违规条件
     * @return 违规条件
     */
    public String getFoulCond() {
        return foulCond;
    }

    /**
     * 设置 违规条件
     * @param foulCond 违规条件
     */
    public void setFoulCond(String foulCond) {
        this.foulCond = foulCond == null ? null : foulCond.trim();
    }

    /**
     * 取得 是否启用(是，否)，默认为是
     * @return 是否启用(是，否)，默认为是
     */
    public String getIsEnabled() {
        return isEnabled;
    }

    /**
     * 设置 是否启用(是，否)，默认为是
     * @param isEnabled 是否启用(是，否)，默认为是
     */
    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled == null ? null : isEnabled.trim();
    }

    /**
     * 取得 资产类型
     * @return 资产类型
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产类型
     * @param assetType 资产类型
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 取得 资产二级类型
     * @return 资产二级类型
     */
    public String getAssetSecType() {
        return assetSecType;
    }

    /**
     * 设置 资产二级类型
     * @param assetSecType 资产二级类型
     */
    public void setAssetSecType(String assetSecType) {
        this.assetSecType = assetSecType == null ? null : assetSecType.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置 备注
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * 取得 资产三级类型
     * @return 资产三级类型
     */
    public String getAssetThrType() {
        return assetThrType;
    }

    /**
     * 设置 资产三级类型
     * @param assetThrType 资产三级类型
     */
    public void setAssetThrType(String assetThrType) {
        this.assetThrType = assetThrType == null ? null : assetThrType.trim();
    }

    /**
     * 取得 投资性质
     * @return 投资性质
     */
    public String getInvestProporty() {
        return investProporty;
    }

    /**
     * 设置 投资性质
     * @param investProporty 投资性质
     */
    public void setInvestProporty(String investProporty) {
        this.investProporty = investProporty == null ? null : investProporty.trim();
    }

    /**
     * 取得 指标分类维度
     * @return 指标分类维度
     */
    public String getSupDisplayClassify() {
        return supDisplayClassify;
    }

    /**
     * 设置 指标分类维度
     * @param supDisplayClassify 指标分类维度
     */
    public void setSupDisplayClassify(String supDisplayClassify) {
        this.supDisplayClassify = supDisplayClassify == null ? null : supDisplayClassify.trim();
    }
}