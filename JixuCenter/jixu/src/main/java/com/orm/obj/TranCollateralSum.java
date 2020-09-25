package com.orm.obj;

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
 * @ClassName 		TranSumCollateral
 * @author  		Pan Wei 
 * @Date 			2016年8月23日 下午11:52:12 	
 * @Description		[该表尚未使用]  
 */

@Entity 
@Table(name="JXT_Tran_CollateralSum") 
public class TranCollateralSum extends BasicEntity {
	
	private int id;
	private PortfolioHdr portfolio;
	private int collateralID;
	private TranBasic tanum;
	private int lastTranNum;
	private int location;
	private double amount;
	private int tranType;
	/**
	 * This table contains summary information for transactions related to a particular piece of collateral, as defined by the key fields
	 */
	private int link;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="TranCollateralSum_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="portfolio")
	public PortfolioHdr getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(PortfolioHdr portfolio) {
		this.portfolio = portfolio;
	}
	
	@Column(name="collateralID")
	public int getCollateralID() {
		return collateralID;
	}
	public void setCollateralID(int collateralID) {
		this.collateralID = collateralID;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tanum")
	public TranBasic getTanum() {
		return tanum;
	}
	public void setTanum(TranBasic tanum) {
		this.tanum = tanum;
	}
	
	@Column(name="lastTranNum")
	public int getLastTranNum() {
		return lastTranNum;
	}
	public void setLastTranNum(int lastTranNum) {
		this.lastTranNum = lastTranNum;
	}
	
	@Column(name="location")
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	
	@Column(name="amount")
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Column(name="tranType")
	public int getTranType() {
		return tranType;
	}
	public void setTranType(int tranType) {
		this.tranType = tranType;
	}
	
	@Column(name="link")
	public int getLink() {
		return link;
	}
	public void setLink(int link) {
		this.link = link;
	}
}
