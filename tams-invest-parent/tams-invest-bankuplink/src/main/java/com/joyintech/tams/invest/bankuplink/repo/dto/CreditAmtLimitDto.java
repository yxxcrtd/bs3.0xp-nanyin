package com.joyintech.tams.invest.bankuplink.repo.dto;

import java.util.List;

import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;

/**
 * 质押式回购R1额度校验Dto
 * 
 * @author ex_kjkfzx_jiangpf
 *
 */
public class CreditAmtLimitDto {
	/**
	 * 证券托管户
	 */
	private String secAcctId;
	/**
	 * 交易方向，买入、卖出、收息、还本、申购、赎回等
	 */
	private String ps;
	/**
	 * 账户来源
	 */
	private String secAcctSource;
	/**
	 * 交易场所，银行间、上交所、深交所、柜面等
	 */
	private String tradeMarket;
	/**
	 * 质押券信息列表
	 */
	private List<PledgeBondInfoDto> pledgeBondList;
	public String getSecAcctId() {
		return secAcctId;
	}
	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	public String getSecAcctSource() {
		return secAcctSource;
	}
	public void setSecAcctSource(String secAcctSource) {
		this.secAcctSource = secAcctSource;
	}
	public String getTradeMarket() {
		return tradeMarket;
	}
	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}
	public List<PledgeBondInfoDto> getPledgeBondList() {
		return pledgeBondList;
	}
	public void setPledgeBondList(List<PledgeBondInfoDto> pledgeBondList) {
		this.pledgeBondList = pledgeBondList;
	}
	
}
