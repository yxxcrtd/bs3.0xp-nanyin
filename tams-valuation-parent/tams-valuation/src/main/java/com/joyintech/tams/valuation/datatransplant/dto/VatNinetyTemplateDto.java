/*
 * 文件名：VatNinetyTemplateDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.datatransplant.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 增值税_90号文-dto
 * 
 * @author niuqianyu
 * @version 1.0 2018年11月14日
 * @see VatNinetyTemplateDto
 * @since 1.0
 */
public class VatNinetyTemplateDto {

    /**
     * 账套代码
     */
    private String booksetId;

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
     * 投资目的 add by wujie on 20181214
     */
    private String invAim;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 待抵扣资产份额
     */
    private BigDecimal cbAmt;

    /**
     * 待抵扣增值额
     */
    private BigDecimal incomeAmt;

    /**
     * 获取 账套代码
     * 
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * 
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 业务明细代码
     * 
     * @return busiId.
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 业务明细代码
     * 
     * @param busiId busiId
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    /**
     * 获取 业务明细类型
     * 
     * @return busiType.
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务明细类型
     * 
     * @param busiType busiType
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    /**
     * 获取 日期
     * 
     * @return impDate.
     */
    public Date getImpDate() {
        return impDate;
    }

    /**
     * 设置 日期
     * 
     * @param impDate impDate
     */
    public void setImpDate(Date impDate) {
        this.impDate = impDate;
    }

    /**
     * 获取 币种
     * 
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * 
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 待抵扣资产份额
     * 
     * @return cbAmt.
     */
    public BigDecimal getCbAmt() {
        return cbAmt;
    }

    /**
     * 设置 待抵扣资产份额
     * 
     * @param cbAmt cbAmt
     */
    public void setCbAmt(BigDecimal cbAmt) {
        this.cbAmt = cbAmt;
    }

    /**
     * 获取 待抵扣增值额
     * 
     * @return incomeAmt.
     */
    public BigDecimal getIncomeAmt() {
        return incomeAmt;
    }

    /**
     * 设置 待抵扣增值额
     * 
     * @param incomeAmt incomeAmt
     */
    public void setIncomeAmt(BigDecimal incomeAmt) {
        this.incomeAmt = incomeAmt;
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

}
