/*
 * 文件名：FinProductExpireMInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年1月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productduration.expire.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.PrdLayerParam;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.tams.constant.DictCode;

/**
 * 产品到期确认
 * 
 * @author zhangliang
 * @version 1.0 2019年1月9日
 * @see FinProductExpireMInfoDto
 * @since 1.0
 */
public class FinProductExpireMInfoDto extends PrdLayerParam {

    /**
     * 
     */
    private static final long serialVersionUID = 2702675400065067977L;

    /**
     * 分支序号
     */
    private String branch;

    /**
     * 计息开始日
     */
    private String startDate;

    /**
     * 计息结束日
     */
    private String endDate;

    /**
     * 当日本金
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tdyPrinAmt;

    /**
     * 收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 实际收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal actYield;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 应付利息
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 实付利息
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal actIntAmt;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getTdyPrinAmt() {
        return tdyPrinAmt;
    }

    public void setTdyPrinAmt(BigDecimal tdyPrinAmt) {
        this.tdyPrinAmt = tdyPrinAmt;
    }

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public BigDecimal getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    public BigDecimal getActIntAmt() {
        return actIntAmt;
    }

    public void setActIntAmt(BigDecimal actIntAmt) {
        this.actIntAmt = actIntAmt;
    }

    public BigDecimal getActYield() {
        return actYield;
    }

    public void setActYield(BigDecimal actYield) {
        this.actYield = actYield;
    }

    /**
     * {@inheritDoc}
     */
    public String getCustomLevelStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.客户等级, super.getCustomLevel());
    }

}
