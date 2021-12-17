package com.joyintech.tams.exchange.common.bean;


/**
 * @ClassName IntegratedRepurchaseQuoteBean
 * @Description 协议回购行情
 * @Author liujianping
 * @Date 2020/9/21
 * @Version
 **/
public class IntegratedRepurchaseQuoteBean {
    private int errorCode;
    private String errorMsg;

    private String brokerId;
    private String brokerName;
    private String cbpconferId;
    private int entrustNo;
    private String entrustProp;
    private String entrustBs;
    private String exchangeType;
    private String stockType;
    private String stockCode;
    private String stockName;
    private double expireYearRate;
    private int repoTerm;
    private int fundUsedDays;
    private int firstExchdate;
    private int repurchaseDate;
    private int settleEndDate;
    private int componentNum;
    private String oldImpawnCode;
    private int impawnAmount;
    private double funderRatio;
    private double impawnBalance;
    private double prevImpawnBalance;
    private double profit;
    private double settleBalance;
    private double assetNetValue;
    private String bprsrcStatus;
    private int origEntrustDate;
    private String origCbpconferId;
    private String agencyNo;
    private String traderId;
    private String fundAccount;
    private String stockAccount;
    private String seatNo;
    private String oppoAgency;
    private String oppoTraderId;
    private String agencyName;
    private String positionStr;
    private String oppoAgencyName;
    private String propStockAccount;
    private String propSeatNo;
    private String remark;
    private String propAccountName;

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getCbpconferId() {
        return cbpconferId;
    }

    public void setCbpconferId(String cbpconferId) {
        this.cbpconferId = cbpconferId;
    }

    public int getEntrustNo() {
        return entrustNo;
    }

    public void setEntrustNo(int entrustNo) {
        this.entrustNo = entrustNo;
    }

    public String getEntrustProp() {
        return entrustProp;
    }

    public void setEntrustProp(String entrustProp) {
        this.entrustProp = entrustProp;
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getExpireYearRate() {
        return expireYearRate;
    }

    public void setExpireYearRate(double expireYearRate) {
        this.expireYearRate = expireYearRate;
    }

    public int getRepoTerm() {
        return repoTerm;
    }

    public void setRepoTerm(int repoTerm) {
        this.repoTerm = repoTerm;
    }

    public int getFundUsedDays() {
        return fundUsedDays;
    }

    public void setFundUsedDays(int fundUsedDays) {
        this.fundUsedDays = fundUsedDays;
    }

    public int getFirstExchdate() {
        return firstExchdate;
    }

    public void setFirstExchdate(int firstExchdate) {
        this.firstExchdate = firstExchdate;
    }

    public int getRepurchaseDate() {
        return repurchaseDate;
    }

    public void setRepurchaseDate(int repurchaseDate) {
        this.repurchaseDate = repurchaseDate;
    }

    public int getSettleEndDate() {
        return settleEndDate;
    }

    public void setSettleEndDate(int settleEndDate) {
        this.settleEndDate = settleEndDate;
    }

    public int getComponentNum() {
        return componentNum;
    }

    public void setComponentNum(int componentNum) {
        this.componentNum = componentNum;
    }

    public String getOldImpawnCode() {
        return oldImpawnCode;
    }

    public void setOldImpawnCode(String oldImpawnCode) {
        this.oldImpawnCode = oldImpawnCode;
    }

    public int getImpawnAmount() {
        return impawnAmount;
    }

    public void setImpawnAmount(int impawnAmount) {
        this.impawnAmount = impawnAmount;
    }

    public double getFunderRatio() {
        return funderRatio;
    }

    public void setFunderRatio(double funderRatio) {
        this.funderRatio = funderRatio;
    }

    public double getImpawnBalance() {
        return impawnBalance;
    }

    public void setImpawnBalance(double impawnBalance) {
        this.impawnBalance = impawnBalance;
    }

    public double getPrevImpawnBalance() {
        return prevImpawnBalance;
    }

    public void setPrevImpawnBalance(double prevImpawnBalance) {
        this.prevImpawnBalance = prevImpawnBalance;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getSettleBalance() {
        return settleBalance;
    }

    public void setSettleBalance(double settleBalance) {
        this.settleBalance = settleBalance;
    }

    public double getAssetNetValue() {
        return assetNetValue;
    }

    public void setAssetNetValue(double assetNetValue) {
        this.assetNetValue = assetNetValue;
    }

    public String getBprsrcStatus() {
        return bprsrcStatus;
    }

    public void setBprsrcStatus(String bprsrcStatus) {
        this.bprsrcStatus = bprsrcStatus;
    }

    public int getOrigEntrustDate() {
        return origEntrustDate;
    }

    public void setOrigEntrustDate(int origEntrustDate) {
        this.origEntrustDate = origEntrustDate;
    }

    public String getOrigCbpconferId() {
        return origCbpconferId;
    }

    public void setOrigCbpconferId(String origCbpconferId) {
        this.origCbpconferId = origCbpconferId;
    }

    public String getAgencyNo() {
        return agencyNo;
    }

    public void setAgencyNo(String agencyNo) {
        this.agencyNo = agencyNo;
    }

    public String getTraderId() {
        return traderId;
    }

    public void setTraderId(String traderId) {
        this.traderId = traderId;
    }

    public String getFundAccount() {
        return fundAccount;
    }

    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount;
    }

    public String getStockAccount() {
        return stockAccount;
    }

    public void setStockAccount(String stockAccount) {
        this.stockAccount = stockAccount;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getOppoAgency() {
        return oppoAgency;
    }

    public void setOppoAgency(String oppoAgency) {
        this.oppoAgency = oppoAgency;
    }

    public String getOppoTraderId() {
        return oppoTraderId;
    }

    public void setOppoTraderId(String oppoTraderId) {
        this.oppoTraderId = oppoTraderId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getPositionStr() {
        return positionStr;
    }

    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr;
    }

    public String getOppoAgencyName() {
        return oppoAgencyName;
    }

    public void setOppoAgencyName(String oppoAgencyName) {
        this.oppoAgencyName = oppoAgencyName;
    }

    public String getPropStockAccount() {
        return propStockAccount;
    }

    public void setPropStockAccount(String propStockAccount) {
        this.propStockAccount = propStockAccount;
    }

    public String getPropSeatNo() {
        return propSeatNo;
    }

    public void setPropSeatNo(String propSeatNo) {
        this.propSeatNo = propSeatNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPropAccountName() {
        return propAccountName;
    }

    public void setPropAccountName(String propAccountName) {
        this.propAccountName = propAccountName;
    }

    public int getRealDateBack() {
        return realDateBack;
    }

    public void setRealDateBack(int realDateBack) {
        this.realDateBack = realDateBack;
    }

    public String getPledgeeName() {
        return pledgeeName;
    }

    public void setPledgeeName(String pledgeeName) {
        this.pledgeeName = pledgeeName;
    }

    private int realDateBack;
    private String pledgeeName;

}
