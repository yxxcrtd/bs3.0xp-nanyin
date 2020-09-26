/*
 * 文件名：CurrentDepositDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年8月2日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.investtradequery.dto;

import java.math.BigDecimal;

/**
 * 活期存款收息交易Dto
 * 
 * @author Administrator
 * @version 1.0 2018年8月2日
 * @see CurrentDepositDto
 * @since 1.0
 */
public class CurrentDepositDto extends InvestTradeDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    private BigDecimal num;

    

    /**
     * 计提日
     */
    private String effDate;

    /**
     * 计提基数
     */
    private String basis;

    /**
     * 利率
     */
    private BigDecimal yield;
    
    
    /**
     * 资金账户账号
     */
    private String acctNo;
    
    
    
    
    


	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}


	

    /**
     * 获取 effDate
     * 
     * @return effDate.
     */
    public String getEffDate() {
        return effDate;
    }

    /**
     * 设置 effDate
     * 
     * @param effDate effDate
     */
    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    /**
     * 获取 basis
     * 
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     * 
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 yield
     * 
     * @return yield.
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 yield
     * 
     * @param yield yield
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 获取 num
     * 
     * @return num.
     */
    public BigDecimal getNum() {
        return num;
    }

    /**
     * 设置 num
     * 
     * @param num num
     */
    public void setNum(BigDecimal num) {
        this.num = num;
    }

}
