package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class ChbAstBillRegist extends ChbAstBillRegistKey {
    /**
     * null
     */
    private String assetCode;

    /**
     * null
     */
    private String issuePartyId;

    /**
     * null
     */
    private String assetType2;

    /**
     * null
     */
    private String ccy;

    /**
     * null
     */
    private String tradeMarket;

    /**
     * null
     */
    private String isRight;

    /**
     * null
     */
    private String isResale;

    /**
     * null
     */
    private String type;

    /**
     * null
     */
    private Integer num;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amtSum;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal remainTerm;

    /**
     * null
     */
    private Integer maxRemainTerm;

    /**
     * null
     */
    private Integer minRemainTerm;

    /**
     * null
     */
    private Date vdate;

    /**
     * null
     */
    private Date mdate;

    /**
     * null
     */
    private String industry;

    /**
     * null
     */
    private String industryLevel2;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal discRate;

    /**
     * null
     */
    private String remark;

    /**
     * null
     */
    private String workflowId;

    /**
     * null
     */
    private String sendStatus;

    /**
     * null
     */
    private Date statusDate;

    /**
     * 取得 null
     * @return null
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 null
     * @param assetCode null
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 null
     * @param issuePartyId null
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getAssetType2() {
        return assetType2;
    }

    /**
     * 设置 null
     * @param assetType2 null
     */
    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2 == null ? null : assetType2.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 null
     * @param ccy null
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 null
     * @param tradeMarket null
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIsRight() {
        return isRight;
    }

    /**
     * 设置 null
     * @param isRight null
     */
    public void setIsRight(String isRight) {
        this.isRight = isRight == null ? null : isRight.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIsResale() {
        return isResale;
    }

    /**
     * 设置 null
     * @param isResale null
     */
    public void setIsResale(String isResale) {
        this.isResale = isResale == null ? null : isResale.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 null
     * @param type null
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置 null
     * @param num null
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getAmtSum() {
        return amtSum;
    }

    /**
     * 设置 null
     * @param amtSum null
     */
    public void setAmtSum(BigDecimal amtSum) {
        this.amtSum = amtSum;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getRemainTerm() {
        return remainTerm;
    }

    /**
     * 设置 null
     * @param remainTerm null
     */
    public void setRemainTerm(BigDecimal remainTerm) {
        this.remainTerm = remainTerm;
    }

    /**
     * 取得 null
     * @return null
     */
    public Integer getMaxRemainTerm() {
        return maxRemainTerm;
    }

    /**
     * 设置 null
     * @param maxRemainTerm null
     */
    public void setMaxRemainTerm(Integer maxRemainTerm) {
        this.maxRemainTerm = maxRemainTerm;
    }

    /**
     * 取得 null
     * @return null
     */
    public Integer getMinRemainTerm() {
        return minRemainTerm;
    }

    /**
     * 设置 null
     * @param minRemainTerm null
     */
    public void setMinRemainTerm(Integer minRemainTerm) {
        this.minRemainTerm = minRemainTerm;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 null
     * @param vdate null
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 null
     * @param mdate null
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置 null
     * @param industry null
     */
    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIndustryLevel2() {
        return industryLevel2;
    }

    /**
     * 设置 null
     * @param industryLevel2 null
     */
    public void setIndustryLevel2(String industryLevel2) {
        this.industryLevel2 = industryLevel2 == null ? null : industryLevel2.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getDiscRate() {
        return discRate;
    }

    /**
     * 设置 null
     * @param discRate null
     */
    public void setDiscRate(BigDecimal discRate) {
        this.discRate = discRate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 null
     * @param remark null
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 null
     * @param workflowId null
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 null
     * @param sendStatus null
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 设置 null
     * @param statusDate null
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
}