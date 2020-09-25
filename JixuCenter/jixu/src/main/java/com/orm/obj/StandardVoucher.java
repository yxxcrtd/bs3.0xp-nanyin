package com.orm.obj;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

@Entity 
@Table(name="JXV_STANDARDVOUCHER") 
public class StandardVoucher extends BasicEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int vouchernum;
	private Date tradeDate;
	private Date settlementDate;
	private Date ledgerDate;
	private int isProcessed;
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
	private int voucherID;
	private String taNum;
	private String portfolio;
	
	private Set<StandardVoucherDetails> standardVoucherDetails;
	
	/**
	 * 获取 portfolio
	 */
	@Column(name="PORTFOLIONUM")
	public String getPortfolio() {
		return portfolio;
	}
	/**
	 * 设置 portfolio
	 */
	
	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
	

	@OneToMany(mappedBy = "voucher", cascade = CascadeType.REMOVE)
	public Set<StandardVoucherDetails> getVoucherDetails() {
		return standardVoucherDetails;
	}
	
	public void setVoucherDetails(Set<StandardVoucherDetails> standardVoucherDetails) {
		this.standardVoucherDetails = standardVoucherDetails;
	}
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="StandardVoucher_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="VOUCHERNUM")
	public int getVouchernum() {
		return vouchernum;
	}
	public void setVouchernum(int vouchernum) {
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
	
	@Column(name="ISPROCESSED")
	public int getIsProcessed() {
		return isProcessed;
	}
	public void setIsProcessed(int isProcessed) {
		this.isProcessed = isProcessed;
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
	
	@Column(name="VOUCHERID")
	public int getVoucherID() {
		return voucherID;
	}
	public void setVoucherID(int voucherID) {
		this.voucherID = voucherID;
	}
	
	@Column(name="TANUM")
	public String getTaNum() {
		return taNum;
	}
	public void setTaNum(String taNum) {
		this.taNum = taNum;
	}
}
