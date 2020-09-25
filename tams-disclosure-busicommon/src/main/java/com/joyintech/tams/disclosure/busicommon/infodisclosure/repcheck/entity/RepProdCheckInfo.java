/*
 * 文件名：RepProdCheckInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年3月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.entity;

/**
 * 校验产品信息
 * 
 * @author wanghb
 * @version 1.0 2019年3月22日
 * @see RepProdCheckInfo
 * @since 1.0
 */
public class RepProdCheckInfo {

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品登记编码
     */
    private String prodRe;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品简称
     */
    private String prodAbbr;

    /**
     * 募集下限
     */
    private String raiseMin;

    /**
     * 募集上限
     */
    private String raiseMax;

    /**
     * 募集开始日
     */
    private String raiseStrdate;

    /**
     * 募集结束日
     */
    private String raiseEnddate;

    /**
     * 募集币种
     */
    private String raiseCcy;

    /**
     * 产品成立日
     */
    private String setDate;

    /**
     * 产品起息日
     */
    private String vdate;

    /**
     * 首次开放日
     */
    private String firstOpenDay;

    /**
     * 产品到期日
     */
    private String mdate;

    /**
     * 实际到期日
     */
    private String actMdate;

    /**
     * 产品期限
     */
    private String term;

    /**
     * 产品清盘日
     */
    private String windupDate;

    /**
     * 发行方式
     */
    private String issueMode;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 产品类型
     */
    private String prodType;

    /**
     * 发行状态
     */
    private String issueStatus;

    /**
     * 募集方式
     */
    private String raiseType;

    /**
     * 产品风险等级
     */
    private String riskLevel;

    /**
     * 取得 发行方式
     * 
     * @return 发行方式
     */
    public String getIssueMode() {
        return issueMode;
    }

    /**
     * 设置 发行方式
     * 
     * @param issueMode 发行方式
     */
    public void setIssueMode(String issueMode) {
        this.issueMode = issueMode == null ? null : issueMode.trim();
    }

    /**
     * 取得 收益类型
     * 
     * @return 收益类型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型
     * 
     * @param profitType 收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 产品类型
     * 
     * @return 产品类型
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * 设置 产品类型
     * 
     * @param prodType 产品类型
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    /**
     * 取得 发行状态
     * 
     * @return 发行状态
     */
    public String getIssueStatus() {
        return issueStatus;
    }

    /**
     * 设置 发行状态
     * 
     * @param issueStatus 发行状态
     */
    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus == null ? null : issueStatus.trim();
    }

    /**
     * 取得 募集方式
     * 
     * @return 募集方式
     */
    public String getRaiseType() {
        return raiseType;
    }

    /**
     * 设置 募集方式
     * 
     * @param raiseType 募集方式
     */
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType == null ? null : raiseType.trim();
    }

    /**
     * 取得 产品风险等级
     * 
     * @return 产品风险等级
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * 设置 产品风险等级
     * 
     * @param riskLevel 产品风险等级
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    /**
     * 获取 prodId
     * 
     * @return prodId.
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 prodId
     * 
     * @param prodId prodId
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 获取 prodRe
     * 
     * @return prodRe.
     */
    public String getProdRe() {
        return prodRe;
    }

    /**
     * 设置 prodRe
     * 
     * @param prodRe prodRe
     */
    public void setProdRe(String prodRe) {
        this.prodRe = prodRe;
    }

    /**
     * 获取 prodName
     * 
     * @return prodName.
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 prodName
     * 
     * @param prodName prodName
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     * 获取 prodAbbr
     * 
     * @return prodAbbr.
     */
    public String getProdAbbr() {
        return prodAbbr;
    }

    /**
     * 设置 prodAbbr
     * 
     * @param prodAbbr prodAbbr
     */
    public void setProdAbbr(String prodAbbr) {
        this.prodAbbr = prodAbbr;
    }

    /**
     * 获取 raiseMin
     * 
     * @return raiseMin.
     */
    public String getRaiseMin() {
        return raiseMin;
    }

    /**
     * 设置 raiseMin
     * 
     * @param raiseMin raiseMin
     */
    public void setRaiseMin(String raiseMin) {
        this.raiseMin = raiseMin;
    }

    /**
     * 获取 raiseMax
     * 
     * @return raiseMax.
     */
    public String getRaiseMax() {
        return raiseMax;
    }

    /**
     * 设置 raiseMax
     * 
     * @param raiseMax raiseMax
     */
    public void setRaiseMax(String raiseMax) {
        this.raiseMax = raiseMax;
    }

    /**
     * 获取 raiseStrdate
     * 
     * @return raiseStrdate.
     */
    public String getRaiseStrdate() {
        return raiseStrdate;
    }

    /**
     * 设置 raiseStrdate
     * 
     * @param raiseStrdate raiseStrdate
     */
    public void setRaiseStrdate(String raiseStrdate) {
        this.raiseStrdate = raiseStrdate;
    }

    /**
     * 获取 raiseEnddate
     * 
     * @return raiseEnddate.
     */
    public String getRaiseEnddate() {
        return raiseEnddate;
    }

    /**
     * 设置 raiseEnddate
     * 
     * @param raiseEnddate raiseEnddate
     */
    public void setRaiseEnddate(String raiseEnddate) {
        this.raiseEnddate = raiseEnddate;
    }

    /**
     * 获取 raiseCcy
     * 
     * @return raiseCcy.
     */
    public String getRaiseCcy() {
        return raiseCcy;
    }

    /**
     * 设置 raiseCcy
     * 
     * @param raiseCcy raiseCcy
     */
    public void setRaiseCcy(String raiseCcy) {
        this.raiseCcy = raiseCcy;
    }

    /**
     * 获取 setDate
     * 
     * @return setDate.
     */
    public String getSetDate() {
        return setDate;
    }

    /**
     * 设置 setDate
     * 
     * @param setDate setDate
     */
    public void setSetDate(String setDate) {
        this.setDate = setDate;
    }

    /**
     * 获取 vdate
     * 
     * @return vdate.
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     * 
     * @param vdate vdate
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 firstOpenDay
     * 
     * @return firstOpenDay.
     */
    public String getFirstOpenDay() {
        return firstOpenDay;
    }

    /**
     * 设置 firstOpenDay
     * 
     * @param firstOpenDay firstOpenDay
     */
    public void setFirstOpenDay(String firstOpenDay) {
        this.firstOpenDay = firstOpenDay;
    }

    /**
     * 获取 mdate
     * 
     * @return mdate.
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * 设置 mdate
     * 
     * @param mdate mdate
     */
    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 actMdate
     * 
     * @return actMdate.
     */
    public String getActMdate() {
        return actMdate;
    }

    /**
     * 设置 actMdate
     * 
     * @param actMdate actMdate
     */
    public void setActMdate(String actMdate) {
        this.actMdate = actMdate;
    }

    /**
     * 获取 term
     * 
     * @return term.
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置 term
     * 
     * @param term term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * 获取 windupDate
     * 
     * @return windupDate.
     */
    public String getWindupDate() {
        return windupDate;
    }

    /**
     * 设置 windupDate
     * 
     * @param windupDate windupDate
     */
    public void setWindupDate(String windupDate) {
        this.windupDate = windupDate;
    }

}
