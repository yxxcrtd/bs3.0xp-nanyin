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

/** 
 * @ClassName 		TranFeeFollowClo
 * @author  		Pan Wei 
 * @Date 			2018年6月7日 上午1:19:09 	
 * @Description		[]   
 */

@Entity 
@Table(name="JXT_TranFeeFollowClo") 
public class TranFeeFollowClo extends BasicEntity implements Serializable{

	private static final long serialVersionUID = -7734044176661832682L;

	private int id;
	
	private TranClo tranClo;
	
	private String portnum;
	
	private String cusip;
	
	private int lotnum;
	
	private int tranFeeDefID;
	
	private Date closeDate;
	
	private double amount;
	
	private double feeamount;
	
	private double feeamounttoday;
	
	private double feeRate;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="TranFeeFollowClo_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Column(name="tranFeeDefID")
	public int getTranFeeDefID() {
		return tranFeeDefID;
	}

	public void setTranFeeDefID(int tranFeeDefID) {
		this.tranFeeDefID = tranFeeDefID;
	}

	@Column(name = "CloseDate")
	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	@Column(name="feeamount")
	public double getFeeamount() {
		return feeamount;
	}

	public void setFeeamount(double feeamount) {
		this.feeamount = feeamount;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tranCloID")
	public TranClo getTranClo() {
		return tranClo;
	}

	public void setTranClo(TranClo tranClo) {
		this.tranClo = tranClo;
	}

	@Column(name="amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name="feeRate")
	public double getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(double feeRate) {
		this.feeRate = feeRate;
	}

	@Column(name="feeamounttoday")
	public double getFeeamounttoday() {
		return feeamounttoday;
	}

	public void setFeeamounttoday(double feeamounttoday) {
		this.feeamounttoday = feeamounttoday;
	}
}
