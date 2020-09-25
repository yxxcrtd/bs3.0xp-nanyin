package com.jixu.data.pojo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName 		Balance
 * @author  		Pan Wei 
 * @Date 			2018年1月1日 下午10:20:43 	
 * @Description		[]  
 * @return  
 */
@Entity 
@Table(name="Balance") 
public class Balance implements Serializable{

	private static final long serialVersionUID = 3191517453821052514L;

	private int id;

	/**
	 * 余额表日期
	 */
	private Date asOfDate;
	
	private String portnum;
	
	/**
	 * 投资组合代码
	 */
	private String portCode;
	
	/**
	 * 投资组合名称
	 */
	private String portName;
	
	/**
	 * 科目号
	 */
	private String accntNum;
	
	/**
	 * 科目名
	 */
	private String accntName;
	
	/**
	 * 第几级科目
	 */
	private int accntLevel;
	
	/**
	 * 科目类型,资产、负债等
	 */
	private int accntCategory;
	
	/**
	 * 是否末级科目
	 */
	private int accntIsLeaf;
	
	/**
	 * 借:本币
	 */
	private double drFunc;
	
	/**
	 * 贷:本币
	 */
	private double crFunc;
	
	/**
	 * 借:原币
	 */
	private double drLocal;
	
	/**
	 * 贷:原币
	 */
	private double crLocal;
	
	/**
	 * 货币
	 */
	private String localCur;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="AsOfDate")
	public Date getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(Date asOfDate) {
		this.asOfDate = asOfDate;
	}

	@Column(name="PortCode")
	public String getPortCode() {
		return portCode;
	}

	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}

	@Column(name="PortName")
	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	@Column(name="AccntNum")
	public String getAccntNum() {
		return accntNum;
	}

	public void setAccntNum(String accntNum) {
		this.accntNum = accntNum;
	}

	@Column(name="AccntName")
	public String getAccntName() {
		return accntName;
	}

	public void setAccntName(String accntName) {
		this.accntName = accntName;
	}

	@Column(name="AccntLevel")
	public int getAccntLevel() {
		return accntLevel;
	}

	public void setAccntLevel(int accntLevel) {
		this.accntLevel = accntLevel;
	}

	@Column(name="AccntCategory")
	public int getAccntCategory() {
		return accntCategory;
	}

	public void setAccntCategory(int accntCategory) {
		this.accntCategory = accntCategory;
	}
	

	@Column(name="DrFunc")
	public double getDrFunc() {
		return drFunc;
	}

	public void setDrFunc(double drFunc) {
		this.drFunc = drFunc;
	}

	@Column(name="CrFunc")
	public double getCrFunc() {
		return crFunc;
	}

	public void setCrFunc(double crFunc) {
		this.crFunc = crFunc;
	}

	@Column(name="DrLocal")
	public double getDrLocal() {
		return drLocal;
	}

	public void setDrLocal(double drLocal) {
		this.drLocal = drLocal;
	}

	@Column(name="CrLocal")
	public double getCrLocal() {
		return crLocal;
	}

	public void setCrLocal(double crLocal) {
		this.crLocal = crLocal;
	}

	@Column(name="AccntIsLeaf")
	public int getAccntIsLeaf() {
		return accntIsLeaf;
	}

	public void setAccntIsLeaf(int accntIsLeaf) {
		this.accntIsLeaf = accntIsLeaf;
	}

	@Column(name="localCur")
	public String getLocalCur() {
		return localCur;
	}

	public void setLocalCur(String localCur) {
		this.localCur = localCur;
	}

	@Column(name="portnum")
	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}
}
