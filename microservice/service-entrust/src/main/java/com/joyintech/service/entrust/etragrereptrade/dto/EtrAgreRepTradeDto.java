package com.joyintech.service.entrust.etragrereptrade.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
public class EtrAgreRepTradeDto  extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3436776283481983592L;
	
	private String tradeId;
	private String intiTradeId;
	private String origRepSecId;
	private BigDecimal origRepSecNum;
	private BigDecimal origPrintAmt;
	private BigDecimal origConRatio;
	private String  portfolioId;
	private String  acctNo;
	private String secAcctId;
	private String tradeMarket;
	private Integer term;
	private BigDecimal tradeAmt;
	private BigDecimal yield;
	private String fristSettleDate;
	private String expireSettleDate;
	private Integer occupyDays;
	private BigDecimal intAmt;
	private BigDecimal expireSettleAmt;
	private String counterId;
	private String counterTrader;
	private String trader;
	private String seatId;
	private String repPs;
	private String addCla;
	private String entrustId;
	private String finprodId;
	private BigDecimal pledgeFaceValue;
	private String newfinprodId;
	private BigDecimal newpledgeFaceValue;
	private String repSecId;
	private BigDecimal conRatio;
	private BigDecimal repSecNum;
	private BigDecimal printAmt;
	private String newRepSecId;
	private BigDecimal newConRatio;
	private BigDecimal newRepSecNum;
	private BigDecimal newPrintAmt;
	private String reqOnId;
	private String vdate;
	private String mdate;
	private String acctTradeMarket;
	private String psList;
	private String operateType;
    private String invManagerId;
    private String entrustBussType;
    private String ps;
    private String agreeOnId;
    private String elementId;
    private String pageId;
    private String businessTypeName;
    private String redoType;
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getIntiTradeId() {
		return intiTradeId;
	}
	public void setIntiTradeId(String intiTradeId) {
		this.intiTradeId = intiTradeId;
	}
	public String getOrigRepSecId() {
		return origRepSecId;
	}
	public void setOrigRepSecId(String origRepSecId) {
		this.origRepSecId = origRepSecId;
	}
	public BigDecimal getOrigRepSecNum() {
		return origRepSecNum;
	}
	public void setOrigRepSecNum(BigDecimal origRepSecNum) {
		this.origRepSecNum = origRepSecNum;
	}
	public BigDecimal getOrigPrintAmt() {
		return origPrintAmt;
	}
	public void setOrigPrintAmt(BigDecimal origPrintAmt) {
		this.origPrintAmt = origPrintAmt;
	}
	public BigDecimal getOrigConRatio() {
		return origConRatio;
	}
	public void setOrigConRatio(BigDecimal origConRatio) {
		this.origConRatio = origConRatio;
	}
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public String getTradeMarket() {
		return tradeMarket;
	}
	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}
	public Integer getTerm() {
		return term;
	}
	public void setTerm(Integer term) {
		this.term = term;
	}
	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	public BigDecimal getYield() {
		return yield;
	}
	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}
	public String getFristSettleDate() {
		return fristSettleDate;
	}
	public void setFristSettleDate(String fristSettleDate) {
		this.fristSettleDate = fristSettleDate;
	}
	public String getExpireSettleDate() {
		return expireSettleDate;
	}
	public void setExpireSettleDate(String expireSettleDate) {
		this.expireSettleDate = expireSettleDate;
	}
	public Integer getOccupyDays() {
		return occupyDays;
	}
	public void setOccupyDays(Integer occupyDays) {
		this.occupyDays = occupyDays;
	}
	public BigDecimal getIntAmt() {
		return intAmt;
	}
	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}
	public BigDecimal getExpireSettleAmt() {
		return expireSettleAmt;
	}
	public void setExpireSettleAmt(BigDecimal expireSettleAmt) {
		this.expireSettleAmt = expireSettleAmt;
	}
	public String getCounterId() {
		return counterId;
	}
	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}
	public String getCounterTrader() {
		return counterTrader;
	}
	public void setCounterTrader(String counterTrader) {
		this.counterTrader = counterTrader;
	}
	public String getTrader() {
		return trader;
	}
	public void setTrader(String trader) {
		this.trader = trader;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getRepPs() {
		return repPs;
	}
	public void setRepPs(String repPs) {
		this.repPs = repPs;
	}
	public String getSecAcctId() {
		return secAcctId;
	}
	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}
	public String getAddCla() {
		return addCla;
	}
	public void setAddCla(String addCla) {
		this.addCla = addCla;
	}
	public String getEntrustId() {
		return entrustId;
	}
	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}
	public String getFinprodId() {
		return finprodId;
	}
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	public BigDecimal getPledgeFaceValue() {
		return pledgeFaceValue;
	}
	public void setPledgeFaceValue(BigDecimal pledgeFaceValue) {
		this.pledgeFaceValue = pledgeFaceValue;
	}
	public String getNewfinprodId() {
		return newfinprodId;
	}
	public void setNewfinprodId(String newfinprodId) {
		this.newfinprodId = newfinprodId;
	}
	public BigDecimal getNewpledgeFaceValue() {
		return newpledgeFaceValue;
	}
	public void setNewpledgeFaceValue(BigDecimal newpledgeFaceValue) {
		this.newpledgeFaceValue = newpledgeFaceValue;
	}
	public String getRepSecId() {
		return repSecId;
	}
	public void setRepSecId(String repSecId) {
		this.repSecId = repSecId;
	}
	public BigDecimal getConRatio() {
		return conRatio;
	}
	public void setConRatio(BigDecimal conRatio) {
		this.conRatio = conRatio;
	}
	public BigDecimal getRepSecNum() {
		return repSecNum;
	}
	public void setRepSecNum(BigDecimal repSecNum) {
		this.repSecNum = repSecNum;
	}
	public BigDecimal getPrintAmt() {
		return printAmt;
	}
	public void setPrintAmt(BigDecimal printAmt) {
		this.printAmt = printAmt;
	}
	public String getNewRepSecId() {
		return newRepSecId;
	}
	public void setNewRepSecId(String newRepSecId) {
		this.newRepSecId = newRepSecId;
	}
	public BigDecimal getNewConRatio() {
		return newConRatio;
	}
	public void setNewConRatio(BigDecimal newConRatio) {
		this.newConRatio = newConRatio;
	}
	public BigDecimal getNewRepSecNum() {
		return newRepSecNum;
	}
	public void setNewRepSecNum(BigDecimal newRepSecNum) {
		this.newRepSecNum = newRepSecNum;
	}
	public BigDecimal getNewPrintAmt() {
		return newPrintAmt;
	}
	public void setNewPrintAmt(BigDecimal newPrintAmt) {
		this.newPrintAmt = newPrintAmt;
	}
	public String getReqOnId() {
		return reqOnId;
	}
	public void setReqOnId(String reqOnId) {
		this.reqOnId = reqOnId;
	}
	public String getVdate() {
		return vdate;
	}
	public void setVdate(String vdate) {
		this.vdate = vdate;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getAcctTradeMarket() {
		return acctTradeMarket;
	}
	public void setAcctTradeMarket(String acctTradeMarket) {
		this.acctTradeMarket = acctTradeMarket;
	}
	public String getPsList() {
		return psList;
	}
	public void setPsList(String psList) {
		this.psList = psList;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getInvManagerId() {
		return invManagerId;
	}
	public void setInvManagerId(String invManagerId) {
		this.invManagerId = invManagerId;
	}
	public String getEntrustBussType() {
		return entrustBussType;
	}
	public void setEntrustBussType(String entrustBussType) {
		this.entrustBussType = entrustBussType;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	public String getAgreeOnId() {
		return agreeOnId;
	}
	public void setAgreeOnId(String agreeOnId) {
		this.agreeOnId = agreeOnId;
	}
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getBusinessTypeName() {
		return businessTypeName;
	}
	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	public String getRedoType() {
		return redoType;
	}

	public void setRedoType(String redoType) {
		this.redoType = redoType;
	}
}
