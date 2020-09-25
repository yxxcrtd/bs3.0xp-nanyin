package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class ChbAstQdiiSecSub extends ChbAstQdiiSecSubKey {
    /**
     * 行内资产/负债编码
     */
    private String assetCode;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 二级资产类别 参照数据字典
     */
    private String assetType2;

    /**
     * 币种 参照数据字典
     */
    private String ccy;

    /**
     * 交易流通场所  参照数据字典
     */
    private String tradeMarket;

    /**
     * 所属国家或地区 参照数据字典
     */
    private String countryArea;

    /**
     * 债券名称
     */
    private String secName;

    /**
     * 债券代码
     */
    private String secId;

    /**
     * 发行机构
     */
    private String issueParty;

    /**
     * 发行机构所属行业
     */
    private String industry;

    /**
     * 发行机构所属行业二级分类
     */
    private String industryLevel2;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 期限(月)
     */
    private Integer term;

    /**
     * 发行机构主体信用评级
     */
    private String partyGrade;

    /**
     * 债券信用评级
     */
    private String secGrade;

    /**
     * 票面利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal paperRate;

    /**
     * 付息频率
     */
    private Short intpayCycle;

    /**
     * 担保情况说明
     */
    private String guaranteeCmt;

    /**
     * 是否含权等特殊条款情况说明
     */
    private String specialCmt;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 取得 行内资产/负债编码
     * @return 行内资产/负债编码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 行内资产/负债编码
     * @param assetCode 行内资产/负债编码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 发行机构代码
     * @return 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 二级资产类别 参照数据字典
     * @return 二级资产类别 参照数据字典
     */
    public String getAssetType2() {
        return assetType2;
    }

    /**
     * 设置 二级资产类别 参照数据字典
     * @param assetType2 二级资产类别 参照数据字典
     */
    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2 == null ? null : assetType2.trim();
    }

    /**
     * 取得 币种 参照数据字典
     * @return 币种 参照数据字典
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种 参照数据字典
     * @param ccy 币种 参照数据字典
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 交易流通场所  参照数据字典
     * @return 交易流通场所  参照数据字典
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易流通场所  参照数据字典
     * @param tradeMarket 交易流通场所  参照数据字典
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 所属国家或地区 参照数据字典
     * @return 所属国家或地区 参照数据字典
     */
    public String getCountryArea() {
        return countryArea;
    }

    /**
     * 设置 所属国家或地区 参照数据字典
     * @param countryArea 所属国家或地区 参照数据字典
     */
    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea == null ? null : countryArea.trim();
    }

    /**
     * 取得 债券名称
     * @return 债券名称
     */
    public String getSecName() {
        return secName;
    }

    /**
     * 设置 债券名称
     * @param secName 债券名称
     */
    public void setSecName(String secName) {
        this.secName = secName == null ? null : secName.trim();
    }

    /**
     * 取得 债券代码
     * @return 债券代码
     */
    public String getSecId() {
        return secId;
    }

    /**
     * 设置 债券代码
     * @param secId 债券代码
     */
    public void setSecId(String secId) {
        this.secId = secId == null ? null : secId.trim();
    }

    /**
     * 取得 发行机构
     * @return 发行机构
     */
    public String getIssueParty() {
        return issueParty;
    }

    /**
     * 设置 发行机构
     * @param issueParty 发行机构
     */
    public void setIssueParty(String issueParty) {
        this.issueParty = issueParty == null ? null : issueParty.trim();
    }

    /**
     * 取得 发行机构所属行业
     * @return 发行机构所属行业
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置 发行机构所属行业
     * @param industry 发行机构所属行业
     */
    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    /**
     * 取得 发行机构所属行业二级分类
     * @return 发行机构所属行业二级分类
     */
    public String getIndustryLevel2() {
        return industryLevel2;
    }

    /**
     * 设置 发行机构所属行业二级分类
     * @param industryLevel2 发行机构所属行业二级分类
     */
    public void setIndustryLevel2(String industryLevel2) {
        this.industryLevel2 = industryLevel2 == null ? null : industryLevel2.trim();
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 期限(月)
     * @return 期限(月)
     */
    public Integer getTerm() {
        return term;
    }

    /**
     * 设置 期限(月)
     * @param term 期限(月)
     */
    public void setTerm(Integer term) {
        this.term = term;
    }

    /**
     * 取得 发行机构主体信用评级
     * @return 发行机构主体信用评级
     */
    public String getPartyGrade() {
        return partyGrade;
    }

    /**
     * 设置 发行机构主体信用评级
     * @param partyGrade 发行机构主体信用评级
     */
    public void setPartyGrade(String partyGrade) {
        this.partyGrade = partyGrade == null ? null : partyGrade.trim();
    }

    /**
     * 取得 债券信用评级
     * @return 债券信用评级
     */
    public String getSecGrade() {
        return secGrade;
    }

    /**
     * 设置 债券信用评级
     * @param secGrade 债券信用评级
     */
    public void setSecGrade(String secGrade) {
        this.secGrade = secGrade == null ? null : secGrade.trim();
    }

    /**
     * 取得 票面利率
     * @return 票面利率
     */
    public BigDecimal getPaperRate() {
        return paperRate;
    }

    /**
     * 设置 票面利率
     * @param paperRate 票面利率
     */
    public void setPaperRate(BigDecimal paperRate) {
        this.paperRate = paperRate;
    }

    /**
     * 取得 付息频率
     * @return 付息频率
     */
    public Short getIntpayCycle() {
        return intpayCycle;
    }

    /**
     * 设置 付息频率
     * @param intpayCycle 付息频率
     */
    public void setIntpayCycle(Short intpayCycle) {
        this.intpayCycle = intpayCycle;
    }

    /**
     * 取得 担保情况说明
     * @return 担保情况说明
     */
    public String getGuaranteeCmt() {
        return guaranteeCmt;
    }

    /**
     * 设置 担保情况说明
     * @param guaranteeCmt 担保情况说明
     */
    public void setGuaranteeCmt(String guaranteeCmt) {
        this.guaranteeCmt = guaranteeCmt == null ? null : guaranteeCmt.trim();
    }

    /**
     * 取得 是否含权等特殊条款情况说明
     * @return 是否含权等特殊条款情况说明
     */
    public String getSpecialCmt() {
        return specialCmt;
    }

    /**
     * 设置 是否含权等特殊条款情况说明
     * @param specialCmt 是否含权等特殊条款情况说明
     */
    public void setSpecialCmt(String specialCmt) {
        this.specialCmt = specialCmt == null ? null : specialCmt.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 报送状态
     * @param sendStatus 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 设置 数据日期
     * @param statusDate 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
}