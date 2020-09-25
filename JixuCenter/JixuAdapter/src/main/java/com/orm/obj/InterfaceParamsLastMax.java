package com.orm.obj;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.util.DateUtil;

@Entity 
@Table(name="ADPT_InterfaceParamsLastMax") 
public class InterfaceParamsLastMax {
	private int id;
	
	private int cfgID;
	
	private String lastValStr;
	
	private double lastValDouble;
	
	private Date lastValDate;
	
	private Date lastExeTime;
	
	public InterfaceParamsLastMax() {
		this.setLastValDouble(0);
		this.setLastValDate(DateUtil.convertStr2Date("20000101"));
		this.setLastValStr("");
		this.setLastExeTime(new Date());
	}

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="ADPT_IParamsLastMax_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="cfgID")
	public int getCfgID() {
		return cfgID;
	}

	public void setCfgID(int cfgID) {
		this.cfgID = cfgID;
	}

	@Column(name="lastValStr")
	public String getLastValStr() {
		return lastValStr;
	}

	public void setLastValStr(String lastValStr) {
		this.lastValStr = lastValStr;
	}

	@Column(name="lastValDouble")
	public double getLastValDouble() {
		return lastValDouble;
	}

	public void setLastValDouble(double lastValDouble) {
		this.lastValDouble = lastValDouble;
	}

	@Column(name="lastValDate")
	public Date getLastValDate() {
		return lastValDate;
	}

	public void setLastValDate(Date lastValDate) {
		this.lastValDate = lastValDate;
	}

	@Column(name="lastExeTime")
	public Date getLastExeTime() {
		return lastExeTime;
	}

	public void setLastExeTime(Date lastExeTime) {
		this.lastExeTime = lastExeTime;
	}
}
