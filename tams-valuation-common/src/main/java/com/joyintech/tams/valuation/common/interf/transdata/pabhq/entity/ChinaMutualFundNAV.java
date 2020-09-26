package com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class ChinaMutualFundNAV extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
     * 对象ID
     */
    private String objectId;

    /**
     * Wind代码
     */
    private String fInfoWindcode;

    /**
     * 公告日期
     */
    private String annDate;

    /**
     * 截止日期
     */
    private String priceDate;

    /**
     * 单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fNavUnit;

    /**
     * 累计净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fNavAccumulated;

    /**
     * 累计分红
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fNavDivaccumulated;

    /**
     * 复权因子
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fNavAdjfactor;

    /**
     * 货币代码
     */
    private String crncyCode;

    /**
     * 资产净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fPrtNetasset;

    /**
     * 是否合计数据
     */
    private BigDecimal fAssetMergedsharesornot;

    /**
     * 合计资产净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netassetTotal;

    /**
     * 复权单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fNavAdjusted;

//    /**
//     * 是否净值除权日
//     */
//    private Integer isExdividenddate;

//    /**
//     * 累计单位分配
//     */
//    @JsonSerialize(using=BigDecimalSerializer.class)
//    private BigDecimal fNavDistribution;
    
    /**
     * 更新日期
     */
    private Date opdate;

    /**
     * 更新模式
     */
    private String opmode;

    /**
     * 取得 对象ID
     * @return 对象ID
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 对象ID
     * @param objectId 对象ID
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 取得 Wind代码
     * @return Wind代码
     */
    public String getfInfoWindcode() {
        return fInfoWindcode;
    }

    /**
     * 设置 Wind代码
     * @param fInfoWindcode Wind代码
     */
    public void setfInfoWindcode(String fInfoWindcode) {
        this.fInfoWindcode = fInfoWindcode == null ? null : fInfoWindcode.trim();
    }

    /**
     * 取得 公告日期
     * @return 公告日期
     */
    public String getAnnDate() {
        return annDate;
    }

    /**
     * 设置 公告日期
     * @param annDate 公告日期
     */
    public void setAnnDate(String annDate) {
        this.annDate = annDate == null ? null : annDate.trim();
    }

    /**
     * 取得 截止日期
     * @return 截止日期
     */
    public String getPriceDate() {
        return priceDate;
    }

    /**
     * 设置 截止日期
     * @param priceDate 截止日期
     */
    public void setPriceDate(String priceDate) {
        this.priceDate = priceDate == null ? null : priceDate.trim();
    }

    /**
     * 取得 单位净值
     * @return 单位净值
     */
    public BigDecimal getfNavUnit() {
        return fNavUnit;
    }

    /**
     * 设置 单位净值
     * @param fNavUnit 单位净值
     */
    public void setfNavUnit(BigDecimal fNavUnit) {
        this.fNavUnit = fNavUnit;
    }

    /**
     * 取得 累计净值
     * @return 累计净值
     */
    public BigDecimal getfNavAccumulated() {
        return fNavAccumulated;
    }

    /**
     * 设置 累计净值
     * @param fNavAccumulated 累计净值
     */
    public void setfNavAccumulated(BigDecimal fNavAccumulated) {
        this.fNavAccumulated = fNavAccumulated;
    }

    /**
     * 取得 累计分红
     * @return 累计分红
     */
    public BigDecimal getfNavDivaccumulated() {
        return fNavDivaccumulated;
    }

    /**
     * 设置 累计分红
     * @param fNavDivaccumulated 累计分红
     */
    public void setfNavDivaccumulated(BigDecimal fNavDivaccumulated) {
        this.fNavDivaccumulated = fNavDivaccumulated;
    }

    /**
     * 取得 复权因子
     * @return 复权因子
     */
    public BigDecimal getfNavAdjfactor() {
        return fNavAdjfactor;
    }

    /**
     * 设置 复权因子
     * @param fNavAdjfactor 复权因子
     */
    public void setfNavAdjfactor(BigDecimal fNavAdjfactor) {
        this.fNavAdjfactor = fNavAdjfactor;
    }

    /**
     * 取得 货币代码
     * @return 货币代码
     */
    public String getCrncyCode() {
        return crncyCode;
    }

    /**
     * 设置 货币代码
     * @param crncyCode 货币代码
     */
    public void setCrncyCode(String crncyCode) {
        this.crncyCode = crncyCode == null ? null : crncyCode.trim();
    }

    /**
     * 取得 资产净值
     * @return 资产净值
     */
    public BigDecimal getfPrtNetasset() {
        return fPrtNetasset;
    }

    /**
     * 设置 资产净值
     * @param fPrtNetasset 资产净值
     */
    public void setfPrtNetasset(BigDecimal fPrtNetasset) {
        this.fPrtNetasset = fPrtNetasset;
    }

    /**
     * 取得 是否合计数据
     * @return 是否合计数据
     */
    public BigDecimal getfAssetMergedsharesornot() {
        return fAssetMergedsharesornot;
    }

    /**
     * 设置 是否合计数据
     * @param fAssetMergedsharesornot 是否合计数据
     */
    public void setfAssetMergedsharesornot(BigDecimal fAssetMergedsharesornot) {
        this.fAssetMergedsharesornot = fAssetMergedsharesornot;
    }

    /**
     * 取得 合计资产净值
     * @return 合计资产净值
     */
    public BigDecimal getNetassetTotal() {
        return netassetTotal;
    }

    /**
     * 设置 合计资产净值
     * @param netassetTotal 合计资产净值
     */
    public void setNetassetTotal(BigDecimal netassetTotal) {
        this.netassetTotal = netassetTotal;
    }

    /**
     * 取得 复权单位净值
     * @return 复权单位净值
     */
    public BigDecimal getfNavAdjusted() {
        return fNavAdjusted;
    }

    /**
     * 设置 复权单位净值
     * @param fNavAdjusted 复权单位净值
     */
    public void setfNavAdjusted(BigDecimal fNavAdjusted) {
        this.fNavAdjusted = fNavAdjusted;
    }

//    /**
//     * 取得 是否净值除权日
//     * @return 是否净值除权日
//     */
//    public Integer getIsExdividenddate() {
//        return isExdividenddate;
//    }

//    /**
//     * 设置 是否净值除权日
//     * @param isExdividenddate 是否净值除权日
//     */
//    public void setIsExdividenddate(Integer isExdividenddate) {
//        this.isExdividenddate = isExdividenddate;
//    }
//
//    /**
//     * 取得 累计单位分配
//     * @return 累计单位分配
//     */
//    public BigDecimal getfNavDistribution() {
//        return fNavDistribution;
//    }

//    /**
//     * 设置 累计单位分配
//     * @param fNavDistribution 累计单位分配
//     */
//    public void setfNavDistribution(BigDecimal fNavDistribution) {
//        this.fNavDistribution = fNavDistribution;
//    }
    
    /**
     * 取得 更新日期
     * @return 更新日期
     */
    public Date getOpdate() {
        return opdate;
    }

    /**
     * 设置 更新日期
     * @param opdate 更新日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpdate(Date opdate) {
        this.opdate = opdate;
    }

    /**
     * 取得 更新模式
     * @return 更新模式
     */
    public String getOpmode() {
        return opmode;
    }

    /**
     * 设置 更新模式
     * @param opmode 更新模式
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode == null ? null : opmode.trim();
    }
}