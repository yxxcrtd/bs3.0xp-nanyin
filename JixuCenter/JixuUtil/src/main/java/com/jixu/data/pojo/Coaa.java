package com.jixu.data.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName 		Coaa
 * @author  		Pan Wei 
 * @Date 			2017年11月29日 下午3:00:17 	
 * @Description		[]  
 * @return  
 */

@Entity 
@Table(name="Coaa") 
public class Coaa implements Serializable {

	private static final long serialVersionUID = 8280000322189638655L;
	
	private int id;

	private int coaaID;
	
	private int accntCateGory;
	
	private String accntName;
	
	private String accntNum;
	
	private int chartNum;
	
	private  int acctLevel;
	
	private int acctIsLeaf;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="coaaID")
	public int getCoaaID() {
		return coaaID;
	}

	public void setCoaaID(int coaaID) {
		this.coaaID = coaaID;
	}

	@Column(name="accntCateGory")
	public int getAccntCateGory() {
		return accntCateGory;
	}

	public void setAccntCateGory(int accntCateGory) {
		this.accntCateGory = accntCateGory;
	}

	@Column(name="accntName")
	public String getAccntName() {
		return accntName;
	}

	public void setAccntName(String accntName) {
		this.accntName = accntName;
	}

	@Column(name="accntNum")
	public String getAccntNum() {
		return accntNum;
	}

	public void setAccntNum(String accntNum) {
		this.accntNum = accntNum;
	}

	@Column(name="chartNum")
	public int getChartNum() {
		return chartNum;
	}

	public void setChartNum(int chartNum) {
		this.chartNum = chartNum;
	}

	@Column(name="acctLevel")
	public int getAcctLevel() {
		return acctLevel;
	}

	public void setAcctLevel(int acctLevel) {
		this.acctLevel = acctLevel;
	}

	@Column(name="acctIsLeaf")
	public int getAcctIsLeaf() {
		return acctIsLeaf;
	}

	public void setAcctIsLeaf(int acctIsLeaf) {
		this.acctIsLeaf = acctIsLeaf;
	}
}
