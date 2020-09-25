package com.orm.obj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;
import com.jixu.common.pojo.InterfaceSource;

/** 
 * @ClassName 		ExchangeCfg
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[汇率 | 汇率的获取需要基准货币，交易货币，日期三个元素。比如人民币兑美元，2000年1月1日的汇率]  
 */

@Entity
@Table(name = "JXM_FX_RATE")
public class FxRate extends BasicEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// primary key
	private int id;

	// fields
	private Date lstUpdate;
	private double rate;
	private Date rateDate;

	// many to one
	private String currID;
	private String baseCurrID;
	private InterfaceSource sourceID;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="FxRate_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="LST_UPDATE")
	public Date getLstUpdate() {
		return lstUpdate;
	}

	public void setLstUpdate(Date lstUpdate) {
		this.lstUpdate = lstUpdate;
	}

	@Column(name="RATE")
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Column(name="RATE_DATE")
	public Date getRateDate() {
		return rateDate;
	}

	public void setRateDate(Date rateDate) {
		this.rateDate = rateDate;
	}

	@Column(name="CURR_ID")
	public String getCurrID() {
		return currID;
	}

	public void setCurrID(String currID) {
		this.currID = currID;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sourceID")
	public InterfaceSource getSourceID() {
		return sourceID;
	}
	public void setSourceID(InterfaceSource sourceID) {
		this.sourceID = sourceID;
	}

	@Column(name="BASECURRID")
	public String getBaseCurrID() {
		return baseCurrID;
	}

	public void setBaseCurrID(String baseCurrID) {
		this.baseCurrID = baseCurrID;
	}
}
