/*
 * 文件名：FinCashDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.msttradeopponent.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 现金流实体类
 * @author fanshuanghu
 * @version 1.0 2018年8月23日
 * @see FinCashDto
 * @since 1.0
 */
public class FinCashDto extends FinCashInfo {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1993983178010823271L;
    /**
     * 利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;
    /**
     * 基数类型
     */
    private String basicBill;
    /**
     * 计算基础
     */
    private String basis;
    /**
     * 费用类型
     */
    private String feeType;
    
    /**
     * 获取 feeType
     * @return feeType.
     */
    public String getFeeType() {
        return feeType;
    }
    
    /**
     * 设置 feeType
     * @param feeType feeType
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    /**
     * 限制费用长度
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public String getFeeId(){
        return super.getFeeId();
    }
    /**
     * 获取 baseType
     * @return baseType.
     */
    public String getBasicBill() {
        return basicBill;
    }
    
    /**
     * 设置 baseType
     * @param basicBill 费用基础
     */
    public void setBasicBill(String basicBill) {
        this.basicBill = basicBill;
    }
    
    /**
     * 获取 basis
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }
    
    /**
     * 设置 basis
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }
    /**
     * 获取 rate
     * @return rate.
     */
    public BigDecimal getRate() {
        return rate;
    }
    /**
     * 设置 rate
     * @param rate rate
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String getCashId(){
        return super.getCashId();
    }
    
}
