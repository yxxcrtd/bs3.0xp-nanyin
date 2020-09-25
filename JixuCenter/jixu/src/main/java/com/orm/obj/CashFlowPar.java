package com.orm.obj;

import java.io.Serializable;
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
 * @ClassName 		CashFlowPar
 * @author  		Wang Zhongyuan
 * @Date 			2017年5月27日 下午10:56:52 	
 * @Description		[现金流配置的还本计划] 
 */

@Entity 
@Table(name="JXS_CashFlowPar") 
public class CashFlowPar extends BasicEntity implements Serializable{

	private static final long serialVersionUID = 3835165420353957045L;

	private int id;
	
	/**
	 * 现金流ID
	 */
	private String cfid="0";
	
	/**
	 * 还本日期
	 */
	private Date paymentDate;
	
	/**
	 * 结转日
	 */
	private Date settleDate;
	
	/**
	 * 交易类型
	 * 按面额还本
	 * 按数量还本
	 */
	private int tranType;
	
	/**
	 *还本比例
	 */
	private double paymentRate;
	
	
	/**
	 * 获取 id
	 */
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="CashFlowPar_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	/**
	 * 设置 id
	 */
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="cfid")
	public String getCfid() {
		return cfid;
	}

	public void setCfid(String cfid) {
		this.cfid = cfid;
	}


	@Column(name="paymentDate")
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Column(name="paymentRate")
	public double getPaymentRate() {
		return paymentRate;
	}

	public void setPaymentRate(double paymentRate) {
		this.paymentRate = paymentRate;
	}

	@Column(name="settleDate")
	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	@Column(name="tranType")
	public int getTranType() {
		return tranType;
	}

	public void setTranType(int tranType) {
		this.tranType = tranType;
	}
}
