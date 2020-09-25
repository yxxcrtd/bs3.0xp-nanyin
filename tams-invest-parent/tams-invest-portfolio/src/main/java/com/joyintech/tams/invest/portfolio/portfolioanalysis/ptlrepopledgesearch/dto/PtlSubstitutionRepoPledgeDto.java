package com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.FinDealPledge;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: 质押券置换查询dto
 * @description: PtlRepoPledgeSearchDto
 * @author: nixiukuan
 * @create: 2019-11-12
 **/
public class PtlSubstitutionRepoPledgeDto extends BaseEntity {
    /**
     * 业务编号
     */
    private String businessCode;
    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 原始业务编号
     */
    private String sourceBusinessCode;

    /**
     * 委托代码
     */
    private String entrustCode;

    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 标的债券代码
     */
    private String finprodId;
    /**
     * 标的债券名称
     */
    private String finprodName;
    /**
     * 交易方向
     */
    private String tradePs;
    /**
     * 交易场所
     */
    private String tradeMarket;
    /**
     * 券面总额
     */
    private BigDecimal tradeAmt;
    /**
     * 交易日期
     */
    private Date tradeDate;
    /**
     * 首次结算日
     */
    private Date vPayDate;
    /**
     * 到期结算日
     */
    private Date mPayDate;

    /**
     * 期限品种
     */
    private String termType;
    /**
     * 实际占券天数
     */
    private int actCapDays;
    /**
     * 质押券置换日期
     */
    private Date pledgeSubstitutionDate;

    /**
     * 币种
     */
    private String ccy;
    /**
     * 备注
     */
    private String remark;

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getTradeType() {
        return tradeType;
    }
    public  String getTradeTypeName()
    {
        return SysDataUtil.getNameFromDataDict(DictCode.业务类型,tradeType);
    }
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getSourceBusinessCode() {
        return sourceBusinessCode;
    }

    public void setSourceBusinessCode(String sourceBusinessCode) {
        this.sourceBusinessCode = sourceBusinessCode;
    }

    public String getEntrustCode() {
        return entrustCode;
    }

    public void setEntrustCode(String entrustCode) {
        this.entrustCode = entrustCode;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getTradePs() {
        return tradePs;
    }

    public  String getTradePsName()
    {
        return SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向,tradePs);
    }
    public void setTradePs(String tradePs) {
        this.tradePs = tradePs;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }
    public  String getTradeMarketName()
    {
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所,tradeMarket);
    }
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Date getvPayDate() {
        return vPayDate;
    }

    public void setvPayDate(Date vPayDate) {
        this.vPayDate = vPayDate;
    }

    public Date getmPayDate() {
        return mPayDate;
    }

    public void setmPayDate(Date mPayDate) {
        this.mPayDate = mPayDate;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }
    public String getTermTypeName() {
        return  SysDataUtil.getNameFromDataDict(DictCode.期限品种_回购,termType);
    }

    public int getActCapDays() {
        return actCapDays;
    }

    public void setActCapDays(int actCapDays) {
        this.actCapDays = actCapDays;
    }

    public Date getPledgeSubstitutionDate() {
        return pledgeSubstitutionDate;
    }

    public void setPledgeSubstitutionDate(Date pledgeSubstitutionDate) {
        this.pledgeSubstitutionDate = pledgeSubstitutionDate;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public  String getCcyName()
    {
        return SysDataUtil.getNameFromDataDict(DictCode.币种,ccy);
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
