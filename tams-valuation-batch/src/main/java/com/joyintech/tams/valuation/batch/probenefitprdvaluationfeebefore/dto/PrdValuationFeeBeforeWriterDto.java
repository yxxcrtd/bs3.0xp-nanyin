/*
 * 文件名：PrdValuationFeeBeforeWriterDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年9月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.probenefitprdvaluationfeebefore.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.BokStatBokPosition;

/**
 * (预期收益型产品估值计算) - writer实体类
 * 
 * @author niuqianyu
 * @version 1.0 2018年9月28日
 * @see PrdValuationFeeBeforeWriterDto
 * @since 1.0
 */
public class PrdValuationFeeBeforeWriterDto extends NeedToWriteEntity {

    /**
     * 账务汇总表（账套）
     */
    private BokStatBokPosition bokStatBokPosition;

    public BokStatBokPosition getBokStatBokPosition() {
        return bokStatBokPosition;
    }

    public void setBokStatBokPosition(BokStatBokPosition bokStatBokPosition) {
        this.bokStatBokPosition = bokStatBokPosition;
    }
}
