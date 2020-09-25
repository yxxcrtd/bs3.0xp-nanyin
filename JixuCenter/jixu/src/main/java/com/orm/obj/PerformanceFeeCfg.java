package com.orm.obj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		BkZGPerformanceFee
 * @author  		Pan Wei 
 * @Date 			2018年9月1日 下午23:59:03 	
 * @Description		[绩效费配置表，用于银行资管、私募等]  
 */


@Entity 
@Table(name="JXS_PerformanceFeeCfg") 
public class PerformanceFeeCfg extends BasicEntity implements Serializable {

	private static final long serialVersionUID = -8234604420547830939L;

	private int id;
	
	private String portnum;
	
	private String cusip;
	
	private int lotnum;
	
	/**
	 * 业绩基准
	 */
	private double benchMark;
	
	/**
	 * 收益率选择
	 */
	private int returnRateType;
	
	/**
	 * 费用基础，原始金额/剩余金额等
	 */
	private int expenseBasisType;
	
	/**
	 * 下限
	 */
	private double minLevel;
	
	/**
	 * 上限
	 */
	private double maxLevel;
	
	/**
	 * 低于下限是否回拨
	 */
	private boolean clawBackFlag;
	
	/**
	 * 用于回拨的费用类型
	 */
	private int clawBackExpenseType;
	
	/**
	 * 上下限内收取比例
	 */
	private double inPercentage;
	
	/**
	 * 上限外收取比例
	 */
	private double outPercentage;
	
	private String drAcct;
	
	private String crAcct;
	
	/**
	 *	是否激活使用 
	 */
	private boolean activeFlag;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="PerformanceFeeCfg_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="benchMark")
	public double getBenchMark() {
		return benchMark;
	}

	public void setBenchMark(double benchMark) {
		this.benchMark = benchMark;
	}

	@Column(name="returnRateType")
	public int getReturnRateType() {
		return returnRateType;
	}

	public void setReturnRateType(int returnRateType) {
		this.returnRateType = returnRateType;
	}

	@Column(name="expenseBasisType")
	public int getExpenseBasisType() {
		return expenseBasisType;
	}

	public void setExpenseBasisType(int expenseBasisType) {
		this.expenseBasisType = expenseBasisType;
	}

	@Column(name="minLevel")
	public double getMinLevel() {
		return minLevel;
	}

	public void setMinLevel(double minLevel) {
		this.minLevel = minLevel;
	}

	@Column(name="maxLevel")
	public double getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(double maxLevel) {
		this.maxLevel = maxLevel;
	}

	@Column(name="clawBackFlag",columnDefinition="integer",length=1)
	public boolean isClawBackFlag() {
		return clawBackFlag;
	}

	public void setClawBackFlag(boolean clawBackFlag) {
		this.clawBackFlag = clawBackFlag;
	}

	@Column(name="inPercentage")
	public double getInPercentage() {
		return inPercentage;
	}

	public void setInPercentage(double inPercentage) {
		this.inPercentage = inPercentage;
	}

	@Column(name="outPercentage")
	public double getOutPercentage() {
		return outPercentage;
	}

	public void setOutPercentage(double outPercentage) {
		this.outPercentage = outPercentage;
	}

	@Column(name="drAcct")
	public String getDrAcct() {
		return drAcct;
	}

	public void setDrAcct(String drAcct) {
		this.drAcct = drAcct;
	}

	@Column(name="crAcct")
	public String getCrAcct() {
		return crAcct;
	}

	public void setCrAcct(String crAcct) {
		this.crAcct = crAcct;
	}

	@Column(name="clawBackExpenseType")
	public int getClawBackExpenseType() {
		return clawBackExpenseType;
	}

	public void setClawBackExpenseType(int clawBackExpenseType) {
		this.clawBackExpenseType = clawBackExpenseType;
	}

	@Column(name="portnum")
	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	@Column(name="cusip")
	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	@Column(name="lotnum")
	public int getLotnum() {
		return lotnum;
	}

	public void setLotnum(int lotnum) {
		this.lotnum = lotnum;
	}

	@Column(name="activeFlag",columnDefinition="integer",length=1)
	public boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}
}
