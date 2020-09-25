package com.orm.obj;

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
import com.jixu.common.util.DateUtil;

/** 
 * @ClassName 		TranSup
 * @author  		Pan Wei 
 * @Date 			2016年8月23日 下午11:02:15 	
 * @Description		[该表尚未使用]  
 */

//@Entity 
//@Table(name="JXT_Tran_Sup") 
public class TranSup extends BasicEntity {
	
	public TranSup() {
		setAltDate(DateUtil.defaultDate());
		setDate1(DateUtil.defaultDate());
		setDate2(DateUtil.defaultDate());
	}
	
	private int id;
	private TranBasic tanum;
	private IssueInfo cusip;
	/**
	 * The maturity date, if the security referred to by Cusip is a money market type of instrument; otherwise not used. 
	 * It may differ from the date on the related main transaction advice.
	 */
	private Date altDate;
	/**
	 * The coupon rate, if the security referred to by Cusip is a money market type of instrument. 
	 * The current coupon rate for floating rate and adjustable rate type securities. Otherwise not used. 
	 * It may differ from the rate on the related main transaction advice.
	 */
	private double altRate;
	
	private double amount;
	
	/**
	 * For Conversions, Tax Free Exchanges, and Taxable Exchanges, the numerator of the conversion ratio. 
	 * For spin offs, the numerator of the distribution ratio. Otherwise not used.
	 */
	private double basisNumber;
	
	/**
	 * For Conversions, Tax Free Exchanges, and Taxable Exchanges, the denominator of the conversion ratio. 
	 * For spin offs, the denominator of the distribution ratio. Otherwise not used.
	 */
	private double basicDenom;
	
	/**
	 * The conversion basis rate, if applicable. 
	 */
	private double basis;
	
	/**
	 * The market value relevant to the amount on the transaction supplement.
	 */
	private double mktValue;
	
	/**
	 * The tax capital basis rate. Relevant only to certain transaction types, such as Security Contributions. 
	 * See mhfstclo:T_CapBasis for a full definition of tax capital basis.
	 */
	private double taxRate;
	
	/**
	 * The leftover amount if the split ratio is not an even fraction.
	 */
	private double cashFrac;
	
	/**
	 * The interest purchased or sold for the transaction on the supplementary record. 
	 * It may differ from the figure on the related main transaction advice.
	 */
	private double intps;
	
	/**
	 * The trade date relevant to the transaction on the supplementary record. 
	 * It may differ from the trade date on the related main transaction advice.
	 */
	private Date date1;
	
	/**
	 * The settlement date relevant to the transaction on the supplementary record. 
	 * It may differ from the date on the related main transaction advice.
	 */
	private Date date2;
	
	/**
	 * The user-defined code for the location of the security referred to on the transaction advice supplement. 
	 * It may differ from the location code on the related main transaction advice.
	 */
	private int location;

	private PortfolioHdr portfolio;
	
	/**
	 * A code designating a long or short term gain: 
	 * 'L' (76) - Long Term; 
	 * 'S' (83) - Short Term; 
	 * 'M' (77) - Mini ("short short" - mutual funds use only).
	 */
	private int lorS;

	/**
	 * For security contributions for TII (Treasury Inflation Indexed) securities, the inflation ratio. 
	 * This is the ratio of the consumer price index at the time of the security's issue, to the index as of the trade date.
	 */
	private double inflationRatio;
	
	/**
	 * For private placement commitment records, a system-generated number uniquely identifying the connected commitment record from pamcomm.
	 */
	private int commitNum;
	
	/**
	 * For wash sale loss deferral transactions, the PAM-assigned transaction number that created the replacement acquisition lot in mhfsttrn.
	 */
	private int replaceLotTaNum;
	
	/**
	 * For wash sale loss deferral transactions, the PAM-assigned lot number in mhfstsum for the replacement acquisition lot
	 */
	private int replaceLotNumber;
	
	private double fxRate;
	
	/**
	 * For Conversions, Tax Free Exchanges and Taxable Exchanges, the Original Face Value of the security received.
	 */
	private double ofv;

	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="TranSup_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	@JoinColumn(name="cusip")
	public IssueInfo getCusip() {
		return cusip;
	}

	public void setCusip(IssueInfo cusip) {
		this.cusip = cusip;
	}

	@Column(name="AltDate")
	public Date getAltDate() {
		return altDate;
	}

	public void setAltDate(Date altDate) {
		this.altDate = altDate;
	}

	@Column(name="AltRate")
	public double getAltRate() {
		return altRate;
	}

	public void setAltRate(double altRate) {
		this.altRate = altRate;
	}

	@Column(name="amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name="basisNumber")
	public double getBasisNumber() {
		return basisNumber;
	}

	public void setBasisNumber(double basisNumber) {
		this.basisNumber = basisNumber;
	}

	@Column(name="basicDenom")
	public double getBasicDenom() {
		return basicDenom;
	}

	public void setBasicDenom(double basicDenom) {
		this.basicDenom = basicDenom;
	}

	@Column(name="basis")
	public double getBasis() {
		return basis;
	}

	public void setBasis(double basis) {
		this.basis = basis;
	}

	@Column(name="mktValue")
	public double getMktValue() {
		return mktValue;
	}

	public void setMktValue(double mktValue) {
		this.mktValue = mktValue;
	}

	@Column(name="taxRate")
	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	@Column(name="cashFrac")
	public double getCashFrac() {
		return cashFrac;
	}

	public void setCashFrac(double cashFrac) {
		this.cashFrac = cashFrac;
	}

	@Column(name="intps")
	public double getIntps() {
		return intps;
	}

	public void setIntps(double intps) {
		this.intps = intps;
	}

	@Column(name="date1")
	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	@Column(name="date2")
	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	@Column(name="location")
	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="portfolio")
	public PortfolioHdr getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(PortfolioHdr portfolio) {
		this.portfolio = portfolio;
	}

	@Column(name="lorS")
	public int getLorS() {
		return lorS;
	}

	public void setLorS(int lorS) {
		this.lorS = lorS;
	}
	
	@Column(name="inflationRatio")
	public double getInflationRatio() {
		return inflationRatio;
	}

	public void setInflationRatio(double inflationRatio) {
		this.inflationRatio = inflationRatio;
	}

	@Column(name="commitNum")
	public int getCommitNum() {
		return commitNum;
	}

	public void setCommitNum(int commitNum) {
		this.commitNum = commitNum;
	}

	@Column(name="replaceLotTaNum")
	public int getReplaceLotTaNum() {
		return replaceLotTaNum;
	}

	public void setReplaceLotTaNum(int replaceLotTaNum) {
		this.replaceLotTaNum = replaceLotTaNum;
	}

	@Column(name="replaceLotNumber")
	public int getReplaceLotNumber() {
		return replaceLotNumber;
	}

	public void setReplaceLotNumber(int replaceLotNumber) {
		this.replaceLotNumber = replaceLotNumber;
	}

	@Column(name="fxRate")
	public double getFxRate() {
		return fxRate;
	}

	public void setFxRate(double fxRate) {
		this.fxRate = fxRate;
	}

	@Column(name="ofv")
	public double getOfv() {
		return ofv;
	}

	public void setOfv(double ofv) {
		this.ofv = ofv;
	}
}
