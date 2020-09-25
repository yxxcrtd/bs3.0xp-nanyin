package com.orm.obj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		TranSum
 * @author  		Pan Wei 
 * @Date 			2017年8月10日 上午10:50:01 	
 * @Description		[Tran_Basic,Tran,Tran_Sum,Tran_Clo四张交易核心表。Tran_Basic是交易原始数据表;Tran是经过计算的交易数据，包括成本等信息;
 					Tran_Sum是最新的头寸表,包括数量、总成本等数据。Tran和Tran_Sum表示迭代运算的一个过程;Tran_Clo表示每个关帐日的头寸数据，包括数量、
 					市值、成本、应收利息等各项数据。]  
 */

@Entity 
@Table(name="JXT_TRAN_SUM_RECORD") 
public class TranSumRecord extends BasicEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private Date ledgerDate;
	
	// fields
	private int lotnum;
	private int lstTrannum;
	private String posclosed;
	private double amount;
	private Date lsttradeDate;
	private Date lstSettledate;
	private Date lstLedgerdate;
	private double gCost;
	private double gCostfunc;
	private double amount2;
	private double gCost2;
	private double gCostfunc2;

	//是否为换汇/互换
	private int fxTranFlg;
	
	// many to one
	private String cusip;
	private String portfolio;
	private String secacct;
	
	/**
	 * long还是short。做多做空
	 */
	private int lsindicator;
	private double altRate;
	private Date altDate;
	private int managerID;
	
	/**
	 * 最后激活的日期
	 * 在债券里,卖空之后可能还会计息,等计息结束,再通过判断这个日期参数,决定是否关闭交易
	 */
	private Date lastActivetyDate;

	/**
	 * 估值方法
	 */
	private int valuationType;

	@Id
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

	@Column(name="LST_TRANNUM")
	public int getLstTrannum() {
		return lstTrannum;
	}

	public void setLstTrannum(int lstTrannum) {
		this.lstTrannum = lstTrannum;
	}

	@Column(name="POSCLOSED")
	public String getPosclosed() {
		return posclosed;
	}

	public void setPosclosed(String posclosed) {
		this.posclosed = posclosed;
	}

	@Column(name="AMOUNT")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name="LSTTRADE_DATE")
	public Date getLsttradeDate() {
		return lsttradeDate;
	}

	public void setLsttradeDate(Date lsttradeDate) {
		this.lsttradeDate = lsttradeDate;
	}

	@Column(name="LST_SETTLEDATE")
	public Date getLstSettledate() {
		return lstSettledate;
	}

	public void setLstSettledate(Date lstSettledate) {
		this.lstSettledate = lstSettledate;
	}

	@Column(name="LST_LEDGERDATE")
	public Date getLstLedgerdate() {
		return lstLedgerdate;
	}

	public void setLstLedgerdate(Date lstLedgerdate) {
		this.lstLedgerdate = lstLedgerdate;
	}

	@Column(name="G_COST")
	public double getgCost() {
		return gCost;
	}

	public void setgCost(double gCost) {
		this.gCost = gCost;
	}

	@Column(name="G_COSTFUNC")
	public double getgCostfunc() {
		return gCostfunc;
	}

	public void setgCostfunc(double gCostfunc) {
		this.gCostfunc = gCostfunc;
	}

	@Column(name="CUSIP")
	public String getCusip() {
		return cusip;
	}

	

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	@Column(name="Portfolio")
	public String getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
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

	@Column(name="amount2")
	public double getAmount2() {
		return amount2;
	}

	public void setAmount2(double amount2) {
		this.amount2 = amount2;
	}

	@Column(name="gCost2")
	public double getgCost2() {
		return gCost2;
	}

	public void setgCost2(double gCost2) {
		this.gCost2 = gCost2;
	}

	@Column(name="gCostfunc2")
	public double getgCostfunc2() {
		return gCostfunc2;
	}

	public void setgCostfunc2(double gCostfunc2) {
		this.gCostfunc2 = gCostfunc2;
	}

	@Column(name="fxTranFlg")
	public int getFxTranFlg() {
		return fxTranFlg;
	}

	public void setFxTranFlg(int fxTranFlg) {
		this.fxTranFlg = fxTranFlg;
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

	@Column(name = "LastActivetyDate")
	public Date getLastActivetyDate() {
		return lastActivetyDate;
	}

	public void setLastActivetyDate(Date lastActivetyDate) {
		this.lastActivetyDate = lastActivetyDate;
	}

	@Column(name="valuationType")
	public int getValuationType() {
		return valuationType;
	}

	public void setValuationType(int valuationType) {
		this.valuationType = valuationType;
	}

	@Column(name="LEDGERDATE")
	public Date getLedgerDate() {
		return ledgerDate;
	}

	public void setLedgerDate(Date ledgerDate) {
		this.ledgerDate = ledgerDate;
	}
}
