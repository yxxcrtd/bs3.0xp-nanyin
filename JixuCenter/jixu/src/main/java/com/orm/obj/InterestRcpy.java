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
 * @ClassName 		IssueInfo
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[尚未使用该表]  
 */


@Entity 
@Table(name="JXB_INTEREST_RCPY") 
public class InterestRcpy extends BasicEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	// primary key
	private int id;

	// fields
	private double cash;
	private Date ledgerdate;
	private String settleStatus;

	// many to one
	private BkCashAcct bkCashAcct;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="InterestRcpy_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="CASH")
	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	@Column(name="LEDGERDATE")
	public Date getLedgerdate() {
		return ledgerdate;
	}

	public void setLedgerdate(Date ledgerdate) {
		this.ledgerdate = ledgerdate;
	}

	@Column(name="SETTLE_STATUS")
	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BK_ACCT_NO")
	public BkCashAcct getBkCashAcct() {
		return bkCashAcct;
	}

	public void setBkCashAcct(BkCashAcct bkCashAcct) {
		this.bkCashAcct = bkCashAcct;
	}
}
