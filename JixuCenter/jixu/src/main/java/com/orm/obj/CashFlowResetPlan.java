package com.orm.obj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName 		CashFlowResetPlan
 * @author  		Pan Wei 
 * @Date 			2019年09月05日 	
 * @Description		[浮动利率的重置计划]  
 */

@Entity 
@Table(name="FIN_CASH_RESET_PLAN") 
public class CashFlowResetPlan implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 现金流ID
	 */
	@Id
	@Column(name="CASH_ID")
	private String cash_id;
	
	/**
	 * 调整前重置日
	 */
	@Column(name="RESET_DATE_UNADJUST")
	private Date reset_date_unadjust;
	
	/**
	 * 调整后重置日
	 */
	@Column(name="RESET_DATE")
	private Date reset_date;
	
	/**
	 * 利率确认日
	 */
	@Column(name="CONFIRM_DATE")
	private Date confirm_date;
	
	/**
	 * 金融产品代码
	 */
	@Column(name="FINPROD_ID")
	private String finprod_id;
	
	/**
	 * 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
	 */
	@Column(name="FINPROD_TYPE")
	private String finprod_type;
	
	/**
	 * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
	 */
	@Column(name="FINPROD_TYPE2")
	private String finprod_type2;
	
	/**
	 * 分支序号
	 */
	@Column(name="BRANCH")
	private int branch;

	public String getCash_id() {
		return cash_id;
	}

	public void setCash_id(String cash_id) {
		this.cash_id = cash_id;
	}

	public Date getReset_date_unadjust() {
		return reset_date_unadjust;
	}

	public void setReset_date_unadjust(Date reset_date_unadjust) {
		this.reset_date_unadjust = reset_date_unadjust;
	}

	public Date getReset_date() {
		return reset_date;
	}

	public void setReset_date(Date reset_date) {
		this.reset_date = reset_date;
	}

	public Date getConfirm_date() {
		return confirm_date;
	}

	public void setConfirm_date(Date confirm_date) {
		this.confirm_date = confirm_date;
	}

	public String getFinprod_id() {
		return finprod_id;
	}

	public void setFinprod_id(String finprod_id) {
		this.finprod_id = finprod_id;
	}

	public String getFinprod_type() {
		return finprod_type;
	}

	public void setFinprod_type(String finprod_type) {
		this.finprod_type = finprod_type;
	}

	public String getFinprod_type2() {
		return finprod_type2;
	}

	public void setFinprod_type2(String finprod_type2) {
		this.finprod_type2 = finprod_type2;
	}

	public int getBranch() {
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}
}
