package com.orm.obj;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;
import com.jixu.common.util.DateUtil;

/** 
 * @ClassName 		BkCashRcpy
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[应收应付表，交易产生的应收应付都被记录在该表。如果是外币的应收应付，根据该表的数据产生汇兑损益]  
 */

@Entity 
@Table(name="JXB_BK_CASH_RCPY", 
	indexes={@Index(name="BkCashRcpy_index1",columnList="portfolio,paydate"),
		@Index(name="BkCashRcpy_index2",columnList="portfolio,ledger,paydate")}) 
public class BkCashRcpy extends BasicEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	// primary key
	private int id;

	// fields
	private int zero;
	private String dbcr;
	private int posttype;
	private TranBasic tanum;
	private IssueInfo cusip;
	private Date ledger;
	//存本币,原始金额
	private double origbal;
	//存本币,剩余金额
	private double remainbal;
	private String fxcurrency;
	//存原币,原始金额
	private double fxorigbal;
	//存原币,剩余金额
	private double fxremainbal;
	private double uorigbal;
	private double uremainbal;
	private double fxrate;
	private int trantype;
	private Date lastmatch;
	private Date paydate;
	private Date expdcashdate;
	private int blockid;
	private int miscorigbal;
	private int miscremainbal;
	private TranBasicFee feeID;
	
	// many to one
	private PortfolioHdr portfolio;
	private BkCashAcct bkCashAcct;
	private String rcpyDesc;
	private Set<BkCashFlow> bkCashFlows;
	
	public BkCashRcpy() {
		this.setLastmatch(DateUtil.convertStr2Date("20000101"));
	}

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="BkCashRcpy_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	/**
	 * C:	应收
	 * D:	应付
	 * 
	 * @param dbcr
	 *            the DBCR value
	 */
	public void setDbcr(String dbcr) {
		this.dbcr = dbcr;
	}


	@Column(name="POSTTYPE")
	public int getPosttype() {
		return posttype;
	}

	public void setPosttype(int posttype) {
		this.posttype = posttype;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tanum")
	public TranBasic getTanum() {
		return tanum;
	}

	public void setTanum(TranBasic tanum) {
		this.tanum = tanum;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSIP")
	public IssueInfo getCusip() {
		return cusip;
	}

	public void setCusip(IssueInfo cusip) {
		this.cusip = cusip;
	}

	@Column(name="LEDGER")
	public Date getLedger() {
		return ledger;
	}

	public void setLedger(Date ledger) {
		this.ledger = ledger;
	}

	/**
	 * 返回本币的原始金额
	 * @return
	 */
	@Column(name="ORIGBAL")
	public double getOrigbal() {
		return origbal;
	}

	public void setOrigbal(double origbal) {
		this.origbal = origbal;
	}

	@Column(name="REMAINBAL")
	public double getRemainbal() {
		return remainbal;
	}

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

	@Column(name="FXORIGBAL")
	public double getFxorigbal() {
		return fxorigbal;
	}

	public void setFxorigbal(double fxorigbal) {
		this.fxorigbal = fxorigbal;
	}

	@Column(name="FXREMAINBAL")
	public double getFxremainbal() {
		return fxremainbal;
	}

	public void setFxremainbal(double fxremainbal) {
		this.fxremainbal = fxremainbal;
	}

	@Column(name="UORIGBAL")
	public double getUorigbal() {
		return uorigbal;
	}

	public void setUorigbal(double uorigbal) {
		this.uorigbal = uorigbal;
	}

	@Column(name="UREMAINBAL")
	public double getUremainbal() {
		return uremainbal;
	}

	public void setUremainbal(double uremainbal) {
		this.uremainbal = uremainbal;
	}

	@Column(name="FXRATE")
	public double getFxrate() {
		return fxrate;
	}

	public void setFxrate(double fxrate) {
		this.fxrate = fxrate;
	}

	@Column(name="TRANTYPE")
	public int getTrantype() {
		return trantype;
	}

	public void setTrantype(int trantype) {
		this.trantype = trantype;
	}

	@Column(name="LASTMATCH")
	public Date getLastmatch() {
		return lastmatch;
	}

	public void setLastmatch(Date lastmatch) {
		this.lastmatch = lastmatch;
	}

	@Column(name="PAYDATE")
	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

	@Column(name="EXPDCASHDATE")
	public Date getExpdcashdate() {
		return expdcashdate;
	}

	public void setExpdcashdate(Date expdcashdate) {
		this.expdcashdate = expdcashdate;
	}


	@Column(name="BLOCKID")
	public int getBlockid() {
		return blockid;
	}

	public void setBlockid(int blockid) {
		this.blockid = blockid;
	}

	@Column(name="MISCORIGBAL")
	public int getMiscorigbal() {
		return miscorigbal;
	}

	public void setMiscorigbal(int miscorigbal) {
		this.miscorigbal = miscorigbal;
	}

	@Column(name="MISCREMAINBAL")
	public int getMiscremainbal() {
		return miscremainbal;
	}

	public void setMiscremainbal(int miscremainbal) {
		this.miscremainbal = miscremainbal;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BK_ACCT_NO")
	public BkCashAcct getBkCashAcct() {
		return bkCashAcct;
	}

	public void setBkCashAcct(BkCashAcct bkCashAcct) {
		this.bkCashAcct = bkCashAcct;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PORTFOLIO")
	public PortfolioHdr getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(PortfolioHdr portfolio) {
		this.portfolio = portfolio;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="feeID")
	public TranBasicFee getFeeID() {
		return feeID;
	}

	public void setFeeID(TranBasicFee feeID) {
		this.feeID = feeID;
	}

	@Column(name="RcpyDesc")
	public String getRcpyDesc() {
		return rcpyDesc;
	}

	public void setRcpyDesc(String rcpyDesc) {
		this.rcpyDesc = rcpyDesc;
	}

	@OneToMany(mappedBy = "rcpyid")
	public Set<BkCashFlow> getBkCashFlows() {
		return bkCashFlows;
	}

	public void setBkCashFlows(Set<BkCashFlow> bkCashFlows) {
		this.bkCashFlows = bkCashFlows;
	}
}
