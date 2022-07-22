package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class DisNostdCreditor extends BaseEntity {
    /**
     * 非标资产编号，主键
     */
    private String nostdAssetId;

    /**
     * 主产品代码
     */
    private String prodId;

    /**
     * 生效日期
     */
    private Date effDate;

    /**
     * 生效截至日
     */
    private Date effEndDate;

    /**
     * 融资客户
     */
    private String financier;

    /**
     * 项目编号
     */
    private String projectNo;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 剩余赎回期限
     */
    private String remFinancingPeriod;

    /**
     * 到期收益分配
     */
    private String incomeDistribute;

    /**
     * 交易结构
     */
    private String trdConstruct;

    /**
     * 风险状况
     */
    private String riskInfo;

    /**
     * 数据状态
     */
    private String dataStatus;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 取得 非标资产编号，主键
     * @return 非标资产编号，主键
     */
    public String getNostdAssetId() {
        return nostdAssetId;
    }

    /**
     * 设置 非标资产编号，主键
     * @param nostdAssetId 非标资产编号，主键
     */
    public void setNostdAssetId(String nostdAssetId) {
        this.nostdAssetId = nostdAssetId == null ? null : nostdAssetId.trim();
    }

    /**
     * 取得 主产品代码
     * @return 主产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 主产品代码
     * @param prodId 主产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 生效日期
     * @return 生效日期
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日期
     * @param effDate 生效日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 取得 生效截至日
     * @return 生效截至日
     */
    public Date getEffEndDate() {
        return effEndDate;
    }

    /**
     * 设置 生效截至日
     * @param effEndDate 生效截至日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffEndDate(Date effEndDate) {
        this.effEndDate = effEndDate;
    }

    /**
     * 取得 融资客户
     * @return 融资客户
     */
    public String getFinancier() {
        return financier;
    }

    /**
     * 设置 融资客户
     * @param financier 融资客户
     */
    public void setFinancier(String financier) {
        this.financier = financier == null ? null : financier.trim();
    }

    /**
     * 取得 项目编号
     * @return 项目编号
     */
    public String getProjectNo() {
        return projectNo;
    }

    /**
     * 设置 项目编号
     * @param projectNo 项目编号
     */
    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    /**
     * 取得 项目名称
     * @return 项目名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 设置 项目名称
     * @param projectName 项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    /**
     * 取得 剩余赎回期限
     * @return 剩余赎回期限
     */
    public String getRemFinancingPeriod() {
        return remFinancingPeriod;
    }

    /**
     * 设置 剩余赎回期限
     * @param remFinancingPeriod 剩余赎回期限
     */
    public void setRemFinancingPeriod(String remFinancingPeriod) {
        this.remFinancingPeriod = remFinancingPeriod == null ? null : remFinancingPeriod.trim();
    }

    /**
     * 取得 到期收益分配
     * @return 到期收益分配
     */
    public String getIncomeDistribute() {
        return incomeDistribute;
    }

    /**
     * 设置 到期收益分配
     * @param incomeDistribute 到期收益分配
     */
    public void setIncomeDistribute(String incomeDistribute) {
        this.incomeDistribute = incomeDistribute == null ? null : incomeDistribute.trim();
    }

    /**
     * 取得 交易结构
     * @return 交易结构
     */
    public String getTrdConstruct() {
        return trdConstruct;
    }

    /**
     * 设置 交易结构
     * @param trdConstruct 交易结构
     */
    public void setTrdConstruct(String trdConstruct) {
        this.trdConstruct = trdConstruct == null ? null : trdConstruct.trim();
    }

    /**
     * 取得 风险状况
     * @return 风险状况
     */
    public String getRiskInfo() {
        return riskInfo;
    }

    /**
     * 设置 风险状况
     * @param riskInfo 风险状况
     */
    public void setRiskInfo(String riskInfo) {
        this.riskInfo = riskInfo == null ? null : riskInfo.trim();
    }

    /**
     * 取得 数据状态
     * @return 数据状态
     */
    public String getDataStatus() {
        return dataStatus;
    }

    /**
     * 设置 数据状态
     * @param dataStatus 数据状态
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }
}