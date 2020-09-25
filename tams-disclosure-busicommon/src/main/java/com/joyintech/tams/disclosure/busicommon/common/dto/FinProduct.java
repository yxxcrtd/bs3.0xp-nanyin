package com.joyintech.tams.disclosure.busicommon.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class FinProduct extends FinProductKey {
    /**
     * 金融产品简称
     */
    private String finprodAbbr;

    /**
     * 金融产品全称
     */
    private String finprodName;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 息票品种
     */
    private String couponSpecies;

    /**
     * 通道代码
     */
    private String chlFinprodId;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 发行价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issuePrice;

    /**
     * 发行规模
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issueAmt;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 境内外
     */
    private String blnArea;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 发行方式
     */
    private String issueType;

    /**
     * 发行人
     */
    private String issuer;

    /**
     * 发行日
     */
    private Date idate;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 实际到期日
     */
    private Date actmdate;

    /**
     * 是否通道
     */
    private String isChl;

    /**
     * 是否永续
     */
    private String isSus;

    /**
     * 是否含权
     */
    private String isRight;

    /**
     * 母金融产品代码
     */
    private String pFinprodId;

    /**
     * 原金融产品代码
     */
    private String oFinprodId;

    /**
     * 原金融产品类型
     */
    private String oFinprodType;

    /**
     * 本金收益特征
     */
    private String capiIncomeFeature;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 含权债摊销日
     */
    private String exeDate;

    /**
     * 到期赎回价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal matePrice;

    /**
     * 取得 金融产品简称
     * @return 金融产品简称
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 金融产品简称
     * @param finprodAbbr 金融产品简称
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr == null ? null : finprodAbbr.trim();
    }

    /**
     * 取得 金融产品全称
     * @return 金融产品全称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 金融产品全称
     * @param finprodName 金融产品全称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName == null ? null : finprodName.trim();
    }

    /**
     * 取得 收益类型
     * @return 收益类型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型
     * @param profitType 收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 息票品种
     * @return 息票品种
     */
    public String getCouponSpecies() {
        return couponSpecies;
    }

    /**
     * 设置 息票品种
     * @param couponSpecies 息票品种
     */
    public void setCouponSpecies(String couponSpecies) {
        this.couponSpecies = couponSpecies == null ? null : couponSpecies.trim();
    }

    /**
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChlFinprodId() {
        return chlFinprodId;
    }

    /**
     * 设置 通道代码
     * @param chlFinprodId 通道代码
     */
    public void setChlFinprodId(String chlFinprodId) {
        this.chlFinprodId = chlFinprodId == null ? null : chlFinprodId.trim();
    }

    /**
     * 取得 市场代码
     * @return 市场代码
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 市场代码
     * @param finprodMarketId 市场代码
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId == null ? null : finprodMarketId.trim();
    }

    /**
     * 取得 发行价
     * @return 发行价
     */
    public BigDecimal getIssuePrice() {
        return issuePrice;
    }

    /**
     * 设置 发行价
     * @param issuePrice 发行价
     */
    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }

    /**
     * 取得 发行规模
     * @return 发行规模
     */
    public BigDecimal getIssueAmt() {
        return issueAmt;
    }

    /**
     * 设置 发行规模
     * @param issueAmt 发行规模
     */
    public void setIssueAmt(BigDecimal issueAmt) {
        this.issueAmt = issueAmt;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 境内外
     * @return 境内外
     */
    public String getBlnArea() {
        return blnArea;
    }

    /**
     * 设置 境内外
     * @param blnArea 境内外
     */
    public void setBlnArea(String blnArea) {
        this.blnArea = blnArea == null ? null : blnArea.trim();
    }

    /**
     * 取得 交易场所
     * @return 交易场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 交易日历
     * @return 交易日历
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 交易日历
     * @param calendarId 交易日历
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId == null ? null : calendarId.trim();
    }

    /**
     * 取得 发行方式
     * @return 发行方式
     */
    public String getIssueType() {
        return issueType;
    }

    /**
     * 设置 发行方式
     * @param issueType 发行方式
     */
    public void setIssueType(String issueType) {
        this.issueType = issueType == null ? null : issueType.trim();
    }

    /**
     * 取得 发行人
     * @return 发行人
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * 设置 发行人
     * @param issuer 发行人
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer == null ? null : issuer.trim();
    }

    /**
     * 取得 发行日
     * @return 发行日
     */
    public Date getIdate() {
        return idate;
    }

    /**
     * 设置 发行日
     * @param idate 发行日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setIdate(Date idate) {
        this.idate = idate;
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
    @JsonDeserialize(using = CustomDateDeserialize.class)
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
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 实际到期日
     * @return 实际到期日
     */
    public Date getActmdate() {
        return actmdate;
    }

    /**
     * 设置 实际到期日
     * @param actmdate 实际到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setActmdate(Date actmdate) {
        this.actmdate = actmdate;
    }

    /**
     * 取得 是否通道
     * @return 是否通道
     */
    public String getIsChl() {
        return isChl;
    }

    /**
     * 设置 是否通道
     * @param isChl 是否通道
     */
    public void setIsChl(String isChl) {
        this.isChl = isChl == null ? null : isChl.trim();
    }

    /**
     * 取得 是否永续
     * @return 是否永续
     */
    public String getIsSus() {
        return isSus;
    }

    /**
     * 设置 是否永续
     * @param isSus 是否永续
     */
    public void setIsSus(String isSus) {
        this.isSus = isSus == null ? null : isSus.trim();
    }

    /**
     * 取得 是否含权
     * @return 是否含权
     */
    public String getIsRight() {
        return isRight;
    }

    /**
     * 设置 是否含权
     * @param isRight 是否含权
     */
    public void setIsRight(String isRight) {
        this.isRight = isRight == null ? null : isRight.trim();
    }

    /**
     * 取得 母金融产品代码
     * @return 母金融产品代码
     */
    public String getpFinprodId() {
        return pFinprodId;
    }

    /**
     * 设置 母金融产品代码
     * @param pFinprodId 母金融产品代码
     */
    public void setpFinprodId(String pFinprodId) {
        this.pFinprodId = pFinprodId == null ? null : pFinprodId.trim();
    }

    /**
     * 取得 原金融产品代码
     * @return 原金融产品代码
     */
    public String getoFinprodId() {
        return oFinprodId;
    }

    /**
     * 设置 原金融产品代码
     * @param oFinprodId 原金融产品代码
     */
    public void setoFinprodId(String oFinprodId) {
        this.oFinprodId = oFinprodId == null ? null : oFinprodId.trim();
    }

    /**
     * 取得 原金融产品类型
     * @return 原金融产品类型
     */
    public String getoFinprodType() {
        return oFinprodType;
    }

    /**
     * 设置 原金融产品类型
     * @param oFinprodType 原金融产品类型
     */
    public void setoFinprodType(String oFinprodType) {
        this.oFinprodType = oFinprodType == null ? null : oFinprodType.trim();
    }

    /**
     * 取得 本金收益特征
     * @return 本金收益特征
     */
    public String getCapiIncomeFeature() {
        return capiIncomeFeature;
    }

    /**
     * 设置 本金收益特征
     * @param capiIncomeFeature 本金收益特征
     */
    public void setCapiIncomeFeature(String capiIncomeFeature) {
        this.capiIncomeFeature = capiIncomeFeature == null ? null : capiIncomeFeature.trim();
    }

    /**
     * 取得 登记托管机构
     * @return 登记托管机构
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构
     * @param registOrg 登记托管机构
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

    /**
     * 取得 含权债摊销日
     * @return 含权债摊销日
     */
    public String getExeDate() {
        return exeDate;
    }

    /**
     * 设置 含权债摊销日
     * @param exeDate 含权债摊销日
     */
    public void setExeDate(String exeDate) {
        this.exeDate = exeDate == null ? null : exeDate.trim();
    }

    /**
     * 取得 到期赎回价
     * @return 到期赎回价
     */
    public BigDecimal getMatePrice() {
        return matePrice;
    }

    /**
     * 设置 到期赎回价
     * @param matePrice 到期赎回价
     */
    public void setMatePrice(BigDecimal matePrice) {
        this.matePrice = matePrice;
    }
}