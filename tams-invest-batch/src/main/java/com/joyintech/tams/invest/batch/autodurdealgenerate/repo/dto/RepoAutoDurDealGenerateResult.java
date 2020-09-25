/*
 * 文件名：RepoAutoDurDealGenerateResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.repo.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.invest.common.dto.period.common.BaseTrdDurDbInertDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;

/**
 * Result
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see RepoAutoDurDealGenerateResult
 * @since 1.0
 */
public class RepoAutoDurDealGenerateResult extends NeedToWriteEntity {

    /**
     * BaseTrdDurDbInertDto
     */
    private BaseTrdDurDbInertDto baseTrdDurDbInertDto;

    /**
     * 持仓登录用DTO
     */
    private FinProductTradeDto finProductTradeDto;

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

    /**
     * 获取 finProductTradeDto
     * 
     * @return finProductTradeDto.
     */
    public FinProductTradeDto getFinProductTradeDto() {
        return finProductTradeDto;
    }

    /**
     * 设置 finProductTradeDto
     * 
     * @param finProductTradeDto finProductTradeDto
     */
    public void setFinProductTradeDto(FinProductTradeDto finProductTradeDto) {
        this.finProductTradeDto = finProductTradeDto;
    }

}
