/*
 * 文件名：InterBorrowBaseInfoInitInputDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.valuationinterf.dto;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.common.base.entity.PaFinCashCalcRule;
import com.joyintech.framework.base.BaseEntity;

/**
 * 同业借款基本信息初始化 输入参数
 *
 * @author etliu
 * @version 1.0 2019年1月12日
 * @see InterBorrowBaseInfoInitInputDto
 * @since 1.0
 */
public class InterBorrowBaseInfoInitInputDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1120215975454839612L;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 标的类金融产品entity
     */
    private FinProduct finProduct;

    /**
     * 金融产品分类表entity
     */
    private FinProductType finProductType;

    /**
     * 现金流利率信息entity
     */
    private FinRateInfo finRateInfo;

    /**
     * 现金流计算规则扩展
     */
    private PaFinCashCalcRule paFinCashCalcRule;

    /**
     * 获取 finProduct
     * 
     * @return finProduct.
     */
    public FinProduct getFinProduct() {
        return finProduct;
    }

    /**
     * 设置 finProduct
     */
    public void setFinProduct(FinProduct finProduct) {
        this.finProduct = finProduct;
    }

    /**
     * 获取 finProductType
     * 
     * @return finProductType.
     */
    public FinProductType getFinProductType() {
        return finProductType;
    }

    /**
     * 设置 finProductType
     * 
     * @param finProductType finProductType
     */
    public void setFinProductType(FinProductType finProductType) {
        this.finProductType = finProductType;
    }

    /**
     * 获取 finRateInfo
     * 
     * @return finRateInfo.
     */
    public FinRateInfo getFinRateInfo() {
        return finRateInfo;
    }

    /**
     * 设置 finRateInfo
     * 
     * @param finRateInfo finRateInfo
     */
    public void setFinRateInfo(FinRateInfo finRateInfo) {
        this.finRateInfo = finRateInfo;
    }

    /**
     * 获取 paFinCashCalcRule
     * 
     * @return paFinCashCalcRule.
     */
    public PaFinCashCalcRule getPaFinCashCalcRule() {
        return paFinCashCalcRule;
    }

    /**
     * 设置 paFinCashCalcRule
     * 
     * @param paFinCashCalcRule paFinCashCalcRule
     */
    public void setPaFinCashCalcRule(PaFinCashCalcRule paFinCashCalcRule) {
        this.paFinCashCalcRule = paFinCashCalcRule;
    }

    /**
     * 获取 dataActionSign
     * 
     * @return dataActionSign.
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 dataActionSign
     * 
     * @param dataActionSign dataActionSign
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign;
    }

}
