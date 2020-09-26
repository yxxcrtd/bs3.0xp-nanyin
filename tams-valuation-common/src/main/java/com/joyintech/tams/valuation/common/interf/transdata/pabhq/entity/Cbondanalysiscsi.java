package com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class Cbondanalysiscsi extends BaseEntity {

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
     * 信用类型
     */
    private String bInfoType;

    /**
     * 发行市场数
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bInfoListingmarketnumbers;

    /**
     * 计算价格
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalDirtyCsi;

    /**
     * 计算收益率(%)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalYieldCsi;

    /**
     * 修正久期
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalModiduraCsi;

    /**
     * 凸性
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalCnvxtyCsi;

    /**
     * 净价
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalNetCsi;

    /**
     * 应计利息
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAnalAccrintCsi;

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
     * 取得 信用类型
     * 
     * @return 信用类型
     */
    public String getbInfoType() {
        return bInfoType;
    }

    /**
     * 设置 信用类型
     * 
     * @param bInfoType 信用类型
     */
    public void setbInfoType(String bInfoType) {
        this.bInfoType = bInfoType == null ? null : bInfoType.trim();
    }

    /**
     * 取得 发行市场数
     * 
     * @return 发行市场数
     */
    public BigDecimal getbInfoListingmarketnumbers() {
        return bInfoListingmarketnumbers;
    }

    /**
     * 设置 发行市场数
     * 
     * @param bInfoListingmarketnumbers 发行市场数
     */
    public void setbInfoListingmarketnumbers(BigDecimal bInfoListingmarketnumbers) {
        this.bInfoListingmarketnumbers = bInfoListingmarketnumbers;
    }

    /**
     * 取得 计算价格
     * 
     * @return 计算价格
     */
    public BigDecimal getbAnalDirtyCsi() {
        return bAnalDirtyCsi;
    }

    /**
     * 设置 计算价格
     * 
     * @param bAnalDirtyCsi 计算价格
     */
    public void setbAnalDirtyCsi(BigDecimal bAnalDirtyCsi) {
        this.bAnalDirtyCsi = bAnalDirtyCsi;
    }

    /**
     * 取得 计算收益率(%)
     * 
     * @return 计算收益率(%)
     */
    public BigDecimal getbAnalYieldCsi() {
        return bAnalYieldCsi;
    }

    /**
     * 设置 计算收益率(%)
     * 
     * @param bAnalYieldCsi 计算收益率(%)
     */
    public void setbAnalYieldCsi(BigDecimal bAnalYieldCsi) {
        this.bAnalYieldCsi = bAnalYieldCsi;
    }

    /**
     * 取得 修正久期
     * 
     * @return 修正久期
     */
    public BigDecimal getbAnalModiduraCsi() {
        return bAnalModiduraCsi;
    }

    /**
     * 设置 修正久期
     * 
     * @param bAnalModiduraCsi 修正久期
     */
    public void setbAnalModiduraCsi(BigDecimal bAnalModiduraCsi) {
        this.bAnalModiduraCsi = bAnalModiduraCsi;
    }

    /**
     * 取得 凸性
     * 
     * @return 凸性
     */
    public BigDecimal getbAnalCnvxtyCsi() {
        return bAnalCnvxtyCsi;
    }

    /**
     * 设置 凸性
     * 
     * @param bAnalCnvxtyCsi 凸性
     */
    public void setbAnalCnvxtyCsi(BigDecimal bAnalCnvxtyCsi) {
        this.bAnalCnvxtyCsi = bAnalCnvxtyCsi;
    }

    /**
     * 取得 净价
     * 
     * @return 净价
     */
    public BigDecimal getbAnalNetCsi() {
        return bAnalNetCsi;
    }

    /**
     * 设置 净价
     * 
     * @param bAnalNetCsi 净价
     */
    public void setbAnalNetCsi(BigDecimal bAnalNetCsi) {
        this.bAnalNetCsi = bAnalNetCsi;
    }

    /**
     * 取得 应计利息
     * 
     * @return 应计利息
     */
    public BigDecimal getbAnalAccrintCsi() {
        return bAnalAccrintCsi;
    }

    /**
     * 设置 应计利息
     * 
     * @param bAnalAccrintCsi 应计利息
     */
    public void setbAnalAccrintCsi(BigDecimal bAnalAccrintCsi) {
        this.bAnalAccrintCsi = bAnalAccrintCsi;
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