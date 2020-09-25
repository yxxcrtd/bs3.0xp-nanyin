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
 * @ClassName 		SmfDiv
 * @author  		Pan Wei 
 * @Date 			2017年8月10日 上午10:50:01 	
 * @Description		[记录了公司行为的参数。在除权除息日(exdate)发生公司行为当天，需要查看以记录基准日(recordDate)该证券的头寸,如果有现金结转，在付款日(payDate)结转]  
 */

@Entity 
@Table(name="JXS_SmfDiv") 
public class SmfDiv extends BasicEntity {
	private int id;
	
	private int caType;
	
	private String cusip;

	//记录日,基于哪天得基准算股数,股权登记日
	private Date recordDate;
	
	//除权除息日
	private Date exDate;
	
	//付款日
	private Date payDate;
	
	private Date LastUpdate;
	
	private double rate;
	
	private int sourceID;
	
	//长期资本损益率
	private double ltCapGlRate;
	
	//短期资本损益率
	private double stCapGlRate;

	/**
	 * 获取 id
	 */
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="SmfDiv_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="RecordDate")
	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	@Column(name="PayDate")
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	@Column(name="LastUpdate")
	public Date getLastUpdate() {
		return LastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		LastUpdate = lastUpdate;
	}

	@Column(name="Rate")
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Column(name="SourceID")
	public int getSourceID() {
		return sourceID;
	}

	public void setSourceID(int sourceID) {
		this.sourceID = sourceID;
	}

	@Column(name="LtCapGlRate")
	public double getLtCapGlRate() {
		return ltCapGlRate;
	}

	public void setLtCapGlRate(double ltCapGlRate) {
		this.ltCapGlRate = ltCapGlRate;
	}


	@Column(name="StCapGlRate")
	public double getStCapGlRate() {
		return stCapGlRate;
	}

	public void setStCapGlRate(double stCapGlRate) {
		this.stCapGlRate = stCapGlRate;
	}

	
	@Column(name="ExDate")
	public Date getExDate() {
		return exDate;
	}

	public void setExDate(Date exDate) {
		this.exDate = exDate;
	}

	@Column(name="cusip")
	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	@Column(name="CaType")
	public int getCaType() {
		return caType;
	}

	public void setCaType(int caType) {
		this.caType = caType;
	}
}
