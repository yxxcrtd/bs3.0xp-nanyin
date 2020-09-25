/*
 * 文件名：ValAssetFundQueryDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.assets.fund.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 基金类资产查询list DTO
 * 
 * @author zhangwei
 * @version 1.0 2018年7月19日
 * @see ValAssetsFundQuery001Dto
 * @since 1.0
 */
public class ValAssetsFundQuery001Dto extends FinProduct implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /** 资产类型： F06基金类型 */
    private String finprodType;

    /** 基金类型 */
    private String fundType;

    /** 币种 */
    private String ccyCode;

    private String payCycle;

    /*
     * 交易日历
     */
    private String calendarId;
    
    /**
     * 金融产品代码
     */
    private String finprodId;

    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 calendarId
     * 
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 calendarId
     * 
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public String getPayCycle() {
        return payCycle;
    }

    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle;
    }

    /**
     * 获取 ccyCode
     * 
     * @return ccyCode.
     */
    public String getCcyCode() {
        return ccyCode;
    }

    /**
     * 设置 ccyCode
     * 
     * @param ccyCode ccyCode
     */
    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
