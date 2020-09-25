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
import com.jixu.common.util.DateUtil;

/** 
 * @ClassName 		NewFundContent
 * @author  		Pan Wei 
 * @Date 			2017年2月14日 下午1:19:50 	
 * @Description		[税费配置表。通过读取该表的配置，计算投资组合各种税费。可以通过配置实现以哪天得基准来计算税费，以及通过动态增加实现类来增加税费的计算方式。ExpenseJob和AfterJob都读取该表来计算税费]  
 */

@Entity 
@Table(name="JXS_PORTEXPENSECFG") 
public class PortExpenseCfg extends BasicEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//费用配置ID
	private int portFeeID;
	
	//费用名字
	private String portFeeName;
	
	//投资对应投资组合
	private PortfolioHdr portfolio;
	
	//费率 / 固定费用
	private double expenseAmount;
	
	/**
	 * 0 At Mat 
	 * 1 January 
	 * 2 February 
	 * 3 March 
	 * 4 April 
	 * 5 May 
	 * 6 June 
	 * 7 July 
	 * 8 August 
	 * 9 September 
	 * 10 October 
	 * 11 November 
	 * 12 December 
	 * 13 Jan-Jul 
	 * 14 Feb-Aug 
	 * 15 Mar-Sep 
	 * 16 Apr-Oct 
	 * 17 May-Nov 
	 * 18 Jun-Dec 
	 * 19 J-A-J-O 
	 * 20 F-M-A-N 
	 * 21 M-J-S-D 
	 * 25 IMM Roll 
	 * 22 Monthly 
	 * 23 Scheduled 
	 * 24 Weekly 
	 */
	private int cpnMo;
	
	//日
	private int cpnDate;
	
	//计算费用方式
	private int intCode;
	
	//期间最低费用
	private double lowestFee;

	//期间最高费用
	private double highestFee;
	
	private String drAcct;
	
	private String crAcct;
	
	/*
	 * 是否固定费用
	 */
	private int fixFeeFlag;
	
	/*
	 * 是否费前净值
	 */
	private int newNavFeeFlag;
	
	//费用的支付频率
	private int cpnfReq;
	
	//开始日期
	private Date startDate;
	
	private Date expireDate;
	
	private boolean expenseFlag;
	
	//基准科目,用于税费计算是基于哪个科目的科目余额来计算，支持正则表达式，支持逗号分隔
	private String baseAcct;

	//应税的证券品种, 明细品种
	private String actionItems;
	
	//AfterJob实现类的service名称,用来反射找到实现类
	private String classService;
	
	private int orderNum;
	
	public PortExpenseCfg() {
		this.actionItems="";
		this.baseAcct="";
		this.startDate=DateUtil.convertStr2Date("20000101");
	}
	
	public PortExpenseCfg(int portFeeID) {
		this.portFeeID=portFeeID;
	}

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="PortExpenseCfg_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="PORTFEEID")
	public int getPortFeeID() {
		return portFeeID;
	}

	public void setPortFeeID(int portFeeID) {
		this.portFeeID = portFeeID;
	}

	@Column(name="portFeeName")
	public String getPortFeeName() {
		return portFeeName;
	}

	public void setPortFeeName(String portFeeName) {
		this.portFeeName = portFeeName;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="portfolio")
	public PortfolioHdr getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(PortfolioHdr portfolio) {
		this.portfolio = portfolio;
	}

	@Column(name="expenseAmount")
	public double getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	
	@Column(name="cpnMo")
	public int getCpnMo() {
		return cpnMo;
	}

	public void setCpnMo(int cpnMo) {
		this.cpnMo = cpnMo;
	}

	@Column(name="cpnDate")
	public int getCpnDate() {
		return cpnDate;
	}

	public void setCpnDate(int cpnDate) {
		this.cpnDate = cpnDate;
	}

	@Column(name="lowestFee")
	public double getLowestFee() {
		return lowestFee;
	}

	public void setLowestFee(double lowestFee) {
		this.lowestFee = lowestFee;
	}

	@Column(name="highestFee")
	public double getHighestFee() {
		return highestFee;
	}

	public void setHighestFee(double highestFee) {
		this.highestFee = highestFee;
	}

	@Column(name="drAcct")
	public String getDrAcct() {
		return drAcct;
	}

	public void setDrAcct(String drAcct) {
		this.drAcct = drAcct;
	}

	@Column(name="crAcct")
	public String getCrAcct() {
		return crAcct;
	}

	public void setCrAcct(String crAcct) {
		this.crAcct = crAcct;
	}

	@Column(name="fixFeeFlag")
	public int getFixFeeFlag() {
		return fixFeeFlag;
	}

	public void setFixFeeFlag(int fixFeeFlag) {
		this.fixFeeFlag = fixFeeFlag;
	}

	@Column(name="newNavFeeFlag")
	public int getNewNavFeeFlag() {
		return newNavFeeFlag;
	}

	public void setNewNavFeeFlag(int newNavFeeFlag) {
		this.newNavFeeFlag = newNavFeeFlag;
	}

	
	@Column(name="intCode")
	public int getIntCode() {
		return intCode;
	}

	public void setIntCode(int intCode) {
		this.intCode = intCode;
	}

	@Column(name="cpnfReq")
	public int getCpnfReq() {
		return cpnfReq;
	}

	public void setCpnfReq(int cpnfReq) {
		this.cpnfReq = cpnfReq;
	}

	@Column(name="ExpireDate")
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Column(name="ExpenseFlag",columnDefinition="integer",length=1)
	public boolean isExpenseFlag() {
		return expenseFlag;
	}

	public void setExpenseFlag(boolean expenseFlag) {
		this.expenseFlag = expenseFlag;
	}

	@Column(name="baseAcct")
	public String getBaseAcct() {
		return baseAcct;
	}

	public void setBaseAcct(String baseAcct) {
		this.baseAcct = baseAcct;
	}

	@Column(name="actionItems")
	public String getActionItems() {
		return actionItems;
	}

	public void setActionItems(String actionItems) {
		this.actionItems = actionItems;
	}

	@Column(name="classService")
	public String getClassService() {
		return classService;
	}

	public void setClassService(String classService) {
		this.classService = classService;
	}

	@Column(name="startDate")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name="orderNum")
	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
}
