/*
 * 文件名：BondAutoDurDealGenerateResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.bond.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondDurDbInertDto;

/**
 * NeedToWriteEntity
 *
 * @author etliu
 * @version 1.0 2018年12月11日
 * @see BondAutoDurDealGenerateResult
 * @since 1.0
 */
public class BondAutoDurDealGenerateResult extends NeedToWriteEntity {

    /**
     * 将要登录的数据对象
     */
    private TrdBondDurDbInertDto trdBondDurDbInertDto;

    /**
     * 获取 trdBondDurDbInertDto
     *
     * @return trdBondDurDbInertDto.
     */
    public TrdBondDurDbInertDto getTrdBondDurDbInertDto() {
        return trdBondDurDbInertDto;
    }

    /**
     * 设置 trdBondDurDbInertDto
     *
     * @param trdBondDurDbInertDto trdBondDurDbInertDto
     */
    public void setTrdBondDurDbInertDto(TrdBondDurDbInertDto trdBondDurDbInertDto) {
        this.trdBondDurDbInertDto = trdBondDurDbInertDto;
    }

}
