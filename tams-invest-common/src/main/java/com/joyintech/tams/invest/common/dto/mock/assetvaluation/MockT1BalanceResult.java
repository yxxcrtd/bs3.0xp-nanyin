/*
 * 文件名：MockT1BalanceResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2020年4月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.dto.mock.assetvaluation;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

/**
 * T+1处理结果集								
 * 〈功能详细描述〉
 * @author admin
 * @version 1.0 2020年4月24日
 * @see MockT1BalanceResult
 * @since 1.0
 */
public class MockT1BalanceResult extends BaseEntity {
	
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1093510700685108147L;

    /**
	 * 公允价值变动类科目余额
	 */
	private BigDecimal tdyFloatIngplBalance;
	
	/**
	 * 溢折价类科目余额	
	 */
	private BigDecimal adjustBalance;
	
	/**
	 * 应计利息科目余额	
	 */
	private BigDecimal interestBalance;
	
	/**
	 * 成本类科目余额	
	 */
	private BigDecimal costBalance;
	
	/**
	 * 数量余额	
	 */
	private BigDecimal amountBalance;

	/**
	 * 获取 tdyFloatIngplBalance
	 * @return tdyFloatIngplBalance.
	 */
	public BigDecimal getTdyFloatIngplBalance() {
		return tdyFloatIngplBalance;
	}

	/**
	 * 设置 tdyFloatIngplBalance
	 * @param tdyFloatIngplBalance tdyFloatIngplBalance
	 */
	public void setTdyFloatIngplBalance(BigDecimal tdyFloatIngplBalance) {
		this.tdyFloatIngplBalance = tdyFloatIngplBalance;
	}

	/**
	 * 获取 adjustBalance
	 * @return adjustBalance.
	 */
	public BigDecimal getAdjustBalance() {
		return adjustBalance;
	}

	/**
	 * 设置 adjustBalance
	 * @param adjustBalance adjustBalance
	 */
	public void setAdjustBalance(BigDecimal adjustBalance) {
		this.adjustBalance = adjustBalance;
	}

	/**
	 * 获取 interestBalance
	 * @return interestBalance.
	 */
	public BigDecimal getInterestBalance() {
		return interestBalance;
	}

	/**
	 * 设置 interestBalance
	 * @param interestBalance interestBalance
	 */
	public void setInterestBalance(BigDecimal interestBalance) {
		this.interestBalance = interestBalance;
	}

	/**
	 * 获取 costBalance
	 * @return costBalance.
	 */
	public BigDecimal getCostBalance() {
		return costBalance;
	}

	/**
	 * 设置 costBalance
	 * @param costBalance costBalance
	 */
	public void setCostBalance(BigDecimal costBalance) {
		this.costBalance = costBalance;
	}

	/**
	 * 获取 amountBalance
	 * @return amountBalance.
	 */
	public BigDecimal getAmountBalance() {
		return amountBalance;
	}

	/**
	 * 设置 amountBalance
	 * @param amountBalance amountBalance
	 */
	public void setAmountBalance(BigDecimal amountBalance) {
		this.amountBalance = amountBalance;
	}

	
}
