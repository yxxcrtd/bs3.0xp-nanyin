package com.jixu.data.pojo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jixu.common.util.DateUtil;

/** 
 * @ClassName 		SecPosition
 * @author  		Pan Wei 
 * @Date 			2017年10月28日 下午9:36:06 	
 * @Description		[]  
 * @return  
 */

@Entity 
@Table(name="tran") 
public class Tran implements Serializable{
	
	private static final long serialVersionUID = 7502190436140521286L;

	private String taNum;
	
	/**
	 * 数量
	 */
    private double amount;
	
    private double amount2;
	
    /**
	 * 现金
	 */
    private double cash;
	
    private double cash2;
	
    /**
	 * 利率
	 */
    private double fxRate;
    
    private double fxRate2;
	
    /**
	 * 记账日期
	 */
    private Date ledgerDate;
    
    /**
	 * 是否 post
	 */
	private String postStatus;
	
	/**
	 * 价格
	 */
	private double price;
	
	/**
	 * 成本
	 */
	private double principal;
	
	private double principal2;
	
	private String settleStatus;
	
	/**
	 * 结算 日期
	 */
	private Date settleDate;
	
	private Date settleDate2;
	
	/**
	 * 交易日期
	 */
	private Date tradeDate;
	
	/**
	 * 交易类型
	 */
	private int tranType;
	
	/**
	 * 交易类型名称
	 */
	private String tranTypeName;
	
	/**
	 * 银行现金账户
	 */
	private int bk_CashAcct_NO;
	
	private int bk_CashAcct_NO2;
	
	/**
	 * 证券id
	 */
	private String cusip;
	
	private String cusip2;
	
	private int secMasterID;
	
	/**
	 * 货币代码
	 */
	private int currencyCode;
	
	private int currencyCode2;
	
	/**
	 * 投资组合
	 */
	private int portfolio;
	
	private int portfolio2;
	
	/**
	 * 回购利率
	 */
	private double altRate;
	
	/**
	 * 回购日期
	 */
	private Date altDate;
	
	/**
	 * 到期利息
	 */
	private double intAtMat;
	
	/**
	 * 总账组
	 */
	private int glGroup;
	
	/**
	 * 券商
	 */
	private String broker;
	
	/**
	 * 结算金额
	 */
	private double settleCash;
	
	/**
	 * 结算 利率
	 */
	private double settleFxRate;
	
	/**
	 * 结算货币
	 */
	private int settleCurrency;
	
	private int intent;
	
	/**
	 * 交易市场
	 */
	private int tradeMarket;
	
	/**
	 * 基金管理人
	 */
	private int managerCode;
	
	/**
	 * 位置
	 */
	private int location;
	
	/**
	 * 证券账户
	 */
	private String secAcctCode;
	
	private String secAcctCode2;
	
	private String glAcct;
	
	/**
	 * 交易描述
	 */
	private String tranDesc;
	
	private Date divBasicDate;
	
	private double price2;
	
	/**
	 * 银行账户
	 */
	private String bankAcct;
	
	/**
	 * 银行账户名称
	 */
	private String bankAcctName;
	
	/**
	 * 货币ID
	 */
	private  int currID;
	
	/**
	 * 货币代码
	 */
	private String currCode;
	
	/**
	 * 账户代码
	 */
	private String acctCode;
	
	/**
	 * 证券ID
	 */
	private String securityID;
	
	/**
	 * 证券名称
	 */
	private String securityName;
	
	/**
	 * 资产类型
	 */
	private String assetType;
	
	private double cost;
	
	private double costFunc;
	
	/**
	 * 买入时候的对应股数的成本
	 */
	private double proceeds;
	
	public Tran() {
		this.setAltDate(DateUtil.ConvertUtil2Sql(DateUtil.convertStr2Date2("2000-01-01")));
		this.setDivBasicDate(DateUtil.ConvertUtil2Sql(DateUtil.convertStr2Date2("1899-12-01")));
	}
	
	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.util.GenerateMyID", parameters={
			@Parameter(name = "idInitValue", value = "2"), // 步长，每次id增加多少
			@Parameter(name = "idLength", value = "10")}) // 初始值	会计系统,初始值1
	@Column(name="taNum")
	public String getTaNum() {
		return taNum;
	}

	public void setTaNum(String taNum) {
		this.taNum = taNum;
	}

	@Column(name="amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name="amount2")
	public double getAmount2() {
		return amount2;
	}

	public void setAmount2(double amount2) {
		this.amount2 = amount2;
	}

	@Column(name="cash")
	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	@Column(name="cash2")
	public double getCash2() {
		return cash2;
	}

	public void setCash2(double cash2) {
		this.cash2 = cash2;
	}

	@Column(name="fxRate")
	public double getFxRate() {
		return fxRate;
	}

	public void setFxRate(double fxRate) {
		this.fxRate = fxRate;
	}

	@Column(name="fxRate2")
	public double getFxRate2() {
		return fxRate2;
	}

	public void setFxRate2(double fxRate2) {
		this.fxRate2 = fxRate2;
	}

	@Column(name="ledgerDate")
	public Date getLedgerDate() {
		return ledgerDate;
	}

	public void setLedgerDate(Date ledgerDate) {
		this.ledgerDate = ledgerDate;
	}

	@Column(name="postStatus")
	public String getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}

	@Column(name="price")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name="principal")
	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	@Column(name="principal2")
	public double getPrincipal2() {
		return principal2;
	}

	public void setPrincipal2(double principal2) {
		this.principal2 = principal2;
	}

	@Column(name="settleStatus")
	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

	@Column(name="settleDate")
	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	@Column(name="settleDate2")
	public Date getSettleDate2() {
		return settleDate2;
	}

	public void setSettleDate2(Date settleDate2) {
		this.settleDate2 = settleDate2;
	}

	@Column(name="tradeDate")
	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Column(name="tranType")
	public int getTranType() {
		return tranType;
	}

	public void setTranType(int tranType) {
		this.tranType = tranType;
	}

	@Column(name="bk_CashAcct_NO")
	public int getBk_CashAcct_NO() {
		return bk_CashAcct_NO;
	}

	public void setBk_CashAcct_NO(int bk_CashAcct_NO) {
		this.bk_CashAcct_NO = bk_CashAcct_NO;
	}

	@Column(name="bk_CashAcct_NO2")
	public int getBk_CashAcct_NO2() {
		return bk_CashAcct_NO2;
	}

	public void setBk_CashAcct_NO2(int bk_CashAcct_NO2) {
		this.bk_CashAcct_NO2 = bk_CashAcct_NO2;
	}

	@Column(name="cusip")
	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	@Column(name="cusip2")
	public String getCusip2() {
		return cusip2;
	}

	public void setCusip2(String cusip2) {
		this.cusip2 = cusip2;
	}

	@Column(name="currencyCode")
	public int getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(int currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Column(name="currencyCode2")
	public int getCurrencyCode2() {
		return currencyCode2;
	}

	public void setCurrencyCode2(int currencyCode2) {
		this.currencyCode2 = currencyCode2;
	}

	@Column(name="portfolio")
	public int getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(int portfolio) {
		this.portfolio = portfolio;
	}

	@Column(name="portfolio2")
	public int getPortfolio2() {
		return portfolio2;
	}

	public void setPortfolio2(int portfolio2) {
		this.portfolio2 = portfolio2;
	}

	@Column(name="altRate")
	public double getAltRate() {
		return altRate;
	}

	public void setAltRate(double altRate) {
		this.altRate = altRate;
	}

	@Column(name="altDate")
	public Date getAltDate() {
		return altDate;
	}

	public void setAltDate(Date altDate) {
		this.altDate = altDate;
	}

	@Column(name="intAtMat")
	public double getIntAtMat() {
		return intAtMat;
	}

	public void setIntAtMat(double intAtMat) {
		this.intAtMat = intAtMat;
	}

	@Column(name="glGroup")
	public int getGlGroup() {
		return glGroup;
	}

	public void setGlGroup(int glGroup) {
		this.glGroup = glGroup;
	}

	@Column(name="broker")
	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	@Column(name="settleCash")
	public double getSettleCash() {
		return settleCash;
	}

	public void setSettleCash(double settleCash) {
		this.settleCash = settleCash;
	}

	@Column(name="settleFxRate")
	public double getSettleFxRate() {
		return settleFxRate;
	}

	public void setSettleFxRate(double settleFxRate) {
		this.settleFxRate = settleFxRate;
	}

	@Column(name="settleCurrency")
	public int getSettleCurrency() {
		return settleCurrency;
	}

	public void setSettleCurrency(int settleCurrency) {
		this.settleCurrency = settleCurrency;
	}

	@Column(name="intent")
	public int getIntent() {
		return intent;
	}

	public void setIntent(int intent) {
		this.intent = intent;
	}

	@Column(name="tradeMarket")
	public int getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(int tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

	@Column(name="managerCode")
	public int getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(int managerCode) {
		this.managerCode = managerCode;
	}

	@Column(name="location")
	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Column(name="secAcctCode")
	public String getSecAcctCode() {
		return secAcctCode;
	}

	public void setSecAcctCode(String secAcctCode) {
		this.secAcctCode = secAcctCode;
	}

	@Column(name="secAcctCode2")
	public String getSecAcctCode2() {
		return secAcctCode2;
	}

	public void setSecAcctCode2(String secAcctCode2) {
		this.secAcctCode2 = secAcctCode2;
	}

	@Column(name="glAcct")
	public String getGlAcct() {
		return glAcct;
	}

	public void setGlAcct(String glAcct) {
		this.glAcct = glAcct;
	}

	@Column(name="tranDesc")
	public String getTranDesc() {
		return tranDesc;
	}

	public void setTranDesc(String tranDesc) {
		this.tranDesc = tranDesc;
	}

	@Column(name="divBasicDate")
	public Date getDivBasicDate() {
		return divBasicDate;
	}

	public void setDivBasicDate(Date divBasicDate) {
		this.divBasicDate = divBasicDate;
	}

	@Column(name="price2")
	public double getPrice2() {
		return price2;
	}

	public void setPrice2(double price2) {
		this.price2 = price2;
	}

	@Column(name="bankAcct")
	public String getBankAcct() {
		return bankAcct;
	}

	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}

	@Column(name="bankAcctName")
	public String getBankAcctName() {
		return bankAcctName;
	}

	public void setBankAcctName(String bankAcctName) {
		this.bankAcctName = bankAcctName;
	}

	@Column(name="currID")
	public int getCurrID() {
		return currID;
	}

	public void setCurrID(int currID) {
		this.currID = currID;
	}

	@Column(name="currCode")
	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	@Column(name="acctCodes")
	public String getAcctCode() {
		return acctCode;
	}

	public void setAcctCode(String acctCode) {
		this.acctCode = acctCode;
	}

	@Column(name="securityID")
	public String getSecurityID() {
		return securityID;
	}

	public void setSecurityID(String securityID) {
		this.securityID = securityID;
	}

	@Column(name="securityName")
	public String getSecurityName() {
		return securityName;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}

	@Column(name="assetType")
	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	@Column(name="tranTypeName")
	public String getTranTypeName() {
		return tranTypeName;
	}

	public void setTranTypeName(String tranTypeName) {
		this.tranTypeName = tranTypeName;
	}

	@Column(name="proceeds")
	public double getProceeds() {
		return proceeds;
	}

	public void setProceeds(double proceeds) {
		this.proceeds = proceeds;
	}

	@Column(name="cost")
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Column(name="costFunc")
	public double getCostFunc() {
		return costFunc;
	}

	public void setCostFunc(double costFunc) {
		this.costFunc = costFunc;
	}

	@Column(name="secMasterID", columnDefinition="int default 0")
	public int getSecMasterID() {
		return secMasterID;
	}

	public void setSecMasterID(int secMasterID) {
		this.secMasterID = secMasterID;
	}
}
