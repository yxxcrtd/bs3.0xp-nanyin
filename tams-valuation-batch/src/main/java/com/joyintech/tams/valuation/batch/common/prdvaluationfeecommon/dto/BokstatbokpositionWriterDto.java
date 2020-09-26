/*
 * 文件名：BokstatbokpositionWriterDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年8月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.prdvaluationfeecommon.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.BokStatBokPosition;
import com.joyintech.tams.common.base.entity.FinProductValuation;

/**
 * (产品估值计算) - writer实体类
 * 
 * @author yangjian
 * @version 1.0 2018年8月13日
 * @see BokstatbokpositionWriterDto
 * @since 1.0
 */
public class BokstatbokpositionWriterDto extends NeedToWriteEntity {

    /**
     * 账务汇总表（账套）
     */
    private BokStatBokPosition bokStatBokPosition;
    
    /**
     * 场外估值行情信息
     */
    private FinProductValuation finProductValuation;
    
    

    /**
	 * @return the finProductValuation
	 */
	public FinProductValuation getFinProductValuation() {
		return finProductValuation;
	}

	/**
	 * @param finProductValuation the finProductValuation to set
	 */
	public void setFinProductValuation(FinProductValuation finProductValuation) {
		this.finProductValuation = finProductValuation;
	}

    public BokStatBokPosition getBokStatBokPosition() {
        return bokStatBokPosition;
    }

    public void setBokStatBokPosition(BokStatBokPosition bokStatBokPosition) {
        this.bokStatBokPosition = bokStatBokPosition;
    }

}
