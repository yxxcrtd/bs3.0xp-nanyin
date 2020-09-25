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
 * @ClassName 		TranAccruedRec
 * @author  		Pan Wei 
 * @Date 			2017年5月27日 下午10:56:52 	
 * @Description		[记录货币基金等利息收入结转的记录，每次接转利息,相应的交易会被记录在此表。如果没有删除该表的相应交易,利息不会被重复计提。
 					通过cusip,lotnum,lasttrannum,基准计息日期]  
 */

@Entity 
@Table(name="JXT_Tran_AccruedRec") 
public class TranAccruedRec extends BasicEntity {
	
	private int id;
	
	private String portnum;
	
	private String cusip;
	
	private int lotnum;
	
	private int lastTranNum;
	
	/**
	 * 交易编号
	 */
	private String tranTanum;
	
	

	/**
	 * 派息的日期
	 */
	private Date accruedDate;
	
	/**
	 * 基于哪天派息
	 */
	private Date basedDate;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="TranAccruedRec_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="Cusip", nullable=false)
	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	@Column(name="Lotnum", nullable=false)
	public int getLotnum() {
		return lotnum;
	}

	public void setLotnum(int lotnum) {
		this.lotnum = lotnum;
	}

	@Column(name="LastTranNum", nullable=false)
	public int getLastTranNum() {
		return lastTranNum;
	}

	public void setLastTranNum(int lastTranNum) {
		this.lastTranNum = lastTranNum;
	}

	@Column(name="AccruedDate", nullable=false)
	public Date getAccruedDate() {
		return accruedDate;
	}

	public void setAccruedDate(Date accruedDate) {
		this.accruedDate = accruedDate;
	}

	@Column(name="BasedDate", nullable=false)
	public Date getBasedDate() {
		return basedDate;
	}

	public void setBasedDate(Date basedDate) {
		this.basedDate = basedDate;
	}

	@Column(name="tranTanum", nullable=false)
	public String getTranTanum() {
		return tranTanum;
	}

	public void setTranTanum(String tranTanum) {
		this.tranTanum = tranTanum;
	}

	@Column(name="portnum")
	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}
}
