package com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio;

import java.math.BigDecimal;

/**
 * @program: tams-disclosure-busicommon
 * @description: 单资产变化 value
 * @author: 强成西
 * @create: 2020-04-16 21:56
 **/
public class MockSingleAssetChanging {
    /**
     * 数量
     */
    private BigDecimal amount;

    /**
     * 成本
     */
    private BigDecimal cost;
    /**
     * 利息
     */
    private BigDecimal interest;
    /**
     * 计提成本
     */
    private BigDecimal accrualCost;
    /**
     * 计提数量
     */
    private BigDecimal accrualAmount;
    /**
     * 计提利息
     */
    private BigDecimal accrualInterest;
    /**
     * 清算资金币种
     */
    private String squareCapitalCcy;
    /**
     * 收入端应计利息
     */
    private BigDecimal inTdyIntincexp;
    /**
     * 支出端应计利息
     */
    private BigDecimal outTdyIntincexp;
    /**
     * 公允价值变动
     */
    private BigDecimal tdyFloatIngpl;
    /**
     * 清算款
     */
    private BigDecimal delayPayAmt;
    
    /**
     * 利息调整
     */
    private BigDecimal interestAdjust;
    
    
    /**
     * 获取 amount
     *
     * @return amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 amount
     *
     * @param amount amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取 cost
     *
     * @return cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * 设置 cost
     *
     * @param cost cost
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * 获取 interest
     *
     * @return interest
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * 设置 interest
     *
     * @param interest interest
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * 获取 accrualCost
     *
     * @return accrualCost
     */
    public BigDecimal getAccrualCost() {
        return accrualCost;
    }

    /**
     * 设置 accrualCost
     *
     * @param accrualCost accrualCost
     */
    public void setAccrualCost(BigDecimal accrualCost) {
        this.accrualCost = accrualCost;
    }

    /**
     * 获取 accrualAmount
     *
     * @return accrualAmount
     */
    public BigDecimal getAccrualAmount() {
        return accrualAmount;
    }

    /**
     * 设置 accrualAmount
     *
     * @param accrualAmount accrualAmount
     */
    public void setAccrualAmount(BigDecimal accrualAmount) {
        this.accrualAmount = accrualAmount;
    }

    /**
     * 获取 accrualInterest
     *
     * @return accrualInterest
     */
    public BigDecimal getAccrualInterest() {
        return accrualInterest;
    }

    /**
     * 设置 accrualInterest
     *
     * @param accrualInterest accrualInterest
     */
    public void setAccrualInterest(BigDecimal accrualInterest) {
        this.accrualInterest = accrualInterest;
    }

    /**
     * 获取 squareCapitalCcy
     *
     * @return squareCapitalCcy
     */
    public String getSquareCapitalCcy() {
        return squareCapitalCcy;
    }

    /**
     * 设置 squareCapitalCcy
     *
     * @param squareCapitalCcy squareCapitalCcy
     */
    public void setSquareCapitalCcy(String squareCapitalCcy) {
        this.squareCapitalCcy = squareCapitalCcy;
    }

    /**
     * 获取 inTdyIntincexp
     *
     * @return inTdyIntincexp
     */
    public BigDecimal getInTdyIntincexp() {
        return inTdyIntincexp;
    }

    /**
     * 设置 inTdyIntincexp
     *
     * @param inTdyIntincexp inTdyIntincexp
     */
    public void setInTdyIntincexp(BigDecimal inTdyIntincexp) {
        this.inTdyIntincexp = inTdyIntincexp;
    }

    /**
     * 获取 outTdyIntincexp
     *
     * @return outTdyIntincexp
     */
    public BigDecimal getOutTdyIntincexp() {
        return outTdyIntincexp;
    }

    /**
     * 设置 outTdyIntincexp
     *
     * @param outTdyIntincexp outTdyIntincexp
     */
    public void setOutTdyIntincexp(BigDecimal outTdyIntincexp) {
        this.outTdyIntincexp = outTdyIntincexp;
    }

    /**
     * 获取 tdyFloatIngpl
     *
     * @return tdyFloatIngpl
     */
    public BigDecimal getTdyFloatIngpl() {
        return tdyFloatIngpl;
    }

    /**
     * 设置 tdyFloatIngpl
     *
     * @param tdyFloatIngpl tdyFloatIngpl
     */
    public void setTdyFloatIngpl(BigDecimal tdyFloatIngpl) {
        this.tdyFloatIngpl = tdyFloatIngpl;
    }

	/**
	 * 获取 delayPayAmt
	 * @return delayPayAmt.
	 */
	public BigDecimal getDelayPayAmt() {
		return delayPayAmt;
	}

	/**
	 * 设置 delayPayAmt
	 * @param delayPayAmt delayPayAmt
	 */
	public void setDelayPayAmt(BigDecimal delayPayAmt) {
		this.delayPayAmt = delayPayAmt;
	}

	/**
	 * 获取 interestAdjust
	 * @return interestAdjust.
	 */
	public BigDecimal getInterestAdjust() {
		return interestAdjust;
	}

	/**
	 * 设置 interestAdjust
	 * @param interestAdjust interestAdjust
	 */
	public void setInterestAdjust(BigDecimal interestAdjust) {
		this.interestAdjust = interestAdjust;
	}
    
}
