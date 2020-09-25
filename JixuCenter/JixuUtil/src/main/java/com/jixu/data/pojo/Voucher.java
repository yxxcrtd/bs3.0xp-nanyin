package com.jixu.data.pojo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName 		SecPosition
 * @author  		Pan Wei 
 * @Date 			2017年10月28日 下午9:36:06 	
 * @Description		[]  
 * @return  
 */

@Entity 
@Table(name="voucher") 
public class Voucher implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7502190436140521286L;

	private int id;
	
	/**
	 * 会计科目
	 */
    private String accountNum;
    
    /**
     * 科目名
     */
    private String accountName;
	
    /**
	 * 数量 
	 */
	private Double amount;
	
	private int chartNum;
	
	private Double cr_Func;
	
	private Double cr_Local;
	
	private Double curFxRate;
	
	private String description;
	
	private Double dr_Func;
	
	private Double dr_Local;
	
	private String functionCurrency;
	
	private Double fxRate;
	
	private int isadJusted;
	
	private int itemNum;
	
	private String localCurrency;
	
	private Double price;
	
	private int repyrefno;
	
	private String securityID;
	
	private String securityTypeCode;
	
	private int tranType;
	
	private int voucherNum;
	
	private int journalSourceCode;
	
	private Date ledgerdate;

	/**
	 * 会计系统portid
	 */
	private int portNum;
	
	private String level1AccntNum;
	
	private String level1AccntName;
	
	/**
	 * 科目类型
	 * 资产、负债
	 */
	private int accntCategory;
	
	/**
	 * 获取 id
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	/**
	 * 设置 id
	 */
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="accountNum")
	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	@Column(name="amount")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name="chartNum")
	public int getChartNum() {
		return chartNum;
	}

	public void setChartNum(int chartNum) {
		this.chartNum = chartNum;
	}

	@Column(name="cr_Func")
	public Double getCr_Func() {
		return cr_Func;
	}

	public void setCr_Func(Double cr_Func) {
		this.cr_Func = cr_Func;
	}

	@Column(name="cr_Local")
	public Double getCr_Local() {
		return cr_Local;
	}

	public void setCr_Local(Double cr_Local) {
		this.cr_Local = cr_Local;
	}

	@Column(name="curFxRate")
	public Double getCurFxRate() {
		return curFxRate;
	}

	public void setCurFxRate(Double curFxRate) {
		this.curFxRate = curFxRate;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="dr_Func")
	public Double getDr_Func() {
		return dr_Func;
	}

	public void setDr_Func(Double dr_Func) {
		this.dr_Func = dr_Func;
	}

	@Column(name="dr_Local")
	public Double getDr_Local() {
		return dr_Local;
	}

	public void setDr_Local(Double dr_Local) {
		this.dr_Local = dr_Local;
	}

	@Column(name="functionCurrency")
	public String getFunctionCurrency() {
		return functionCurrency;
	}

	public void setFunctionCurrency(String functionCurrency) {
		this.functionCurrency = functionCurrency;
	}

	@Column(name="fxRate")
	public Double getFxRate() {
		return fxRate;
	}

	public void setFxRate(Double fxRate) {
		this.fxRate = fxRate;
	}

	@Column(name="isadJusted")
	public int getIsadJusted() {
		return isadJusted;
	}

	public void setIsadJusted(int isadJusted) {
		this.isadJusted = isadJusted;
	}

	@Column(name="itemNum")
	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	@Column(name="localCurrency")
	public String getLocalCurrency() {
		return localCurrency;
	}

	public void setLocalCurrency(String localCurrency) {
		this.localCurrency = localCurrency;
	}

	@Column(name="price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name="repyrefno")
	public int getRepyrefno() {
		return repyrefno;
	}

	public void setRepyrefno(int repyrefno) {
		this.repyrefno = repyrefno;
	}

	@Column(name="securityID")
	public String getSecurityID() {
		return securityID;
	}

	public void setSecurityID(String securityID) {
		this.securityID = securityID;
	}

	@Column(name="securityTypeCode")
	public String getSecurityTypeCode() {
		return securityTypeCode;
	}

	public void setSecurityTypeCode(String securityTypeCode) {
		this.securityTypeCode = securityTypeCode;
	}

	@Column(name="tranType")
	public int getTranType() {
		return tranType;
	}

	public void setTranType(int tranType) {
		this.tranType = tranType;
	}

	@Column(name="voucherNum")
	public int getVoucherNum() {
		return voucherNum;
	}

	public void setVoucherNum(int voucherNum) {
		this.voucherNum = voucherNum;
	}

	@Column(name="journalSourceCode")
	public int getJournalSourceCode() {
		return journalSourceCode;
	}

	public void setJournalSourceCode(int journalSourceCode) {
		this.journalSourceCode = journalSourceCode;
	}

	@Column(name="ledgerdate", columnDefinition="Date default '2000-01-01'")
	public Date getLedgerdate() {
		return ledgerdate;
	}

	public void setLedgerdate(Date ledgerdate) {
		this.ledgerdate = ledgerdate;
	}

	@Column(name="portNum")
	public int getPortNum() {
		return portNum;
	}

	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}

	@Column(name="Level1AccntNum")
	public String getLevel1AccntNum() {
		return level1AccntNum;
	}

	public void setLevel1AccntNum(String level1AccntNum) {
		this.level1AccntNum = level1AccntNum;
	}

	@Column(name="Level1AccntName")
	public String getLevel1AccntName() {
		return level1AccntName;
	}

	public void setLevel1AccntName(String level1AccntName) {
		this.level1AccntName = level1AccntName;
	}

	@Column(name="AccntCategory")
	public int getAccntCategory() {
		return accntCategory;
	}

	public void setAccntCategory(int accntCategory) {
		this.accntCategory = accntCategory;
	}

	@Column(name="accountName")
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
