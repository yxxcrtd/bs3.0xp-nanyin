package com.orm.obj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.jixu.common.pojo.BasicEntity;
import com.jixu.common.type.LongShortType;
import com.jixu.common.types.ValuationType;

/** 
 * @ClassName 		SmfDiv
 * @author  		Pan Wei 
 * @Date 			2017年8月10日 上午10:50:01 	
 * @Description		[Tran_Basic,Tran,Tran_Sum,Tran_Clo四张交易核心表。Tran_Basic是交易原始数据表;Tran是经过计算的交易数据，包括成本等信息;
 					Tran_Sum是最新的头寸表,包括数量、总成本等数据。Tran和Tran_Sum表示迭代运算的一个过程;Tran_Clo表示每个关帐日的头寸数据，包括数量、
 					市值、成本、应收利息等各项数据。]  
 */

@Entity 
@Table(name="JXT_TRAN", 
	indexes={@Index(name="tran_index1",columnList="Portfolio,Cusip"),
		@Index(name="tran_index2",columnList="Portfolio,Cusip,Lotnum"),
		@Index(name="tran_index3",columnList="Portfolio,Cusip,Trade_Date"),
		@Index(name="tran_index4",columnList="Portfolio,Cusip,Ledger_Date"),
		@Index(name="tran_index5",columnList="Portfolio,Cusip,Lotnum,Ledger_Date"),
		@Index(name="tran_index6",columnList="Portfolio,Cusip,Settle_Date")},
		uniqueConstraints={@UniqueConstraint(name="tran_unique1",columnNames={"Portfolio","Cusip","Lotnum","trannum"})})
public class Tran extends BasicEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// primary key
	private int id;

	// fields
	private int lotnum;
	private int trannum;
	private int trantype;
	private Date tradeDate;
	private Date settleDate;
	private Date ledgerDate;
	private double amount;
	//用于卖出交易时,卖出数量的成本是多少 The total proceeds of a disposal transaction
	private double proceeds;
	private double prinFxrate;
	private double incFxrate;
	private double gCost;
	private double gCostFunc;
	private double gOriginalamt;
	
	private double amount2;
	private double proceeds2;
	private double prinFxrate2;
	private double incFxrate2;
	private double gCost2;
	private double gCostFunc2;
	private double gOriginalamt2;
	private int managerID;

	// many to one
	private IssueInfo cusip;
	private String portfolio;
	private TranBasic tanum;
	//利息收入 债券使用
	private double intps;
	
	//证券帐号
	private String secacct;
	
	/**
	 * long还是short。做多做空
	 */
	private int lsindicator;
	
	/**
	 * 估值方法
	 */
	private int valuationType;

	/**
	 * 是否结清所有
	 */
	private boolean setAllIntpsFlag;
	
	public Tran() {
		this.setPortfolio("");
		this.setLsindicator(LongShortType.longTerm);
		this.setValuationType(ValuationType.FV);
		this.setSecacct("0");
	}
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="TRAN_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="LOTNUM")
	public int getLotnum() {
		return lotnum;
	}

	public void setLotnum(int lotnum) {
		this.lotnum = lotnum;
	}

	@Column(name="TRANNUM")
	public int getTrannum() {
		return trannum;
	}

	public void setTrannum(int trannum) {
		this.trannum = trannum;
	}

	@Column(name="TRANTYPE")
	public int getTrantype() {
		return trantype;
	}

	public void setTrantype(int trantype) {
		this.trantype = trantype;
	}

	@Column(name="TRADE_DATE")
	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Column(name="SETTLE_DATE")
	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	@Column(name="LEDGER_DATE")
	public Date getLedgerDate() {
		return ledgerDate;
	}

	public void setLedgerDate(Date ledgerDate) {
		this.ledgerDate = ledgerDate;
	}

	@Column(name="AMOUNT")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Column(name="PROCEEDS")
	public double getProceeds() {
		return proceeds;
	}

	public void setProceeds(double proceeds) {
		this.proceeds = proceeds;
	}

	@Column(name="PRIN_FXRATE")
	public double getPrinFxrate() {
		return prinFxrate;
	}

	public void setPrinFxrate(double prinFxrate) {
		this.prinFxrate = prinFxrate;
	}

	@Column(name="INC_FXRATE")
	public double getIncFxrate() {
		return incFxrate;
	}

	public void setIncFxrate(double incFxrate) {
		this.incFxrate = incFxrate;
	}

	@Column(name="G_COST")
	public double getgCost() {
		return gCost;
	}

	public void setgCost(double gCost) {
		this.gCost = gCost;
	}

	@Column(name="G_COSTFUNC")
	public double getgCostFunc() {
		return gCostFunc;
	}

	public void setgCostFunc(double gCostFunc) {
		this.gCostFunc = gCostFunc;
	}


	@Column(name="G_ORIGINALAMT")
	public double getgOriginalamt() {
		return gOriginalamt;
	}

	public void setgOriginalamt(double gOriginalamt) {
		this.gOriginalamt = gOriginalamt;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSIP")
	public IssueInfo getCusip() {
		return cusip;
	}

	public void setCusip(IssueInfo cusip) {
		this.cusip = cusip;
	}

	@Column(name="Portfolio")
	public String getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TANUM")
	public TranBasic getTanum() {
		return tanum;
	}

	public void setTanum(TranBasic tanum) {
		this.tanum = tanum;
	}

	@Column(name="amount2")
	public double getAmount2() {
		return amount2;
	}

	public void setAmount2(double amount2) {
		this.amount2 = amount2;
	}

	@Column(name="proceeds2")
	public double getProceeds2() {
		return proceeds2;
	}

	public void setProceeds2(double proceeds2) {
		this.proceeds2 = proceeds2;
	}

	@Column(name="prinFxrate2")
	public double getPrinFxrate2() {
		return prinFxrate2;
	}

	public void setPrinFxrate2(double prinFxrate2) {
		this.prinFxrate2 = prinFxrate2;
	}

	@Column(name="incFxrate2")
	public double getIncFxrate2() {
		return incFxrate2;
	}

	public void setIncFxrate2(double incFxrate2) {
		this.incFxrate2 = incFxrate2;
	}

	@Column(name="gCost2")
	public double getgCost2() {
		return gCost2;
	}

	public void setgCost2(double gCost2) {
		this.gCost2 = gCost2;
	}

	@Column(name="gCostFunc2")
	public double getgCostFunc2() {
		return gCostFunc2;
	}

	public void setgCostFunc2(double gCostFunc2) {
		this.gCostFunc2 = gCostFunc2;
	}

	@Column(name="gOriginalamt2")
	public double getgOriginalamt2() {
		return gOriginalamt2;
	}

	public void setgOriginalamt2(double gOriginalamt2) {
		this.gOriginalamt2 = gOriginalamt2;
	}

	@Column(name="intps")
	public double getIntps() {
		return intps;
	}

	public void setIntps(double intps) {
		this.intps = intps;
	}

	@Column(name="secacct")
	public String getSecacct() {
		return secacct;
	}

	public void setSecacct(String secacct) {
		this.secacct = secacct;
	}

	@Column(name="managerID")
	public int getManagerID() {
		return managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}

	@Column(name="Lsindicator")
	public int getLsindicator() {
		return lsindicator;
	}

	public void setLsindicator(int lsindicator) {
		this.lsindicator = lsindicator;
	}
	
	@Column(name="valuationType")
	public int getValuationType() {
		return valuationType;
	}

	public void setValuationType(int valuationType) {
		this.valuationType = valuationType;
	}

	@Column(name="SETALLINTPSFLAG")
	public boolean isSetAllIntpsFlag() {
		return setAllIntpsFlag;
	}

	public void setSetAllIntpsFlag(boolean setAllIntpsFlag) {
		this.setAllIntpsFlag = setAllIntpsFlag;
	}
}
