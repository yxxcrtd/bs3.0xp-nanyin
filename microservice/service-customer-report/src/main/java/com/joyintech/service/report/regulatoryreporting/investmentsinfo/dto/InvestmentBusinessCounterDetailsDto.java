package com.joyintech.service.report.regulatoryreporting.investmentsinfo.dto;

import java.math.BigDecimal;

/**
 * 表内外投资业务情况统计表大型银行统计报表
 * 表７-2
 * @author Administrator
 *
 */
public class InvestmentBusinessCounterDetailsDto {
	// 属性代码
	private Integer propertycode;
	//属性类型
	private String propertytype;
	//表七同业交易对手id
	private  String counterId;
	//表七同业交易对手名称
	private  String counterName;
	// 表七本季末数额
	private BigDecimal amt;
	// 表七本季末数额
	private BigDecimal sessionendNum;
	// 表七上季末数额
	private BigDecimal lastSessionendNum;
	//表七城商行主动管理类业务余额
	private BigDecimal zdSessionendNum;
	//表七穿透后非标债权资产余额
	private BigDecimal fbSessionendNum;


    //表七同业交易对手名称-信托公司
	private  String xtCounterName;
	// 表七本季末数额-信托公司
	private BigDecimal xtSessionendNum;
	// 表七上季末数额-信托公司
	private BigDecimal xtLastSessionendNum;
	//表7城商行主动管理类业务余额
	private BigDecimal xtZdSessionendNum;
	//表七穿透后非标债权资产余额-信托公司
	private BigDecimal xtFbSessionendNum;

	//表七同业交易对手名称-证券期货公司及其子公司
	private  String zqqhCounterName;
	// 表七本季末数额-证券期货公司及其子公司
	private BigDecimal zqqhSessionendNum;
	// 表七上季末数额-证券期货公司及其子公司
	private BigDecimal zqqhLastSessionendNum;
	//表7城商行主动管理类业务余额
	private BigDecimal zqqhZdSessionendNum;
	//表七穿透后非标债权资产余额-证券期货公司及其子公司
	private BigDecimal zqqhFbSessionendNum;

	//表七同业交易对手名称-证券投资基金（公募基金）及其子公司（含公募基金专户）
	private  String zqjjCounterName;
	// 表七本季末数额-证券投资基金（公募基金）及其子公司（含公募基金专户）
	private BigDecimal zqjjSessionendNum;
	// 表七上季末数额-证券投资基金（公募基金）及其子公司（含公募基金专户）
	private BigDecimal zqjjLastSessionendNum;
	//表7城商行主动管理类业务余额
	private BigDecimal zqjjZdSessionendNum;
	//表七穿透后非标债权资产余额-证券投资基金（公募基金）及其子公司（含公募基金专户）
	private BigDecimal zqjjFbSessionendNum;

	//表七同业交易对手名称-政策性银行、商业银行（含农商行、农信社、村镇银行|农村合作银行）及其理财子公司
	private  String yhCounterName;
	// 表七本季末数额-政策性银行、商业银行（含农商行、农信社、村镇银行|农村合作银行）及其理财子公司
	private BigDecimal yhSessionendNum;
	// 表七上季末数额-政策性银行、商业银行（含农商行、农信社、村镇银行|农村合作银行）及其理财子公司
	private BigDecimal yhLastSessionendNum;
	//表7城商行主动管理类业务余额
	private BigDecimal yhZdSessionendNum;
	//表七穿透后非标债权资产余额-政策性银行、商业银行（含农商行、农信社、村镇银行|农村合作银行）及其理财子公司
	private BigDecimal yhFbSessionendNum;

	//表七同业交易对手名称-保险及其资管公司、私募基金、地方金融资产交易所等其他金融机构
	private  String bxCounterName;
	// 表七本季末数额-保险及其资管公司、私募基金、地方金融资产交易所等其他金融机构
	private BigDecimal bxSessionendNum;
	// 表七上季末数额-保险及其资管公司、私募基金、地方金融资产交易所等其他金融机构
	private BigDecimal bxLastSessionendNum;
	//表7城商行主动管理类业务余额
	private BigDecimal bxZdSessionendNum;
	//表七穿透后非标债权资产余额-保险及其资管公司、私募基金、地方金融资产交易所等其他金融机构
	private BigDecimal bxFbSessionendNum;




	public String getXtCounterName() {
		return xtCounterName;
	}

	public void setXtCounterName(String xtCounterName) {
		this.xtCounterName = xtCounterName;
	}

	public BigDecimal getXtSessionendNum() {
		return xtSessionendNum;
	}

	public void setXtSessionendNum(BigDecimal xtSessionendNum) {
		this.xtSessionendNum = xtSessionendNum;
	}

	public BigDecimal getXtLastSessionendNum() {
		return xtLastSessionendNum;
	}

	public void setXtLastSessionendNum(BigDecimal xtLastSessionendNum) {
		this.xtLastSessionendNum = xtLastSessionendNum;
	}

	public BigDecimal getXtFbSessionendNum() {
		return xtFbSessionendNum;
	}

	public void setXtFbSessionendNum(BigDecimal xtFbSessionendNum) {
		this.xtFbSessionendNum = xtFbSessionendNum;
	}

	public String getZqqhCounterName() {
		return zqqhCounterName;
	}

	public void setZqqhCounterName(String zqqhCounterName) {
		this.zqqhCounterName = zqqhCounterName;
	}

	public BigDecimal getZqqhSessionendNum() {
		return zqqhSessionendNum;
	}

	public void setZqqhSessionendNum(BigDecimal zqqhSessionendNum) {
		this.zqqhSessionendNum = zqqhSessionendNum;
	}

	public BigDecimal getZqqhLastSessionendNum() {
		return zqqhLastSessionendNum;
	}

	public void setZqqhLastSessionendNum(BigDecimal zqqhLastSessionendNum) {
		this.zqqhLastSessionendNum = zqqhLastSessionendNum;
	}

	public BigDecimal getZqqhFbSessionendNum() {
		return zqqhFbSessionendNum;
	}

	public void setZqqhFbSessionendNum(BigDecimal zqqhFbSessionendNum) {
		this.zqqhFbSessionendNum = zqqhFbSessionendNum;
	}

	public String getZqjjCounterName() {
		return zqjjCounterName;
	}

	public void setZqjjCounterName(String zqjjCounterName) {
		this.zqjjCounterName = zqjjCounterName;
	}

	public BigDecimal getZqjjSessionendNum() {
		return zqjjSessionendNum;
	}

	public void setZqjjSessionendNum(BigDecimal zqjjSessionendNum) {
		this.zqjjSessionendNum = zqjjSessionendNum;
	}

	public BigDecimal getZqjjLastSessionendNum() {
		return zqjjLastSessionendNum;
	}

	public void setZqjjLastSessionendNum(BigDecimal zqjjLastSessionendNum) {
		this.zqjjLastSessionendNum = zqjjLastSessionendNum;
	}

	public BigDecimal getZqjjFbSessionendNum() {
		return zqjjFbSessionendNum;
	}

	public void setZqjjFbSessionendNum(BigDecimal zqjjFbSessionendNum) {
		this.zqjjFbSessionendNum = zqjjFbSessionendNum;
	}

	public String getYhCounterName() {
		return yhCounterName;
	}

	public void setYhCounterName(String yhCounterName) {
		this.yhCounterName = yhCounterName;
	}



	public String getBxCounterName() {
		return bxCounterName;
	}

	public void setBxCounterName(String bxCounterName) {
		this.bxCounterName = bxCounterName;
	}





	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public BigDecimal getSessionendNum() {
		return sessionendNum;
	}

	public void setSessionendNum(BigDecimal sessionendNum) {
		this.sessionendNum = sessionendNum;
	}

	public BigDecimal getLastSessionendNum() {
		return lastSessionendNum;
	}

	public void setLastSessionendNum(BigDecimal lastSessionendNum) {
		this.lastSessionendNum = lastSessionendNum;
	}

	public BigDecimal getFbSessionendNum() {
		return fbSessionendNum;
	}

	public void setFbSessionendNum(BigDecimal fbSessionendNum) {
		this.fbSessionendNum = fbSessionendNum;
	}

	public String getPropertytype() {
		return propertytype;
	}

	public void setPropertytype(String propertytype) {
		this.propertytype = propertytype;
	}

	public String getCounterId() {
		return counterId;
	}

	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}


	public Integer getPropertycode() {
		return propertycode;
	}

	public void setPropertycode(Integer propertycode) {
		this.propertycode = propertycode;
	}

	public BigDecimal getYhSessionendNum() {
		return yhSessionendNum;
	}

	public void setYhSessionendNum(BigDecimal yhSessionendNum) {
		this.yhSessionendNum = yhSessionendNum;
	}

	public BigDecimal getYhLastSessionendNum() {
		return yhLastSessionendNum;
	}

	public void setYhLastSessionendNum(BigDecimal yhLastSessionendNum) {
		this.yhLastSessionendNum = yhLastSessionendNum;
	}

	public BigDecimal getYhFbSessionendNum() {
		return yhFbSessionendNum;
	}

	public void setYhFbSessionendNum(BigDecimal yhFbSessionendNum) {
		this.yhFbSessionendNum = yhFbSessionendNum;
	}

	public BigDecimal getBxSessionendNum() {
		return bxSessionendNum;
	}

	public void setBxSessionendNum(BigDecimal bxSessionendNum) {
		this.bxSessionendNum = bxSessionendNum;
	}

	public BigDecimal getBxLastSessionendNum() {
		return bxLastSessionendNum;
	}

	public void setBxLastSessionendNum(BigDecimal bxLastSessionendNum) {
		this.bxLastSessionendNum = bxLastSessionendNum;
	}

	public BigDecimal getBxFbSessionendNum() {
		return bxFbSessionendNum;
	}

	public void setBxFbSessionendNum(BigDecimal bxFbSessionendNum) {
		this.bxFbSessionendNum = bxFbSessionendNum;
	}

	public BigDecimal getXtZdSessionendNum() {
		return xtZdSessionendNum;
	}

	public void setXtZdSessionendNum(BigDecimal xtZdSessionendNum) {
		this.xtZdSessionendNum = xtZdSessionendNum;
	}

	public BigDecimal getZqqhZdSessionendNum() {
		return zqqhZdSessionendNum;
	}

	public void setZqqhZdSessionendNum(BigDecimal zqqhZdSessionendNum) {
		this.zqqhZdSessionendNum = zqqhZdSessionendNum;
	}

	public BigDecimal getZqjjZdSessionendNum() {
		return zqjjZdSessionendNum;
	}

	public void setZqjjZdSessionendNum(BigDecimal zqjjZdSessionendNum) {
		this.zqjjZdSessionendNum = zqjjZdSessionendNum;
	}

	public BigDecimal getYhZdSessionendNum() {
		return yhZdSessionendNum;
	}

	public void setYhZdSessionendNum(BigDecimal yhZdSessionendNum) {
		this.yhZdSessionendNum = yhZdSessionendNum;
	}

	public BigDecimal getBxZdSessionendNum() {
		return bxZdSessionendNum;
	}

	public void setBxZdSessionendNum(BigDecimal bxZdSessionendNum) {
		this.bxZdSessionendNum = bxZdSessionendNum;
	}

	public BigDecimal getZdSessionendNum() {
		return zdSessionendNum;
	}

	public void setZdSessionendNum(BigDecimal zdSessionendNum) {
		this.zdSessionendNum = zdSessionendNum;
	}
}
