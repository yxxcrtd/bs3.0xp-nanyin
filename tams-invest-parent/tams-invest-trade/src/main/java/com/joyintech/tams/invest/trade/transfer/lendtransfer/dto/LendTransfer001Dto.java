package com.joyintech.tams.invest.trade.transfer.lendtransfer.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 拆解资产转仓
 *
 * @author liuwei
 * @version 1.0 2020年02月18日
 * @see LendTransfer001Dto
 * @since 1.0
 */
public class LendTransfer001Dto extends BaseEntity implements ProcessEntity {
    /**
     * 实际组合代码
     */
    private String actPortfolioId;
    /**
     * 待转出组合
     */
    private String portfolioIdOut;
    /**
     * 待转入组合代码
     */
    private String portfolioId;
    /**
     * 拆借类型
     */
    private String paBussType;
    /**
     * 交易日期
     */
    private Date tradeDate;
    /**
     * 交易方向
     */
    private String ps;
    /**
     * 起息日
     */
    private Date vdate;
    /**
     * 到期日
     */
    private Date mdate;
    /**
     * 计息基础
     */
    private String basis;
    /**
     * 币种
     */
    private String ccy;
    /**
     * 利率%
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal intRate;
    /**
     * 本金金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal prin;
    /**
     * 剩余可转仓份额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal undistributedAmount;
    /**
     * 转仓列表信息
     */
    private List<com.joyintech.tams.invest.trade.transfer.lendtransfer.dto.LendTransfer002Dto> lendTransfer002DtoList;
    /**
     * 本金金额(待转入)
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal prinAmt;
    /**
     * 交易金额(待转入)
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal tradeAmt;
    /**
     * 应计利息总额(待转入)
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal intAmt;
    /**
     * 组合剩余现金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal avalibleCash;
    /**
     * 画面元素
     */
    private String elementId;
    /**
     * 交易编号
     */
    private String tradeId;
    /**
     * 交易日历编号
     */
    private String calendarId;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;
    
    /**
     * 投资目的
     */
    private String invAim;
    /**
     * 通道代码
     */
    private String chlId;
    /**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 证券托管户代码
     */
    private String secAcctId;

    /**
     * 获取 实际组合代码
     *
     * @return actPortfolioId 实际组合代码
     */
    public String getActPortfolioId() {
        return this.actPortfolioId;
    }

    /**
     * 设置 实际组合代码
     *
     * @param actPortfolioId 实际组合代码
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    /**
     * 获取 待转出组合
     *
     * @return portfolioIdOut 待转出组合
     */
    public String getPortfolioIdOut() {
        return this.portfolioIdOut;
    }

    /**
     * 设置 待转出组合
     *
     * @param portfolioIdOut 待转出组合
     */
    public void setPortfolioIdOut(String portfolioIdOut) {
        this.portfolioIdOut = portfolioIdOut;
    }

    /**
     * 获取 拆借类型
     *
     * @return paBussType 拆借类型
     */
    public String getPaBussType() {
        return this.paBussType;
    }

    /**
     * 设置 拆借类型
     *
     * @param paBussType 拆借类型
     */
    public void setPaBussType(String paBussType) {
        this.paBussType = paBussType;
    }

    /**
     * 获取 交易日期
     *
     * @return tradeDate 交易日期
     */
    public Date getTradeDate() {
        return this.tradeDate;
    }

    /**
     * 设置 交易日期
     *
     * @param tradeDate 交易日期
     */
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 获取 交易方向
     *
     * @return ps 交易方向
     */
    public String getPs() {
        return this.ps;
    }

    /**
     * 设置 交易方向
     *
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 获取 起息日
     *
     * @return vdate 起息日
     */
    public Date getVdate() {
        return this.vdate;
    }

    /**
     * 设置 起息日
     *
     * @param vdate 起息日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 到期日
     *
     * @return mdate 到期日
     */
    public Date getMdate() {
        return this.mdate;
    }

    /**
     * 设置 到期日
     *
     * @param mdate 到期日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 计息基础
     *
     * @return basis 计息基础
     */
    public String getBasis() {
        return this.basis;
    }

    /**
     * 设置 计息基础
     *
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 币种
     *
     * @return ccy 币种
     */
    public String getCcy() {
        return this.ccy;
    }

    /**
     * 设置 币种
     *
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 利率%
     *
     * @return intRate 利率%
     */
    public BigDecimal getIntRate() {
        return this.intRate;
    }

    /**
     * 设置 利率%
     *
     * @param intRate 利率%
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 获取 本金金额
     *
     * @return prin 本金金额
     */
    public BigDecimal getPrin() {
        return this.prin;
    }

    /**
     * 设置 本金金额
     *
     * @param prin 本金金额
     */
    public void setPrin(BigDecimal prin) {
        this.prin = prin;
    }

    /**
     * 获取 剩余可转仓份额
     *
     * @return undistributedAmount 剩余可转仓份额
     */
    public BigDecimal getUndistributedAmount() {
        return this.undistributedAmount;
    }

    /**
     * 设置 剩余可转仓份额
     *
     * @param undistributedAmount 剩余可转仓份额
     */
    public void setUndistributedAmount(BigDecimal undistributedAmount) {
        this.undistributedAmount = undistributedAmount;
    }

    /**
     * 获取 转仓列表信息
     *
     * @return lendTransfer002DtoList 转仓列表信息
     */
    public List<com.joyintech.tams.invest.trade.transfer.lendtransfer.dto.LendTransfer002Dto> getLendTransfer002DtoList() {
        return this.lendTransfer002DtoList;
    }

    /**
     * 设置 转仓列表信息
     *
     * @param lendTransfer002DtoList 转仓列表信息
     */
    public void setLendTransfer002DtoList(List<com.joyintech.tams.invest.trade.transfer.lendtransfer.dto.LendTransfer002Dto> lendTransfer002DtoList) {
        this.lendTransfer002DtoList = lendTransfer002DtoList;
    }

    /**
     * 获取 待转入组合代码
     *
     * @return portfolioId 待转入组合代码
     */
    public String getPortfolioId() {
        return this.portfolioId;
    }

    /**
     * 设置 待转入组合代码
     *
     * @param portfolioId 待转入组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 本金金额(待转入)
     *
     * @return prinAmt 本金金额(待转入)
     */
    public BigDecimal getPrinAmt() {
        return this.prinAmt;
    }

    /**
     * 设置 本金金额(待转入)
     *
     * @param prinAmt 本金金额(待转入)
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 获取 交易金额(待转入)
     *
     * @return tradeAmt 交易金额(待转入)
     */
    public BigDecimal getTradeAmt() {
        return this.tradeAmt;
    }

    /**
     * 设置 交易金额(待转入)
     *
     * @param tradeAmt 交易金额(待转入)
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 获取 应计利息总额(待转入)
     *
     * @return intAmt 应计利息总额(待转入)
     */
    public BigDecimal getIntAmt() {
        return this.intAmt;
    }

    /**
     * 设置 应计利息总额(待转入)
     *
     * @param intAmt 应计利息总额(待转入)
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 获取 组合剩余现金
     *
     * @return avalibleCash 组合剩余现金
     */
    public BigDecimal getAvalibleCash() {
        return this.avalibleCash;
    }

    /**
     * 设置 组合剩余现金
     *
     * @param avalibleCash 组合剩余现金
     */
    public void setAvalibleCash(BigDecimal avalibleCash) {
        this.avalibleCash = avalibleCash;
    }

    /**
     * 获取 画面元素
     *
     * @return elementId 画面元素
     */
    public String getElementId() {
        return this.elementId;
    }

    /**
     * 设置 画面元素
     *
     * @param elementId 画面元素
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 交易编号
     *
     * @return tradeId 交易编号
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getTradeId() {
        return this.tradeId;
    }

    /**
     * 设置 交易编号
     *
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 获取 交易日历编号
     *
     * @return calendarId 交易日历编号
     */
    public String getCalendarId() {
        return this.calendarId;
    }

    /**
     * 设置 交易日历编号
     *
     * @param calendarId 交易日历编号
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 获取 操作类型
     *
     * @return operateType 操作类型
     */
    public String getOperateType() {
        return this.operateType;
    }

    /**
     * 设置 操作类型
     *
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 风险试算实体类
     *
     * @return riskTrialRetDto 风险试算实体类
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return this.riskTrialRetDto;
    }

    /**
     * 设置 风险试算实体类
     *
     * @param riskTrialRetDto 风险试算实体类
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

	public String getInvAim() {
		return invAim;
	}

	public void setInvAim(String invAim) {
		this.invAim = invAim;
	}

	public String getChlId() {
		return chlId;
	}

	public void setChlId(String chlId) {
		this.chlId = chlId;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getSecAcctId() {
		return secAcctId;
	}

	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}
}
