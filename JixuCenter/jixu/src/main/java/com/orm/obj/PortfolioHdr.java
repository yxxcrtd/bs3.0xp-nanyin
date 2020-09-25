package com.orm.obj;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		NewFundContent
 * @author  		Pan Wei 
 * @Date 			2017年2月14日 下午1:19:50 	
 * @Description		[投资组合的基本配置,所有的组合信息该表]  
 */

@Entity 
@Table(name="JXS_PORTFOLIO_HDR") 
public class PortfolioHdr extends BasicEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	// primary key
	private String id;

	// fields
	private String shortCode;
	private String shortName;
	private String portName;
	private String singlelot;
	
	private int classid;

	// collections
	private Set<TranBasic> tranBasic;
	private Set<Tran> tran;
	private Set<TranSum> tranSum;
	private Set<BkCashRcpy> bkCashRcpies;
	private Set<Voucher> vouchers;
	private Set<PortExpenseCfg> portExpenseCfg;
	private Set<BkCashAcct> bkCashAcct;
	private String curID;
	private String coaahdr;
	private CoaaGroup coaaGroup;
	
	//投资组合类型  公募基金，私募基金，专户等
	private int portType;
	//成立日
	private Date inceptionDate;
	//上一关帐日
	private Date lastTranClose;
	//下一关帐日(即做账日)
	private Date nextTranClose;
	//上一总账关帐日
	private Date lastGLClose;
	//下一总账关帐日
	private Date nextGLClose;
	//本年度做账日
	private Date nextYealClose;
	
	private Province province;
	
	//对应的客户信息
	private int portfolioClient;
	
	/**
	 * For mutual fund portfolios, a code indicating whether the portfolio is using the Load Forward or Load Back method of accruing income, amortization and expenses over a weekend or holiday. Not used for other portfolio types.
	 * 'B' 0 Load Back  
	 * 'F' 1 Load Forward 
	 */
	private int bookNBDays;

	private String holidaySD;;
	
	//单位净值的计算精度
	private int navPrecision; 
	
	//移动加权平均等选择
	private int lotSelection;
	
	//A code indicating whether a portfolio allows valuation closes to be performed on non-traditional business days (e.g., Saturday or Sunday). Applies only to mutual fund portfolios.
	private int wkndValuationClose;
	
	//A code referring to the portfolio's default method of accounting, i.e., whether it is on a trade or a settlement date basis, or a combination.
	private int tradeSettleLedger;
	
	private Set<PortfolioInterfaceMap> interfaceMap;
	
	private int managerGroup;
	
	private String defaultSecAcct;
	
	private Date lastActiveDate;

	//净值归一
	private boolean jzgyFlag;
	
	//收益转份额
	private boolean syzfeFlag;
	
	public PortfolioHdr() {
		this.setDefaultSecAcct("0");
		this.setJzgyFlag(false);
		this.setSyzfeFlag(false);
	}
	
	public PortfolioHdr(String id) {
		this.setId(id);
		this.setJzgyFlag(false);
		this.setSyzfeFlag(false);
	}
	
	
	@OneToMany(mappedBy = "portfolio", cascade = CascadeType.REMOVE)
	public Set<TranBasic> getTranBasic() {
		return tranBasic;
	}

	public void setTranBasic(Set<TranBasic> tranBasic) {
		this.tranBasic = tranBasic;
	}
	
	@OneToMany(mappedBy = "portfolio", cascade = CascadeType.REMOVE)
	public Set<TranSum> getTranSum() {
		return tranSum;
	}

	public void setTranSum(Set<TranSum> tranSum) {
		this.tranSum = tranSum;
	}

	@OneToMany(mappedBy = "portfolio", cascade = CascadeType.REMOVE)
	public Set<Tran> getTran() {
		return tran;
	}

	public void setTran(Set<Tran> tran) {
		this.tran = tran;
	}

	@OneToMany(mappedBy = "portfolio", cascade = CascadeType.REMOVE)
	public Set<BkCashRcpy> getBkCashRcpies() {
		return bkCashRcpies;
	}

	public void setBkCashRcpies(Set<BkCashRcpy> bkCashRcpies) {
		this.bkCashRcpies = bkCashRcpies;
	}

	@OneToMany(mappedBy = "portfolio", cascade = CascadeType.REMOVE)
	public Set<Voucher> getVouchers() {
		return vouchers;
	}


	public void setVouchers(Set<Voucher> vouchers) {
		this.vouchers = vouchers;
	}

	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.func.GenCustomTBID_Joyin", parameters={
			@Parameter(name = "seq_name", value = "trade_id_sequence"),
			@Parameter(name = "short_name", value = "PD")})
	@Column(name="PORTFOLIO")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="PORT_NAME")
	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	@Column(name="SINGLELOT")
	public String getSinglelot() {
		return singlelot;
	}

	public void setSinglelot(String singlelot) {
		this.singlelot = singlelot;
	}

	@Column(name="CHARTNUMBER")
	public String getCoaahdr() {
		return coaahdr;
	}
	
	public void setCoaahdr(String coaahdr) {
		this.coaahdr = coaahdr;
	}
	
	@Column(name="CURRENCYCODE")
	public String getCurID() {
		return curID;
	}

	public void setCurID(String curID) {
		this.curID = curID;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GLGROUP")
	public CoaaGroup getCoaaGroup() {
		return coaaGroup;
	}

	public void setCoaaGroup(CoaaGroup coaaGroup) {
		this.coaaGroup = coaaGroup;
	}

	@OneToMany(mappedBy = "portfolio", cascade = CascadeType.REMOVE)
	public Set<PortExpenseCfg> getPortExpenseCfg() {
		return portExpenseCfg;
	}

	public void setPortExpenseCfg(Set<PortExpenseCfg> portExpenseCfg) {
		this.portExpenseCfg = portExpenseCfg;
	}


	@Column(name="bookNBDays")
	public int getBookNBDays() {
		return bookNBDays;
	}

	public void setBookNBDays(int bookNBDays) {
		this.bookNBDays = bookNBDays;
	}

	@Column(name="HOLIDAYSD")
	public String getHolidaySD() {
		return holidaySD;
	}

	public void setHolidaySD(String holidaySD) {
		this.holidaySD = holidaySD;
	}

	@Column(name="classid")
	public int getClassid() {
		return classid;
	}


	public void setClassid(int classid) {
		this.classid = classid;
	}

	@Column(name="inceptionDate")
	public Date getInceptionDate() {
		return inceptionDate;
	}


	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	@Column(name="lastTranClose")
	public Date getLastTranClose() {
		return lastTranClose;
	}


	public void setLastTranClose(Date lastTranClose) {
		this.lastTranClose = lastTranClose;
	}

	@Column(name="nextTranClose")
	public Date getNextTranClose() {
		return nextTranClose;
	}


	public void setNextTranClose(Date nextTranClose) {
		this.nextTranClose = nextTranClose;
	}

	@Column(name="lastGLClose")
	public Date getLastGLClose() {
		return lastGLClose;
	}


	public void setLastGLClose(Date lastGLClose) {
		this.lastGLClose = lastGLClose;
	}

	@Column(name="nextGLClose")
	public Date getNextGLClose() {
		return nextGLClose;
	}

	public void setNextGLClose(Date nextGLClose) {
		this.nextGLClose = nextGLClose;
	}

	@Column(name="portfolioClient")
	public int getPortfolioClient() {
		return portfolioClient;
	}


	public void setPortfolioClient(int portfolioClient) {
		this.portfolioClient = portfolioClient;
	}

	@Column(name="portfolioType")
	public int getPortType() {
		return portType;
	}

	public void setPortType(int portType) {
		this.portType = portType;
	}

	@Column(name="shortCode")
	public String getShortCode() {
		return shortCode;
	}


	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	@Column(name="shortName")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name="nextYealClose")
	public Date getNextYealClose() {
		return nextYealClose;
	}

	public void setNextYealClose(Date nextYealClose) {
		this.nextYealClose = nextYealClose;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="province")
	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@Column(name="navPrecision")
	public int getNavPrecision() {
		return navPrecision;
	}

	public void setNavPrecision(int navPrecision) {
		this.navPrecision = navPrecision;
	}

	@OneToMany(mappedBy = "portfolio", cascade = CascadeType.REMOVE)
	public Set<BkCashAcct> getBkCashAcct() {
		return bkCashAcct;
	}

	public void setBkCashAcct(Set<BkCashAcct> bkCashAcct) {
		this.bkCashAcct = bkCashAcct;
	}

	@Column(name="lotSelection")
	public int getLotSelection() {
		return lotSelection;
	}

	public void setLotSelection(int lotSelection) {
		this.lotSelection = lotSelection;
	}

	@Column(name="wkndValuationClose")
	public int getWkndValuationClose() {
		return wkndValuationClose;
	}

	public void setWkndValuationClose(int wkndValuationClose) {
		this.wkndValuationClose = wkndValuationClose;
	}

	@Column(name="TradeSettleLedger")
	public int getTradeSettleLedger() {
		return tradeSettleLedger;
	}

	public void setTradeSettleLedger(int tradeSettleLedger) {
		this.tradeSettleLedger = tradeSettleLedger;
	}

	@OneToMany(mappedBy = "portfolio", cascade = CascadeType.REMOVE)
	public Set<PortfolioInterfaceMap> getInterfaceMap() {
		return interfaceMap;
	}

	public void setInterfaceMap(Set<PortfolioInterfaceMap> interfaceMap) {
		this.interfaceMap = interfaceMap;
	}

	@Column(name="managerGroup")
	public int getManagerGroup() {
		return managerGroup;
	}

	public void setManagerGroup(int managerGroup) {
		this.managerGroup = managerGroup;
	}

	@Column(name="DefaultSecAcct")
	public String getDefaultSecAcct() {
		return defaultSecAcct;
	}

	public void setDefaultSecAcct(String defaultSecAcct) {
		this.defaultSecAcct = defaultSecAcct;
	}

	@Column(name="lastActiveDate")
	public Date getLastActiveDate() {
		return lastActiveDate;
	}

	public void setLastActiveDate(Date lastActiveDate) {
		this.lastActiveDate = lastActiveDate;
	}

	@Column(name="jzgyFlag",columnDefinition="integer",length=1)
	public boolean isJzgyFlag() {
		return jzgyFlag;
	}

	
	public void setJzgyFlag(boolean jzgyFlag) {
		this.jzgyFlag = jzgyFlag;
	}

	@Column(name="syzfeFlag",columnDefinition="integer",length=1)
	public boolean isSyzfeFlag() {
		return syzfeFlag;
	}

	public void setSyzfeFlag(boolean syzfeFlag) {
		this.syzfeFlag = syzfeFlag;
	}
}
