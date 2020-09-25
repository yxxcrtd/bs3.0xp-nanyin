package com.orm.obj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		CashFlowResetPlan
 * @author  		Pan Wei 
 * @Date 			2019年09月05日 	
 * @Description		[浮动利率的重置计划]  
 */

@Entity 
@Table(name="FIN_CASH_PLAN") 
public class CashFlowPlan implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 现金流代码
	 */
	@Id
	@Column(name="CASH_ID")
	private String cash_id;
	
	/**
	 * 现金流序号，从1开始
	 */
	@Column(name="CASH_NUM")
	private int cash_num;
	
	/**
	 * 现金流类型，中间还本、中间收息、到期还本、到期收息、费用支付等
	 */
	@Column(name="CASH_TYPE")
	private String cash_type;
	
	/**
	 * 未调整的计算开始日
	 */
	@Column(name="VDATE_UNADJUST")
	private Date vdate_unadjust;
	
	/**
	 * 未调整的计算结束日
	 */
	@Column(name="MDATE_UNADJUST")
	private Date mdate_unadjust;
	
	/**
	 * 未调整的计划支付日
	 */
	@Column(name="PAY_DATE_UNADJUST")
	private Date pay_date_unadjust;
	
	/**
	 * 调整后的计算开始日
	 */
	@Column(name="VDATE")
	private Date vDate;
	
	/**
	 * 调整后的计算结束日
	 */
	@Column(name="MDATE")
	private Date mDate;
	
	/**
	 * 调整后的计划支付日
	 */
	@Column(name="PAY_DATE")
	private Date pay_date;
	
//	/**
//	 * 计算周期天数
//	 */
//	@Column(name="TERMDAYS")
//	private int termdays;
//	
//	/**
//	 * 理论计息年度开始日，A/A_BOND适用
//	 */
//	@Column(name="VDATE_Y")
//	private Date vDate_y;
//	
//	/**
//	 * 完整周期开始日
//	 */
//	@Column(name="VDATE_TERM")
//	private Date vDate_term;
//	
//	/**
//	 * 完整周期结束日
//	 */
//	@Column(name="MDATE_TERM")
//	private Date mDate_term;
//	
//	/**
//	 * 完整周期天数
//	 */
//	@Column(name="TERMDAYS_TERM")
//	private int termdays_term;
//	
//	/**
//	 * 计划支付本金，针对现金流类型为本金的
//	 */
//	@Column(name="PRIN_AMT")
//	private double prin_amt;
//	
//	/**
//	 * 计息本金，存计划支付利息对应的本金
//	 */
//	@Column(name="INT_PRIN_AMT")
//	private double int_prin_amt;
//	
//	/**
//	 * 计划支付利息，还本计息区间对应的利息
//	 */
//	@Column(name="INT_AMT")
//	private double int_mat;
//	
//	/**
//	 * 是否支付利息，针对现金流类型为本金的
//	 */
//	@Column(name="IS_PAY_INT")
//	private String is_pay_int;
//	
//	/**
//	 * 计划支付现金流，如果支付利息：计划支付本金+计划支付利息。如果不支付利息：计划支付本金
//	 */
//	@Column(name="CASH_AMT")
//	private double cash_amt;
//	
//	/**
//	 * 现金流基数，存100或者总金额的具体数值
//	 */
//	@Column(name="CASH_BASEAMT")
//	private double cash_baseamt;
//	
//	/**
//	 * 现金流单位，单位、百元、 总金额
//	 */
//	@Column(name="CASH_UNIT_TYPE")
//	private String cash_unit_type;
//	
//	/**
//	 * 计息算法，分摊法、累计法，目前无
//	 */
//	@Column(name="CALC_FUNCTION")
//	private String calc_function;
//	
//	/**
//	 * 年付息次数
//	 */
//	@Column(name="FREQUENCY")
//	private int frequency;
//	
//	/**
//	 * 金融产品代码
//	 */
//	@Column(name="FINPROD_ID")
//	private String finprod_id;
//	
//	/**
//	 * 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
//	 */
//	@Column(name="FINPROD_TYPE")
//	private String finprod_type;
//	
//	/**
//	 * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
//	 */
//	@Column(name="FINPROD_TYPE2")
//	private String finprod_type2;
//	
//	/**
//	 * 分支序号
//	 */
//	@Column(name="BRANCH")
//	private int branch;
//	
//	/**
//	 * 支付方式，现金、红利再投等
//	 */
//	@Column(name="PAY_TYPE")
//	private String pay_type;
//	
//	/**
//	 * 区间净收益率
//	 */
//	@Column(name="RANGE_YIELD")
//	private double range_yield;
//	
//	/**
//	 * 备注
//	 */
//	@Column(name="REMARK")
//	private String remark;

	public String getCash_id() {
		return cash_id;
	}

	public void setCash_id(String cash_id) {
		this.cash_id = cash_id;
	}

	public int getCash_num() {
		return cash_num;
	}

	public void setCash_num(int cash_num) {
		this.cash_num = cash_num;
	}

	public String getCash_type() {
		return cash_type;
	}

	public void setCash_type(String cash_type) {
		this.cash_type = cash_type;
	}

	public Date getVdate_unadjust() {
		return vdate_unadjust;
	}

	public void setVdate_unadjust(Date vdate_unadjust) {
		this.vdate_unadjust = vdate_unadjust;
	}

	public Date getMdate_unadjust() {
		return mdate_unadjust;
	}

	public void setMdate_unadjust(Date mdate_unadjust) {
		this.mdate_unadjust = mdate_unadjust;
	}

	public Date getPay_date_unadjust() {
		return pay_date_unadjust;
	}

	public void setPay_date_unadjust(Date pay_date_unadjust) {
		this.pay_date_unadjust = pay_date_unadjust;
	}

	public Date getvDate() {
		return vDate;
	}

	public void setvDate(Date vDate) {
		this.vDate = vDate;
	}

	public Date getmDate() {
		return mDate;
	}

	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}

//	public int getTermdays() {
//		return termdays;
//	}
//
//	public void setTermdays(int termdays) {
//		this.termdays = termdays;
//	}
//
//	public Date getvDate_y() {
//		return vDate_y;
//	}
//
//	public void setvDate_y(Date vDate_y) {
//		this.vDate_y = vDate_y;
//	}
//
//	public Date getvDate_term() {
//		return vDate_term;
//	}
//
//	public void setvDate_term(Date vDate_term) {
//		this.vDate_term = vDate_term;
//	}
//
//	public Date getmDate_term() {
//		return mDate_term;
//	}
//
//	public void setmDate_term(Date mDate_term) {
//		this.mDate_term = mDate_term;
//	}
//
//	public int getTermdays_term() {
//		return termdays_term;
//	}
//
//	public void setTermdays_term(int termdays_term) {
//		this.termdays_term = termdays_term;
//	}
//
//	public double getPrin_amt() {
//		return prin_amt;
//	}
//
//	public void setPrin_amt(double prin_amt) {
//		this.prin_amt = prin_amt;
//	}
//
//	public double getInt_prin_amt() {
//		return int_prin_amt;
//	}
//
//	public void setInt_prin_amt(double int_prin_amt) {
//		this.int_prin_amt = int_prin_amt;
//	}
//
//	public double getInt_mat() {
//		return int_mat;
//	}
//
//	public void setInt_mat(double int_mat) {
//		this.int_mat = int_mat;
//	}
//
//	public String getIs_pay_int() {
//		return is_pay_int;
//	}
//
//	public void setIs_pay_int(String is_pay_int) {
//		this.is_pay_int = is_pay_int;
//	}
//
//	public double getCash_amt() {
//		return cash_amt;
//	}
//
//	public void setCash_amt(double cash_amt) {
//		this.cash_amt = cash_amt;
//	}
//
//	public double getCash_baseamt() {
//		return cash_baseamt;
//	}
//
//	public void setCash_baseamt(double cash_baseamt) {
//		this.cash_baseamt = cash_baseamt;
//	}
//
//	public String getCash_unit_type() {
//		return cash_unit_type;
//	}
//
//	public void setCash_unit_type(String cash_unit_type) {
//		this.cash_unit_type = cash_unit_type;
//	}
//
//	public String getCalc_function() {
//		return calc_function;
//	}
//
//	public void setCalc_function(String calc_function) {
//		this.calc_function = calc_function;
//	}
//
//	public int getFrequency() {
//		return frequency;
//	}
//
//	public void setFrequency(int frequency) {
//		this.frequency = frequency;
//	}
//
//	public String getFinprod_id() {
//		return finprod_id;
//	}
//
//	public void setFinprod_id(String finprod_id) {
//		this.finprod_id = finprod_id;
//	}
//
//	public String getFinprod_type() {
//		return finprod_type;
//	}
//
//	public void setFinprod_type(String finprod_type) {
//		this.finprod_type = finprod_type;
//	}
//
//	public String getFinprod_type2() {
//		return finprod_type2;
//	}
//
//	public void setFinprod_type2(String finprod_type2) {
//		this.finprod_type2 = finprod_type2;
//	}
//
//	public int getBranch() {
//		return branch;
//	}
//
//	public void setBranch(int branch) {
//		this.branch = branch;
//	}
//
//	public String getPay_type() {
//		return pay_type;
//	}
//
//	public void setPay_type(String pay_type) {
//		this.pay_type = pay_type;
//	}
//
//	public double getRange_yield() {
//		return range_yield;
//	}
//
//	public void setRange_yield(double range_yield) {
//		this.range_yield = range_yield;
//	}
//
//	public String getRemark() {
//		return remark;
//	}
//
//	public void setRemark(String remark) {
//		this.remark = remark;
//	}
}
