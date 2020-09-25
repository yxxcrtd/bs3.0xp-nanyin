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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jixu.common.pojo.BasicEntity;
import com.jixu.common.util.DateUtil;

/** 
 * @ClassName 		InterfaceMsgColMap
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[现金流配置表,用于利息等收益类产品的配置]  
 */

@Entity 
@Table(name="JXS_CASHFLOWCFG")
public class CashFlowCfg extends BasicEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cashFlowID="0";

	/**
	 * 名字
	 */
	private String cashFlowName;
	
	/**
	 * 是否开启使用 
	 */
	private int activeFlag;
	
	/**
	 * 支出还是收入
	 */
	private int recOrPay;
	
	/**
	 * 交易日N天后开始计息
	 */
	private int startNDays;
	
	/**
	 * N天的类型,自然日/交易日
	 */
	private int startNDaysType;
	
	/**
	 * 以交易日还是结算日为基准
	 */
	private int startNDaysBase;
	
	/**
	 * 以结束交易日的结算日还是交易日为基准
	 */
	private int endNDaysBase;
	
	/**
	 * 结算日是交易日的T+N天
	 */
	private int endNDays;
	
	/**
	 * N天的类型,自然日/交易日
	 */
	private int endNdaysType;
	
	/**
	 * 是否使用外部利率配置
	 */
	private boolean externalRateFlag;
	
	/**
	 * 固定计提数量
	 */
	private double fixedIncome;
	
	/**
	 * The date as of which the cash flow information on this record was known.
	 */
	private int effectiveDate;
	
	/**
	 * 年华收益/年利率
	 * 如果没有勾选外部利率,计算每天收入利息将采用该字段
	 * The annual coupon rate, if applicable. For options, the strike price.
	 */
	private double coupon;
	
	/**
	 * 有效利率
	 */
	private double effectiveRate;
	
	/**
	 * 现金流利率类型 | 70:固定  86:浮动
	 */
	private int cpnType;
	
	/**
	 * 税费结转频率 | 65:每年	66:到期	77：每月	79:每N天	81:每个季度	83:每半年	84:自定义计划
	 */
	private int cpnFreq;
	
	/**
	 * 付息月份.
	 * 0 At Mat  
	* 1 January  
	* 2 February  
	* 3 March  
	* 4 April  
	* 5 May  
	* 6 June  
	* 7 July  
	* 8 August  
	* 9 September  
	* 10 October  
	* 11 November  
	* 12 December  
	* 13 Jan-Jul  
	* 14 Feb-Aug  
	* 15 Mar-Sep  
	* 16 Apr-Oct  
	* 17 May-Nov  
	* 18 Jun-Dec  
	* 19 J-A-J-O  
	* 20 F-M-A-N  
	* 21 M-J-S-D  
	* 25 IMM Roll  
	* 22 Monthly  
	* 23 Scheduled  
	* 24 Weekly 
	 */
	private int cpnMo;
	
	/**
	 * 付息的日期
	 */
	private int cpnDate;

	/**
	 * The date on which a bond makes its first interest payment.
	 */
	private Date firstIntDate;
	
	/**
	 * 1.现金结转
	 * 2.现金转份额/股数
	 */
	private int paymentKind;
	
	/**
	 * A code indicating the security's method of paying interest (e.g., 30/360 Coupons).
	 *	'A' 65 30/360 Coupons  
		'F' 70 30/360 Coupons (European)  
		'B' 66 Act/Act Coupons (Treasury)  
		'C' 67 Act/365 Coupons (Fixed)  
		'D' 68 Act/360 Coupons (CD's)  
		'E' 69 Act/365 Coupons (CD's)  
		'J' 74 Act/Act Canadian MBS  
		'U' 85 Act/360 (Japan)  
		'H' 72 Act/365 (Japan)  
		'I' 73 Act/Act (Uneven Cpns)  
		'K' 75 Act/Act (French OATs)  
		'L' 76 Act/Act (Italian)  
		'O' 79 Act/Act (Non Treasury)  
		'X' 88 Act/365 (Asia Pacific)  
		'x' 120 Act/Act (Asia Pacific)  
		'M' 77 Act/360 Discount Notes  
		'Q' 81 Act/365 Discount Notes  
		'N' 78 Discount Basis  
		'P' 80 Act/360 at Maturity(CD's)  
		'R' 82 30/360 at Maturity (Muni)  
		'S' 83 Act/Act at Maturity  
		'T' 84 Zero Coupon Bond  
		'V' 86 Act/365 at Maturity  
		'Z' 90 Normal Normal 
		'W' 87 Money Market Mutual Fund 
	 */
	private int intCode;
	
	/**
	 * The holiday schedule code for a payment. Taken from mhfshdr:HolidaySched for the portfolio involved with the cash flow
	 */
	private String holidaySD;
	
	/**
	 * 1. 工作日
	 * 2. 自然日
	 */
	private int cpnDelayDateType;
	
	/**
	 * 推迟天数
	 */
	private int cpnDelayDateNum;
	
	/**
	 * The maturity date, if applicable, of the security
	 */
	private int matDate;
	
	/**
	 * For preferred stocks, the per share par value
	 */
	private double psPar;
	
	/**
	 * Applicable only to securities with a PrinType indicating scheduled payments with a prepayment allocation method. 
	 * This field indicates the prepayment allocation or regeneration method code
	 * 	'F' 70 Front End  
		'B' 66 Back End  
		'P' 80 Pro Rata  
		'L' 76 Level  
		'M' 77 Manual 
	 */
	private String prePayAllocMethod;
	
	/**
	 * A code indicating the type of mortgage backed security, if applicable.
	 * 	10 GNMA  
		11 GNMA SF  
		12 GNMA MH  
		13 GNMA Project Loans  
		14 GNMA CLC  
		15 GNMA GPM  
		20 GNMA2  
		21 GNMA2 SF  
		22 GNMA2 MH  
		23 GNMA2 GPM  
		24 GNMA2 ARM  
		30 FHLMC PC  
		31 FHLMC SF  
		32 FHLMC MF  
		33 FHLMC ARM  
		34 FHLMC Guarantor  
		35 FHLMC Giant  
		36 FHLMC Gold  
		40 FNMA  
		41 FNMA SF  
		42 FNMA MF  
		43 FNMA ARM  
		44 FNMA Mega  
		45 FNMA GPM  
		50 FHA Projects  
		60 Non-Agency  
		70 Whole Loans  
		80 Commercial R.E.  
		90 R.F.C.'s  
		95 SBA  
		96 Veterans Admin.  
		98 Other - AVR Exempt  
		99 Unknown 
	 */
	private int mtgType;
	
	/**
	 * The original term Weighted Average Maturity (WAM) for applicable security types.
	 */
	private int origTerm;
	
	/**
	 * The date, if any, on which a Level P/I type security will make a balloon payment (payoff of outstanding principal)
	 */
	private int balloonDate;
	
	/**
	 * The service fee, if any, paid to the issuing agency (e.g., GNMA) of a Level P/I type security
	 */
	private double svcFee;
	
	/**
	 * 	10 Sequential Pay  
		20 Accrual  
		30 Residual  
		99 Other  
		110 PAC Tranche  
		120 TAC Tranche  
		130 PAC2 Tranche  
		140 VADM Tranche  
		150 PAC Z Tranche  
		199 Other 
	 */
	private int trancheType;
	
	/**
	 * For CMOs and ABSs, the current prepayment rate.
	 */
	private double prepaymentRate;

	/**
	 * For CMOs and ABSs, the date the security begins paying down principal
	 */
	private int trancheStartDate;
	
	/**
	 * For CMOs and ABSs, the cash flow end date.
	 */
	private int trancheEndDate;
	
	/**
	 * A code indicating the payment model for certain security types:
		20 - MBS
		25 - CMO
		26 - ABS
		90 - Not Applicable
	 */
	private int paymentModel;
	
	/**
	 * 利率重置的假期安排，用在浮动利率等
	 */
	private String resetHolidaySched;
	
	/**
	 * A Y/N flag indicating whether a one-day interest accrual lag exists for this security. This type of lag exists if accrual takes place on the last day, 
	 * but not on the first day of the accrual period
	 */
	private String dayLag;
	
	/**
	 * 浮动利率基于哪个指数的值调整，可以指向cusip
	 */
	private String vrBase;
	
	/**
	 * 浮动利率的计算规则选择  相加基数，相乘系数，复利计算
	 */
	private int vrMethod;
	
	/**
	 * 浮动利率的辅助浮动规则，相加基数，相乘系数，复利计算的辅助计算数值
	 */
	private double vrBasisPtAdjustment;
	
	/**
	 * 浮动利率的最大值
	 */
	private double vrMax;
	
	/**
	 * 浮动利率的最小值
	 */
	private double vrMin;
	
	/**
	 * 浮动利率重置的频率
	 */
	private int vrResetFreq;
	
	/**
	 * 浮动利率重置的月份
	 */
	private int vrResetMo;
	
	/**
	 * 浮动利率重置的日期
	 */
	private int vrResetDate;
	
	/**
	 * 如果vrResetFreq选择了跟随付款日，这里填写N，可以决定T+/-N
	 */
	private int vrResetNDays;
	
	/**
	 * 浮动利率调整月份
	 */
	private int vrDeterAdjMo;
	
	/**
	 * 浮动利率调整天数
	 */
	private int vrDeterAdjDays;
	
	/**
	 * For a floating rate instrument, a lockout period is the period during which the floating rate is locked in and does not fluctuate with changes in the underlying index. 
	 * This period begins on the day after the calculation date and ends on the date preceding the payment date. The LockoutPeriod field represents the number of business days in this period. 
	 * Not used for other types of securities.
	 */
	private int lockoutPeriod;
	
	/**
	 * For a floating rate instrument, the lookback period represents the period between (1) the date of the rate to be used for the floating rate interest calculation 
	 * during the lockout period and (2) the day preceding the calculation date, inclusive. The LookBackPeriod field represents the number of business days in this period. 
	 * Not used for other types of securities
	 */
	private int lookBackPeriod;
	
	/**
	 * The number of decimal places the "dirty" and "clean" price is to be rounded if the day count method (from IntCode) is one of the following:
		Act/Act French Oats
		Act/Act Italian
		Act/365 Asia Pacific
	 */
	private int priceDecimals;
	
	/**
	 * The number of decimal places the accrued interest factor is to be rounded when calculating accrued interest for any of the day count methods listed under PriceDecimals, 
	 * the preceding field.
	 */
	private int intDecimals;
	
	/**
	 * 是否自动结转利息
	 */
	private boolean autoSetFlag;
	
	private boolean autoInterestFlag;

	private Set<BkInteAcct> bkInteAcct;
	
	/**
	 * 还本计划
	 */
	private Set<CashFlowPar> cashFlowPar;
	
	public CashFlowCfg() {
		this.setFirstIntDate(DateUtil.convertStr2Date("20000101"));
	}
	
	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.func.GenCustomTBID_Joyin", parameters={
			@Parameter(name = "seq_name", value = "CASHFlOWCFG_ID_SEQ"),
			@Parameter(name = "short_name", value = "CF")})
	
	@Column(name="CASHFLOWID")
	public String getCashFlowID() {
		return cashFlowID;
	}

	public void setCashFlowID(String cashFlowID) {
		this.cashFlowID = cashFlowID;
	}
	
	@Column(name="CashFlowName")
	public String getCashFlowName() {
		return cashFlowName;
	}

	public void setCashFlowName(String cashFlowName) {
		this.cashFlowName = cashFlowName;
	}
	
	@Column(name="RecOrPay")
	public int getRecOrPay() {
		return recOrPay;
	}

	public void setRecOrPay(int recOrPay) {
		this.recOrPay = recOrPay;
	}

	@Column(name="effectiveDate")
	public int getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(int effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Column(name="coupon")
	public double getCoupon() {
		return coupon;
	}

	public void setCoupon(double coupon) {
		this.coupon = coupon;
	}

	@Column(name="cpnType")
	public int getCpnType() {
		return cpnType;
	}

	public void setCpnType(int cpnType) {
		this.cpnType = cpnType;
	}

	@Column(name="cpnFreq")
	public int getCpnFreq() {
		return cpnFreq;
	}

	public void setCpnFreq(int cpnFreq) {
		this.cpnFreq = cpnFreq;
	}

	@Column(name="cpnMo")
	public int getCpnMo() {
		return cpnMo;
	}

	public void setCpnMo(int cpnMo) {
		this.cpnMo = cpnMo;
	}

	@Column(name="cpnDate")
	public int getCpnDate() {
		return cpnDate;
	}

	public void setCpnDate(int cpnDate) {
		this.cpnDate = cpnDate;
	}

	@Column(name="firstIntDate")
	public Date getFirstIntDate() {
		return firstIntDate;
	}

	public void setFirstIntDate(Date firstIntDate) {
		this.firstIntDate = firstIntDate;
	}


	@Column(name="paymentKind")
	public int getPaymentKind() {
		return paymentKind;
	}

	public void setPaymentKind(int paymentKind) {
		this.paymentKind = paymentKind;
	}

	@Column(name="intCode")
	public int getIntCode() {
		return intCode;
	}

	public void setIntCode(int intCode) {
		this.intCode = intCode;
	}

	@Column(name="cpnDelayDateType")
	public int getCpnDelayDateType() {
		return cpnDelayDateType;
	}

	public void setCpnDelayDateType(int cpnDelayDateType) {
		this.cpnDelayDateType = cpnDelayDateType;
	}

	@Column(name="cpnDelayDateNum")
	public int getCpnDelayDateNum() {
		return cpnDelayDateNum;
	}

	public void setCpnDelayDateNum(int cpnDelayDateNum) {
		this.cpnDelayDateNum = cpnDelayDateNum;
	}

	@Column(name="matDate")
	public int getMatDate() {
		return matDate;
	}

	public void setMatDate(int matDate) {
		this.matDate = matDate;
	}

	@Column(name="psPar")
	public double getPsPar() {
		return psPar;
	}

	public void setPsPar(double psPar) {
		this.psPar = psPar;
	}

	@Column(name="prePayAllocMethod")
	public String getPrePayAllocMethod() {
		return prePayAllocMethod;
	}

	public void setPrePayAllocMethod(String prePayAllocMethod) {
		this.prePayAllocMethod = prePayAllocMethod;
	}

	@Column(name="mtgType")
	public int getMtgType() {
		return mtgType;
	}

	public void setMtgType(int mtgType) {
		this.mtgType = mtgType;
	}

	@Column(name="origTerm")
	public int getOrigTerm() {
		return origTerm;
	}

	public void setOrigTerm(int origTerm) {
		this.origTerm = origTerm;
	}

	@Column(name="balloonDate")
	public int getBalloonDate() {
		return balloonDate;
	}

	public void setBalloonDate(int balloonDate) {
		this.balloonDate = balloonDate;
	}

	@Column(name="svcFee")
	public double getSvcFee() {
		return svcFee;
	}

	public void setSvcFee(double svcFee) {
		this.svcFee = svcFee;
	}

	@Column(name="trancheType")
	public int getTrancheType() {
		return trancheType;
	}

	public void setTrancheType(int trancheType) {
		this.trancheType = trancheType;
	}

	@Column(name="prepaymentRate")
	public double getPrepaymentRate() {
		return prepaymentRate;
	}

	public void setPrepaymentRate(double prepaymentRate) {
		this.prepaymentRate = prepaymentRate;
	}

	@Column(name="trancheStartDate")
	public int getTrancheStartDate() {
		return trancheStartDate;
	}

	public void setTrancheStartDate(int trancheStartDate) {
		this.trancheStartDate = trancheStartDate;
	}

	@Column(name="trancheEndDate")
	public int getTrancheEndDate() {
		return trancheEndDate;
	}

	public void setTrancheEndDate(int trancheEndDate) {
		this.trancheEndDate = trancheEndDate;
	}

	@Column(name="paymentModel")
	public int getPaymentModel() {
		return paymentModel;
	}

	public void setPaymentModel(int paymentModel) {
		this.paymentModel = paymentModel;
	}

	@Column(name="RESETHOLIDAYSCHED")
	public String getResetHolidaySched() {
		return resetHolidaySched;
	}

	public void setResetHolidaySched(String resetHolidaySched) {
		this.resetHolidaySched = resetHolidaySched;
	}

	@Column(name="dayLag")
	public String getDayLag() {
		return dayLag;
	}

	public void setDayLag(String dayLag) {
		this.dayLag = dayLag;
	}
	
	@Column(name="lockoutPeriod")
	public int getLockoutPeriod() {
		return lockoutPeriod;
	}

	public void setLockoutPeriod(int lockoutPeriod) {
		this.lockoutPeriod = lockoutPeriod;
	}

	@Column(name="lookBackPeriod")
	public int getLookBackPeriod() {
		return lookBackPeriod;
	}

	public void setLookBackPeriod(int lookBackPeriod) {
		this.lookBackPeriod = lookBackPeriod;
	}

	@Column(name="priceDecimals")
	public int getPriceDecimals() {
		return priceDecimals;
	}

	public void setPriceDecimals(int priceDecimals) {
		this.priceDecimals = priceDecimals;
	}

	@Column(name="intDecimals")
	public int getIntDecimals() {
		return intDecimals;
	}

	public void setIntDecimals(int intDecimals) {
		this.intDecimals = intDecimals;
	}

	@OneToMany(mappedBy = "cashFlowCfg", cascade = CascadeType.REMOVE)
	public Set<BkInteAcct> getBkInteAcct() {
		return bkInteAcct;
	}

	public void setBkInteAcct(Set<BkInteAcct> bkInteAcct) {
		this.bkInteAcct = bkInteAcct;
	}

	@Column(name="HOLIDAYSD")
	public String getHolidaySD() {
		return holidaySD;
	}

	public void setHolidaySD(String holidaySD) {
		this.holidaySD = holidaySD;
	}

	@Column(name="StartNDays")
	public int getStartNDays() {
		return startNDays;
	}

	public void setStartNDays(int startNDays) {
		this.startNDays = startNDays;
	}

	@Column(name="StartNDaysType")
	public int getStartNDaysType() {
		return startNDaysType;
	}

	public void setStartNDaysType(int startNDaysType) {
		this.startNDaysType = startNDaysType;
	}

	@Column(name="ActiveFlag")
	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Column(name="EndNDays")
	public int getEndNDays() {
		return endNDays;
	}

	public void setEndNDays(int endNDays) {
		this.endNDays = endNDays;
	}

	@Column(name="EndNdaysType")
	public int getEndNdaysType() {
		return endNdaysType;
	}

	public void setEndNdaysType(int endNdaysType) {
		this.endNdaysType = endNdaysType;
	}

	@Column(name="ExternalRateFlag",columnDefinition="integer",length=1)
	public boolean isExternalRateFlag() {
		return externalRateFlag;
	}

	public void setExternalRateFlag(boolean externalRateFlag) {
		this.externalRateFlag = externalRateFlag;
	}

	@Column(name="EffectiveRate")
	public double getEffectiveRate() {
		return effectiveRate;
	}

	public void setEffectiveRate(double effectiveRate) {
		this.effectiveRate = effectiveRate;
	}

	@Column(name="FixedIncome")
	public double getFixedIncome() {
		return fixedIncome;
	}

	public void setFixedIncome(double fixedIncome) {
		this.fixedIncome = fixedIncome;
	}

	@Column(name="StartNDaysBase")
	public int getStartNDaysBase() {
		return startNDaysBase;
	}

	public void setStartNDaysBase(int startNDaysBase) {
		this.startNDaysBase = startNDaysBase;
	}

	@Column(name="autoSetFlag",columnDefinition="integer",length=1)
	public boolean isAutoSetFlag() {
		return autoSetFlag;
	}

	public void setAutoSetFlag(boolean autoSetFlag) {
		this.autoSetFlag = autoSetFlag;
	}

	@Column(name="autoInterestFlag",columnDefinition="integer",length=1)
	public boolean isAutoInterestFlag() {
		return autoInterestFlag;
	}

	public void setAutoInterestFlag(boolean autoInterestFlag) {
		this.autoInterestFlag = autoInterestFlag;
	}

	@Column(name="endNDaysBase")
	public int getEndNDaysBase() {
		return endNDaysBase;
	}

	public void setEndNDaysBase(int endNDaysBase) {
		this.endNDaysBase = endNDaysBase;
	}

	@OneToMany(mappedBy = "cfid", fetch=FetchType.EAGER)
	public Set<CashFlowPar> getCashFlowPar() {
		return cashFlowPar;
	}

	public void setCashFlowPar(Set<CashFlowPar> cashFlowPar) {
		this.cashFlowPar = cashFlowPar;
	}

	@Column(name="vrBase")
	public String getVrBase() {
		return vrBase;
	}

	public void setVrBase(String vrBase) {
		this.vrBase = vrBase;
	}

	@Column(name="vrMethod")
	public int getVrMethod() {
		return vrMethod;
	}

	public void setVrMethod(int vrMethod) {
		this.vrMethod = vrMethod;
	}
	
	@Column(name="vrBasisPtAdjustment")
	public double getVrBasisPtAdjustment() {
		return vrBasisPtAdjustment;
	}

	public void setVrBasisPtAdjustment(double vrBasisPtAdjustment) {
		this.vrBasisPtAdjustment = vrBasisPtAdjustment;
	}

	@Column(name="vrMax")
	public double getVrMax() {
		return vrMax;
	}

	public void setVrMax(double vrMax) {
		this.vrMax = vrMax;
	}

	@Column(name="vrMin")
	public double getVrMin() {
		return vrMin;
	}

	public void setVrMin(double vrMin) {
		this.vrMin = vrMin;
	}

	@Column(name="vrResetFreq")
	public int getVrResetFreq() {
		return vrResetFreq;
	}

	public void setVrResetFreq(int vrResetFreq) {
		this.vrResetFreq = vrResetFreq;
	}

	@Column(name="vrResetMo")
	public int getVrResetMo() {
		return vrResetMo;
	}

	public void setVrResetMo(int vrResetMo) {
		this.vrResetMo = vrResetMo;
	}

	@Column(name="vrResetDate")
	public int getVrResetDate() {
		return vrResetDate;
	}

	public void setVrResetDate(int vrResetDate) {
		this.vrResetDate = vrResetDate;
	}

	@Column(name="vrResetNDays")
	public int getVrResetNDays() {
		return vrResetNDays;
	}

	public void setVrResetNDays(int vrResetNDays) {
		this.vrResetNDays = vrResetNDays;
	}

	@Column(name="vrDeterAdjMo")
	public int getVrDeterAdjMo() {
		return vrDeterAdjMo;
	}

	public void setVrDeterAdjMo(int vrDeterAdjMo) {
		this.vrDeterAdjMo = vrDeterAdjMo;
	}

	@Column(name="vrDeterAdjDays")
	public int getVrDeterAdjDays() {
		return vrDeterAdjDays;
	}

	public void setVrDeterAdjDays(int vrDeterAdjDays) {
		this.vrDeterAdjDays = vrDeterAdjDays;
	}
}
