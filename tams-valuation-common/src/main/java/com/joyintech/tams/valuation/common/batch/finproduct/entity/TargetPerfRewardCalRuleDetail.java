/*
 * 文件名：TargetPerfRewardRuleDetail.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.finproduct.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 业绩报酬规则明细 - 实体类
 * 
 * @author yangjian
 * @version 1.0 2018年5月25日
 * @see TargetPerfRewardCalRuleDetail
 * @since 1.0
 */
public class TargetPerfRewardCalRuleDetail {

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 生效日期
     */
    private Date effDate;

    /**
     * 层级
     */
    private int level;

    /**
     * 基准利率
     */
    private String basicRate;

    /**
     * 调整系数
     */
    private BigDecimal coefficient;

    /**
     * 利差
     */
    private BigDecimal spreadRate;

    /**
     * 银行收取比例
     */
    private BigDecimal chargeRatio;

    /**
     * 研发部门收取比例
     */
    private BigDecimal saleRatio;

    /**
     * 业绩比较标准
     */
    private BigDecimal compareBasis;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 获取 createUser
     * 
     * @return createUser.
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置 createUser
     * 
     * @param createUser createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取 createDept
     * 
     * @return createDept.
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 设置 createDept
     * 
     * @param createDept createDept
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    /**
     * 获取 createTime
     * 
     * @return createTime.
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 createTime
     * 
     * @param createTime createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 updateUser
     * 
     * @return updateUser.
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置 updateUser
     * 
     * @param updateUser updateUser
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取 updateTime
     * 
     * @return updateTime.
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 updateTime
     * 
     * @param updateTime updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取 业绩比较标准
     * 
     * @return 业绩比较标准.
     */
    public BigDecimal getCompareBasis() {
        return compareBasis;
    }

    /**
     * 设置 业绩比较标准
     * 
     * @param compareBasis 业绩比较标准
     */
    public void setCompareBasis(BigDecimal compareBasis) {
        this.compareBasis = compareBasis;
    }

    public String getCashId() {
        return cashId;
    }

    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getBasicRate() {
        return basicRate;
    }

    public void setBasicRate(String basicRate) {
        this.basicRate = basicRate;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    public BigDecimal getChargeRatio() {
        return chargeRatio;
    }

    public void setChargeRatio(BigDecimal chargeRatio) {
        this.chargeRatio = chargeRatio;
    }

    public BigDecimal getSaleRatio() {
        return saleRatio;
    }

    public void setSaleRatio(BigDecimal saleRatio) {
        this.saleRatio = saleRatio;
    }

}
