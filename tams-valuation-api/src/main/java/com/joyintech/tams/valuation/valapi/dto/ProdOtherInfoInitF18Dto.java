package com.joyintech.tams.valuation.valapi.dto;

import com.joyintech.tams.valuation.valapi.annotations.ValInterfApiField;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ProdOtherInfoInit001Dto
 * @Description: 产品其他信息初始化
 * @Description: 用于初始化F18组合
 * @Author yanya
 * @Date 2021/2/8
 * @Version V1.0
 **/
public class ProdOtherInfoInitF18Dto implements Serializable {


    private static final long serialVersionUID = -5494928966714336315L;
    /**
     * 金融产品代码
     */
    @ValInterfApiField
    private String finprodId;

    /**
     * 金融产品类型
     */
    @ValInterfApiField(fieldVal="F18")
    private String finprodType;

    /**
     * 金融产品简称（无金融产品简称传值同金融产品名称）
     */
    private String finprodAbbr;

    /**
     * 金融产品全称
     */
    @ValInterfApiField
    private String finprodName;

    /**
     * 母金融产品代码
     */
    private String pFinprodId;

    /**
     * 收益类型，净值、预期收益、货币、结构
     */
    @ValInterfApiField(dictCode="PROFIT_TYPE")
    private String profitType;

    /**
     * 起息日
     */
    @ValInterfApiField
    private Date vdate;

    /**
     * 到期日
     */
    @ValInterfApiField
    private Date mdate;

    /**
     * 币种
     */
    @ValInterfApiField(dictCode="CCY")
    private String ccy;

    /**
     * 交易日历
     */
    @ValInterfApiField
    private String calendarId;

    /**
     * 托管活期利息计提
     */
    private String isCalc;

    /**
     * 是否分层核算
     */
    @ValInterfApiField(dictCode="YES_OR_NO")
    private String isLayerAccount;

    /**
     * 投资组合代码
     */
    private String portfolioId;

    /**
     * 是否核算
     */
    @ValInterfApiField(dictCode="YES_OR_NO")
    private String isAccount;

    /**
     * 是否计税
     */
    @ValInterfApiField(dictCode="YES_OR_NO")
    private String isTax;

    /**
     * 估值表模板
     */
    private String valuationTemplate;

    /**
     * 实际到期日
     */
    private Date actmdate;

    /**
     * 单位净值/万份收益小数位
     */
    private String valDecDigits;

    /**
     * 单位净值/万份收益尾数处理方式
     */
    private String valDecType;

    /**
     * 利率小数位
     */
    private String yeildDecDigits;

    /**
     * 利率处理方式
     */
    private String yeildDecType;

    /**
     * 是否托管活期利息计提
     */
    private String depositAccout;



    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getpFinprodId() {
        return pFinprodId;
    }

    public void setpFinprodId(String pFinprodId) {
        this.pFinprodId = pFinprodId;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public String getIsCalc() {
        return isCalc;
    }

    public void setIsCalc(String isCalc) {
        this.isCalc = isCalc;
    }

    public String getIsLayerAccount() {
        return isLayerAccount;
    }

    public void setIsLayerAccount(String isLayerAccount) {
        this.isLayerAccount = isLayerAccount;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getIsAccount() {
        return isAccount;
    }

    public void setIsAccount(String isAccount) {
        this.isAccount = isAccount;
    }

    public String getIsTax() {
        return isTax;
    }

    public void setIsTax(String isTax) {
        this.isTax = isTax;
    }

    public String getValuationTemplate() {
        return valuationTemplate;
    }

    public void setValuationTemplate(String valuationTemplate) {
        this.valuationTemplate = valuationTemplate;
    }

    public Date getActmdate() {
        return actmdate;
    }

    public void setActmdate(Date actmdate) {
        this.actmdate = actmdate;
    }

    public String getValDecDigits() {
        return valDecDigits;
    }

    public void setValDecDigits(String valDecDigits) {
        this.valDecDigits = valDecDigits;
    }

    public String getValDecType() {
        return valDecType;
    }

    public void setValDecType(String valDecType) {
        this.valDecType = valDecType;
    }

    public String getYeildDecDigits() {
        return yeildDecDigits;
    }

    public void setYeildDecDigits(String yeildDecDigits) {
        this.yeildDecDigits = yeildDecDigits;
    }

    public String getYeildDecType() {
        return yeildDecType;
    }

    public void setYeildDecType(String yeildDecType) {
        this.yeildDecType = yeildDecType;
    }

    public String getDepositAccout() {
        return depositAccout;
    }

    public void setDepositAccout(String depositAccout) {
        this.depositAccout = depositAccout;
    }

    @Override
    public String toString() {
        return "ProdOtherInfoInit001Dto{" +
                "finprodId='" + finprodId + '\'' +
                ", finprodType='" + finprodType + '\'' +
                ", finprodAbbr='" + finprodAbbr + '\'' +
                ", finprodName='" + finprodName + '\'' +
                ", pFinprodId='" + pFinprodId + '\'' +
                ", profitType='" + profitType + '\'' +
                ", vdate=" + vdate +
                ", mdate=" + mdate +
                ", ccy='" + ccy + '\'' +
                ", calendarId='" + calendarId + '\'' +
                ", isCalc='" + isCalc + '\'' +
                ", isLayerAccount='" + isLayerAccount + '\'' +
                ", portfolioId='" + portfolioId + '\'' +
                ", isAccount='" + isAccount + '\'' +
                ", isTax='" + isTax + '\'' +
                ", valuationTemplate='" + valuationTemplate + '\'' +
                ", actmdate=" + actmdate +
                ", valDecDigits='" + valDecDigits + '\'' +
                ", valDecType='" + valDecType + '\'' +
                ", yeildDecDigits='" + yeildDecDigits + '\'' +
                ", yeildDecType='" + yeildDecType + '\'' +
                ", depositAccout='" + depositAccout + '\'' +
                '}';
    }
}
