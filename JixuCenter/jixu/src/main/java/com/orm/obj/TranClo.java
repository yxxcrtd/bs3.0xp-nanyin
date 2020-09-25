package com.orm.obj;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		SmfDiv
 * @author  		Pan Wei 
 * @Date 			2017年8月10日 上午10:50:01 	
 * @Description		[Tran_Basic,Tran,Tran_Sum,Tran_Clo四张交易核心表。Tran_Basic是交易原始数据表;Tran是经过计算的交易数据，包括成本等信息;
 					Tran_Sum是最新的头寸表,包括数量、总成本等数据。Tran和Tran_Sum表示迭代运算的一个过程;Tran_Clo表示每个关帐日的头寸数据，包括数量、
 					市值、成本、应收利息等各项数据。]  
 */

@Entity
@Table(name = "JXT_TRAN_CLO", 
	indexes={@Index(name="tranclo_index1",columnList="Portfolio,CloseDate"),
		@Index(name="tranclo_index2",columnList="Portfolio,CloseDate,Cusip"),
		@Index(name="tranclo_index3",columnList="Portfolio,CloseDate,Cusip,Lotnum")})
public class TranClo extends BasicEntity implements Serializable{

	private static final long serialVersionUID = -9013692044800819612L;

	// primary key
	private String id;

	// fields
	private String cusip;
	private String portfolio;
	private int lotnum;
	private int lstTrannum;
	private double price;
	private double amount;
	private double gCost;
	private double gCostFunc;
	private double gMkValue;

	// 用于计息
	private double accrued;
	private double prinFxRate;
	private double incFxRate;

	private Date closeDate;
	private double altRate;
	private Date lsttradeDate;
	
	private Date lstSettledate;
	/*
	 * 最后一笔接转日期
	 */
	private java.sql.Date altDate;
	
	private int managerID;
	private String secacct;
	private int lsindicator;
	//摊余成本价值
	private double amrValue;
	
	//本期的期初摊余成本
	private double cycleStartAmrValue;
	
	private Set<TranFeeFollowClo> tranFeeFollowCloSet;
	
	/**
	 * 最后激活的日期
	 * 在债券里,卖空之后可能还会计息,等计息结束,再通过判断这个日期参数,决定是否关闭交易
	 */
	private Date lastActivetyDate;
	
	private double todayInterest;
	
	private int valuationType;
	
	public TranClo() {
		this.setPortfolio("");
		this.setSecacct("0");
	}
	
	@Id
//	@SequenceGenerator(name="seq_generator", sequenceName="TranClo_ID_SEQ", allocationSize=1, initialValue=1)
//	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.func.GenCustomTBID_Joyin", parameters={
			@Parameter(name = "seq_name", value = "TranClo_ID_SEQ"),
			@Parameter(name = "short_name", value = "PO")})
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "CUSIP")
	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	@Column(name = "PORTFOLIO")
	public String getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}

	@Column(name = "LOTNUM")
	public int getLotnum() {
		return lotnum;
	}

	public void setLotnum(int lotnum) {
		this.lotnum = lotnum;
	}

	@Column(name = "LSTTRANNUM")
	public int getLstTrannum() {
		return lstTrannum;
	}

	public void setLstTrannum(int lstTrannum) {
		this.lstTrannum = lstTrannum;
	}

	@Column(name = "AMOUNT")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name = "GCOST")
	public double getgCost() {
		return gCost;
	}

	public void setgCost(double gCost) {
		this.gCost = gCost;
	}

	@Column(name = "GMKVALUE")
	public double getgMkValue() {
		return gMkValue;
	}

	public void setgMkValue(double gMkValue) {
		this.gMkValue = gMkValue;
	}

	@Column(name = "ACCRUED")
	public double getAccrued() {
		return accrued;
	}

	public void setAccrued(double accrued) {
		this.accrued = accrued;
	}

	@Column(name = "PRINFXRATE")
	public double getPrinFxRate() {
		return prinFxRate;
	}

	public void setPrinFxRate(double prinFxRate) {
		this.prinFxRate = prinFxRate;
	}

	@Column(name = "INCFXRATE")
	public double getIncFxRate() {
		return incFxRate;
	}

	public void setIncFxRate(double incFxRate) {
		this.incFxRate = incFxRate;
	}

	@Column(name = "CloseDate")
	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	@Column(name = "gCostFunc")
	public double getgCostFunc() {
		return gCostFunc;
	}

	public void setgCostFunc(double gCostFunc) {
		this.gCostFunc = gCostFunc;
	}

	@Column(name = "lsttradeDate")
	public Date getLsttradeDate() {
		return lsttradeDate;
	}

	public void setLsttradeDate(Date lsttradeDate) {
		this.lsttradeDate = lsttradeDate;
	}

	@Column(name = "AltRate")
	public double getAltRate() {
		return altRate;
	}

	public void setAltRate(double altRate) {
		this.altRate = altRate;
	}

	@Column(name = "AltDate")
	public java.sql.Date getAltDate() {
		return altDate;
	}

	public void setAltDate(java.sql.Date altDate) {
		this.altDate = altDate;
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

	@Column(name="lsindicator")
	public int getLsindicator() {
		return lsindicator;
	}

	public void setLsindicator(int lsindicator) {
		this.lsindicator = lsindicator;
	}

	@Column(name="lastActivetyDate")
	public Date getLastActivetyDate() {
		return lastActivetyDate;
	}

	public void setLastActivetyDate(Date lastActivetyDate) {
		this.lastActivetyDate = lastActivetyDate;
	}

	@Column(name="price")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name="amrValue")
	public double getAmrValue() {
		return amrValue;
	}

	public void setAmrValue(double amrValue) {
		this.amrValue = amrValue;
	}
	
	@Column(name="valuationType")
	public int getValuationType() {
		return valuationType;
	}

	public void setValuationType(int valuationType) {
		this.valuationType = valuationType;
	}

	@OneToMany(mappedBy = "tranClo", cascade=CascadeType.ALL)
	public Set<TranFeeFollowClo> getTranFeeFollowCloSet() {
		return tranFeeFollowCloSet;
	}

	public void setTranFeeFollowCloSet(Set<TranFeeFollowClo> tranFeeFollowCloSet) {
		this.tranFeeFollowCloSet = tranFeeFollowCloSet;
	}

	@Column(name="todayInterest")
	public double getTodayInterest() {
		return todayInterest;
	}

	public void setTodayInterest(double todayInterest) {
		this.todayInterest = todayInterest;
	}

	@Column(name="lstSettledate")
	public Date getLstSettledate() {
		return lstSettledate;
	}

	public void setLstSettledate(Date lstSettledate) {
		this.lstSettledate = lstSettledate;
	}

	@Column(name="cycleStartAmrValue")
	public double getCycleStartAmrValue() {
		return cycleStartAmrValue;
	}

	public void setCycleStartAmrValue(double cycleStartAmrValue) {
		this.cycleStartAmrValue = cycleStartAmrValue;
	}
}
