package com.orm.obj;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		YGZTaiZhang
 * @author  		Pan Wei 
 * @Date 			2018年4月18日 上午11:09:57 	
 * @Description		[营改增台账表]  
 */

@Entity 
@Table(name="JXV_YGZTAIZHANG") 
public class YGZTaiZhang extends BasicEntity {
	private int id;
	
	/**
	 * 投资组合
	 */
	private String portnum;
	
	//日期
	private Date ledgerDate;
	
	//税费ID
	private int taxFeeType;
	
	//证券类型
	private String issueType;
	
	//明细品种,rpsectype表 tablecode=1,grouplevel=3的值
	private int rpSecType;
	
	//证券ID
	private String cusip;
	
	//交易市场
	private String exchangeID;
	
	//应税方式	应税/免税
	private boolean ysfs;
	
	//上一日税费基准余额
	private double syrsfjzye;
	
	//当日税费基准余额
	private double drsfjzye;
	
	//上一日税费余额
	private double syrsfye;
	
	//当日税费基准发生额
	private double drsfjzfse;
	
	//当日税费发生额
	private double drsffse;
	
	//当日税费余额
	private double drsfye;
	
	//上一日科目余额
	private double syrkmye;
	
	//当日凭证计提
	private double drpzjt;
	
	//当日科目余额
	private double drkmye;
	
	//当日抵扣金额
	private double drdkje;
	
	//历史抵扣金额
	private double lsdkje;
	
	//历史基准余额,用于抵扣计算
	private double lsjzye;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="YGZTaiZhang_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获取投资组合
	 * @return	投资组合编号
	 */
	@Column(name="portnum")
	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	@Column(name="ledgerDate")
	public Date getLedgerDate() {
		return ledgerDate;
	}

	public void setLedgerDate(Date ledgerDate) {
		this.ledgerDate = ledgerDate;
	}

	@Column(name="taxFeeType")
	public int getTaxFeeType() {
		return taxFeeType;
	}

	public void setTaxFeeType(int taxFeeType) {
		this.taxFeeType = taxFeeType;
	}

	@Column(name="issueType")
	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	@Column(name="rpSecType")
	public int getRpSecType() {
		return rpSecType;
	}

	public void setRpSecType(int rpSecType) {
		this.rpSecType = rpSecType;
	}

	@Column(name="cusip")
	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	@Column(name="exchangeID")
	public String getExchangeID() {
		return exchangeID;
	}

	public void setExchangeID(String exchangeID) {
		this.exchangeID = exchangeID;
	}

	@Column(name="ysfs",columnDefinition="integer",length=1)
	public boolean isYsfs() {
		return ysfs;
	}

	public void setYsfs(boolean ysfs) {
		this.ysfs = ysfs;
	}

	@Column(name="syrsfjzye")
	public double getSyrsfjzye() {
		return syrsfjzye;
	}

	public void setSyrsfjzye(double syrsfjzye) {
		this.syrsfjzye = syrsfjzye;
	}

	@Column(name="drsfjzye")
	public double getDrsfjzye() {
		return drsfjzye;
	}

	public void setDrsfjzye(double drsfjzye) {
		this.drsfjzye = drsfjzye;
	}

	@Column(name="syrsfye")
	public double getSyrsfye() {
		return syrsfye;
	}

	public void setSyrsfye(double syrsfye) {
		this.syrsfye = syrsfye;
	}

	@Column(name="drsffse")
	public double getDrsffse() {
		return drsffse;
	}

	public void setDrsffse(double drsffse) {
		this.drsffse = drsffse;
	}

	@Column(name="drsfye")
	public double getDrsfye() {
		return drsfye;
	}

	public void setDrsfye(double drsfye) {
		this.drsfye = drsfye;
	}

	@Column(name="syrkmye")
	public double getSyrkmye() {
		return syrkmye;
	}

	public void setSyrkmye(double syrkmye) {
		this.syrkmye = syrkmye;
	}

	@Column(name="drpzjt")
	public double getDrpzjt() {
		return drpzjt;
	}

	public void setDrpzjt(double drpzjt) {
		this.drpzjt = drpzjt;
	}

	@Column(name="drkmye")
	public double getDrkmye() {
		return drkmye;
	}

	public void setDrkmye(double drkmye) {
		this.drkmye = drkmye;
	}

	@Column(name="drdkje")
	public double getDrdkje() {
		return drdkje;
	}

	public void setDrdkje(double drdkje) {
		this.drdkje = drdkje;
	}

	@Column(name="drsfjzfse")
	public double getDrsfjzfse() {
		return drsfjzfse;
	}

	public void setDrsfjzfse(double drsfjzfse) {
		this.drsfjzfse = drsfjzfse;
	}

	@Column(name="lsdkje")
	public double getLsdkje() {
		return lsdkje;
	}

	public void setLsdkje(double lsdkje) {
		this.lsdkje = lsdkje;
	}

	@Column(name="lsjzye")
	public double getLsjzye() {
		return lsjzye;
	}

	public void setLsjzye(double lsjzye) {
		this.lsjzye = lsjzye;
	}
}
