package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class WflTrdProductDealAdd extends WflTrdProductDealAddKey {
    /**
     * ??????
     */
    private String portfolioId;

    /**
     * ??????
     */
    private String prodId;

    /**
     * ????????
     */
    private String customCashType;

    /**
     * ????????????????????
     */
    private String genType;

    /**
     * ???????
     */
    private Date purCfmDate;

    /**
     * ???????
     */
    private Date redCfmDate;

    /**
     * ?????
     */
    private Date regDate;

    /**
     * ?????
     */
    private Date bonusCfmDate;

    /**
     * ????
     */
    private String entrustId;

    /**
     * ???????????????????????????
     */
    private String payType;

    /**
     * ?????
     */
    private String workflowId;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal regDateAmt;

    /**
     * DEAL_MODE
     */
    private String dealMode;

    /**
     * ??????
     */
    private String excDealType;

    /**
     * ???
     */
    private String curPair;

    /**
     * ????
     */
    private String termType;

    /**
     * ?????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal usdAmt;

    /**
     * ????
     */
    private String ourRole;

    /**
     * ????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal quotPre;

    /**
     * ???
     */
    private Date pricedDate;

    /**
     * ???????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pricedDateRate;

    /**
     * ??????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal difPayAmt;

    /**
     * ??????
     */
    private String difPayCcy;

    /**
     * ??????
     */
    private String difPs;

    /**
     * ????????
     */
    private String refRate;

    /**
     * ??????
     */
    private String withLockPeriod;

    /**
     * ??????
     */
    private Date lockMdate;

    /**
     * 偏离度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal deviation;

    /**
     * 资产推荐方
     */
    private String assetRecommandOrg;

    /**
     * 提前终止利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal terminateRate;

    /**
     * 罚息金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal penaltyIntamt;

    /**
     * 预计转股价值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expConValue;

    /**
     * 零星股数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spoShareNum;

    /**
     * 正股价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal firstUnitCprice3;

    /**
     * 转股溢价率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal preb;

    /**
     * 纯债溢价率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prec;

    /**
     * 是否现金交割
     */
    private String isCashset;

    /**
     * 转换资产代码
     */
    private String rFinprodId;

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 ??????
     * @param portfolioId ??????
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 ??????
     * @param prodId ??????
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 ????????
     * @return ????????
     */
    public String getCustomCashType() {
        return customCashType;
    }

    /**
     * 设置 ????????
     * @param customCashType ????????
     */
    public void setCustomCashType(String customCashType) {
        this.customCashType = customCashType == null ? null : customCashType.trim();
    }

    /**
     * 取得 ????????????????????
     * @return ????????????????????
     */
    public String getGenType() {
        return genType;
    }

    /**
     * 设置 ????????????????????
     * @param genType ????????????????????
     */
    public void setGenType(String genType) {
        this.genType = genType == null ? null : genType.trim();
    }

    /**
     * 取得 ???????
     * @return ???????
     */
    public Date getPurCfmDate() {
        return purCfmDate;
    }

    /**
     * 设置 ???????
     * @param purCfmDate ???????
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPurCfmDate(Date purCfmDate) {
        this.purCfmDate = purCfmDate;
    }

    /**
     * 取得 ???????
     * @return ???????
     */
    public Date getRedCfmDate() {
        return redCfmDate;
    }

    /**
     * 设置 ???????
     * @param redCfmDate ???????
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRedCfmDate(Date redCfmDate) {
        this.redCfmDate = redCfmDate;
    }

    /**
     * 取得 ?????
     * @return ?????
     */
    public Date getRegDate() {
        return regDate;
    }

    /**
     * 设置 ?????
     * @param regDate ?????
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    /**
     * 取得 ?????
     * @return ?????
     */
    public Date getBonusCfmDate() {
        return bonusCfmDate;
    }

    /**
     * 设置 ?????
     * @param bonusCfmDate ?????
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBonusCfmDate(Date bonusCfmDate) {
        this.bonusCfmDate = bonusCfmDate;
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 ????
     * @param entrustId ????
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 ???????????????????????????
     * @return ???????????????????????????
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置 ???????????????????????????
     * @param payType ???????????????????????????
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    /**
     * 取得 ?????
     * @return ?????
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 ?????
     * @param workflowId ?????
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getRegDateAmt() {
        return regDateAmt;
    }

    /**
     * 设置 null
     * @param regDateAmt null
     */
    public void setRegDateAmt(BigDecimal regDateAmt) {
        this.regDateAmt = regDateAmt;
    }

    /**
     * 取得 DEAL_MODE
     * @return DEAL_MODE
     */
    public String getDealMode() {
        return dealMode;
    }

    /**
     * 设置 DEAL_MODE
     * @param dealMode DEAL_MODE
     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode == null ? null : dealMode.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getExcDealType() {
        return excDealType;
    }

    /**
     * 设置 ??????
     * @param excDealType ??????
     */
    public void setExcDealType(String excDealType) {
        this.excDealType = excDealType == null ? null : excDealType.trim();
    }

    /**
     * 取得 ???
     * @return ???
     */
    public String getCurPair() {
        return curPair;
    }

    /**
     * 设置 ???
     * @param curPair ???
     */
    public void setCurPair(String curPair) {
        this.curPair = curPair == null ? null : curPair.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getTermType() {
        return termType;
    }

    /**
     * 设置 ????
     * @param termType ????
     */
    public void setTermType(String termType) {
        this.termType = termType == null ? null : termType.trim();
    }

    /**
     * 取得 ?????
     * @return ?????
     */
    public BigDecimal getUsdAmt() {
        return usdAmt;
    }

    /**
     * 设置 ?????
     * @param usdAmt ?????
     */
    public void setUsdAmt(BigDecimal usdAmt) {
        this.usdAmt = usdAmt;
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getOurRole() {
        return ourRole;
    }

    /**
     * 设置 ????
     * @param ourRole ????
     */
    public void setOurRole(String ourRole) {
        this.ourRole = ourRole == null ? null : ourRole.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public BigDecimal getQuotPre() {
        return quotPre;
    }

    /**
     * 设置 ????
     * @param quotPre ????
     */
    public void setQuotPre(BigDecimal quotPre) {
        this.quotPre = quotPre;
    }

    /**
     * 取得 ???
     * @return ???
     */
    public Date getPricedDate() {
        return pricedDate;
    }

    /**
     * 设置 ???
     * @param pricedDate ???
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPricedDate(Date pricedDate) {
        this.pricedDate = pricedDate;
    }

    /**
     * 取得 ???????
     * @return ???????
     */
    public BigDecimal getPricedDateRate() {
        return pricedDateRate;
    }

    /**
     * 设置 ???????
     * @param pricedDateRate ???????
     */
    public void setPricedDateRate(BigDecimal pricedDateRate) {
        this.pricedDateRate = pricedDateRate;
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public BigDecimal getDifPayAmt() {
        return difPayAmt;
    }

    /**
     * 设置 ??????
     * @param difPayAmt ??????
     */
    public void setDifPayAmt(BigDecimal difPayAmt) {
        this.difPayAmt = difPayAmt;
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getDifPayCcy() {
        return difPayCcy;
    }

    /**
     * 设置 ??????
     * @param difPayCcy ??????
     */
    public void setDifPayCcy(String difPayCcy) {
        this.difPayCcy = difPayCcy == null ? null : difPayCcy.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getDifPs() {
        return difPs;
    }

    /**
     * 设置 ??????
     * @param difPs ??????
     */
    public void setDifPs(String difPs) {
        this.difPs = difPs == null ? null : difPs.trim();
    }

    /**
     * 取得 ????????
     * @return ????????
     */
    public String getRefRate() {
        return refRate;
    }

    /**
     * 设置 ????????
     * @param refRate ????????
     */
    public void setRefRate(String refRate) {
        this.refRate = refRate == null ? null : refRate.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getWithLockPeriod() {
        return withLockPeriod;
    }

    /**
     * 设置 ??????
     * @param withLockPeriod ??????
     */
    public void setWithLockPeriod(String withLockPeriod) {
        this.withLockPeriod = withLockPeriod == null ? null : withLockPeriod.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public Date getLockMdate() {
        return lockMdate;
    }

    /**
     * 设置 ??????
     * @param lockMdate ??????
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLockMdate(Date lockMdate) {
        this.lockMdate = lockMdate;
    }

    /**
     * 取得 偏离度
     * @return 偏离度
     */
    public BigDecimal getDeviation() {
        return deviation;
    }

    /**
     * 设置 偏离度
     * @param deviation 偏离度
     */
    public void setDeviation(BigDecimal deviation) {
        this.deviation = deviation;
    }

    /**
     * 取得 资产推荐方
     * @return 资产推荐方
     */
    public String getAssetRecommandOrg() {
        return assetRecommandOrg;
    }

    /**
     * 设置 资产推荐方
     * @param assetRecommandOrg 资产推荐方
     */
    public void setAssetRecommandOrg(String assetRecommandOrg) {
        this.assetRecommandOrg = assetRecommandOrg == null ? null : assetRecommandOrg.trim();
    }

    /**
     * 取得 提前终止利率
     * @return 提前终止利率
     */
    public BigDecimal getTerminateRate() {
        return terminateRate;
    }

    /**
     * 设置 提前终止利率
     * @param terminateRate 提前终止利率
     */
    public void setTerminateRate(BigDecimal terminateRate) {
        this.terminateRate = terminateRate;
    }

    /**
     * 取得 罚息金额
     * @return 罚息金额
     */
    public BigDecimal getPenaltyIntamt() {
        return penaltyIntamt;
    }

    /**
     * 设置 罚息金额
     * @param penaltyIntamt 罚息金额
     */
    public void setPenaltyIntamt(BigDecimal penaltyIntamt) {
        this.penaltyIntamt = penaltyIntamt;
    }

    /**
     * 取得 预计转股价值
     * @return 预计转股价值
     */
    public BigDecimal getExpConValue() {
        return expConValue;
    }

    /**
     * 设置 预计转股价值
     * @param expConValue 预计转股价值
     */
    public void setExpConValue(BigDecimal expConValue) {
        this.expConValue = expConValue;
    }

    /**
     * 取得 零星股数
     * @return 零星股数
     */
    public BigDecimal getSpoShareNum() {
        return spoShareNum;
    }

    /**
     * 设置 零星股数
     * @param spoShareNum 零星股数
     */
    public void setSpoShareNum(BigDecimal spoShareNum) {
        this.spoShareNum = spoShareNum;
    }

    /**
     * 取得 正股价
     * @return 正股价
     */
    public BigDecimal getFirstUnitCprice3() {
        return firstUnitCprice3;
    }

    /**
     * 设置 正股价
     * @param firstUnitCprice3 正股价
     */
    public void setFirstUnitCprice3(BigDecimal firstUnitCprice3) {
        this.firstUnitCprice3 = firstUnitCprice3;
    }

    /**
     * 取得 转股溢价率
     * @return 转股溢价率
     */
    public BigDecimal getPreb() {
        return preb;
    }

    /**
     * 设置 转股溢价率
     * @param preb 转股溢价率
     */
    public void setPreb(BigDecimal preb) {
        this.preb = preb;
    }

    /**
     * 取得 纯债溢价率
     * @return 纯债溢价率
     */
    public BigDecimal getPrec() {
        return prec;
    }

    /**
     * 设置 纯债溢价率
     * @param prec 纯债溢价率
     */
    public void setPrec(BigDecimal prec) {
        this.prec = prec;
    }

    /**
     * 取得 是否现金交割
     * @return 是否现金交割
     */
    public String getIsCashset() {
        return isCashset;
    }

    /**
     * 设置 是否现金交割
     * @param isCashset 是否现金交割
     */
    public void setIsCashset(String isCashset) {
        this.isCashset = isCashset == null ? null : isCashset.trim();
    }

    /**
     * 取得 转换资产代码
     * @return 转换资产代码
     */
    public String getrFinprodId() {
        return rFinprodId;
    }

    /**
     * 设置 转换资产代码
     * @param rFinprodId 转换资产代码
     */
    public void setrFinprodId(String rFinprodId) {
        this.rFinprodId = rFinprodId == null ? null : rFinprodId.trim();
    }
}