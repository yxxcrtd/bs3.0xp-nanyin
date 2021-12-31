package com.joyintech.service.trade.investtrade.commanddispatcher.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.background.component.common.pack.common.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * @author meifaxin
 * @create 2021-11-21 13:59
 */
public class EtrEntrustDetailDto extends BaseEntity{
    private static final long serialVersionUID = -7130177040330108562L;

    private String entrustId = "";// 委托代码

    private String portfolioId = "";// 组合代码

    private String portfolioName = "";//组合名称

    @JsonSerialize(using = BigDecimalSerializer.class)
    private String disRate = "";//分配比例

    @JsonSerialize(using = BigDecimalSerializer.class)
    private String entrustAmt = "";//委托金额
    
    @JsonSerialize(using = BigDecimalSerializer.class)
    private String entrustNum = "";//委托数量
    
    private String targetType = "";//目标类型



	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getDisRate() {
		return disRate;
	}

	public void setDisRate(String disRate) {
		this.disRate = disRate;
	}

	public String getEntrustAmt() {
		return entrustAmt;
	}

	public void setEntrustAmt(String entrustAmt) {
		this.entrustAmt = entrustAmt;
	}

	public String getEntrustNum() {
		return entrustNum;
	}

	public void setEntrustNum(String entrustNum) {
		this.entrustNum = entrustNum;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

    
}
