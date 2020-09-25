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
 * @ClassName 		BkInteRatio
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[银行利息的自动结转表，系统用来查看当天是否已经结转利息，防止撤帐后的重复结息]  
 */

@Entity 
@Table(name="JXB_Bk_Inte_SetHis") 
public class BkInteSetHis extends BasicEntity implements Serializable{

	private static final long serialVersionUID = 6671259313454070386L;
	private int id;
	private PortfolioHdr portfolio;
	private BkCashAcct bankacct;
	private TranBasic tanum;
	private Date inteDate;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="BkInteSetHis_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bankacct")
	public BkCashAcct getBankacct() {
		return bankacct;
	}
	public void setBankacct(BkCashAcct bankacct) {
		this.bankacct = bankacct;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="portfolio")
	public PortfolioHdr getPortfolio() {
		return portfolio;
	}
	
	public void setPortfolio(PortfolioHdr portfolio) {
		this.portfolio = portfolio;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TANUM")
	public TranBasic getTanum() {
		return tanum;
	}
	public void setTanum(TranBasic tanum) {
		this.tanum = tanum;
	}
	
	@Column(name="InteDate")
	public Date getInteDate() {
		return inteDate;
	}
	public void setInteDate(Date inteDate) {
		this.inteDate = inteDate;
	}
	
	
}
