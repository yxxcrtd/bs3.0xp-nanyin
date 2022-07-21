package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;


import java.util.Date;


/**
 * 非标债权类资产明细信息
 *
 */
public class DisNostdCreditor extends BaseEntity{

    /**
     * 非标资产编号
     */
    private String nostdAssetId;

    /**
     * 主产品代码
     */
    private String prodId;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 生效截止日期
     */
    private Date effEndDate;

    /**
     * 数据状态
     */
    private String dataStatus;

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
     * 剩余融资期限
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
     * 数据来源
     */
    private String dataSource;


    public String getNostdAssetId() { return nostdAssetId; }
    public void setNostdAssetId(String nostdAssetId) { this.nostdAssetId = nostdAssetId; }
    public String getProdId() { return prodId; }
    public void setProdId(String prodId) { this.prodId = prodId; }
    public Date getEffDate() { return effDate; }
    public void setEffDate(Date effDate) { this.effDate = effDate; }
    public Date getEffEndDate() { return effEndDate; }
    public void setEffEndDate(Date effEndDate) { this.effEndDate = effEndDate; }
    public String getDataStatus() { return dataStatus; }
    public void setDataStatus(String dataStatus) { this.dataStatus = dataStatus; }
    public String getFinancier() { return financier; }
    public void setFinancier(String financier) { this.financier = financier; }
    public String getProjectNo() { return projectNo; }
    public void setProjectNo(String projectNo) { this.projectNo = projectNo; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public String getRemFinancingPeriod() { return remFinancingPeriod; }
    public void setRemFinancingPeriod(String remFinancingPeriod) { this.remFinancingPeriod = remFinancingPeriod; }
    public String getIncomeDistribute() { return incomeDistribute; }
    public void setIncomeDistribute(String incomeDistribute) { this.incomeDistribute = incomeDistribute; }
    public String getTrdConstruct() { return trdConstruct; }
    public void setTrdConstruct(String trdConstruct) { this.trdConstruct = trdConstruct; }
    public String getRiskInfo() { return riskInfo; }
    public void setRiskInfo(String riskInfo) { this.riskInfo = riskInfo; }
    public String getDataSource() { return dataSource; }
    public void setDataSource(String dataSource) { this.dataSource = dataSource; }
}
