package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbEquperiod extends ChbEquperiodKey {
    /**
     * 可用数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal availableQuantity;

    /**
     * 冻结数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal frozenQuantity;

    /**
     * 质押数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgedQuantity;

    /**
     * 初值日期
     */
    private Date initValDate;

    /**
     * 初值登记日期
     */
    private Date initValRegistDate;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 折算人民币金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal converCnyAmount;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 取得 可用数量
     * @return 可用数量
     */
    public BigDecimal getAvailableQuantity() {
        return availableQuantity;
    }

    /**
     * 设置 可用数量
     * @param availableQuantity 可用数量
     */
    public void setAvailableQuantity(BigDecimal availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    /**
     * 取得 冻结数量
     * @return 冻结数量
     */
    public BigDecimal getFrozenQuantity() {
        return frozenQuantity;
    }

    /**
     * 设置 冻结数量
     * @param frozenQuantity 冻结数量
     */
    public void setFrozenQuantity(BigDecimal frozenQuantity) {
        this.frozenQuantity = frozenQuantity;
    }

    /**
     * 取得 质押数量
     * @return 质押数量
     */
    public BigDecimal getPledgedQuantity() {
        return pledgedQuantity;
    }

    /**
     * 设置 质押数量
     * @param pledgedQuantity 质押数量
     */
    public void setPledgedQuantity(BigDecimal pledgedQuantity) {
        this.pledgedQuantity = pledgedQuantity;
    }

    /**
     * 取得 初值日期
     * @return 初值日期
     */
    public Date getInitValDate() {
        return initValDate;
    }

    /**
     * 设置 初值日期
     * @param initValDate 初值日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setInitValDate(Date initValDate) {
        this.initValDate = initValDate;
    }

    /**
     * 取得 初值登记日期
     * @return 初值登记日期
     */
    public Date getInitValRegistDate() {
        return initValRegistDate;
    }

    /**
     * 设置 初值登记日期
     * @param initValRegistDate 初值登记日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setInitValRegistDate(Date initValRegistDate) {
        this.initValRegistDate = initValRegistDate;
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
     * 取得 折算人民币金额（元）
     * @return 折算人民币金额（元）
     */
    public BigDecimal getConverCnyAmount() {
        return converCnyAmount;
    }

    /**
     * 设置 折算人民币金额（元）
     * @param converCnyAmount 折算人民币金额（元）
     */
    public void setConverCnyAmount(BigDecimal converCnyAmount) {
        this.converCnyAmount = converCnyAmount;
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
}