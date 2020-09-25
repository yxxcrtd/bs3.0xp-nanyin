package com.orm.obj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;


/** 
 * @ClassName 		TranSubRed
 * @author  		Pan Wei 
 * @Date 			2016年9月1日 上午10:54:57 	
 * @Description		[对于申购赎回交易，原始数据被记录在Tran_Basic表，二次计算数据则被保存在TranSubRed表]  
 */


@Entity 
@Table(name="JXT_TRAN_SUBRED") 
public class TranSubRed extends BasicEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tranid;
	private PortfolioHdr portfolio;
	private int classID;
	private TranBasic tanum;
	private Date tradeDate;
	private Date settlementDate;
	private Date effectiveDate;
	private Date ledgerin;
	private int ledgerReversed;
	private int tranType;
	private double amount;
	private double gCost;
	private double cash;
	private double nav;
	private double fxrate;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="TranSubRed_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="TRANID")
	public int getTranid() {
		return tranid;
	}
	public void setTranid(int tranid) {
		this.tranid = tranid;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PORTFOLIO")
	public PortfolioHdr getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(PortfolioHdr portfolio) {
		this.portfolio = portfolio;
	}
	
	@Column(name="CLASSID")
	public int getClassID() {
		return classID;
	}
	public void setClassID(int classID) {
		this.classID = classID;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TANUM")
	public TranBasic getTanum() {
		return tanum;
	}
	public void setTanum(TranBasic tanum) {
		this.tanum = tanum;
	}
	
	@Column(name="TRADEDATE")
	public Date getTradeDate() {
		return tradeDate;
	}
	
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	
	@Column(name="SETTLEMENTDATE")
	public Date getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	
	@Column(name="EFFECTIVEDATE")
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	@Column(name="LEDGERIN")
	public Date getLedgerin() {
		return ledgerin;
	}
	public void setLedgerin(Date ledgerin) {
		this.ledgerin = ledgerin;
	}
	
	@Column(name="LEDGERREVERSED")
	public int getLedgerReversed() {
		return ledgerReversed;
	}
	public void setLedgerReversed(int ledgerReversed) {
		this.ledgerReversed = ledgerReversed;
	}
	
	@Column(name="TRANTYPE")
	public int getTranType() {
		return tranType;
	}
	public void setTranType(int tranType) {
		this.tranType = tranType;
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
	
	@Column(name="NAV")
	public double getNav() {
		return nav;
	}
	public void setNav(double nav) {
		this.nav = nav;
	}
	
	@Column(name="FXRATE")
	public double getFxrate() {
		return fxrate;
	}
	public void setFxrate(double fxrate) {
		this.fxrate = fxrate;
	}
	
	@Column(name="gCost")
	public double getgCost() {
		return gCost;
	}
	public void setgCost(double gCost) {
		this.gCost = gCost;
	}
}
