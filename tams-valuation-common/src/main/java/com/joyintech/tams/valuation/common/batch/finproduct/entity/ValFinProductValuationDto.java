/*
 * 文件名：ValFinProductValuationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年1月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.finproduct.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 万分收益信息Dto
 * 
 * @author niuqianyu
 * @version 1.0 2019年1月29日
 * @see ValFinProductValuationDto
 * @since 1.0
 */
public class ValFinProductValuationDto {

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 估值日期
     */
    private Date valDate;

    /**
     * 估值
     */
    private BigDecimal valuation;

    /**
     * 估值类型
     */
    private String inputType;

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 finprodType
     * 
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 finprodType
     * 
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 获取 valDate
     * 
     * @return valDate.
     */
    public Date getValDate() {
        return valDate;
    }

    /**
     * 设置 valDate
     * 
     * @param valDate valDate
     */
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    /**
     * 获取 valuation
     * 
     * @return valuation.
     */
    public BigDecimal getValuation() {
        return valuation;
    }

    /**
     * 设置 valuation
     * 
     * @param valuation valuation
     */
    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    /**
     * 获取 inputType
     * 
     * @return inputType.
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 inputType
     * 
     * @param inputType inputType
     */
    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

}
