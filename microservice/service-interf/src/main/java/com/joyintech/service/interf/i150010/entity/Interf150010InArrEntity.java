/*
 * 文件名：Interf150010InArrEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i150010.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 资管费用信息接口（150010）-输入费项数组
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月18日
 * @see Interf150010InArrEntity
 * @since 1.0
 */
public class Interf150010InArrEntity extends Interf150010InEntity {

    /**
     * 费用生效日
     */
    private String feeEffectDate;

    /**
     * 费用失效日
     */
    private String feeInvalidDate;

    /**
     * 费用种类
     */
    private String feeType;

    /**
     * 费用名称
     */
    private String feeName;

    /**
     * 收费模式
     */
    private String chargeMode;

    /**
     * 手续费率
     */
    private String commissionRate;

    /**
     * 尾数处理模式
     */
    private String tailDealType;

    /**
     * 取得 费用生效日
     * 
     * @return 费用生效日
     */
    @EsbName(value = "FEE_EFFECT_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getFeeEffectDate() {
        return feeEffectDate;
    }

    /**
     * 取得 费用失效日
     * 
     * @return 费用失效日
     */
    @EsbName(value = "FEE_INVALID_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getFeeInvalidDate() {
        return feeInvalidDate;
    }

    /**
     * 取得 费用种类
     * 
     * @return 费用种类
     */
    @EsbName(value = "FEE_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getFeeType() {
        return feeType;
    }

    /**
     * 取得 费用名称
     * 
     * @return 费用名称
     */
    @EsbName(value = "FEE_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    @Required
    public String getFeeName() {
        return feeName;
    }

    /**
     * 取得 收费模式
     * 
     * @return 收费模式
     */
    @EsbName(value = "CHARGE_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    public String getChargeMode() {
        return chargeMode;
    }

    /**
     * 取得 手续费率
     * 
     * @return 手续费率
     */
    @EsbName(value = "COMMISSION_RATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getCommissionRate() {
        return commissionRate;
    }

    /**
     * 取得 尾数处理模式
     * 
     * @return 尾数处理模式
     */
    @EsbName(value = "TAIL_DEAL_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getTailDealType() {
        return tailDealType;
    }

    /**
     * 设置 费用生效日
     * 
     * @param feeEffectDate 费用生效日
     */
    public void setFeeEffectDate(String feeEffectDate) {
        this.feeEffectDate = feeEffectDate;
    }

    /**
     * 设置 费用失效日
     * 
     * @param feeInvalidDate 费用失效日
     */
    public void setFeeInvalidDate(String feeInvalidDate) {
        this.feeInvalidDate = feeInvalidDate;
    }

    /**
     * 设置 费用种类
     * 
     * @param feeType 费用种类
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    /**
     * 设置 费用名称
     * 
     * @param feeName 费用名称
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    /**
     * 设置 收费模式
     * 
     * @param chargeMode 收费模式
     */
    public void setChargeMode(String chargeMode) {
        this.chargeMode = chargeMode;
    }

    /**
     * 设置 手续费率
     * 
     * @param commissionRate 手续费率
     */
    public void setCommissionRate(String commissionRate) {
        this.commissionRate = commissionRate;
    }

    /**
     * 设置 尾数处理模式
     * 
     * @param tailDealType 尾数处理模式
     */
    public void setTailDealType(String tailDealType) {
        this.tailDealType = tailDealType;
    }
}
