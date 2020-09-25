/*
 * 文件名：FinProductFeePay004Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年1月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.feepay.dto;

import java.math.BigDecimal;

/**
 * 附加税明细
 * 
 * @author zhangliang
 * @version 1.0 2019年1月2日
 * @see FinProductFeePay004Dto
 * @since 1.0
 */
public class FinProductFeePay004Dto {
    
    
    /**
     * 费用名称
     */
    private String feeName;
    
    /**
     * 理论费用金额
     */
    private BigDecimal feeAmt;
    
    /**
     * 实际费用金额
     */
    private BigDecimal actFeeAmt;
    
    /**
     * 调整费用金额
     */
    private BigDecimal adjustFeeAmt;

    
    /**
     * 获取 feeName
     * @return feeName.
     */
    public String getFeeName() {
        return feeName;
    }

    
    /**
     * 设置 feeName
     * @param feeName feeName
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    
    /**
     * 获取 feeAmt
     * @return feeAmt.
     */
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    
    /**
     * 设置 feeAmt
     * @param feeAmt feeAmt
     */
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    
    /**
     * 获取 actFeeAmt
     * @return actFeeAmt.
     */
    public BigDecimal getActFeeAmt() {
        return actFeeAmt;
    }

    
    /**
     * 设置 actFeeAmt
     * @param actFeeAmt actFeeAmt
     */
    public void setActFeeAmt(BigDecimal actFeeAmt) {
        this.actFeeAmt = actFeeAmt;
    }

    
    /**
     * 获取 adjustFeeAmt
     * @return adjustFeeAmt.
     */
    public BigDecimal getAdjustFeeAmt() {
        return adjustFeeAmt;
    }

    
    /**
     * 设置 adjustFeeAmt
     * @param adjustFeeAmt adjustFeeAmt
     */
    public void setAdjustFeeAmt(BigDecimal adjustFeeAmt) {
        this.adjustFeeAmt = adjustFeeAmt;
    }

    
}
