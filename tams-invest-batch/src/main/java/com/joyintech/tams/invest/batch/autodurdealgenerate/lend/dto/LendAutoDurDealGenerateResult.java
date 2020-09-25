/*
 * 文件名：LendAutoDurDealGenerateResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.lend.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.invest.common.dto.period.common.BaseTrdDurDbInertDto;

/**
 * NeedToWriteEntity
 *
 * @author etliu
 * @version 1.0 2018年12月14日
 * @see LendAutoDurDealGenerateResult
 * @since 1.0
 */
public class LendAutoDurDealGenerateResult extends NeedToWriteEntity {

    /**
     * BaseTrdDurDbInertDto
     */
    private BaseTrdDurDbInertDto baseTrdDurDbInertDto;

    /**
     * 获取 baseTrdDurDbInertDto
     *
     * @return baseTrdDurDbInertDto.
     */
    public BaseTrdDurDbInertDto getBaseTrdDurDbInertDto() {
        return baseTrdDurDbInertDto;
    }

    /**
     * 设置 baseTrdDurDbInertDto
     *
     * @param baseTrdDurDbInertDto baseTrdDurDbInertDto
     */
    public void setBaseTrdDurDbInertDto(BaseTrdDurDbInertDto baseTrdDurDbInertDto) {
        this.baseTrdDurDbInertDto = baseTrdDurDbInertDto;
    }

}
