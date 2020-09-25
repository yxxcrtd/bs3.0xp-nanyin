package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RskIsupTrial extends BaseEntity {
    /**
     * 业务编号，包括委托编号，交易编号等
     */
    private String tradeId;

    /**
     * 业务类型，模糊委托、预占信息、实占信息
     */
    private String tradeType;

    /**
     * 组合或产品代码
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
     * 监督值

     */
    private String supVal;

    /**
     * 监督状态

     */
    private String supStat;

    /**
     * 联合监督状态

     */
    private String uniteSupStat;

    /**
     * 预警条件
     */
    private String warnCond;

    /**
     * 审批条件
     */
    private String approveCond;

    /**
     * 违规条件
     */
    private String foulCond;

    /**
     * 试算结论
     */
    private String conclusion;

    /**
     * 原值
     */
    private String oldSupVal;

    /**
     * 趋势
     */
    private String supTrend;

    /**
     * null
     */
    private String uuid;

    /**
     * null
     */
    private String assetCode;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 取得 业务编号，包括委托编号，交易编号等
     * @return 业务编号，包括委托编号，交易编号等
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 业务编号，包括委托编号，交易编号等
     * @param tradeId 业务编号，包括委托编号，交易编号等
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 业务类型，模糊委托、预占信息、实占信息
     * @return 业务类型，模糊委托、预占信息、实占信息
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置 业务类型，模糊委托、预占信息、实占信息
     * @param tradeType 业务类型，模糊委托、预占信息、实占信息
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 取得 组合或产品代码
     * @return 组合或产品代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合或产品代码
     * @param portfolioId 组合或产品代码
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
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSupDate(Date supDate) {
        this.supDate = supDate;
    }

    /**
     * 取得 监督值

     * @return 监督值

     */
    public String getSupVal() {
        return supVal;
    }

    /**
     * 设置 监督值

     * @param supVal 监督值

     */
    public void setSupVal(String supVal) {
        this.supVal = supVal == null ? null : supVal.trim();
    }

    /**
     * 取得 监督状态

     * @return 监督状态

     */
    public String getSupStat() {
        return supStat;
    }

    /**
     * 设置 监督状态

     * @param supStat 监督状态

     */
    public void setSupStat(String supStat) {
        this.supStat = supStat == null ? null : supStat.trim();
    }

    /**
     * 取得 联合监督状态

     * @return 联合监督状态

     */
    public String getUniteSupStat() {
        return uniteSupStat;
    }

    /**
     * 设置 联合监督状态

     * @param uniteSupStat 联合监督状态

     */
    public void setUniteSupStat(String uniteSupStat) {
        this.uniteSupStat = uniteSupStat == null ? null : uniteSupStat.trim();
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
     * 取得 审批条件
     * @return 审批条件
     */
    public String getApproveCond() {
        return approveCond;
    }

    /**
     * 设置 审批条件
     * @param approveCond 审批条件
     */
    public void setApproveCond(String approveCond) {
        this.approveCond = approveCond == null ? null : approveCond.trim();
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
     * 取得 试算结论
     * @return 试算结论
     */
    public String getConclusion() {
        return conclusion;
    }

    /**
     * 设置 试算结论
     * @param conclusion 试算结论
     */
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion == null ? null : conclusion.trim();
    }

    /**
     * 取得 原值
     * @return 原值
     */
    public String getOldSupVal() {
        return oldSupVal;
    }

    /**
     * 设置 原值
     * @param oldSupVal 原值
     */
    public void setOldSupVal(String oldSupVal) {
        this.oldSupVal = oldSupVal == null ? null : oldSupVal.trim();
    }

    /**
     * 取得 趋势
     * @return 趋势
     */
    public String getSupTrend() {
        return supTrend;
    }

    /**
     * 设置 趋势
     * @param supTrend 趋势
     */
    public void setSupTrend(String supTrend) {
        this.supTrend = supTrend == null ? null : supTrend.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 null
     * @param uuid null
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 null
     * @param assetCode null
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }
}