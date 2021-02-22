/*
 * 文件名：MockPtlSecValuationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.mock.portfolio;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.PtlSecValution;

import java.math.BigDecimal;

/**
 * 组合资产估值扩展Entity
 * 组合资产分析查询 返回结果
 *
 * @author etliu
 * @version 1.0 2020年4月13日
 * @see MockPtlSecValuationDto
 * @since 1.0
 */
public class MockPtlSecValuationDto extends PtlSecValution {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1808252252836104232L;

    /**
     * 计提成本
     */
    private BigDecimal accrualCost;

    /**
     * 计提数量
     */
    private BigDecimal accrualAmt;

    /**
     * 计提利息
     */
    private BigDecimal accrualInt;

    /**
     * 待清算款币种
     */
    private String delayPayAmtCcy;

    /**
     * 实际日利率(actDYield 父类已存在)
     */

    /**
     * 投管资产类型
     */
    private String tgAssetType;

    /**
     * 资产负债标识(01：资产，02：负债)
     */
    private String assetLiabilitiesMark;

    /**
     * 资产价值
     */
    private BigDecimal assetValue;
    /**
     * 回购交易费用
     */
    private BigDecimal repFeeamt;
    /**
     * mock虚拟资产用 投管资产类型（用于资产配置）
     */
    private String finprodType3;

    public String getFinprodType3() {
        return finprodType3;
    }

    public void setFinprodType3(String finprodType3) {
        this.finprodType3 = finprodType3;
    }

    /**
     * 获取 accrualCost
     * 
     * @return accrualCost.
     */
    public BigDecimal getAccrualCost() {
        return accrualCost;
    }

    /**
     * 设置 accrualCost
     * 
     * @param accrualCost accrualCost
     */
    public void setAccrualCost(BigDecimal accrualCost) {
        this.accrualCost = accrualCost;
    }

    /**
     * 获取 accrualAmt
     * 
     * @return accrualAmt.
     */
    public BigDecimal getAccrualAmt() {
        return accrualAmt;
    }

    /**
     * 设置 accrualAmt
     * 
     * @param accrualAmt accrualAmt
     */
    public void setAccrualAmt(BigDecimal accrualAmt) {
        this.accrualAmt = accrualAmt;
    }

    /**
     * 获取 accrualInt
     * 
     * @return accrualInt.
     */
    public BigDecimal getAccrualInt() {
        return accrualInt;
    }

    /**
     * 设置 accrualInt
     * 
     * @param accrualInt accrualInt
     */
    public void setAccrualInt(BigDecimal accrualInt) {
        this.accrualInt = accrualInt;
    }

    /**
     * 获取 delayPayAmtCcy
     * 
     * @return delayPayAmtCcy.
     */
    public String getDelayPayAmtCcy() {
        return delayPayAmtCcy;
    }

    /**
     * 设置 delayPayAmtCcy
     * 
     * @param delayPayAmtCcy delayPayAmtCcy
     */
    public void setDelayPayAmtCcy(String delayPayAmtCcy) {
        this.delayPayAmtCcy = delayPayAmtCcy;
    }

    /**
     * 获取 tgAssetType
     * 
     * @return tgAssetType.
     */
    public String getTgAssetType() {
        return tgAssetType;
    }

    /**
     * 设置 tgAssetType
     * 
     * @param tgAssetType tgAssetType
     */
    public void setTgAssetType(String tgAssetType) {
        this.tgAssetType = tgAssetType;
    }

    /**
     * 获取 assetLiabilitiesMark
     * 
     * @return assetLiabilitiesMark.
     */
    public String getAssetLiabilitiesMark() {
        return assetLiabilitiesMark;
    }

    /**
     * 设置 assetLiabilitiesMark
     * 
     * @param assetLiabilitiesMark assetLiabilitiesMark
     */
    public void setAssetLiabilitiesMark(String assetLiabilitiesMark) {
        this.assetLiabilitiesMark = assetLiabilitiesMark;
    }

    /**
     * 获取 assetValue
     * 
     * @return assetValue.
     */
    public BigDecimal getAssetValue() {
        return assetValue;
    }

    /**
     * 设置 assetValue
     * 
     * @param assetValue assetValue
     */
    public void setAssetValue(BigDecimal assetValue) {
        this.assetValue = assetValue;
    }

    /**
     * 回购成本不包含费用，计算净值时使用
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal dscCostAmtNoFeeamt;

    public BigDecimal getDscCostAmtNoFeeamt() {
        return dscCostAmtNoFeeamt;
    }

    public void setDscCostAmtNoFeeamt(BigDecimal dscCostAmtNoFeeamt) {
        this.dscCostAmtNoFeeamt = dscCostAmtNoFeeamt;
    }


    /**
     * 资产价值-不包含费用，主要回购交易用
     */
    private BigDecimal assetValueNoFeeamt;

    public BigDecimal getAssetValueNoFeeamt() {
        return assetValueNoFeeamt;
    }

    public void setAssetValueNoFeeamt(BigDecimal assetValueNoFeeamt) {
        this.assetValueNoFeeamt = assetValueNoFeeamt;
    }

    /**
     * 获取 repFeeamt
     *
     * @return repFeeamt
     */
    public BigDecimal getRepFeeamt() {
        return repFeeamt;
    }

    /**
     * 设置 repFeeamt
     *
     * @param repFeeamt repFeeamt
     */
    public void setRepFeeamt(BigDecimal repFeeamt) {
        this.repFeeamt = repFeeamt;
    }
}
