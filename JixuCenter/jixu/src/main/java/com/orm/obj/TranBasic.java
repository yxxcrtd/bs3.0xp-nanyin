package com.orm.obj;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jixu.common.pojo.BasicEntity;
import com.jixu.common.type.LotSelectionType;
import com.jixu.common.type.TradeMarket;
import com.jixu.common.types.ValuationType;
import com.jixu.common.util.DateUtil;

/** 
 * @ClassName 		SmfDiv
 * @author  		Pan Wei 
 * @Date 			2017年8月10日 上午10:50:01 	
 * @Description		[Tran_Basic,Tran,Tran_Sum,Tran_Clo四张交易核心表。Tran_Basic是交易原始数据表;Tran是经过计算的交易数据，包括成本等信息;
 					Tran_Sum是最新的头寸表,包括数量、总成本等数据。Tran和Tran_Sum表示迭代运算的一个过程;Tran_Clo表示每个关帐日的头寸数据，包括数量、
 					市值、成本、应收利息等各项数据。]  
 */

@Entity 
@Table(name="JXT_TRAN_BASIC", 
		indexes={@Index(name="tranbasic_index1",columnList="Post_Status,TradeDate"),
		@Index(name="tranbasic_index2",columnList="Post_Status,TradeDate,Tanum"),
		@Index(name="tranbasic_index3",columnList="TranType,Tanum")}) 
public class TranBasic extends BasicEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// primary key
	private String id;

	/**
	 * 交易类型
	 */
	private int trantype;
	/**
	 * 交易日
	 */
	private Date tradedate;
	
	/**
	 * 现金结算日
	 */
	private Date settledate;
	
	/**
	 * 出账日
	 */
	private Date ledgerdate;
	
	/**
	 * 交易状态，是否已被提交。已提交 P /未提交 N/已删除 D
	 */
	private String postStatus;
	
	/**
	 * 该字段目前尚未使用
	 */
	private String settleStatus;
	
	/**
	 * 成交价格，该价格如果不提供，会被反算之后填入
	 */
	private double price;
	
	/**
	 * 成交数量
	 */
	private double amount;
	
	/**
	 * 交易金额，包括费用
	 */
	private double cash;
	
	/**
	 * 交易成本金额，不包括费用
	 */
	private double principal;
	
	/**
	 * 交易汇率
	 */
	private double fxrate;
	
	/**
	 * 空方数量
	 */
	private double amount2;
	
	/**
	 * 空方价格
	 */
	private double price2;
	
	/**
	 * 空方交易金额，包括费用
	 */
	private double cash2;
	
	/**
	 * 空方交易成本金额，不包括费用
	 */
	private double principal2;
	
	/**
	 * 空方交易汇率
	 */
	private double fxrate2;
	
	/**
	 * 证券账户/托管账户
	 */
	private String secacct;
	
	/**
	 * 空方证券账户/托管账户
	 */
	private String secacct2;
	
	/**
	 * 二级结算日期
	 */
	private Date settledate2;
	/**
	 * The coupon rate, if the security is a money market instrument. 
	 * The current coupon rate for floating rate and adjustable rate type securities. Otherwise not used.
	 * 1.如果是股票股利,则是分股比例
	 */
	private double altRate;

	/**
	 * The maturity date, if the security is a money market instrument; otherwise not used.
	 * 到期日，比如逆回购的到期日
	 */
	private Date altDate;
	
	/**
	 * The amount of interest received at maturity, for applicable security types
	 * 1.购买债券时,接手过来的利息
	 * 2.卖出回购债券等,获取的利息
	 */
	private double intatmat;
	
	/**
	 * 是否按比例结清所有利息
	 */
	private boolean setAllIntatmatFlag;
	
	/**
	 * 交易使用的总账组，换汇等交易会被用到
	 */
	private int glGroup;
	
	/**
	 * 估值方法, 1.公允价值法/2.摊余成本法
	 */
	private int valuationType;
	
	/**
	 * 合同编号
	 */
	private String contract;

	// many to one
	/**
	 * 证券
	 */
	private IssueInfo cusip;
	
	/**
	 * 产品/投资组合
	 */
	private String portfolio;
	
	/**
	 * 银行现金账户
	 */
	private BkCashAcct bkCashacctNo;
	
	/**
	 * 空方证券
	 */
	private IssueInfo cusip2;
	
	/**
	 * 空方产品/投资组合
	 */
	private String portfolio2;
	
	/**
	 * 空方银行现金账户
	 */
	private BkCashAcct bkCashacctNo2;
	/**
	 * The amount of interest received at maturity, for applicable security types
	 * 1.购买债券时,接手过来的利息
	 * 2.卖出回购债券等,获取的利息
	 */
	private double intatmat2;
	
	/**
	 * 是否按比例结清空方利率
	 */
	private boolean setAllIntatmatFlag2;
	
	/**
	 * 导入类型
	 * 1. 手工导入
	 * 2. 接口导入
	 * 3. 自动导入 比如自动结息等交易
	 */
	private int importType;

	// collections
	private Set<Tran> tran;
	private Set<TranBasicFee> tranBasicFees;
	private Set<TranSubRed> tranSubRed;
	private Set<TranCollateral> tranCollaterals;
	private Set<BkInteSetHis> InteSetHis;
	
	/**
	 * 货币
	 */
	private String fxcurrency;
	
	/**
	 *空方货币
	 */
	private String fxcurrency2;
	
	/**
	 * 券商
	 */
	private String broker;
	
	/**
	 * 结算货币
	 */
	private String settleCurrency;
	
	/**
	 * 结算汇率
	 */
	private double settleFxRate;
	
	/**
	 * 结算金额
	 */
	private double settleCash;
	
	/**
	 * 空方结算货币
	 */
	private String settleCurrency2;
	
	/**
	 * 空方汇率
	 */
	private double settleFxRate2;
	
	/**
	 * 空方结算金额
	 */
	private double settleCash2;
	
	/**
	 * 投资经理
	 */
	private int manager;
	
	/**
	 * 交易市场 1/2级市场
	 */
	private int tradeMarket;
	
	/**
	 * 目前用于现金冲销,指定和银行现金对冲的科目
	 */
	private String glacct;
	
	/**
	 * 交易的描述
	 */
	private String tranDesc;
	
	/**
	 * 用什么方法卖出存在的lot
	 * 参考 LotSelectionType
	 */
	private int hiloCode;
	
	/**
	 * 如果hiloCode选择了特定，则在这里指定哪个lot
	 */
	private int specificLotID;

	public TranBasic() {
		this.setPortfolio("");
		this.setPortfolio2("");
		this.setDivBasicDate(DateUtil.convertStr2Date("2000-01-01"));
		this.setSettledate2(DateUtil.convertStr2Date("2000-01-01"));
		this.setAltDate(DateUtil.convertStr2Date("2000-01-01"));
		this.setValuationType(ValuationType.FV);
		this.setTradeMarket(TradeMarket.level1);
		this.setHiloCode(LotSelectionType.MoveAvgStatCost);
		this.setContract("");
		this.setTranDesc("");
		this.setSecacct("0");
		this.setSecacct2("0");
	}
	
	/**
	 * 用与股权登记日
	 */
	private Date divBasicDate;

	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.func.GenCustomTBID_Joyin", parameters={
			@Parameter(name = "seq_name", value = "trade_id_sequence"),
			@Parameter(name = "short_name", value = "TD")})
	@Column(name="TANUM")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="TRANTYPE")
	public int getTrantype() {
		return trantype;
	}

	public void setTrantype(int trantype) {
		this.trantype = trantype;
	}

	@Column(name="TRADEDATE")
	public Date getTradedate() {
		return tradedate;
	}

	public void setTradedate(Date tradedate) {
		this.tradedate = tradedate;
	}

	@Column(name="SETTLEDATE")
	public Date getSettledate() {
		return settledate;
	}

	public void setSettledate(Date settledate) {
		this.settledate = settledate;
	}

	@Column(name="LEDGERDATE")
	public Date getLedgerdate() {
		return ledgerdate;
	}

	public void setLedgerdate(Date ledgerdate) {
		this.ledgerdate = ledgerdate;
	}

	@Column(name="POST_STATUS")
	public String getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}

	@Column(name="SETTLE_STATUS")
	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

	@Column(name="PRICE")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name="AMOUNT")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name="CASH")
	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	@Column(name="PRINCIPAL")
	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	@Column(name="FXRATE")
	public double getFxrate() {
		return fxrate;
	}

	public void setFxrate(double fxrate) {
		this.fxrate = fxrate;
	}

	@Column(name="AMOUNT2")
	public double getAmount2() {
		return amount2;
	}

	public void setAmount2(double amount2) {
		this.amount2 = amount2;
	}

	@Column(name="CASH2")
	public double getCash2() {
		return cash2;
	}

	public void setCash2(double cash2) {
		this.cash2 = cash2;
	}

	@Column(name="PRINCIPAL2")
	public double getPrincipal2() {
		return principal2;
	}

	public void setPrincipal2(double principal2) {
		this.principal2 = principal2;
	}

	@Column(name="FXRATE2")
	public double getFxrate2() {
		return fxrate2;
	}

	public void setFxrate2(double fxrate2) {
		this.fxrate2 = fxrate2;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSIP")
	public IssueInfo getCusip() {
		return cusip;
	}

	/**
	 * 设值证券
	 * @param cusip
	 */
	public void setCusip(IssueInfo cusip) {
		this.cusip = cusip;
	}
	
	/**
	 * 获取产品/投资组合
	 * @return
	 */
	@Column(name="Portfolio")
	public String getPortfolio() {
		return portfolio;
	}

	/**
	 * 设值产品/投资组合
	 * @param portfolio
	 */
	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}

	/**
	 * 获取银行现金账户
	 * @return
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BK_CASHACCT_NO")
	public BkCashAcct getBkCashacctNo() {
		return bkCashacctNo;
	}

	/**
	 * 设值银行现金账户
	 * @param bkCashacctNo
	 */
	public void setBkCashacctNo(BkCashAcct bkCashacctNo) {
		this.bkCashacctNo = bkCashacctNo;
	}

	/**
	 * 获取空方证券
	 * @return
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSIP2")
	public IssueInfo getCusip2() {
		return cusip2;
	}

	/**
	 * 设值空方证券
	 * @param cusip2
	 */
	public void setCusip2(IssueInfo cusip2) {
		this.cusip2 = cusip2;
	}

	/**
	 * 获取空方产品/投资组合
	 * @return
	 */
	@Column(name="Portfolio2")
	public String getPortfolio2() {
		return portfolio2;
	}

	/**
	 * 设值空方产品/投资组合
	 * @param portfolio2
	 */
	public void setPortfolio2(String portfolio2) {
		this.portfolio2 = portfolio2;
	}

	/**
	 * 获取空方银行现金账户
	 * @return
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BK_CASHACCT_NO2")
	public BkCashAcct getBkCashacctNo2() {
		return bkCashacctNo2;
	}

	/**
	 * 设值银行现金账户
	 * @param bkCashacctNo2
	 */
	public void setBkCashacctNo2(BkCashAcct bkCashacctNo2) {
		this.bkCashacctNo2 = bkCashacctNo2;
	}
	
	@OneToMany(mappedBy = "tanum")
	public java.util.Set<Tran> getTran() {
		return tran;
	}

	public void setTran(java.util.Set<Tran> tran) {
		this.tran = tran;
	}

	@OneToMany(mappedBy = "tanum")
	public Set<TranBasicFee> getTranBasicFees() {
		return tranBasicFees;
	}

	public void setTranBasicFees(Set<TranBasicFee> tranBasicFees) {
		this.tranBasicFees = tranBasicFees;
	}

	@OneToMany(mappedBy = "tanum")
	public Set<TranSubRed> getTranSubRed() {
		return tranSubRed;
	}

	public void setTranSubRed(Set<TranSubRed> tranSubRed) {
		this.tranSubRed = tranSubRed;
	}

	@Column(name="CURRENCYCODE")
	public String getFxcurrency() {
		return fxcurrency;
	}

	public void setFxcurrency(String fxcurrency) {
		this.fxcurrency = fxcurrency;
	}

	@Column(name="ALTRATE")
	public double getAltRate() {
		return altRate;
	}

	public void setAltRate(double altRate) {
		this.altRate = altRate;
	}

	@Column(name="ALTDATE")
	public Date getAltDate() {
		return altDate;
	}

	public void setAltDate(Date altDate) {
		this.altDate = altDate;
	}

	@Column(name="INTATMAT")
	public double getIntatmat() {
		return intatmat;
	}
	
	@Column(name="INTATMAT2")
	public double getIntatmat2() {
		return intatmat2;
	}

	public void setIntatmat2(double intatmat2) {
		this.intatmat2 = intatmat2;
	}

	public void setIntatmat(double intatmat) {
		this.intatmat = intatmat;
	}

	@Column(name="CURRENCYCODE2")
	public String getFxcurrency2() {
		return fxcurrency2;
	}

	public void setFxcurrency2(String fxcurrency2) {
		this.fxcurrency2 = fxcurrency2;
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

	@Column(name="SETTLECURRENCY")
	public String getSettleCurrency() {
		return settleCurrency;
	}

	public void setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
	}

	@Column(name="settleFxRate")
	public double getSettleFxRate() {
		return settleFxRate;
	}

	public void setSettleFxRate(double settleFxRate) {
		this.settleFxRate = settleFxRate;
	}

	@Column(name="settleCash")
	public double getSettleCash() {
		return settleCash;
	}

	public void setSettleCash(double settleCash) {
		this.settleCash = settleCash;
	}

	@Column(name="ManagerCode")
	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	@Column(name="SecAcctCode")
	public String getSecacct() {
		return secacct;
	}

	public void setSecacct(String secacct) {
		this.secacct = secacct;
	}

	@Column(name="valuationType")
	public int getValuationType() {
		return valuationType;
	}

	public void setValuationType(int valuationType) {
		this.valuationType = valuationType;
	}

	@Column(name="tradeMarket")
	public int getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(int tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

	@Column(name="glacct")
	public String getGlacct() {
		return glacct;
	}

	public void setGlacct(String glacct) {
		this.glacct = glacct;
	}

	@Column(name="tranDesc")
	public String getTranDesc() {
		return tranDesc;
	}

	public void setTranDesc(String tranDesc) {
		this.tranDesc = tranDesc;
	}

	@OneToMany(mappedBy = "tanum")
	public Set<TranCollateral> getTranCollaterals() {
		return tranCollaterals;
	}

	public void setTranCollaterals(Set<TranCollateral> tranCollaterals) {
		this.tranCollaterals = tranCollaterals;
	}

	@OneToMany(mappedBy = "tanum")
	public Set<BkInteSetHis> getInteSetHis() {
		return InteSetHis;
	}

	public void setInteSetHis(Set<BkInteSetHis> inteSetHis) {
		InteSetHis = inteSetHis;
	}

	@Column(name="SecAcctCode2")
	public String getSecacct2() {
		return secacct2;
	}

	public void setSecacct2(String secacct2) {
		this.secacct2 = secacct2;
	}

	@Column(name="DivBasicDate")
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

	@Column(name="SettleDate2")
	public Date getSettledate2() {
		return settledate2;
	}

	public void setSettledate2(Date settledate2) {
		this.settledate2 = settledate2;
	}

	@Column(name="contract")
	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	@Column(name="hiloCode")
	public int getHiloCode() {
		return hiloCode;
	}

	public void setHiloCode(int hiloCode) {
		this.hiloCode = hiloCode;
	}

	@Column(name="specificLotID")
	public int getSpecificLotID() {
		return specificLotID;
	}

	public void setSpecificLotID(int specificLotID) {
		this.specificLotID = specificLotID;
	}

	@Column(name="SETTLECURRENCY2")
	public String getSettleCurrency2() {
		return settleCurrency2;
	}

	public void setSettleCurrency2(String settleCurrency2) {
		this.settleCurrency2 = settleCurrency2;
	}

	@Column(name="settleFxRate2")
	public double getSettleFxRate2() {
		return settleFxRate2;
	}

	public void setSettleFxRate2(double settleFxRate2) {
		this.settleFxRate2 = settleFxRate2;
	}

	@Column(name="settleCash2")
	public double getSettleCash2() {
		return settleCash2;
	}

	public void setSettleCash2(double settleCash2) {
		this.settleCash2 = settleCash2;
	}

	@Column(name="IMPORTTYPE")
	public int getImportType() {
		return importType;
	}

	public void setImportType(int importType) {
		this.importType = importType;
	}

	@Column(name="SetAllIntatmatFlag")
	public boolean isSetAllIntatmatFlag() {
		return setAllIntatmatFlag;
	}

	public void setSetAllIntatmatFlag(boolean setAllIntatmatFlag) {
		this.setAllIntatmatFlag = setAllIntatmatFlag;
	}

	@Column(name="SetAllIntatmatFlag2")
	public boolean isSetAllIntatmatFlag2() {
		return setAllIntatmatFlag2;
	}

	public void setSetAllIntatmatFlag2(boolean setAllIntatmatFlag2) {
		this.setAllIntatmatFlag2 = setAllIntatmatFlag2;
	}


}
