/*
 * 文件名：PlanInterestAutoDurDealGenerateReaderResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.planinterest.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.invest.common.dto.period.trdplanintduration.TrdPlanInterestDurationDto;

/**
 * reader结果
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see PlanInterestAutoDurDealGenerateReaderResult
 * @since 1.0
 */
public class PlanInterestAutoDurDealGenerateReaderResult extends NeedOperateEntity {

    /**
     * TrdPlanInterestDurationDto
     */
    private TrdPlanInterestDurationDto tradeDataDto;

    /**
     * 获取 tradeDataDto
     *
     * @return tradeDataDto.
     */
    public TrdPlanInterestDurationDto getTradeDataDto() {
        return tradeDataDto;
    }

    /**
     * 设置 tradeDataDto
     *
     * @param tradeDataDto tradeDataDto
     */
    public void setTradeDataDto(TrdPlanInterestDurationDto tradeDataDto) {
        this.tradeDataDto = tradeDataDto;
    }

}
