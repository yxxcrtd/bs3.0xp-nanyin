/*
 * 文件名：CashFlowsDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：QuJianZhong
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.dto;

import java.math.BigDecimal;

/**
 * 交易现金流信息Dto
 * 
 * @author qujianzhong
 * @version 1.0 2018年10月23日
 * @see CashFlowsDto
 * @since 1.0
 */
public class CashFlowsDto {

    /**
     * 金融产品类型
     */
    private String finProdType;

    /**
     * 金融产品代码
     */
    private String booksetId;
    
    /**
     * 交易方向
     */
    private String ps;
    
    /**
     * 本金
     */
    private BigDecimal prinamt;
    
    /**
     * 利息
     */
    private BigDecimal intamt;
    
    /**
     * 交易金额
     */
    private BigDecimal tradeamt;

    
    /**
     * 获取 finProdType
     * @return finProdType.
     */
    public String getFinProdType() {
        return finProdType;
    }

    
    /**
     * 设置 finProdType
     * @param finProdType finProdType
     */
    public void setFinProdType(String finProdType) {
        this.finProdType = finProdType;
    }

    
    /**
     * 获取 ps
     * @return ps.
     */
    public String getPs() {
        return ps;
    }

    
    /**
     * 设置 ps
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    
    /**
     * 获取 prinamt
     * @return prinamt.
     */
    public BigDecimal getPrinamt() {
        return prinamt;
    }

    
    /**
     * 设置 prinamt
     * @param prinamt prinamt
     */
    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
    }

    
    /**
     * 获取 intamt
     * @return intamt.
     */
    public BigDecimal getIntamt() {
        return intamt;
    }

    
    /**
     * 设置 intamt
     * @param intamt intamt
     */
    public void setIntamt(BigDecimal intamt) {
        this.intamt = intamt;
    }

    
    /**
     * 获取 tradeamt
     * @return tradeamt.
     */
    public BigDecimal getTradeamt() {
        return tradeamt;
    }

    
    /**
     * 设置 tradeamt
     * @param tradeamt tradeamt
     */
    public void setTradeamt(BigDecimal tradeamt) {
        this.tradeamt = tradeamt;
    }


    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }
}
