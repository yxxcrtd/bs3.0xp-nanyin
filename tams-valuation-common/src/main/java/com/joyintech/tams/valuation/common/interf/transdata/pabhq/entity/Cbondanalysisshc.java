package com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class Cbondanalysisshc extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 对象ID
     */
    private String objectId;

    /**
     * Wind代码
     */
    private String sInfoWindcode;

    /**
     * 交易日期
     */
    private String tradeDt;

    /**
     * 待偿期(年)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalMatuCnbd;

    /**
     * 估价全价
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalDirtyCnbd;

    /**
     * 日间应计利息
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalAccrintCnbd;

    /**
     * 估价净价
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalNetCnbd;

    /**
     * 估价收益率(%)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalYieldCnbd;

    /**
     * 估价修正久期
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalModiduraCnbd;

    /**
     * 估价凸性
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalCnvxtyCnbd;

    /**
     * 估价基点价值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalVobpCnbd;

    /**
     * 估价利差久期
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalSprduraCnbd;

    /**
     * 估价利差凸性
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalSprcnxtCnbd;

    /**
     * 市场全价
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalPrice;

    /**
     * 市场净价
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalNetprice;

    /**
     * 市场收益率(%)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalYield;

    /**
     * 市场修正久期
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalModifiedduration;

    /**
     * 市场凸性
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalConvexity;

    /**
     * 市场基点价值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalBpvalue;

    /**
     * 市场利差久期
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalSduration;

    /**
     * 市场利差凸性
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalScnvxty;

    /**
     * 估价利率久期
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalInterestdurationCnbd;

    /**
     * 估价利率凸性
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalInterestcnvxtyCnbd;

    /**
     * 市场利率久期
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalInterestduration;

    /**
     * 市场利率凸性
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalInterestcnvxty;

    /**
     * 日终估价全价
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalPriceCnbd;

    /**
     * 日终应计利息
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalBpyield;

    /**
     * 剩余本金
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalSurpluscapital;

    /**
     * 流通场所
     */
    private String bAnalExchange;

    /**
     * 可信度
     */
    private String bAnalCredibility;

    /**
     * 更新时间
     */
    private Date opdate;

    /**
     * 更新模式
     */
    private String opmode;

    /**
     * 取得 对象ID
     * 
     * @return 对象ID
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 对象ID
     * 
     * @param objectId 对象ID
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 取得 Wind代码
     * 
     * @return Wind代码
     */
    public String getsInfoWindcode() {
        return sInfoWindcode;
    }

    /**
     * 设置 Wind代码
     * 
     * @param sInfoWindcode Wind代码
     */
    public void setsInfoWindcode(String sInfoWindcode) {
        this.sInfoWindcode = sInfoWindcode == null ? null : sInfoWindcode.trim();
    }

    /**
     * 取得 交易日期
     * 
     * @return 交易日期
     */
    public String getTradeDt() {
        return tradeDt;
    }

    /**
     * 设置 交易日期
     * 
     * @param tradeDt 交易日期
     */
    public void setTradeDt(String tradeDt) {
        this.tradeDt = tradeDt == null ? null : tradeDt.trim();
    }

    /**
     * 取得 待偿期(年)
     * 
     * @return 待偿期(年)
     */
    public BigDecimal getbAnalMatuCnbd() {
        return bAnalMatuCnbd;
    }

    /**
     * 设置 待偿期(年)
     * 
     * @param bAnalMatuCnbd 待偿期(年)
     */
    public void setbAnalMatuCnbd(BigDecimal bAnalMatuCnbd) {
        this.bAnalMatuCnbd = bAnalMatuCnbd;
    }

    /**
     * 取得 估价全价
     * 
     * @return 估价全价
     */
    public BigDecimal getbAnalDirtyCnbd() {
        return bAnalDirtyCnbd;
    }

    /**
     * 设置 估价全价
     * 
     * @param bAnalDirtyCnbd 估价全价
     */
    public void setbAnalDirtyCnbd(BigDecimal bAnalDirtyCnbd) {
        this.bAnalDirtyCnbd = bAnalDirtyCnbd;
    }

    /**
     * 取得 日间应计利息
     * 
     * @return 日间应计利息
     */
    public BigDecimal getbAnalAccrintCnbd() {
        return bAnalAccrintCnbd;
    }

    /**
     * 设置 日间应计利息
     * 
     * @param bAnalAccrintCnbd 日间应计利息
     */
    public void setbAnalAccrintCnbd(BigDecimal bAnalAccrintCnbd) {
        this.bAnalAccrintCnbd = bAnalAccrintCnbd;
    }

    /**
     * 取得 估价净价
     * 
     * @return 估价净价
     */
    public BigDecimal getbAnalNetCnbd() {
        return bAnalNetCnbd;
    }

    /**
     * 设置 估价净价
     * 
     * @param bAnalNetCnbd 估价净价
     */
    public void setbAnalNetCnbd(BigDecimal bAnalNetCnbd) {
        this.bAnalNetCnbd = bAnalNetCnbd;
    }

    /**
     * 取得 估价收益率(%)
     * 
     * @return 估价收益率(%)
     */
    public BigDecimal getbAnalYieldCnbd() {
        return bAnalYieldCnbd;
    }

    /**
     * 设置 估价收益率(%)
     * 
     * @param bAnalYieldCnbd 估价收益率(%)
     */
    public void setbAnalYieldCnbd(BigDecimal bAnalYieldCnbd) {
        this.bAnalYieldCnbd = bAnalYieldCnbd;
    }

    /**
     * 取得 估价修正久期
     * 
     * @return 估价修正久期
     */
    public BigDecimal getbAnalModiduraCnbd() {
        return bAnalModiduraCnbd;
    }

    /**
     * 设置 估价修正久期
     * 
     * @param bAnalModiduraCnbd 估价修正久期
     */
    public void setbAnalModiduraCnbd(BigDecimal bAnalModiduraCnbd) {
        this.bAnalModiduraCnbd = bAnalModiduraCnbd;
    }

    /**
     * 取得 估价凸性
     * 
     * @return 估价凸性
     */
    public BigDecimal getbAnalCnvxtyCnbd() {
        return bAnalCnvxtyCnbd;
    }

    /**
     * 设置 估价凸性
     * 
     * @param bAnalCnvxtyCnbd 估价凸性
     */
    public void setbAnalCnvxtyCnbd(BigDecimal bAnalCnvxtyCnbd) {
        this.bAnalCnvxtyCnbd = bAnalCnvxtyCnbd;
    }

    /**
     * 取得 估价基点价值
     * 
     * @return 估价基点价值
     */
    public BigDecimal getbAnalVobpCnbd() {
        return bAnalVobpCnbd;
    }

    /**
     * 设置 估价基点价值
     * 
     * @param bAnalVobpCnbd 估价基点价值
     */
    public void setbAnalVobpCnbd(BigDecimal bAnalVobpCnbd) {
        this.bAnalVobpCnbd = bAnalVobpCnbd;
    }

    /**
     * 取得 估价利差久期
     * 
     * @return 估价利差久期
     */
    public BigDecimal getbAnalSprduraCnbd() {
        return bAnalSprduraCnbd;
    }

    /**
     * 设置 估价利差久期
     * 
     * @param bAnalSprduraCnbd 估价利差久期
     */
    public void setbAnalSprduraCnbd(BigDecimal bAnalSprduraCnbd) {
        this.bAnalSprduraCnbd = bAnalSprduraCnbd;
    }

    /**
     * 取得 估价利差凸性
     * 
     * @return 估价利差凸性
     */
    public BigDecimal getbAnalSprcnxtCnbd() {
        return bAnalSprcnxtCnbd;
    }

    /**
     * 设置 估价利差凸性
     * 
     * @param bAnalSprcnxtCnbd 估价利差凸性
     */
    public void setbAnalSprcnxtCnbd(BigDecimal bAnalSprcnxtCnbd) {
        this.bAnalSprcnxtCnbd = bAnalSprcnxtCnbd;
    }

    /**
     * 取得 市场全价
     * 
     * @return 市场全价
     */
    public BigDecimal getbAnalPrice() {
        return bAnalPrice;
    }

    /**
     * 设置 市场全价
     * 
     * @param bAnalPrice 市场全价
     */
    public void setbAnalPrice(BigDecimal bAnalPrice) {
        this.bAnalPrice = bAnalPrice;
    }

    /**
     * 取得 市场净价
     * 
     * @return 市场净价
     */
    public BigDecimal getbAnalNetprice() {
        return bAnalNetprice;
    }

    /**
     * 设置 市场净价
     * 
     * @param bAnalNetprice 市场净价
     */
    public void setbAnalNetprice(BigDecimal bAnalNetprice) {
        this.bAnalNetprice = bAnalNetprice;
    }

    /**
     * 取得 市场收益率(%)
     * 
     * @return 市场收益率(%)
     */
    public BigDecimal getbAnalYield() {
        return bAnalYield;
    }

    /**
     * 设置 市场收益率(%)
     * 
     * @param bAnalYield 市场收益率(%)
     */
    public void setbAnalYield(BigDecimal bAnalYield) {
        this.bAnalYield = bAnalYield;
    }

    /**
     * 取得 市场修正久期
     * 
     * @return 市场修正久期
     */
    public BigDecimal getbAnalModifiedduration() {
        return bAnalModifiedduration;
    }

    /**
     * 设置 市场修正久期
     * 
     * @param bAnalModifiedduration 市场修正久期
     */
    public void setbAnalModifiedduration(BigDecimal bAnalModifiedduration) {
        this.bAnalModifiedduration = bAnalModifiedduration;
    }

    /**
     * 取得 市场凸性
     * 
     * @return 市场凸性
     */
    public BigDecimal getbAnalConvexity() {
        return bAnalConvexity;
    }

    /**
     * 设置 市场凸性
     * 
     * @param bAnalConvexity 市场凸性
     */
    public void setbAnalConvexity(BigDecimal bAnalConvexity) {
        this.bAnalConvexity = bAnalConvexity;
    }

    /**
     * 取得 市场基点价值
     * 
     * @return 市场基点价值
     */
    public BigDecimal getbAnalBpvalue() {
        return bAnalBpvalue;
    }

    /**
     * 设置 市场基点价值
     * 
     * @param bAnalBpvalue 市场基点价值
     */
    public void setbAnalBpvalue(BigDecimal bAnalBpvalue) {
        this.bAnalBpvalue = bAnalBpvalue;
    }

    /**
     * 取得 市场利差久期
     * 
     * @return 市场利差久期
     */
    public BigDecimal getbAnalSduration() {
        return bAnalSduration;
    }

    /**
     * 设置 市场利差久期
     * 
     * @param bAnalSduration 市场利差久期
     */
    public void setbAnalSduration(BigDecimal bAnalSduration) {
        this.bAnalSduration = bAnalSduration;
    }

    /**
     * 取得 市场利差凸性
     * 
     * @return 市场利差凸性
     */
    public BigDecimal getbAnalScnvxty() {
        return bAnalScnvxty;
    }

    /**
     * 设置 市场利差凸性
     * 
     * @param bAnalScnvxty 市场利差凸性
     */
    public void setbAnalScnvxty(BigDecimal bAnalScnvxty) {
        this.bAnalScnvxty = bAnalScnvxty;
    }

    /**
     * 取得 估价利率久期
     * 
     * @return 估价利率久期
     */
    public BigDecimal getbAnalInterestdurationCnbd() {
        return bAnalInterestdurationCnbd;
    }

    /**
     * 设置 估价利率久期
     * 
     * @param bAnalInterestdurationCnbd 估价利率久期
     */
    public void setbAnalInterestdurationCnbd(BigDecimal bAnalInterestdurationCnbd) {
        this.bAnalInterestdurationCnbd = bAnalInterestdurationCnbd;
    }

    /**
     * 取得 估价利率凸性
     * 
     * @return 估价利率凸性
     */
    public BigDecimal getbAnalInterestcnvxtyCnbd() {
        return bAnalInterestcnvxtyCnbd;
    }

    /**
     * 设置 估价利率凸性
     * 
     * @param bAnalInterestcnvxtyCnbd 估价利率凸性
     */
    public void setbAnalInterestcnvxtyCnbd(BigDecimal bAnalInterestcnvxtyCnbd) {
        this.bAnalInterestcnvxtyCnbd = bAnalInterestcnvxtyCnbd;
    }

    /**
     * 取得 市场利率久期
     * 
     * @return 市场利率久期
     */
    public BigDecimal getbAnalInterestduration() {
        return bAnalInterestduration;
    }

    /**
     * 设置 市场利率久期
     * 
     * @param bAnalInterestduration 市场利率久期
     */
    public void setbAnalInterestduration(BigDecimal bAnalInterestduration) {
        this.bAnalInterestduration = bAnalInterestduration;
    }

    /**
     * 取得 市场利率凸性
     * 
     * @return 市场利率凸性
     */
    public BigDecimal getbAnalInterestcnvxty() {
        return bAnalInterestcnvxty;
    }

    /**
     * 设置 市场利率凸性
     * 
     * @param bAnalInterestcnvxty 市场利率凸性
     */
    public void setbAnalInterestcnvxty(BigDecimal bAnalInterestcnvxty) {
        this.bAnalInterestcnvxty = bAnalInterestcnvxty;
    }

    /**
     * 取得 日终估价全价
     * 
     * @return 日终估价全价
     */
    public BigDecimal getbAnalPriceCnbd() {
        return bAnalPriceCnbd;
    }

    /**
     * 设置 日终估价全价
     * 
     * @param bAnalPriceCnbd 日终估价全价
     */
    public void setbAnalPriceCnbd(BigDecimal bAnalPriceCnbd) {
        this.bAnalPriceCnbd = bAnalPriceCnbd;
    }

    /**
     * 取得 日终应计利息
     * 
     * @return 日终应计利息
     */
    public BigDecimal getbAnalBpyield() {
        return bAnalBpyield;
    }

    /**
     * 设置 日终应计利息
     * 
     * @param bAnalBpyield 日终应计利息
     */
    public void setbAnalBpyield(BigDecimal bAnalBpyield) {
        this.bAnalBpyield = bAnalBpyield;
    }

    /**
     * 取得 剩余本金
     * 
     * @return 剩余本金
     */
    public BigDecimal getbAnalSurpluscapital() {
        return bAnalSurpluscapital;
    }

    /**
     * 设置 剩余本金
     * 
     * @param bAnalSurpluscapital 剩余本金
     */
    public void setbAnalSurpluscapital(BigDecimal bAnalSurpluscapital) {
        this.bAnalSurpluscapital = bAnalSurpluscapital;
    }

    /**
     * 获取 bAnalExchange
     * 
     * @return bAnalExchange.
     */
    public String getbAnalExchange() {
        return bAnalExchange;
    }

    /**
     * 设置 bAnalExchange
     * 
     * @param bAnalExchange bAnalExchange
     */
    public void setbAnalExchange(String bAnalExchange) {
        this.bAnalExchange = bAnalExchange;
    }

    /**
     * 获取 bAnalCredibility
     * 
     * @return bAnalCredibility.
     */
    public String getbAnalCredibility() {
        return bAnalCredibility;
    }

    /**
     * 设置 bAnalCredibility
     * 
     * @param bAnalCredibility bAnalCredibility
     */
    public void setbAnalCredibility(String bAnalCredibility) {
        this.bAnalCredibility = bAnalCredibility;
    }

    /**
     * 取得 更新时间
     * 
     * @return 更新时间
     */
    public Date getOpdate() {
        return opdate;
    }

    /**
     * 设置 更新时间
     * 
     * @param opdate 更新时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpdate(Date opdate) {
        this.opdate = opdate;
    }

    /**
     * 取得 更新模式
     * 
     * @return 更新模式
     */
    public String getOpmode() {
        return opmode;
    }

    /**
     * 设置 更新模式
     * 
     * @param opmode 更新模式
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode == null ? null : opmode.trim();
    }
}