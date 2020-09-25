/*
 * 文件名：ProjectAutoDurDealGenerateResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.project.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;

/**
 * NeedToWriteEntity
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see ProjectAutoDurDealGenerateResult
 * @since 1.0
 */
public class ProjectAutoDurDealGenerateResult extends NeedToWriteEntity {

    /**
     * BaseTotalTradeInfoDto
     */
    private BaseTotalTradeInfoDto baseTotalTradeInfoDto;

    /**
     * 获取 baseTotalTradeInfoDto
     *
     * @return baseTotalTradeInfoDto.
     */
    public BaseTotalTradeInfoDto getBaseTotalTradeInfoDto() {
        return baseTotalTradeInfoDto;
    }

    /**
     * 设置 baseTotalTradeInfoDto
     *
     * @param baseTotalTradeInfoDto baseTotalTradeInfoDto
     */
    public void setBaseTotalTradeInfoDto(BaseTotalTradeInfoDto baseTotalTradeInfoDto) {
        this.baseTotalTradeInfoDto = baseTotalTradeInfoDto;
    }

}
