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
 * @ClassName 		SecContract
 * @author  		Pan Wei 
 * @Date 			2018年8月27日 下午12:28:48 	
 * @Description		[主要给予smf是合同的资产类型辅助支持作用]  
 */
@Entity 
@Table(name="JXS_SECCONTRACT") 
public class SecContract extends BasicEntity implements Serializable {

	private static final long serialVersionUID = 7564219316870034014L;
	
	private int id;
	
	/**
	 * 证券ID
	 */
	private String cusip;
	
	/**
	 * 做多货币ID
	 */
	private String longCurID;
	
	/**
	 * 做多货币金额
	 */
	private double longCash;
	
	/**
	 * 做空货币ID
	 */
	private String shortCurID;
	
	/**
	 * 做空货币金额
	 */
	private double shortCash;
	
	/**
	 * 期货类型
	 */
	private int futuresType;
	
	/**
	 * 合约标的
	 */
	private int futuresTarget;
	
	/**
	 * 保证金
	 */
	private double marginRate;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="SecContract_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="cusip")
	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	@Column(name="LONGCURID")
	public String getLongCurID() {
		return longCurID;
	}

	public void setLongCurID(String longCurID) {
		this.longCurID = longCurID;
	}

	@Column(name="longCash")
	public double getLongCash() {
		return longCash;
	}

	public void setLongCash(double longCash) {
		this.longCash = longCash;
	}

	@Column(name="SHORTCURID")
	public String getShortCurID() {
		return shortCurID;
	}

	public void setShortCurID(String shortCurID) {
		this.shortCurID = shortCurID;
	}

	@Column(name="shortCash")
	public double getShortCash() {
		return shortCash;
	}

	public void setShortCash(double shortCash) {
		this.shortCash = shortCash;
	}

	@Column(name="futuresType")
	public int getFuturesType() {
		return futuresType;
	}

	public void setFuturesType(int futuresType) {
		this.futuresType = futuresType;
	}

	@Column(name="futuresTarget")
	public int getFuturesTarget() {
		return futuresTarget;
	}

	public void setFuturesTarget(int futuresTarget) {
		this.futuresTarget = futuresTarget;
	}

	@Column(name="marginRate")
	public double getMarginRate() {
		return marginRate;
	}

	public void setMarginRate(double marginRate) {
		this.marginRate = marginRate;
	}
	
}
