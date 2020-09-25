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
 * @ClassName 		TranCollateral
 * @author  		Pan Wei 
 * @Date 			2016年8月23日 下午11:45:20 	
 * @Description		[证券抵押物交易的Tran表]  
 */

@Entity 
@Table(name="JXT_Tran_Collateral") 
public class TranCollateral extends BasicEntity implements Serializable{

	private static final long serialVersionUID = -4465660237766820129L;
	private int id;
	private IssueInfo cusip;
	private PortfolioHdr portfolio;
	private int collateralNum;
	private int tranNum;
	private String location;
	private TranBasic tanum;
	/**
	 * A code indicating the type of transaction
	 * 100 - Receive Collateral
	 * 200 - Deliver Collateral
	 */
	private int tranType;
	private double amount;
	private Date tradeDate;
	private Date settleDate;
	private int effectiveIn;
	private int ledgerIn;
	private int ledgerReversed;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="TranCollateral_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cusip")
	public IssueInfo getCusip() {
		return cusip;
	}
	public void setCusip(IssueInfo cusip) {
		this.cusip = cusip;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="portfolio")
	public PortfolioHdr getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(PortfolioHdr portfolio) {
		this.portfolio = portfolio;
	}
	
	@Column(name="collateralNum")
	public int getCollateralNum() {
		return collateralNum;
	}
	
	public void setCollateralNum(int collateralNum) {
		this.collateralNum = collateralNum;
	}
	
	@Column(name="tranNum")
	public int getTranNum() {
		return tranNum;
	}
	public void setTranNum(int tranNum) {
		this.tranNum = tranNum;
	}
	
	@Column(name="location")
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tanum")
	public TranBasic getTanum() {
		return tanum;
	}
	
	public void setTanum(TranBasic tanum) {
		this.tanum = tanum;
	}
	
	@Column(name="tranType")
	public int getTranType() {
		return tranType;
	}
	public void setTranType(int tranType) {
		this.tranType = tranType;
	}
	
	@Column(name="amount")
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Column(name="tradeDate")
	public Date getTradeDate() {
		return tradeDate;
	}
	
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	
	@Column(name="settleDate")
	public Date getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}
	
	@Column(name="effectiveIn")
	public int getEffectiveIn() {
		return effectiveIn;
	}
	public void setEffectiveIn(int effectiveIn) {
		this.effectiveIn = effectiveIn;
	}
	
	@Column(name="ledgerIn")
	public int getLedgerIn() {
		return ledgerIn;
	}
	public void setLedgerIn(int ledgerIn) {
		this.ledgerIn = ledgerIn;
	}
	
	@Column(name="ledgerReversed")
	public int getLedgerReversed() {
		return ledgerReversed;
	}
	public void setLedgerReversed(int ledgerReversed) {
		this.ledgerReversed = ledgerReversed;
	}
}
