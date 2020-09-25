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

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		BkCashFlow
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[实收实付表，现金的结转都被记录在该表]  
 */

@Entity 
@Table(name="JXB_BK_CASH_FLOW", 
	indexes={@Index(name="bkcashflow_index1",columnList="Portfolio,Ledger"), 
		@Index(name="bkcashflow_index2",columnList="bankcashdate,Portfolio,Zero"),
		@Index(name="bkcashflow_index3",columnList="Ledger,BK_ACCT_NO,Portfolio")})
public class BkCashFlow extends BasicEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	// primary key
	private int id;

	// fields
	private PortfolioHdr portfolio;
	private int zero;
	private String dbcr;
	private Date bankcashdate;
	private double origbal;
	private double remainbal;
	private String fxcurrency;
	private double fxorigbal;
	private double fxremainbal;
	private double uorigbal;
	private double uremainbal;
	private IssueInfo cusip;
	private double fxrate;
	private String cashdesc;
	private Date lastmatch;
	private Date ledger;
	private Date origpaydate;
	private String bankcashdateRev;

	// many to one
	private BkCashRcpy rcpyid;
	private BkCashAcct bkCashAcct;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="BkCashFlow_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PORTFOLIO")
	public PortfolioHdr getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(PortfolioHdr portfolio) {
		this.portfolio = portfolio;
	}

	@Column(name="ZERO")
	public int getZero() {
		return zero;
	}

	public void setZero(int zero) {
		this.zero = zero;
	}

	@Column(name="DBCR")
	public String getDbcr() {
		return dbcr;
	}

	public void setDbcr(String dbcr) {
		this.dbcr = dbcr;
	}

	@Column(name="BANKCASHDATE")
	public Date getBankcashdate() {
		return bankcashdate;
	}

	public void setBankcashdate(Date bankcashdate) {
		this.bankcashdate = bankcashdate;
	}

	@Column(name="ORIGBAL")
	public double getOrigbal() {
		return origbal;
	}

	/**
	 * 设值本币
	 * @param origbal
	 */
	public void setOrigbal(double origbal) {
		this.origbal = origbal;
	}

	/**
	 * 本币剩余
	 * @return
	 */
	@Column(name="REMAINBAL")
	public double getRemainbal() {
		return remainbal;
	}

	/**
	 * 设值本币剩余
	 * @param remainbal
	 */
	public void setRemainbal(double remainbal) {
		this.remainbal = remainbal;
	}

	@Column(name="CURR_CODE")
	public String getFxcurrency() {
		return fxcurrency;
	}

	public void setFxcurrency(String fxcurrency) {
		this.fxcurrency = fxcurrency;
	}

	/**
	 * 原币
	 * @return
	 */
	@Column(name="FXORIGBAL")
	public double getFxorigbal() {
		return fxorigbal;
	}

	/**
	 * 设置原币
	 * @param fxorigbal
	 */
	public void setFxorigbal(double fxorigbal) {
		this.fxorigbal = fxorigbal;
	}

	/**
	 * 获取原币剩余
	 * @return
	 */
	@Column(name="FXREMAINBAL")
	public double getFxremainbal() {
		return fxremainbal;
	}

	/**
	 * 设值原币剩余
	 * @param fxremainbal
	 */
	public void setFxremainbal(double fxremainbal) {
		this.fxremainbal = fxremainbal;
	}

	/**
	 *	数量
	 * @return
	 */
	@Column(name="UORIGBAL")
	public double getUorigbal() {
		return uorigbal;
	}

	/**
	 * 设值数量
	 * @param uorigbal
	 */
	public void setUorigbal(double uorigbal) {
		this.uorigbal = uorigbal;
	}

	/**
	 * 设值数量剩余
	 * @return
	 */
	@Column(name="UREMAINBAL")
	public double getUremainbal() {
		return uremainbal;
	}

	/**
	 * 设值数量剩余
	 * @param uremainbal
	 */
	public void setUremainbal(double uremainbal) {
		this.uremainbal = uremainbal;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSIp")
	public IssueInfo getCusip() {
		return cusip;
	}

	public void setCusip(IssueInfo cusip) {
		this.cusip = cusip;
	}

	@Column(name="FXRATE")
	public double getFxrate() {
		return fxrate;
	}

	public void setFxrate(double fxrate) {
		this.fxrate = fxrate;
	}

	@Column(name="CASHDESC")
	public String getCashdesc() {
		return cashdesc;
	}

	public void setCashdesc(String cashdesc) {
		this.cashdesc = cashdesc;
	}

	@Column(name="LASTMATCH")
	public Date getLastmatch() {
		return lastmatch;
	}

	public void setLastmatch(Date lastmatch) {
		this.lastmatch = lastmatch;
	}

	@Column(name="LEDGER")
	public Date getLedger() {
		return ledger;
	}

	public void setLedger(Date ledger) {
		this.ledger = ledger;
	}


	@Column(name="ORIGPAYDATE")
	public Date getOrigpaydate() {
		return origpaydate;
	}

	public void setOrigpaydate(Date origpaydate) {
		this.origpaydate = origpaydate;
	}

	@Column(name="BANKCASHDATE_REV")
	public String getBankcashdateRev() {
		return bankcashdateRev;
	}

	public void setBankcashdateRev(String bankcashdateRev) {
		this.bankcashdateRev = bankcashdateRev;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RCPYID")
	public BkCashRcpy getRcpyid() {
		return rcpyid;
	}

	public void setRcpyid(BkCashRcpy rcpyid) {
		this.rcpyid = rcpyid;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BK_ACCT_NO")
	public BkCashAcct getBkCashAcct() {
		return bkCashAcct;
	}
	
	public void setBkCashAcct(BkCashAcct bkCashAcct) {
		this.bkCashAcct = bkCashAcct;
	}
}
