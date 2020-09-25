/*
 * 文件名：BussFinProductSaleParamDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod.dto;

import java.math.BigDecimal;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.Rate;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.common.base.entity.PrdSaleParam;
import com.joyintech.tams.component.business.prod.validator.BussFinProductSaleParamValidator;
import com.joyintech.tams.constant.DictCodeList;


/**
 * 销售参数业务组件Dto
 * 
 * @author zhangliang
 * @version 1.0 2018年10月17日
 * @see BussFinProductSaleParamDto
 * @since 1.0
 */
@ValidLogic(targetClass = BussFinProductSaleParamValidator.class)
public class BussFinProductSaleParamDto extends PrdSaleParam {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5554860812729741361L;
    
    /**
     * 主画面传入的pageId
     */
    private String pageId;

    
    /**
     * 获取 pageId
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }
    
    /**
     * 设置 pageId
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Amount(fieldName = "BussFinProductSaleParam.saleMax", maxDecBit = 2, maxIntBit= 16)
    public BigDecimal getSaleMax() {
        return super.getSaleMax();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Amount(fieldName = "BussFinProductSaleParam.saleMin", maxDecBit = 2, maxIntBit= 16)
    public BigDecimal getSaleMin() {
        return super.getSaleMin();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Amount(fieldName = "BussFinProductSaleParam.toffStart")
    public BigDecimal getToffStart() {
        return super.getToffStart();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Amount(fieldName = "BussFinProductSaleParam.lowestAmt")
    public BigDecimal getLowestAmt() {
        return super.getLowestAmt();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Amount(fieldName = "BussFinProductSaleParam.minPaperQty")
    public BigDecimal getMinPaperQty() {
        return super.getMinPaperQty();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Amount(fieldName = "BussFinProductSaleParam.minRedmQty")
    public BigDecimal getMinRedmQty() {
        return super.getMinRedmQty();
    }

    public boolean isPlge() {
        return StringUtil.equals(DictCodeList.是否_是.getValue(), getCanPlge());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @Rate(fieldName = "BussFinProductSaleParam.hugeRedRatio", maxDecBit = 2, maxIntBit= 3)
    public BigDecimal getHugeRedRatio() {
        return super.getHugeRedRatio();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Rate(fieldName = "BussFinProductSaleParam.maxPlgeRate", maxDecBit = 4)
    public BigDecimal getMaxPlgeRate() {
        return super.getMaxPlgeRate();
    }
    
    @Override
    @Amount(fieldName = "BussFinProductSaleParam.raiseAmtPlan", maxIntBit = 13)
    public BigDecimal getRaiseAmtPlan() {
        return super.getRaiseAmtPlan();
    }
    

}
