package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValFxTargetRateAdjust extends BaseEntity {
    /**
     * 逾期资产代码
     */
    private String assetCode;

    /**
     * 逾期资产类型
     */
    private String finprodType;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 利率类型
     */
    private String intType;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 新增、撤销
     */
    private String dataActionSign;

    /**
     * 取得 逾期资产代码
     * @return 逾期资产代码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 逾期资产代码
     * @param assetCode 逾期资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 逾期资产类型
     * @return 逾期资产类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 逾期资产类型
     * @param finprodType 逾期资产类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * @param effDate 生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 取得 利率类型
     * @return 利率类型
     */
    public String getIntType() {
        return intType;
    }

    /**
     * 设置 利率类型
     * @param intType 利率类型
     */
    public void setIntType(String intType) {
        this.intType = intType == null ? null : intType.trim();
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
     * 取得 利率
     * @return 利率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 利率
     * @param rate 利率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 取得 新增、撤销
     * @return 新增、撤销
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 新增、撤销
     * @param dataActionSign 新增、撤销
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }
}