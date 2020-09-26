package com.joyintech.tams.valuation.assets.loanmark.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 拆借标的查询dto
 * 
 * @author huwei
 * @version 1.0 2018年10月19日
 * @see ValLoanMarkquery001Dto
 * @since 1.0
 */
public class ValLoanMarkquery001Dto extends BaseEntity {

    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 金融产品类型Code
     */
    private String finprodTypeCode;

    /**
     * 名义本金
     */
    private BigDecimal prin;

    /**
     * 本金币种
     */
    private String ccy;

    /**
     * 本金币种Code
     */
    private String ccyCode;

    /**
     * 取得 本金币种Code
     * 
     * @return 本金币种Code
     */
    public String getCcyCode() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种,
            this.ccyCode)) ? SysDataUtil.getNameFromDataDict(DictCode.币种, this.ccyCode) : ccyCode;
    }

    /**
     * 设置本金币种Code
     * 
     * @param 本金币种Code 本金币种Code
     */
    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode == null ? null : ccyCode.trim();
    }

    /**
     * 起息日
     */
    private String vdate;

    /**
     * 到期日
     */
    private String mdate;

    /**
     * 期限(天)
     */
    private String term;

    /**
     * 期限品种
     */
    private String termType;

    /**
     * 期限品种Code
     */
    private String termTypeCode;

    /**
     * 利率类型
     */
    private String intType;

    /**
     * 固定利率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal intRate;

    /**
     * 利率编号
     */
    private String intRateId;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 到期本金
     */
    private BigDecimal mPrinAmt;

    /**
     * 到期利息
     */
    private BigDecimal mIntAmt;

    /**
     * 到期金额
     */
    private BigDecimal mTradeAmt;

    /**
     * 分类2
     */
    private String type2;

    /**
     * 分类2Code
     */
    private String type2Code;

    /**
     * 本金收益特征
     */
    private String capiIncomeFeature;

    /**
     * 本金收益特征Code
     */
    private String capiIncomeFeatureCode;

    /**
     * 取得 本金币种Code
     * 
     * @return 本金币种Code
     */
    public String getCapiIncomeFeatureCode() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.本金收益特征,
            this.capiIncomeFeatureCode)) ? SysDataUtil.getNameFromDataDict(DictCode.本金收益特征,
                this.capiIncomeFeatureCode) : capiIncomeFeatureCode;
    }

    /**
     * 设置本金币种Code
     * 
     * @param 本金币种Code 本金币种Code
     */
    public void setCapiIncomeFeatureCode(String capiIncomeFeatureCode) {
        this.capiIncomeFeatureCode = capiIncomeFeatureCode == null ? null : capiIncomeFeatureCode.trim();
    }

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 交易日历名称
     */
    private String calendarName;

    /**
     * 取得 金融产品代码
     * 
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * 
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品类型
     * 
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * 
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 金融产品类型Code
     * 
     * @return 金融产品类型Code
     */
    public String getFinprodTypeCode() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.金融产品类型,
            this.finprodTypeCode)) ? SysDataUtil.getNameFromDataDict(DictCode.金融产品类型,
                this.finprodTypeCode) : finprodTypeCode;
    }

    /**
     * 设置金融产品类型Code
     * 
     * @param finprodTypeCode 金融产品类型Code
     */
    public void setFinprodTypeCode(String finprodTypeCode) {
        this.finprodTypeCode = finprodTypeCode == null ? null : finprodTypeCode.trim();
    }

    /**
     * 取得 名义本金
     * 
     * @return 名义本金
     */
    public BigDecimal getPrin() {
        if (prin == null || prin.compareTo(BigDecimal.ZERO) == 0) {
            return ArithmeticUtil.round(BigDecimal.ZERO, 2, null);
        }
        return prin;
    }

    /**
     * 设置 名义本金
     * 
     * @param prin 名义本金
     */
    public void setPrin(BigDecimal prin) {
        this.prin = prin;
    }

    /**
     * 取得 本金币种
     * 
     * @return 本金币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 本金币种
     * 
     * @param ccy 本金币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 起息日
     * 
     * @return 起息日
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * 
     * @param vdate 起息日
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * 
     * @return 到期日
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * 
     * @param mdate 到期日
     */
    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 期限(天)
     * 
     * @return 期限(天)
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置 期限(天)
     * 
     * @param term 期限(天)
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * 取得 期限品种
     * 
     * @return 期限品种
     */
    public String getTermType() {
        return termType;
    }

    /**
     * 设置 期限品种
     * 
     * @param termType 期限品种
     */
    public void setTermType(String termType) {
        this.termType = termType == null ? null : termType.trim();
    }

    /**
     * 取得 期限品种Code
     * 
     * @return 期限品种Code
     */
    public String getTermTypeCode() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.期限品种_内部拆借,
            this.termTypeCode)) ? SysDataUtil.getNameFromDataDict(DictCode.期限品种_内部拆借,
                this.termTypeCode) : termTypeCode;
    }

    /**
     * 设置 期限品种Code
     * 
     * @param termTypeCode 金融产品类型Code
     */
    public void setTermTypeCode(String termTypeCode) {
        this.termTypeCode = termTypeCode == null ? null : termTypeCode.trim();
    }

    /**
     * 取得 利率类型
     * 
     * @return 利率类型
     */
    public String getIntType() {
        return intType;
    }

    /**
     * 设置 利率类型
     * 
     * @param intType 利率类型
     */
    public void setIntType(String intType) {
        this.intType = intType == null ? null : intType.trim();
    }

    /**
     * 取得 固定利率
     * 
     * @return 固定利率
     */
    public BigDecimal getIntRate() {
        if (intRate == null || intRate.compareTo(BigDecimal.ZERO) == 0) {
            return ArithmeticUtil.round(BigDecimal.ZERO, 2, null);
        }
        return intRate;
    }

    /**
     * 设置 固定利率
     * 
     * @param intRate 固定利率
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 取得 利率编号
     * 
     * @return 利率编号
     */
    public String getIntRateId() {
        return intRateId;
    }

    /**
     * 设置 利率编号
     * 
     * @param intRateId 利率编号
     */
    public void setIntRateId(String intRateId) {
        this.intRateId = intRateId == null ? null : intRateId.trim();
    }

    /**
     * 取得 计息基础
     * 
     * @return 计息基础
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础
     * 
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 到期本金
     * 
     * @return 到期本金
     */
    public BigDecimal getmPrinAmt() {
        return mPrinAmt;
    }

    /**
     * 设置 到期本金
     * 
     * @param mPrinAmt 到期本金
     */
    public void setmPrinAmt(BigDecimal mPrinAmt) {
        this.mPrinAmt = mPrinAmt;
    }

    /**
     * 取得 到期利息
     * 
     * @return 到期利息
     */
    public BigDecimal getmIntAmt() {
        if (mIntAmt == null || mIntAmt.compareTo(BigDecimal.ZERO) == 0) {
            return ArithmeticUtil.round(BigDecimal.ZERO, 2, null);
        }
        return mIntAmt;
    }

    /**
     * 设置 到期利息
     * 
     * @param mIntAmt 到期利息
     */
    public void setmIntAmt(BigDecimal mIntAmt) {
        this.mIntAmt = mIntAmt;
    }

    /**
     * 取得 到期金额
     * 
     * @return 到期金额
     */
    public BigDecimal getmTradeAmt() {

        if (mTradeAmt == null || mTradeAmt.compareTo(BigDecimal.ZERO) == 0) {
            return ArithmeticUtil.round(BigDecimal.ZERO, 2, null);
        }
        return mTradeAmt;
    }

    /**
     * 设置 到期金额
     * 
     * @param mTradeAmt 到期金额
     */
    public void setmTradeAmt(BigDecimal mTradeAmt) {
        this.mTradeAmt = mTradeAmt;
    }

    /**
     * 取得 分类2
     * 
     * @return 分类2
     */
    public String getType2() {
        return type2;
    }

    /**
     * 设置 分类2
     * 
     * @param type2 分类2
     */
    public void setType2(String type2) {
        this.type2 = type2 == null ? null : type2.trim();
    }

    /**
     * 取得 分类2Code
     * 
     * @return 分类2
     */
    public String getType2Code() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.拆借核算分类,
            this.type2Code)) ? SysDataUtil.getNameFromDataDict(DictCode.拆借核算分类,
                this.type2Code) : type2Code;
    }

    /**
     * 设置 分类2Code
     * 
     * @param type2Code 分类2
     */
    public void setType2Code(String type2Code) {
        this.type2Code = type2Code == null ? null : type2Code.trim();
    }

    /**
     * 取得 本金收益特征
     * 
     * @return 本金收益特征
     */
    public String getCapiIncomeFeature() {
        return capiIncomeFeature;
    }

    /**
     * 设置 本金收益特征
     * 
     * @param capiIncomeFeature 本金收益特征
     */
    public void setCapiIncomeFeature(String capiIncomeFeature) {
        this.capiIncomeFeature = capiIncomeFeature == null ? null : capiIncomeFeature.trim();
    }

    /**
     * 取得交易日历
     * 
     * @return 交易日历
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 交易日历
     * 
     * @param calendarId 交易日历
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId == null ? null : calendarId.trim();
    }

    /**
     * 获取 交易日历名称
     * 
     * @return 交易日历名称
     */
    public String getCalendarName() {
        return calendarName;
    }

    /**
     * 设置 交易日历名称
     * 
     * @param calendarName 交易日历名称
     */
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

}