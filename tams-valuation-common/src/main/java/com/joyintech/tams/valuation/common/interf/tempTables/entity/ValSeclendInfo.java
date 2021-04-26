package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValSeclendInfo extends BaseEntity {
    /**
     * 标的代码
     */
    private String tradeId;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 借贷期限
     */
    private Long term;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeRate;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 首期结算方式
     */
    private String vSettMethod;

    /**
     * 到期结算方式
     */
    private String mSettMethod;

    /**
     * 标的债券代码
     */
    private String secId;

    /**
     * 标的券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinAmt;

    /**
     * 质押券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mPrinAmt;

    /**
     * 本金收益特征
     */
    private String capiIncomeFeature;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 押品只数
     */
    private Long pledgeSecNum;

    /**
     * 借贷费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fee;

    /**
     * 取得 标的代码
     * @return 标的代码
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 标的代码
     * @param tradeId 标的代码
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 数据动作标志
     * @return 数据动作标志
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 数据动作标志
     * @param dataActionSign 数据动作标志
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
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
     * 取得 借贷期限
     * @return 借贷期限
     */
    public Long getTerm() {
        return term;
    }

    /**
     * 设置 借贷期限
     * @param term 借贷期限
     */
    public void setTerm(Long term) {
        this.term = term;
    }

    /**
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 计息基础
     * @return 计息基础
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 费率
     * @return 费率
     */
    public BigDecimal getFeeRate() {
        return feeRate;
    }

    /**
     * 设置 费率
     * @param feeRate 费率
     */
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
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
     * 取得 首期结算方式
     * @return 首期结算方式
     */
    public String getvSettMethod() {
        return vSettMethod;
    }

    /**
     * 设置 首期结算方式
     * @param vSettMethod 首期结算方式
     */
    public void setvSettMethod(String vSettMethod) {
        this.vSettMethod = vSettMethod == null ? null : vSettMethod.trim();
    }

    /**
     * 取得 到期结算方式
     * @return 到期结算方式
     */
    public String getmSettMethod() {
        return mSettMethod;
    }

    /**
     * 设置 到期结算方式
     * @param mSettMethod 到期结算方式
     */
    public void setmSettMethod(String mSettMethod) {
        this.mSettMethod = mSettMethod == null ? null : mSettMethod.trim();
    }

    /**
     * 取得 标的债券代码
     * @return 标的债券代码
     */
    public String getSecId() {
        return secId;
    }

    /**
     * 设置 标的债券代码
     * @param secId 标的债券代码
     */
    public void setSecId(String secId) {
        this.secId = secId == null ? null : secId.trim();
    }

    /**
     * 取得 标的券面总额
     * @return 标的券面总额
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 标的券面总额
     * @param prinAmt 标的券面总额
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 取得 质押券面总额
     * @return 质押券面总额
     */
    public BigDecimal getmPrinAmt() {
        return mPrinAmt;
    }

    /**
     * 设置 质押券面总额
     * @param mPrinAmt 质押券面总额
     */
    public void setmPrinAmt(BigDecimal mPrinAmt) {
        this.mPrinAmt = mPrinAmt;
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
     * 取得 押品只数
     * @return 押品只数
     */
    public Long getPledgeSecNum() {
        return pledgeSecNum;
    }

    /**
     * 设置 押品只数
     * @param pledgeSecNum 押品只数
     */
    public void setPledgeSecNum(Long pledgeSecNum) {
        this.pledgeSecNum = pledgeSecNum;
    }

    /**
     * 取得 借贷费用
     * @return 借贷费用
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * 设置 借贷费用
     * @param fee 借贷费用
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
    
    private String belongInstitutionCode;

    public String getBelongInstitutionCode() {
        return belongInstitutionCode;
    }

    public void setBelongInstitutionCode(String belongInstitutionCode) {
        this.belongInstitutionCode = belongInstitutionCode;
    }
    
}