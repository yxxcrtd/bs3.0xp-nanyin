package com.orm.obj;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;


/** 
 * @ClassName 		CashFlowFloatRate
 * @author  		Pan Wei 
 * @Date 			2017年5月27日 下午10:56:52 	
 * @Description		[现金流配置的浮动利率] 
 */
@Entity 
@Table(name="JXS_CASHFLOWFLOATRATE")
public class CashFlowFloatRate extends BasicEntity{

	private int id;
	
	/**
	 * 现金流ID
	 */
	private String cfID="0";
	/**
	 * 利率重置日期
	 */
	private Date rateResetDate;
	/**
	 * 利率最大值
	 */
	private double maxRate;
	/**
	 * 利率最小值
	 */
	private double minRate;
	/**
	 * 利率基准类
	 */
	private String basisClass;
	/**
	 * 规则公式
	 */
	private String ruleParams;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="CashFlowFloatRate_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 获取RateResetDate
	 */
	@Column(name="RateResetDate")
	public Date getRateResetDate() {
		return rateResetDate;
	}
	/**
	 * 设置RateResetDate
	 */
	public void setRateResetDate(Date rateResetDate) {
		this.rateResetDate = rateResetDate;
	}
	
	/**
	 * cashflowID
	 */
	@Column(name="CfID")
	public String getCfID() {
		return cfID;
	}
	public void setCfID(String cfID) {
		this.cfID = cfID;
	}
	
	@Column(name="MaxRate")
	public double getMaxRate() {
		return maxRate;
	}
	public void setMaxRate(double maxRate) {
		this.maxRate = maxRate;
	}
	
	@Column(name="MinRate")
	public double getMinRate() {
		return minRate;
	}
	public void setMinRate(double minRate) {
		this.minRate = minRate;
	}
	
	@Column(name="BasisClass")
	public void setBasisClass(String basisClass) {
		this.basisClass = basisClass;
	}
	public String getBasisClass() {
		return basisClass;
	}

	
	@Column(name="RuleParams")
	public String getRuleParams() {
		return ruleParams;
	}
	public void setRuleParams(String ruleParams) {
		this.ruleParams = ruleParams;
	}

}
