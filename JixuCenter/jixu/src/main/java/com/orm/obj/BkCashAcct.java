package com.orm.obj;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;
import com.jixu.common.util.DateUtil;


/** 
 * @ClassName 		BkCashAcct
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[银行现金账户表,所有的交易如果涉及到现金的结转，都需要指定银行现金账户。银行现金和利息的相关科目，也需要在该表配置]  
 */

@Entity 
@Table(name="JXB_BK_CASH_ACCT") 
public class BkCashAcct extends BasicEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	// primary key
	private int id;

	// fields
	private BkInfo bkinfo;
	private int accounttype;
	private String bankdesc;
	private String externalacctnumber;
	private String fxcurrency;
	private int reason;
	private int structure;
	private int status;
	private String cashier;
	private String cashmanager;
	private String cashglcode;
	//会计冲销账户
	private String defaultoffset;
	private double lastBalance;
	private Date lastBalDate;
	private int defaultport;
	//已收利息
	private String intrecvgl;
	//应收款/应付款
	private String intrcvpaygl;
	private String odindicator;
	private String odpaidgl;
	private String odrcvpaygl;
	private Date lastclose;
	private Date nextclose;
	private String omnibusindicator;
	//已实现外汇损益账户
	private String cashrealfxgnls;
	private String inttolerancegl;
	//未实现外汇损益账户
	private String cashunrealfxgnls;
	//未实现外汇损益冲销账户
	private String cashunrealfxoffset;
	private String sttlmntcurrdefault;
	private int odcshflwmasterrec;
	private String portbalindicator;
	//利息已实现外汇损益账户
	private String intrealfxgnls;
	//利息未实现外汇损益账户
	private String intunrealfxgnls;
	////利息未实现外汇损益冲销
	private String intunrealfxoffset;
	private String odrealfxgnls;
	private String odunrealfxgnls;
	private String odunrealfxoffset;
	private String cashtruncationgain;
	private String cashtruncationloss;
	//应计利息
	private String interestaccrued;
	//应计利息冲销
	private String interestaccoffset;
	private String odaccrued;
	private String odaccruedoffset;
	private String odtolerancegl;

	// collections
	private Set<InterestRcpy> interestRcpies;
	private Set<BkCashFlow> bkCashFlows;
	private Set<BkCashRcpy> bkCashRcpies;
	
	//银行利息的配置，没有配置则为0
	private int intCshflwMasterRec;
	
	private PortfolioHdr portfolio;
	private String secAcctCode;
	private String seatNumber;

	public BkCashAcct() {
		this.setNextclose(DateUtil.getInitDate());
		this.setLastBalDate(DateUtil.getInitDate());
		this.setLastclose(DateUtil.getInitDate());
		this.setSecAcctCode("0");
	}
	
	public BkCashAcct(int bkCashAcctNo) {
		this.setId(bkCashAcctNo);
	}

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="BkCashAcct_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="BK_ACCT_NO")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="ACCOUNTTYPE")
	public int getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(int accounttype) {
		this.accounttype = accounttype;
	}

	@Column(name="BANKDESC")
	public String getBankdesc() {
		return bankdesc;
	}

	public void setBankdesc(String bankdesc) {
		this.bankdesc = bankdesc;
	}

	@Column(name="EXTERNALACCTNUMBER")
	public String getExternalacctnumber() {
		return externalacctnumber;
	}

	public void setExternalacctnumber(String externalacctnumber) {
		this.externalacctnumber = externalacctnumber;
	}

	@Column(name="CURRENCYCODE")
	public String getFxcurrency() {
		return fxcurrency;
	}

	public void setFxcurrency(String fxcurrency) {
		this.fxcurrency = fxcurrency;
	}
	
	@Column(name="REASON")
	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	@Column(name="STRUCTURE")
	public int getStructure() {
		return structure;
	}

	public void setStructure(int structure) {
		this.structure = structure;
	}

	@Column(name="STATUS")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name="CASHIER")
	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	@Column(name="CASHMANAGER")
	public String getCashmanager() {
		return cashmanager;
	}

	public void setCashmanager(String cashmanager) {
		this.cashmanager = cashmanager;
	}

	@Column(name="CASHGLCODE")
	public String getCashglcode() {
		return cashglcode;
	}

	public void setCashglcode(String cashglcode) {
		this.cashglcode = cashglcode;
	}

	@Column(name="DEFAULTOFFSET")
	public String getDefaultoffset() {
		return defaultoffset;
	}

	public void setDefaultoffset(String defaultoffset) {
		this.defaultoffset = defaultoffset;
	}

	@Column(name="LAST_BALANCE")
	public double getLastBalance() {
		return lastBalance;
	}

	public void setLastBalance(double lastBalance) {
		this.lastBalance = lastBalance;
	}

	@Column(name="LAST_BAL_DATE")
	public Date getLastBalDate() {
		return lastBalDate;
	}

	public void setLastBalDate(Date lastBalDate) {
		this.lastBalDate = lastBalDate;
	}

	@Column(name="DEFAULTPORT")
	public int getDefaultport() {
		return defaultport;
	}

	public void setDefaultport(int defaultport) {
		this.defaultport = defaultport;
	}

	@Column(name="INTRECVGL")
	public String getIntrecvgl() {
		return intrecvgl;
	}

	public void setIntrecvgl(String intrecvgl) {
		this.intrecvgl = intrecvgl;
	}

	@Column(name="INTRCVPAYGL")
	public String getIntrcvpaygl() {
		return intrcvpaygl;
	}

	public void setIntrcvpaygl(String intrcvpaygl) {
		this.intrcvpaygl = intrcvpaygl;
	}

	@Column(name="ODINDICATOR")
	public String getOdindicator() {
		return odindicator;
	}

	public void setOdindicator(String odindicator) {
		this.odindicator = odindicator;
	}


	@Column(name="ODPAIDGL")
	public String getOdpaidgl() {
		return odpaidgl;
	}

	public void setOdpaidgl(String odpaidgl) {
		this.odpaidgl = odpaidgl;
	}

	@Column(name="ODRCVPAYGL")
	public String getOdrcvpaygl() {
		return odrcvpaygl;
	}

	public void setOdrcvpaygl(String odrcvpaygl) {
		this.odrcvpaygl = odrcvpaygl;
	}

	@Column(name="LASTCLOSE")
	public Date getLastclose() {
		return lastclose;
	}

	public void setLastclose(Date lastclose) {
		this.lastclose = lastclose;
	}

	@Column(name="NEXTCLOSE")
	public Date getNextclose() {
		return nextclose;
	}

	public void setNextclose(Date nextclose) {
		this.nextclose = nextclose;
	}


	@Column(name="OMNIBUSINDICATOR")
	public String getOmnibusindicator() {
		return omnibusindicator;
	}

	public void setOmnibusindicator(String omnibusindicator) {
		this.omnibusindicator = omnibusindicator;
	}

	@Column(name="CASHREALFXGNLS")
	public String getCashrealfxgnls() {
		return cashrealfxgnls;
	}

	public void setCashrealfxgnls(String cashrealfxgnls) {
		this.cashrealfxgnls = cashrealfxgnls;
	}

	@Column(name="INTTOLERANCEGL")
	public String getInttolerancegl() {
		return inttolerancegl;
	}


	public void setInttolerancegl(String inttolerancegl) {
		this.inttolerancegl = inttolerancegl;
	}

	
	@Column(name="CASHUNREALFXGNLS")
	public String getCashunrealfxgnls() {
		return cashunrealfxgnls;
	}

	public void setCashunrealfxgnls(String cashunrealfxgnls) {
		this.cashunrealfxgnls = cashunrealfxgnls;
	}

	@Column(name="CASHUNREALFXOFFSET")
	public String getCashunrealfxoffset() {
		return cashunrealfxoffset;
	}

	public void setCashunrealfxoffset(String cashunrealfxoffset) {
		this.cashunrealfxoffset = cashunrealfxoffset;
	}

	@Column(name="STTLMNTCURRDEFAULT")
	public String getSttlmntcurrdefault() {
		return sttlmntcurrdefault;
	}

	public void setSttlmntcurrdefault(String sttlmntcurrdefault) {
		this.sttlmntcurrdefault = sttlmntcurrdefault;
	}


	@Column(name="ODCSHFLWMASTERREC")
	public int getOdcshflwmasterrec() {
		return odcshflwmasterrec;
	}

	public void setOdcshflwmasterrec(int odcshflwmasterrec) {
		this.odcshflwmasterrec = odcshflwmasterrec;
	}

	@Column(name="PORTBALINDICATOR")
	public String getPortbalindicator() {
		return portbalindicator;
	}

	public void setPortbalindicator(String portbalindicator) {
		this.portbalindicator = portbalindicator;
	}

	@Column(name="INTREALFXGNLS")
	public String getIntrealfxgnls() {
		return intrealfxgnls;
	}
	
	public void setIntrealfxgnls(String intrealfxgnls) {
		this.intrealfxgnls = intrealfxgnls;
	}

	@Column(name="INTUNREALFXGNLS")
	public String getIntunrealfxgnls() {
		return intunrealfxgnls;
	}

	public void setIntunrealfxgnls(String intunrealfxgnls) {
		this.intunrealfxgnls = intunrealfxgnls;
	}

	@Column(name="INTUNREALFXOFFSET")
	public String getIntunrealfxoffset() {
		return intunrealfxoffset;
	}

	public void setIntunrealfxoffset(String intunrealfxoffset) {
		this.intunrealfxoffset = intunrealfxoffset;
	}

	@Column(name="ODREALFXGNLS")
	public String getOdrealfxgnls() {
		return odrealfxgnls;
	}

	public void setOdrealfxgnls(String odrealfxgnls) {
		this.odrealfxgnls = odrealfxgnls;
	}

	@Column(name="ODUNREALFXGNLS")
	public String getOdunrealfxgnls() {
		return odunrealfxgnls;
	}

	public void setOdunrealfxgnls(String odunrealfxgnls) {
		this.odunrealfxgnls = odunrealfxgnls;
	}

	@Column(name="ODUNREALFXOFFSET")
	public String getOdunrealfxoffset() {
		return odunrealfxoffset;
	}

	public void setOdunrealfxoffset(String odunrealfxoffset) {
		this.odunrealfxoffset = odunrealfxoffset;
	}

	@Column(name="CASHTRUNCATIONGAIN")
	public String getCashtruncationgain() {
		return cashtruncationgain;
	}

	public void setCashtruncationgain(String cashtruncationgain) {
		this.cashtruncationgain = cashtruncationgain;
	}

	@Column(name="CASHTRUNCATIONLOSS")
	public String getCashtruncationloss() {
		return cashtruncationloss;
	}

	public void setCashtruncationloss(String cashtruncationloss) {
		this.cashtruncationloss = cashtruncationloss;
	}

	@Column(name="INTERESTACCRUED")
	public String getInterestaccrued() {
		return interestaccrued;
	}

	public void setInterestaccrued(String interestaccrued) {
		this.interestaccrued = interestaccrued;
	}

	@Column(name="INTERESTACCOFFSET")
	public String getInterestaccoffset() {
		return interestaccoffset;
	}

	public void setInterestaccoffset(String interestaccoffset) {
		this.interestaccoffset = interestaccoffset;
	}

	@Column(name="ODACCRUED")
	public String getOdaccrued() {
		return odaccrued;
	}

	public void setOdaccrued(String odaccrued) {
		this.odaccrued = odaccrued;
	}

	@Column(name="ODACCRUEDOFFSET")
	public String getOdaccruedoffset() {
		return odaccruedoffset;
	}

	public void setOdaccruedoffset(String odaccruedoffset) {
		this.odaccruedoffset = odaccruedoffset;
	}

	@Column(name="ODTOLERANCEGL")
	public String getOdtolerancegl() {
		return odtolerancegl;
	}

	public void setOdtolerancegl(String odtolerancegl) {
		this.odtolerancegl = odtolerancegl;
	}
	
	@Column(name="INTCSHFLWMASTERREC")
	public int getIntCshflwMasterRec() {
		return intCshflwMasterRec;
	}

	public void setIntCshflwMasterRec(int intCshflwMasterRec) {
		this.intCshflwMasterRec = intCshflwMasterRec;
	}
	
	@OneToMany(mappedBy = "bkCashAcct")
	public Set<InterestRcpy> getInterestRcpies() {
		return interestRcpies;
	}

	public void setInterestRcpies(Set<InterestRcpy> interestRcpies) {
		this.interestRcpies = interestRcpies;
	}

	@OneToMany(mappedBy = "bkCashAcct")
	public Set<BkCashFlow> getBkCashFlows() {
		return bkCashFlows;
	}

	public void setBkCashFlows(Set<BkCashFlow> bkCashFlows) {
		this.bkCashFlows = bkCashFlows;
	}

	@OneToMany(mappedBy = "bkCashAcct")
	public Set<BkCashRcpy> getBkCashRcpies() {
		return bkCashRcpies;
	}

	public void setBkCashRcpies(Set<BkCashRcpy> bkCashRcpies) {
		this.bkCashRcpies = bkCashRcpies;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BANKID")
	public BkInfo getBkinfo() {
		return bkinfo;
	}

	public void setBkinfo(BkInfo bkinfo) {
		this.bkinfo = bkinfo;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="portfolio")
	public PortfolioHdr getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(PortfolioHdr portfolio) {
		this.portfolio = portfolio;
	}


	@Column(name="secAcctCode")
	public String getSecAcctCode() {
		return secAcctCode;
	}

	public void setSecAcctCode(String secAcctCode) {
		this.secAcctCode = secAcctCode;
	}
	
	@Column(name="seatNumber")
	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
}
