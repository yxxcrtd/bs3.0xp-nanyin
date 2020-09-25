/*
 * 文件名：FinProductFloatFeeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年12月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.framework.validation.annotation.Amount;


/**
 * 浮动管理费层级信息
 * 
 * @author zhangliang
 * @version 1.0 2018年12月13日
 * @see FinProductFloatFeeDto
 * @since 1.0
 */
public class FinProductFloatFeeDto extends FinFloatCalcRuleDetail {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -7171568165155864619L;
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Amount(fieldName = "BussFinProductFloatFee.table.coefficient", maxDecBit = 14)
    public BigDecimal getCoefficient() {
        return super.getCoefficient();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Amount(fieldName = "BussFinProductFloatFee.table.spreadRate", maxDecBit = 14)
    public BigDecimal getSpreadRate() {
        return super.getSpreadRate();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Amount(fieldName = "BussFinProductFloatFee.table.chargeRatio", maxDecBit = 14)
    public BigDecimal getChargeRatio() {
        return super.getChargeRatio();
    }

}
