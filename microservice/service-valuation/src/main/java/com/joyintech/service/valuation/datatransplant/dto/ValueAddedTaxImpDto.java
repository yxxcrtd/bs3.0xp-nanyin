/*
 * 文件名：ValueAddedTaxImpDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.datatransplant.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 【增值税模版】excel对应的实体类
 * 
 * @author yangjian
 * @version 1.0 2018年11月9日
 * @see ValueAddedTaxImpDto
 * @since 1.0
 */
public class ValueAddedTaxImpDto implements Cloneable{

    /**
     * 账套代码
     */
    private String bookSetId;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 业务明细类型
     */
    private String busiType;

    /**
     * 日期
     */
    private Date impDate;
    
    /**
     * 投资目的 add by wujie on 20181211
     */
    private String invAim;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 含税销售额
     */
    private BigDecimal saleAmt;

    /**
     * 计税余额
     */
    private BigDecimal taxAmt;

    /**
     * 税类型
     */
    private String taxType;

    /**
     * 确认标识
     */
    private String confrim;

    /**
     * 计税方式
     */
    private String calcType;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 免税收入类型
     */
    private String feeType;

    /**
     * 余额类型
     */
    private String balanceType;

    /**
     * 获取 bookSetId
     * 
     * @return bookSetId.
     */
    public String getBookSetId() {
        return bookSetId;
    }

    /**
     * 设置 bookSetId
     * 
     * @param bookSetId bookSetId
     */
    public void setBookSetId(String bookSetId) {
        this.bookSetId = bookSetId;
    }

    /**
     * 获取 busiId
     * 
     * @return busiId.
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 busiId
     * 
     * @param busiId busiId
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    /**
     * 获取 busiType
     * 
     * @return busiType.
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 busiType
     * 
     * @param busiType busiType
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    /**
     * 获取 impDate
     * 
     * @return impDate.
     */
    public Date getImpDate() {
        return impDate;
    }

    /**
     * 设置 impDate
     * 
     * @param impDate impDate
     */
    public void setImpDate(Date impDate) {
        this.impDate = impDate;
    }

    /**
     * 获取 ccy
     * 
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     * 
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 saleAmt
     * 
     * @return saleAmt.
     */
    public BigDecimal getSaleAmt() {
        return saleAmt;
    }

    /**
     * 设置 saleAmt
     * 
     * @param saleAmt saleAmt
     */
    public void setSaleAmt(BigDecimal saleAmt) {
        this.saleAmt = saleAmt;
    }

    /**
     * 获取 taxAmt
     * 
     * @return taxAmt.
     */
    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    /**
     * 设置 taxAmt
     * 
     * @param taxAmt taxAmt
     */
    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    /**
     * 获取 taxType
     * 
     * @return taxType.
     */
    public String getTaxType() {
        return taxType;
    }

    /**
     * 设置 taxType
     * 
     * @param taxType taxType
     */
    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    /**
     * 获取 confrim
     * 
     * @return confrim.
     */
    public String getConfrim() {
        return confrim;
    }

    /**
     * 设置 confrim
     * 
     * @param confrim confrim
     */
    public void setConfrim(String confrim) {
        this.confrim = confrim;
    }

    /**
     * 获取 calcType
     * 
     * @return calcType.
     */
    public String getCalcType() {
        return calcType;
    }

    /**
     * 设置 calcType
     * 
     * @param calcType calcType
     */
    public void setCalcType(String calcType) {
        this.calcType = calcType;
    }

    /**
     * 获取 taxRate
     * 
     * @return taxRate.
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * 设置 taxRate
     * 
     * @param taxRate taxRate
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * 获取 feeType
     * 
     * @return feeType.
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 feeType
     * 
     * @param feeType feeType
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    /**
     * 获取 balanceType
     * 
     * @return balanceType.
     */
    public String getBalanceType() {
        return balanceType;
    }

    /**
     * 设置 balanceType
     * 
     * @param balanceType balanceType
     */
    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }

	/**
	 * @return the invAim
	 */
	public String getInvAim() {
		return invAim;
	}

	/**
	 * @param invAim the invAim to set
	 */
	public void setInvAim(String invAim) {
		this.invAim = invAim;
	}

	public ValueAddedTaxImpDto clone(){
		ValueAddedTaxImpDto object = null;
		try {
			object = (ValueAddedTaxImpDto)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

}
