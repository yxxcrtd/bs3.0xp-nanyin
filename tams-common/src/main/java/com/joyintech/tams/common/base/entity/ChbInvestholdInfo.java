package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ChbInvestholdInfo extends ChbInvestholdInfoKey {
    /**
     * 产品登记编码
     */
    private String prdRegistCode;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qty;

    /**
     * 折算人民币金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cnyAmt;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 经办人
     */
    private String agent;

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getPrdRegistCode() {
        return prdRegistCode;
    }

    /**
     * 设置 产品登记编码
     * @param prdRegistCode 产品登记编码
     */
    public void setPrdRegistCode(String prdRegistCode) {
        this.prdRegistCode = prdRegistCode == null ? null : prdRegistCode.trim();
    }

    /**
     * 取得 金额
     * @return 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 金额
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 取得 份额
     * @return 份额
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 设置 份额
     * @param qty 份额
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * 取得 折算人民币金额（元）
     * @return 折算人民币金额（元）
     */
    public BigDecimal getCnyAmt() {
        return cnyAmt;
    }

    /**
     * 设置 折算人民币金额（元）
     * @param cnyAmt 折算人民币金额（元）
     */
    public void setCnyAmt(BigDecimal cnyAmt) {
        this.cnyAmt = cnyAmt;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 经办人
     * @return 经办人
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 设置 经办人
     * @param agent 经办人
     */
    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }
}