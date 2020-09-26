/*
 * 文件名：TradeFeeDetail.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.trademanage.investtradequery.dto;

import java.math.BigDecimal;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/***
 * 交易费用详情
 * @author zhangwei
 * @version 1.0 2018年7月28日
 * @see TradeFeeDetail
 * @since 1.0
 */
public class TradeFeeDetailDto {

    /** 费用类型 **/
    private String feeType;
    
    /** 费用类型名 **/
    private String feeTypeName;
    
    /** 费用金额 **/
    private BigDecimal feeAmt;
    
    /** 是否随交易支付 **/
    private String isPayWithTrade;

    
    public String getFeeType() {
        return feeType;
    }

    
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    
    public String getFeeTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.一次性费用类型, this.getFeeType());
    }

    
    public void setFeeTypeName(String feeTypeName) {
        this.feeTypeName = feeTypeName;
    }

    
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    
    public String getIsPayWithTrade() {
        return isPayWithTrade;
    }

    
    public void setIsPayWithTrade(String isPayWithTrade) {
        this.isPayWithTrade = isPayWithTrade;
    }
    
    
    
    
}
