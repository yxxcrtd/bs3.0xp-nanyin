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
import com.jixu.common.pojo.InterfaceSource;

/** 
 * @ClassName 		IssueInfo
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[证券表,包括一些证券的基础信息。上面还有证券公司表,一个证券公司可以发行多个证券]  
 */


@Entity 
@Table(name="JXS_ISSUE_INFO") 
public class IssueInfo extends BasicEntity  implements Serializable {

	private static final long serialVersionUID = 1L;
	// primary key
	
	private String id;

	// fields
	//证券主ID
	private String securityid;
	//证券名称
	private String securityname;
	//总账组
	private int coaaGroup;
	//现金流配置
	private String cashFlowCfgID="0";
	
	//互换时，空方的cashflowcfgid
	private String cashFlowCfgID2="0";

	// collections
	private Set<TranBasic> tranBasic;
	//private Set<SecPrice> secPrices;
	private Set<Tran> tran;
	private Set<SecPrice> secPrice;
	private Set<BkCashFlow> cashFlow;
	private Set<BkCashRcpy> cashRcpy;
	//成本货币
	private String priCur;
	
	private String priCur2;
	//收入货币
	private String incomeCur;
	
	private String incomeCur2;
	//资产类别
	private String issueType;
	//开发商
	private String issuer;
	//股票来源 
	private InterfaceSource sourceID;
	
	private int interfaceSourceID;
	//资产分组
	private RPSecType rpSecType;
	//交易所
	private String exchangeCode;
	//国家
	private String country;
	
	private Date expireDate;
	
	//期权类型,认沽/认购
	private int optionType;
	
	//该股票和其它一只股票关联
	private String underCusip;
	
	//固收类型
	private int fixIncomeType;
	//发行价
	private double startPrice;
	//到期价
	private double endPrice;
	
	/**
	 * 在swap交易中，判定前后端是否结转现金的标记
	 * 无/不结转, 前端结转，后端结转，前后和后端结转
	 */
	private int swapExchangeInd;
	
	/**
	 * 合约成书
	 * 目前用在期货交易中,比如股指期货,1点代表300
	 * 债券就是指票面价值
	 */
	private double contractSize;
	
	private Date activeDate;
	
	/**
	 * 
	 */
	public IssueInfo() {
		this.setContractSize(1);
	}
	
	/*//货币基金的结转频率:每日结转；每月结转；自定义结转；
	private Integer carryOverType;
	//移到cashflowcfg的cpnType字段
	
	//货币基金的结转频率:每日结转；每月结转；自定义结转；
	private Integer carryOverMethod;*/
	//移到cashflowcfg的paymentKind字段
		
	//@SequenceGenerator(name="seq_generator", sequenceName="IssueInfo_ID_SEQ", allocationSize=1, initialValue=15898)
	//@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.func.GenCustomTBID_Joyin", parameters={
			@Parameter(name = "seq_name", value = "issueinfo_id_sequence"),
			@Parameter(name = "short_name", value = "SEC")})
	@Column(name="CUSIP")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="SECURITYID")
	public String getSecurityid() {
		return securityid;
	}
	public void setSecurityid(String securityid) {
		this.securityid = securityid;
	}
	
	@Column(name="GLGROUP")
	public int getCoaaGroup() {
		return coaaGroup;
	}
	public void setCoaaGroup(int coaaGroup) {
		this.coaaGroup = coaaGroup;
	}
	
	@OneToMany(mappedBy = "cusip", cascade = CascadeType.REMOVE)
	public Set<TranBasic> getTranBasic() {
		return tranBasic;
	}

	
	public void setTranBasic(Set<TranBasic> tranBasic) {
		this.tranBasic = tranBasic;
	}
	
	@OneToMany(mappedBy = "cusip", cascade = CascadeType.REMOVE)
	public Set<Tran> getTran() {
		return tran;
	}
	
	public void setTran(Set<Tran> tran) {
		this.tran = tran;
	}
	
	@OneToMany(mappedBy = "cusip", cascade = CascadeType.REMOVE)
	public Set<SecPrice> getSecPrice() {
		return secPrice;
	}
	public void setSecPrice(Set<SecPrice> secPrice) {
		this.secPrice = secPrice;
	}

	@OneToMany(mappedBy = "cusip", cascade = CascadeType.REMOVE)
	public Set<BkCashFlow> getCashFlow() {
		return cashFlow;
	}
	public void setCashFlow(Set<BkCashFlow> cashFlow) {
		this.cashFlow = cashFlow;
	}
	
	@OneToMany(mappedBy = "cusip", cascade = CascadeType.REMOVE)
	public Set<BkCashRcpy> getCashRcpy() {
		return cashRcpy;
	}
	public void setCashRcpy(Set<BkCashRcpy> cashRcpy) {
		this.cashRcpy = cashRcpy;
	}
	
	@Column(name="PRICUR")
	public String getPriCur() {
		return priCur;
	}
	public void setPriCur(String priCur) {
		this.priCur = priCur;
	}
	
	@Column(name="INCOMECUR")
	public String getIncomeCur() {
		return incomeCur;
	}
	public void setIncomeCur(String incomeCur) {
		this.incomeCur = incomeCur;
	}
	
	@Column(name="PRICUR2")
	public String getPriCur2() {
		return priCur2;
	}

	public void setPriCur2(String priCur2) {
		this.priCur2 = priCur2;
	}

	@Column(name="INCOMECUR2")
	public String getIncomeCur2() {
		return incomeCur2;
	}

	public void setIncomeCur2(String incomeCur2) {
		this.incomeCur2 = incomeCur2;
	}

	@Column(name="issueType")
	public String getIssueType() {
		return issueType;
	}
	
	
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	
	@Column(name="CASHFLOWCFG")
	public String getCashFlowCfgID() {
		return cashFlowCfgID;
	}
	
	public void setCashFlowCfgID(String cashFlowCfgID) {
		this.cashFlowCfgID = cashFlowCfgID;
	}
	
	@Column(name="CASHFLOWCFG2")
	public String getCashFlowCfgID2() {
		return cashFlowCfgID2;
	}

	public void setCashFlowCfgID2(String cashFlowCfgID2) {
		this.cashFlowCfgID2 = cashFlowCfgID2;
	}

	@Column(name="issuer")
	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sourceID", updatable=false, insertable=false)
	public InterfaceSource getSourceID() {
		return sourceID;
	}
	public void setSourceID(InterfaceSource sourceID) {
		this.sourceID = sourceID;
	}
	
	@Column(name="sourceID")
	public int getInterfaceSourceID() {
		return interfaceSourceID;
	}

	public void setInterfaceSourceID(int interfaceSourceID) {
		this.interfaceSourceID = interfaceSourceID;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rpSecType")
	public RPSecType getRpSecType() {
		return rpSecType;
	}
	public void setRpSecType(RPSecType rpSecType) {
		this.rpSecType = rpSecType;
	}
	
	@Column(name="exchangeCode")
	public String getExchangeCode() {
		return exchangeCode;
	}
	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}
	
	@Column(name="COUNTRYCODE")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name="securityname")
	public String getSecurityname() {
		return securityname;
	}
	public void setSecurityname(String securityname) {
		this.securityname = securityname;
	}

	@Column(name="ExpireDate")
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Column(name="ContractSize")
	public double getContractSize() {
		return contractSize;
	}

	public void setContractSize(double contractSize) {
		this.contractSize = contractSize;
	}

	@Column(name="OptionType")
	public int getOptionType() {
		return optionType;
	}

	public void setOptionType(int optionType) {
		this.optionType = optionType;
	}

	@Column(name="UnderCusip")
	public String getUnderCusip() {
		return underCusip;
	}

	public void setUnderCusip(String underCusip) {
		this.underCusip = underCusip;
	}

	@Column(name="ActiveDate")
	public Date getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}

	@Column(name="FixIncomeType")
	public int getFixIncomeType() {
		return fixIncomeType;
	}

	public void setFixIncomeType(int fixIncomeType) {
		this.fixIncomeType = fixIncomeType;
	}

	@Column(name="StartPrice")
	public double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}

	@Column(name="EndPrice")
	public double getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(double endPrice) {
		this.endPrice = endPrice;
	}
	
	@Column(name="swapExchangeInd")
	public int getSwapExchangeInd() {
		return swapExchangeInd;
	}

	public void setSwapExchangeInd(int swapExchangeInd) {
		this.swapExchangeInd = swapExchangeInd;
	}
}
