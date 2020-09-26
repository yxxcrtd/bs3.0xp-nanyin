/*
 * 文件名：BondTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.trademanage.investtradequery.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 债券交易查询Dto
 * @author zhangwei
 * @version 1.0 2018年7月27日
 * @see BondTradeDto
 * @since 1.0
 */
public class BondTradeDto extends InvestTradeDto{

    
    private static final long serialVersionUID = 1L;

    
    /**交易员**/
    private String trader;
    
    /**交易对手**/
    private String counterId;
    
    /** 债券管理户 **/
    private String secManageAcctId;
    
    /** 通道 **/
    private String chlId;

//    /** 随交易支付费用 **/
//    private String feeAmtPay;
//    
//    /** 随交易支付费用 **/
//    private String feeAmtUnpay;
    
    
    /** 交易费用详情列表  **/
    private List<TradeFeeDetailDto> tradeFeeDetailDtoList;
    
    
    
    /** 当期单位面值 **/
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal parValue;
    

    
    
    public String getTrader() {
        return trader;
    }

    



    
    public BigDecimal getParValue() {
        return parValue;
    }


    
    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
    }


    public void setTrader(String trader) {
        this.trader = trader;
    }


    
    public String getCounterId() {
        return counterId;
    }

    
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    
    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

    
    public String getChlId() {
        return chlId;
    }

    
    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    


    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    
    
//    public String getFeeAmtPay() {
//        return feeAmtPay;
//    }
//
//
//    
//    public void setFeeAmtPay(String feeAmtPay) {
//        this.feeAmtPay = feeAmtPay;
//    }
//
//
//    
//    public String getFeeAmtUnpay() {
//        return feeAmtUnpay;
//    }
//
//
//    
//    public void setFeeAmtUnpay(String feeAmtUnpay) {
//        this.feeAmtUnpay = feeAmtUnpay;
//    }


    public List<TradeFeeDetailDto> getTradeFeeDetailDtoList() {
        return tradeFeeDetailDtoList;
    }


    
    public void setTradeFeeDetailDtoList(List<TradeFeeDetailDto> tradeFeeDetailDtoList) {
        this.tradeFeeDetailDtoList = tradeFeeDetailDtoList;
    }
    
    
    

}
