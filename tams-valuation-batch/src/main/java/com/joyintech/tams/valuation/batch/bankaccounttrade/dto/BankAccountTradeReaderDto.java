/*
 * 文件名：BankAccountTradeReaderDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年4月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bankaccounttrade.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 
 * 银行账交易账务批处理-reader
 * 
 * @author niuqianyu
 * @version 1.0 2019年4月10日
 * @see BankAccountTradeReaderDto
 * @since 1.0
 */
public class BankAccountTradeReaderDto extends NeedOperateEntity {
	/**
	 * 金融产品代码
	 */
	private String finprodId;
	/**
	 * 金融产品类型
	 */
	private String finprodType;
	/**
	 * 分支序号
	 */
	private Long branch;
	/**
	 * 交易编号
	 */
	private String tradeId;
	/**
	 * 交易币种
	 */
	private String ccy;
	/**
	 * 交易金额
	 */
	private BigDecimal tradeAmt;
	/**
	 * 交易方向
	 */
	private String ps;
	/**
	 * 交易状态
	 */
	private String tradeStatus;
	/**
     * 是否结清
     */
    private String isClean;
	/**
	 * 理财产品代码
	 */
	private String proId;
	/**
	 * 任务代码
	 */
	private String taskId;
	/**
	 * 账套代码
	 */
	private String booksetId;
	/**
	 * 账套日期
	 */
	private Date booksetDate;
	/**
	 * 发生日期
	 */
	private Date happenDate;
	/**
	 * 入账日期
	 */
	private Date bookDate;
	/**
	 * 本位币
	 */
	private String standardCcy;

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getFinprodType() {
		return finprodType;
	}

	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}

	public Long getBranch() {
		return branch;
	}

	public void setBranch(Long branch) {
		this.branch = branch;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public Date getBooksetDate() {
		return booksetDate;
	}

	public void setBooksetDate(Date booksetDate) {
		this.booksetDate = booksetDate;
	}

	public Date getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public String getStandardCcy() {
		return standardCcy;
	}

	public void setStandardCcy(String standardCcy) {
		this.standardCcy = standardCcy;
	}

    
    /**
     * 获取 isClean
     * @return isClean.
     */
    public String getIsClean() {
        return isClean;
    }

    
    /**
     * 设置 isClean
     * @param isClean isClean
     */
    public void setIsClean(String isClean) {
        this.isClean = isClean;
    }

}
