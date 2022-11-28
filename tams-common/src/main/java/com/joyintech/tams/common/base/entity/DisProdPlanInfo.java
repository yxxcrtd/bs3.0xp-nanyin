package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.util.Date;

public class DisProdPlanInfo extends BaseEntity {

    /**
     * 产品排期计划编号
     */
    private String prodPlanId;

    /**
     * 产品ID
     */
    @NotEmpty(fieldName = "产品主代码")
    private String prodId;

    /**
     * 开放期起始日
     */
    @NotEmpty(fieldName = "开放期起始日")
    private Date openStartDate;

    /**
     * 开放期结束日
     */
    @NotEmpty(fieldName = "开放期结束日")
    private Date openEndDate;

    /**
     * 产品冷静期
     */
    private Date collingoffPeriod;

    /**
     * 运营起始日
     */
    @NotEmpty(fieldName = "运作起始日")
    private Date operationStartDate;

    /**
     * 自动赎回日
     */
    private Date autoRedeemDate;

    /**
     * 客户持有天数
     */
    private Short holdDays;

    /**
     * 数据状态：数据字典，系统模块-DIS，字典code：DATA_STATUS:E-有效，D-删除
     */
    private String dataStatus;

    /**
     * 数据来源:数据字典code:DATA_SOURCE
     */
    private String dataSource;

    public String getProdPlanId() {
        return prodPlanId;
    }

    public void setProdPlanId(String prodPlanId) {
        this.prodPlanId = prodPlanId;
    }

    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Date getOpenStartDate() {
        return openStartDate;
    }

    public void setOpenStartDate(Date openStartDate) {
        this.openStartDate = openStartDate;
    }

    public Date getOpenEndDate() {
        return openEndDate;
    }

    public void setOpenEndDate(Date openEndDate) {
        this.openEndDate = openEndDate;
    }

    public Date getCollingoffPeriod() {
        return collingoffPeriod;
    }

    public void setCollingoffPeriod(Date collingoffPeriod) {
        this.collingoffPeriod = collingoffPeriod;
    }

    public Date getOperationStartDate() {
        return operationStartDate;
    }

    public void setOperationStartDate(Date operationStartDate) {
        this.operationStartDate = operationStartDate;
    }

    public Date getAutoRedeemDate() {
        return autoRedeemDate;
    }

    public void setAutoRedeemDate(Date autoRedeemDate) {
        this.autoRedeemDate = autoRedeemDate;
    }

    public Short getHoldDays() {
        return holdDays;
    }

    public void setHoldDays(Short holdDays) {
        this.holdDays = holdDays;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}