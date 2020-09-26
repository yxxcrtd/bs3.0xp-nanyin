/*
 * 文件名：Exchangerate002Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年10月13日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitmaintenance.exchangerate.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.report.submitmaintenance.exchangerate.validator.Exchangerate002Validator;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2017年10月13日
 * @see Exchangerate002Dto
 * @since 1.0
 */
@ValidLogic(targetClass = Exchangerate002Validator.class)
public class Exchangerate002Dto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4502215142439428689L;
    /**
     * 汇率（新建的时候用）
     */
    @NotEmpty(fieldName = "汇率")
    private String exchangePrice;
    /**
     * 汇率（界面显示用）
     */
    private BigDecimal exchangeRate;
    /**
     * 币种
     */
    @NotEmpty(fieldName = "币种")
    private String ccyOriginal;

    /**
     * 折算币种
     */
    @NotEmpty(fieldName = "折算币种")
    private String ccyConvert;

    /**
     * 公布日期
     */
    @NotEmpty(fieldName = "公布日期")
    private String publishDate;
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 页面的模式
     */
    private String pageModel;
    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }


    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }


    /**
     * 获取 exchangePrice
     * @return exchangePrice.
     */
    public String getExchangePrice() {
        return exchangePrice;
    }

    
    /**
     * 设置 exchangePrice
     * @param exchangePrice exchangePrice
     */
    public void setExchangePrice(String exchangePrice) {
        this.exchangePrice = exchangePrice;
    }

    
    /**
     * 获取 ccyOriginal
     * @return ccyOriginal.
     */
    public String getCcyOriginal() {
        return ccyOriginal;
    }

    
    /**
     * 设置 ccyOriginal
     * @param ccyOriginal ccyOriginal
     */
    public void setCcyOriginal(String ccyOriginal) {
        this.ccyOriginal = ccyOriginal;
    }

    
    /**
     * 获取 ccyConvert
     * @return ccyConvert.
     */
    public String getCcyConvert() {
        return ccyConvert;
    }

    
    /**
     * 设置 ccyConvert
     * @param ccyConvert ccyConvert
     */
    public void setCcyConvert(String ccyConvert) {
        this.ccyConvert = ccyConvert;
    }

    
    /**
     * 获取 publishDate
     * @return publishDate.
     */
    public String getPublishDate() {
        return publishDate;
    }

    
    /**
     * 设置 publishDate
     * @param publishDate publishDate
     */
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }
    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }



    
    /**
     * 获取 exchangeRate
     * @return exchangeRate.
     */
    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }



    
    /**
     * 设置 exchangeRate
     * @param exchangeRate exchangeRate
     */
    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
