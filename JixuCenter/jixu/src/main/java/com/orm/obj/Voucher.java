package com.orm.obj;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
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
@Table(name="JXV_VOUCHER", 
	indexes={@Index(name="voucher_index1",columnList="Portfolionum,LedgerDate")})
public class Voucher extends BasicEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String vouchernum;
	private Date tradeDate;
	private Date settlementDate;
	private Date ledgerDate;
	private int managerGroupCode;
	private int journalSourceCode;
	private String stampUser;
	private Date stampDate;
	private String affirmUser;
	private Date affirmDate;
	private String auditUser;
	private Date auditDate;
	private String importedUser;
	private Date importedDate;
	private int isAudit;
	private int isAuditMsgValid;
	private String auditMsg;
	private String taNum;
	private String portfolio;
	private String description;	//凭证注释
	
	private Set<VoucherDetails> voucherDetails;
	
	@Column(name="PORTFOLIONUM")
	public String getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}

	@OneToMany(mappedBy = "voucher", cascade = CascadeType.REMOVE)
	public Set<VoucherDetails> getVoucherDetails() {
		return voucherDetails;
	}
	
	public void setVoucherDetails(Set<VoucherDetails> voucherDetails) {
		this.voucherDetails = voucherDetails;
	}
	
	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.func.GenCustomTBID_Joyin", parameters={
			@Parameter(name = "seq_name", value = "JXV_VOUCHER_ID_SEQ"),
			@Parameter(name = "short_name", value = "VC")})
	@Column(name="VOUCHERNUM", length=32)
	public String getVouchernum() {
		return vouchernum;
	}
	public void setVouchernum(String vouchernum) {
		this.vouchernum = vouchernum;
	}
	
	@Column(name="TRADEDATE")
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	
	@Column(name="SETTLEMENTDATE")
	public Date getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	
	@Column(name="LEDGERDATE")
	public Date getLedgerDate() {
		return ledgerDate;
	}
	public void setLedgerDate(Date ledgerDate) {
		this.ledgerDate = ledgerDate;
	}
	
	@Column(name="MANAGERGROUPCODE")
	public int getManagerGroupCode() {
		return managerGroupCode;
	}
	public void setManagerGroupCode(int managerGroupCode) {
		this.managerGroupCode = managerGroupCode;
	}
	
	@Column(name="JOURNALSOURCECODE")
	public int getJournalSourceCode() {
		return journalSourceCode;
	}
	public void setJournalSourceCode(int journalSourceCode) {
		this.journalSourceCode = journalSourceCode;
	}
	
	@Column(name="STAMPUSER")
	public String getStampUser() {
		return stampUser;
	}
	public void setStampUser(String stampUser) {
		this.stampUser = stampUser;
	}
	
	@Column(name="STAMPDATE")
	public Date getStampDate() {
		return stampDate;
	}
	public void setStampDate(Date stampDate) {
		this.stampDate = stampDate;
	}
	
	@Column(name="AFFIRMUSER")
	public String getAffirmUser() {
		return affirmUser;
	}
	public void setAffirmUser(String affirmUser) {
		this.affirmUser = affirmUser;
	}
	
	@Column(name="AFFIRMDATE")
	public Date getAffirmDate() {
		return affirmDate;
	}
	public void setAffirmDate(Date affirmDate) {
		this.affirmDate = affirmDate;
	}
	
	@Column(name="AUDITUSER")
	public String getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	
	@Column(name="AUDITDATE")
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
	@Column(name="IMPORTEDUSER")
	public String getImportedUser() {
		return importedUser;
	}
	public void setImportedUser(String importedUser) {
		this.importedUser = importedUser;
	}
	
	@Column(name="IMPORTEDDATE")
	public Date getImportedDate() {
		return importedDate;
	}
	public void setImportedDate(Date importedDate) {
		this.importedDate = importedDate;
	}
	
	@Column(name="ISAUDIT")
	public int getIsAudit() {
		return isAudit;
	}
	public void setIsAudit(int isAudit) {
		this.isAudit = isAudit;
	}
	
	@Column(name="ISAUDITMSGVALID")
	public int getIsAuditMsgValid() {
		return isAuditMsgValid;
	}
	public void setIsAuditMsgValid(int isAuditMsgValid) {
		this.isAuditMsgValid = isAuditMsgValid;
	}
	
	@Column(name="AUDITMSG")
	public String getAuditMsg() {
		return auditMsg;
	}
	public void setAuditMsg(String auditMsg) {
		this.auditMsg = auditMsg;
	}
	
	@Column(name="TANUM")
	public String getTaNum() {
		return taNum;
	}
	public void setTaNum(String taNum) {
		this.taNum = taNum;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
