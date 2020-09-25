package com.joyintech.service.portfolio.ptlcashflowgapanalysis.dto;
import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;

/**
 * 单一现金流缺口实体类Dto
 * @author pancz
 *
 */
public class PtlCashFlowGapAnalysisDto extends BaseEntity {
	
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 8496377411327582051L;

    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 资金账户
     */
    private String fundAccount;

    /**
     * 金额显示精度
     */
    private String displayAccuracy;

    /**
     * 日期
     */
    private Date cDate;
	private String cDateStr;
	
    /**
     * 投管用业务类型或平安业务类型
     */
    private String cashFlowType;

    /**
     * 现金流类型
     */
    private String cashType;
    /**
     * 费用类型
     */
    private String feetype;

    /**
     * 资产标的
     */
    private String assetSign;

    /**
     * 现金流
     */
    private BigDecimal cashFlow;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 汇总维度
     */
    private String aggregDimension;
    /**
     * 流入现金流
     * @return
     */
    private BigDecimal inCashflow;
    /**
     * 流出现金流
     * @return
     */
    private BigDecimal outCashflow;
    /**
     * 净现金流
     * @return
     */
    private BigDecimal netCashflow;

	/**
	 * 是否是平安业务类型
	 */
	private boolean paType;

	/**
	 * 获取 paType
	 *
	 * @return paType
	 */
	public boolean isPaType() {
		return paType;
	}

	/**
	 * 设置 paType
	 *
	 * @param paType paType
	 */
	public void setPaType(boolean paType) {
		this.paType = paType;
	}

	public String getAggregDimension() {
		return aggregDimension;
	}

	public void setAggregDimension(String aggregDimension) {
		this.aggregDimension = aggregDimension;
	}

	public BigDecimal getInCashflow() {
		return inCashflow;
	}

	public void setInCashflow(BigDecimal inCashflow) {
		this.inCashflow = inCashflow;
	}

	public BigDecimal getOutCashflow() {
		return outCashflow;
	}

	public void setOutCashflow(BigDecimal outCashflow) {
		this.outCashflow = outCashflow;
	}

	public BigDecimal getNetCashflow() {
		return netCashflow;
	}

	public void setNetCashflow(BigDecimal netCashflow) {
		this.netCashflow = netCashflow;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getFundAccount() {
		return fundAccount;
	}

	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}

	public String getDisplayAccuracy() {
		return displayAccuracy;
	}

	public void setDisplayAccuracy(String displayAccuracy) {
		this.displayAccuracy = displayAccuracy;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public String getCashFlowType() {
		return cashFlowType;
	}

	public void setCashFlowType(String cashFlowType) {
		this.cashFlowType = cashFlowType;
	}

	public String getAssetSign() {
		return assetSign;
	}

	public void setAssetSign(String assetSign) {
		this.assetSign = assetSign;
	}

	public BigDecimal getCashFlow() {
		return cashFlow;
	}

	public void setCashFlow(BigDecimal cashFlow) {
		this.cashFlow = cashFlow;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	/**
	 * 获取 cashType
	 *
	 * @return cashType
	 */
	public String getCashType() {
		return cashType;
	}

	/**
	 * 设置 cashType
	 *
	 * @param cashType cashType
	 */
	public void setCashType(String cashType) {
		this.cashType = cashType;
	}

	public String getcDateStr() {
		if (cDate != null){
			return DateUtil.format(cDate);
		}
		return cDateStr;
	}

	public void setcDateStr(String cDateStr) {
		this.cDateStr = cDateStr;
	}
	public String getFeetype() {
		return feetype;
	}

	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}
}
