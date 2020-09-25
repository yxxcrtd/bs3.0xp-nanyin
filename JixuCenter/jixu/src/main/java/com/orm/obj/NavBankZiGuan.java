package com.orm.obj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

@Entity 
@Table(name="JXV_NavBankZiGuan") 
public class NavBankZiGuan extends BasicEntity implements Serializable{

	private static final long serialVersionUID = -6562372545051526311L;

	private int id;
	
	/**
	 * 投资组合编号
	 */
	private String portnum;
	
	/**
	 * 资管产品在smf的id
	 */
	private String cusip;
	
	/**
	 * 
	 */
	private int lotnum;
	
	/**
	 * 日期
	 */
	private Date navDate;
	
	/**
	 * 净资产
	 */
	private double netAsset;
	
	/**
	 * 原始申购时的金额
	 */
	private double gcost;
	
	/**
	 * 产品对外的份额,申购时份额=金额
	 */
	private double sharesEnternal;
	
	/**
	 * 产品在组合内占有的份额
	 */
	private double sharesInternal;
	
	/**
	 * 单位净值
	 */
	private double nav;
	
	/**
	 * 关帐记录的ID
	 */
	private String tranCloID;
	
	/**
	 * 当天净值增长率
	 */
	private double navTodayIncrease;
	
	/**
	 * 总的净值增长率
	 */
	private double navIncrease;
	
	/**
	 * 对外的单位净值
	 */
	private double navExternal;
	
	/**
	 * 万份收益
	 */
	private double incomeWf;
	
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="NavBankZiGuan_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="NAVID")
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

	@Column(name="navDate")
	public Date getNavDate() {
		return navDate;
	}

	public void setNavDate(Date navDate) {
		this.navDate = navDate;
	}

	@Column(name="netAsset")
	public double getNetAsset() {
		return netAsset;
	}

	public void setNetAsset(double netAsset) {
		this.netAsset = netAsset;
	}

	@Column(name="nav")
	public double getNav() {
		return nav;
	}

	public void setNav(double nav) {
		this.nav = nav;
	}

	@Column(name="tranCloID")
	public String getTranCloID() {
		return tranCloID;
	}

	public void setTranCloID(String tranCloID) {
		this.tranCloID = tranCloID;
	}

	@Column(name="sharesEnternal")
	public double getSharesEnternal() {
		return sharesEnternal;
	}

	public void setSharesEnternal(double sharesEnternal) {
		this.sharesEnternal = sharesEnternal;
	}

	@Column(name="sharesInternal")
	public double getSharesInternal() {
		return sharesInternal;
	}

	public void setSharesInternal(double sharesInternal) {
		this.sharesInternal = sharesInternal;
	}

	@Column(name="navTodayIncrease")
	public double getNavTodayIncrease() {
		return navTodayIncrease;
	}

	public void setNavTodayIncrease(double navTodayIncrease) {
		this.navTodayIncrease = navTodayIncrease;
	}

	@Column(name="navExternal")
	public double getNavExternal() {
		return navExternal;
	}

	public void setNavExternal(double navExternal) {
		this.navExternal = navExternal;
	}

	@Column(name="incomeWf")
	public double getIncomeWf() {
		return incomeWf;
	}

	public void setIncomeWf(double incomeWf) {
		this.incomeWf = incomeWf;
	}

	@Column(name="gcost")
	public double getGcost() {
		return gcost;
	}

	public void setGcost(double gcost) {
		this.gcost = gcost;
	}

	@Column(name="navIncrease")
	public double getNavIncrease() {
		return navIncrease;
	}

	public void setNavIncrease(double navIncrease) {
		this.navIncrease = navIncrease;
	}
}
