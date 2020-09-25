/*
 * 文件名：ProjectAutoDurDealGenerateReaderResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.project.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.invest.common.dto.period.trdprojduration.TrdProjectDurationDto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see ProjectAutoDurDealGenerateReaderResult
 * @since 1.0
 */
public class ProjectAutoDurDealGenerateReaderResult extends NeedOperateEntity {

    /**
     * 待处理数据dto
     */
    private TrdProjectDurationDto tradeDataDto;

    /**
     * 获取 tradeDataDto
     *
     * @return tradeDataDto.
     */
    public TrdProjectDurationDto getTradeDataDto() {
        return tradeDataDto;
    }

    /**
     * 设置 tradeDataDto
     *
     * @param tradeDataDto tradeDataDto
     */
    public void setTradeDataDto(TrdProjectDurationDto tradeDataDto) {
        this.tradeDataDto = tradeDataDto;
    }

}
