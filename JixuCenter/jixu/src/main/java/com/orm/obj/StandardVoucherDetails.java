package com.orm.obj;

import java.io.Serializable;

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

@Entity 
@Table(name="JXV_STANDARDVOUCHERDETAILS") 
public class StandardVoucherDetails extends BasicEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private StandardVoucher voucher;
	private int itemNum;
	private String accountNum;
	private int chartNum;
	private String description;
	private int isAdjusted;
	private String localCurrency;
	private String functionCurrency;
	private double fxRate;
	private String securityID;
	private String securityTypeCode;
	private double cr_local;
	private double dr_local;
	private double cr_func;
	private double dr_func;
	private double price;
	private double amount;
	private double curFxRate;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="StandardVoucherDetails_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="voucherNum")
	public StandardVoucher getVoucher() {
		return voucher;
	}
	
	public void setVoucher(StandardVoucher voucher) {
		this.voucher = voucher;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	
	@Column(name="ITEMNUM")
	public int getItemNum() {
		return itemNum;
	}
	
	
	@Column(name="ACCOUNTNUM")
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	
	@Column(name="CHARTNUM")
	public int getChartNum() {
		return chartNum;
	}
	public void setChartNum(int chartNum) {
		this.chartNum = chartNum;
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="ISADJUSTED")
	public int getIsAdjusted() {
		return isAdjusted;
	}
	public void setIsAdjusted(int isAdjusted) {
		this.isAdjusted = isAdjusted;
	}
	
	@Column(name="LOCALCURRENCY")
	public String getLocalCurrency() {
		return localCurrency;
	}
	public void setLocalCurrency(String localCurrency) {
		this.localCurrency = localCurrency;
	}
	
	@Column(name="FUNCTIONCURRENCY")
	public String getFunctionCurrency() {
		return functionCurrency;
	}
	public void setFunctionCurrency(String functionCurrency) {
		this.functionCurrency = functionCurrency;
	}
	
	@Column(name="FXRATE")
	public double getFxRate() {
		return fxRate;
	}
	public void setFxRate(double fxRate) {
		this.fxRate = fxRate;
	}
	
	@Column(name="SECURITYID")
	public String getSecurityID() {
		return securityID;
	}
	public void setSecurityID(String securityID) {
		this.securityID = securityID;
	}
	
	@Column(name="SECURITYTYPECODE")
	public String getSecurityTypeCode() {
		return securityTypeCode;
	}
	public void setSecurityTypeCode(String securityTypeCode) {
		this.securityTypeCode = securityTypeCode;
	}
	
	@Column(name="CR_LOCAL")
	public double getCr_local() {
		return cr_local;
	}
	public void setCr_local(double cr_local) {
		this.cr_local = cr_local;
	}
	
	@Column(name="DR_LOCAL")
	public double getDr_local() {
		return dr_local;
	}
	public void setDr_local(double dr_local) {
		this.dr_local = dr_local;
	}
	
	@Column(name="CR_FUNC")
	public double getCr_func() {
		return cr_func;
	}
	public void setCr_func(double cr_func) {
		this.cr_func = cr_func;
	}
	
	@Column(name="DR_FUNC")
	public double getDr_func() {
		return dr_func;
	}
	public void setDr_func(double dr_func) {
		this.dr_func = dr_func;
	}
	
	@Column(name="PRICE")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Column(name="AMOUNT")
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Column(name="CURFXRATE")
	public double getCurFxRate() {
		return curFxRate;
	}
	public void setCurFxRate(double curFxRate) {
		this.curFxRate = curFxRate;
	}
}
