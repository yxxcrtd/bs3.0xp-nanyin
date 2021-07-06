package com.jixu.msg.dto;

import com.jixu.framework.msg.BaseMsgDto;

public class SmfOperationDto extends BaseMsgDto{

	private static final long serialVersionUID = 4638831000455759035L;
	
	/**
	 * 证券主键
	 */
	private String id;
	
	/**
	 * 资产类型
	 */
	private String issuetype;
	
	/**
	 * 发行商，机构
	 */
	private String issuer;
	
	/**
	 * 发行国
	 */
	private String country;
	
	/**
	 * 交易所
	 */
	private String exchange;
	
	/**
	 * 证券id
	 */
	private String secid;
	
	/**
	 * 证券名称
	 */
	private String securityName;
	
	/**
	 * 自定义资产类型(rpSecType)
	 */
	private String assetType;
	
	/**
	 * 证券货币
	 */
	private String prcurrency;
	
	/**
	 * 收入货币
	 */
	private String incurrency;
	
	/**
	 * 工作流配置
	 */
	private String cashflowcfg="0";
	
	/**
	 * 空方证券货币
	 */
	private String prcurrency2;
	
	/**
	 * 空方收入货币
	 */
	private String incurrency2;
	
	/**
	 * 空方工作流配置
	 */
	private String cashflowcfg2="0";
	
	/**
	 * 截至日期
	 */
	private String secExpireDate;
	
	/**
	 * 合约乘数
	 */
	private double secContractSize;
	
	/**
	 * 总账栏
	 */
	private int glgroup;
	
	/**
	 * 数据源id
	 */
	private int source;
	
	/**
	 * 期权类型,认沽/认购
	 */
	private int optionType;
	
	/**
	 * 关联证券
	 */
	private String underCusip;
	
	/**
	 * 证券激活日期
	 */
	private String activeDate;
	
	/**
	 * 固收类型 
	 * 普通付息债,贴现债,一次性还本付息债,贷款
	 */
	private int fixIncomeType;
	
	/**
	 * 发行价
	 */
	private double startPrice;
	
	/**
	 * 到期价
	 */
	private double endPrice;
	
	/**
	 * 互换面额类型
	 */
	private int swapExchangeInd;
	
	
	//辅助表
	/**
	 * 做多货币
	 */
	private String longCurrency;
	
	/**
	 * 做空金额
	 */
	private double longPrincipal;
	
	/**
	 * 做空货币
	 */
	private String shortCurrency;
	
	/**
	 * 做空金额
	 */
	private double shortPrincipal;
	
	/**
	 * 期货类型
	 */
	private int futuresType;
	
	/**
	 * 合约标的
	 */
	private int futuresTarget;
	
	/**
	 * 保证金
	 */
	private double marginRate;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIssuetype() {
		return issuetype;
	}

	public void setIssuetype(String issuetype) {
		this.issuetype = issuetype;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getSecid() {
		return secid;
	}

	public void setSecid(String secid) {
		this.secid = secid;
	}

	public String getSecurityName() {
		return securityName;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getPrcurrency() {
		return prcurrency;
	}

	public void setPrcurrency(String prcurrency) {
		this.prcurrency = prcurrency;
	}

	public String getIncurrency() {
		return incurrency;
	}

	public void setIncurrency(String incurrency) {
		this.incurrency = incurrency;
	}

	public String getCashflowcfg() {
		return cashflowcfg;
	}

	public void setCashflowcfg(String cashflowcfg) {
		this.cashflowcfg = cashflowcfg;
	}

	public String getPrcurrency2() {
		return prcurrency2;
	}

	public void setPrcurrency2(String prcurrency2) {
		this.prcurrency2 = prcurrency2;
	}

	public String getIncurrency2() {
		return incurrency2;
	}

	public void setIncurrency2(String incurrency2) {
		this.incurrency2 = incurrency2;
	}

	public String getCashflowcfg2() {
		return cashflowcfg2;
	}

	public void setCashflowcfg2(String cashflowcfg2) {
		this.cashflowcfg2 = cashflowcfg2;
	}

	public String getSecExpireDate() {
		return secExpireDate;
	}

	public void setSecExpireDate(String secExpireDate) {
		this.secExpireDate = secExpireDate;
	}

	public double getSecContractSize() {
		return secContractSize;
	}

	public void setSecContractSize(double secContractSize) {
		this.secContractSize = secContractSize;
	}

	public int getGlgroup() {
		return glgroup;
	}

	public void setGlgroup(int glgroup) {
		this.glgroup = glgroup;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getOptionType() {
		return optionType;
	}

	public void setOptionType(int optionType) {
		this.optionType = optionType;
	}

	public String getUnderCusip() {
		return underCusip;
	}

	public void setUnderCusip(String underCusip) {
		this.underCusip = underCusip;
	}

	public String getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(String activeDate) {
		this.activeDate = activeDate;
	}

	public int getFixIncomeType() {
		return fixIncomeType;
	}

	public void setFixIncomeType(int fixIncomeType) {
		this.fixIncomeType = fixIncomeType;
	}

	public double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}

	public double getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(double endPrice) {
		this.endPrice = endPrice;
	}

	public int getSwapExchangeInd() {
		return swapExchangeInd;
	}

	public void setSwapExchangeInd(int swapExchangeInd) {
		this.swapExchangeInd = swapExchangeInd;
	}

	public String getLongCurrency() {
		return longCurrency;
	}

	public void setLongCurrency(String longCurrency) {
		this.longCurrency = longCurrency;
	}

	public double getLongPrincipal() {
		return longPrincipal;
	}

	public void setLongPrincipal(double longPrincipal) {
		this.longPrincipal = longPrincipal;
	}

	public String getShortCurrency() {
		return shortCurrency;
	}

	public void setShortCurrency(String shortCurrency) {
		this.shortCurrency = shortCurrency;
	}

	public double getShortPrincipal() {
		return shortPrincipal;
	}

	public void setShortPrincipal(double shortPrincipal) {
		this.shortPrincipal = shortPrincipal;
	}

	public int getFuturesType() {
		return futuresType;
	}

	public void setFuturesType(int futuresType) {
		this.futuresType = futuresType;
	}

	public int getFuturesTarget() {
		return futuresTarget;
	}

	public void setFuturesTarget(int futuresTarget) {
		this.futuresTarget = futuresTarget;
	}

	public double getMarginRate() {
		return marginRate;
	}

	public void setMarginRate(double marginRate) {
		this.marginRate = marginRate;
	}
}
