package com.joyintech.tams.disclosure.information.config.repprodinfo.dto;

import com.joyintech.tams.common.base.entity.RepProdInfo;

import java.math.BigDecimal;


/**
 * @author zhoubin
 */
public class CompareProdInfoDto extends RepProdInfo{

    //产品状态
    private String pdStatus;
    //产品系列
    private String prodSeries;
    //产品名称
    private String pProdName;
    //产品简称
    private String prodAbbr;
    //产品登记编码
    private String prodRe;
    //产品主代码
    private String pProdId;
    //募集起始日期
    private String raiseStrdateStr;
    //募集结束日期
    private String raiseEnddateStr;
    //总产品募集金额
    private BigDecimal raiseAmt;
    //产品成立日
    private String pSetDateStr;
    //产品到期日
    private String mdateStr;
    //募集方式
    private String raiseType;
    //产品类型
    private String prodType;
    //发行方式
    private String issuingWay;
    //开放频率
    private String openFreq;
    //低持有天数
    private BigDecimal holdday;
    //产品运作方式
    private String profitType;
    //是否客户周期型产品
    private String isPeriod;
    //是否分级产品
    private String isGrade;
    //是否指数型产品
    private String isIndex;
    //投资性质
    private String natureInvest;
    //产品托管人名称
    private String acctChnname;
    //托管部门
    private String trustpart;

    @Override
    public String getPdStatus() {
        return pdStatus;
    }

    @Override
    public void setPdStatus(String pdStatus) {
        this.pdStatus = pdStatus;
    }

    @Override
    public String getProdSeries() {
        return prodSeries;
    }

    @Override
    public void setProdSeries(String prodSeries) {
        this.prodSeries = prodSeries;
    }

    public String getpProdName() {
        return pProdName;
    }

    public void setpProdName(String pProdName) {
        this.pProdName = pProdName;
    }

    @Override
    public String getProdAbbr() {
        return prodAbbr;
    }

    @Override
    public void setProdAbbr(String prodAbbr) {
        this.prodAbbr = prodAbbr;
    }

    @Override
    public String getProdRe() {
        return prodRe;
    }

    @Override
    public void setProdRe(String prodRe) {
        this.prodRe = prodRe;
    }

    @Override
    public String getpProdId() {
        return pProdId;
    }

    @Override
    public void setpProdId(String pProdId) {
        this.pProdId = pProdId;
    }

    public String getRaiseStrdateStr() {
        return raiseStrdateStr;
    }

    public void setRaiseStrdateStr(String raiseStrdateStr) {
        this.raiseStrdateStr = raiseStrdateStr;
    }

    public String getRaiseEnddateStr() {
        return raiseEnddateStr;
    }

    public void setRaiseEnddateStr(String raiseEnddateStr) {
        this.raiseEnddateStr = raiseEnddateStr;
    }

    @Override
    public BigDecimal getRaiseAmt() {
        return raiseAmt;
    }

    @Override
    public void setRaiseAmt(BigDecimal raiseAmt) {
        this.raiseAmt = raiseAmt;
    }

    public String getpSetDateStr() {
        return pSetDateStr;
    }

    public void setpSetDateStr(String pSetDateStr) {
        this.pSetDateStr = pSetDateStr;
    }

    public String getMdateStr() {
        return mdateStr;
    }

    public void setMdateStr(String mdateStr) {
        this.mdateStr = mdateStr;
    }

    @Override
    public String getRaiseType() {
        return raiseType;
    }

    @Override
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }

    @Override
    public String getProdType() {
        return prodType;
    }

    @Override
    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    @Override
    public String getIssuingWay() {
        return issuingWay;
    }

    @Override
    public void setIssuingWay(String issuingWay) {
        this.issuingWay = issuingWay;
    }

    @Override
    public String getOpenFreq() {
        return openFreq;
    }

    @Override
    public void setOpenFreq(String openFreq) {
        this.openFreq = openFreq;
    }

    @Override
    public BigDecimal getHoldday() {
        return holdday;
    }

    @Override
    public void setHoldday(BigDecimal holdday) {
        this.holdday = holdday;
    }

    @Override
    public String getProfitType() {
        return profitType;
    }

    @Override
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    @Override
    public String getIsPeriod() {
        return isPeriod;
    }

    @Override
    public void setIsPeriod(String isPeriod) {
        this.isPeriod = isPeriod;
    }

    @Override
    public String getIsGrade() {
        return isGrade;
    }

    @Override
    public void setIsGrade(String isGrade) {
        this.isGrade = isGrade;
    }

    @Override
    public String getIsIndex() {
        return isIndex;
    }

    @Override
    public void setIsIndex(String isIndex) {
        this.isIndex = isIndex;
    }

    @Override
    public String getNatureInvest() {
        return natureInvest;
    }

    @Override
    public void setNatureInvest(String natureInvest) {
        this.natureInvest = natureInvest;
    }

    @Override
    public String getAcctChnname() {
        return acctChnname;
    }

    @Override
    public void setAcctChnname(String acctChnname) {
        this.acctChnname = acctChnname;
    }

    @Override
    public String getTrustpart() {
        return trustpart;
    }

    @Override
    public void setTrustpart(String trustpart) {
        this.trustpart = trustpart;
    }

    @Override
    public String toString() {
        StringBuffer sb=new StringBuffer();
        sb.append(pdStatus).append("_");
        sb.append(prodSeries).append("_");
        sb.append(pProdName).append("_");
        sb.append(prodAbbr).append("_");
        sb.append(prodRe).append("_");
        sb.append(pProdId).append("_");
        sb.append(raiseStrdateStr).append("_");
        sb.append(raiseEnddateStr).append("_");
        sb.append(raiseAmt).append("_");
        sb.append(pSetDateStr).append("_");
        sb.append(mdateStr).append("_");
        sb.append(raiseType).append("_");
        sb.append(prodType).append("_");
        sb.append(issuingWay).append("_");
        sb.append(holdday).append("_");
        sb.append(profitType).append("_");
        sb.append(isPeriod).append("_");
        sb.append(isGrade).append("_");
        sb.append(isIndex).append("_");
        sb.append(natureInvest).append("_");
        sb.append(acctChnname).append("_");
        sb.append(trustpart);
        return sb.toString();
    }
}