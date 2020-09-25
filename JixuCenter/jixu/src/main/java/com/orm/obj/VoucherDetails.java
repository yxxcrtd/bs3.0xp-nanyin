package com.orm.obj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		UserRoleGroupMap
 * @author  		Pan Wei 
 * @Date 			2017年1月4日 上午10:07:43 	
 * @Description		[凭证表主表 Voucher和VoucherDetails两表组成了凭证表。Voucher表是纲领，VoucherDetails表是具体数据内容。一条Voucher记录对应N条VoucherDetails记录]  
 */

@Entity 
@Table(name="JXV_VOUCHERDETAILS", 
	indexes={@Index(name="voucherdetails_index2",columnList="Chartnum,SUBJECT_NO")}) 
public class VoucherDetails extends BasicEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Voucher voucher;
	private String itemNum;
	private String accountNum;
	private String chartNum;
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
	private String cd_flag;	//借贷方向
	private String busiID;  //业务明细代码
	private String busiDetailType; //业务明细类型
	
	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.func.GenCustomTBID_Joyin", parameters={
			@Parameter(name = "seq_name", value = "JXV_VOUCHER_ID_SEQ"),
			@Parameter(name = "short_name", value = "VD")})
	@Column(name="VOUCH_SUBNUM", length=32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="VOUCH_NUM")
	public Voucher getVoucher() {
		return voucher;
	}
	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}
	
	@Column(name="BUS_VOUCHSUBNUM")
	public String getItemNum() {
		return itemNum;
	}
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	
	@Column(name="SUBJECT_NO")
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	
	@Column(name="CHARTNUM")
	public String getChartNum() {
		return chartNum;
	}
	public void setChartNum(String chartNum) {
		this.chartNum = chartNum;
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
	
	@Column(name="detail_subject_no")
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
	
	@Column(name="cd_flag", length=2)
	public String getCd_flag() {
		return cd_flag;
	}
	public void setCd_flag(String cd_flag) {
		this.cd_flag = cd_flag;
	}
	
	public String getBusiID() {
		return busiID;
	}
	public void setBusiID(String busiID) {
		this.busiID = busiID;
	}
	
	public String getBusiDetailType() {
		return busiDetailType;
	}
	public void setBusiDetailType(String busiDetailType) {
		this.busiDetailType = busiDetailType;
	}
}
