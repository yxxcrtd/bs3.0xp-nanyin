/*
 * 文件名：RepoAutoDurDealGenerateReaderResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.repo.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.TrdRepoDurationDto;

/**
 * ReaderResult
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see RepoAutoDurDealGenerateReaderResult
 * @since 1.0
 */
public class RepoAutoDurDealGenerateReaderResult extends NeedOperateEntity {

    /**
     * 待处理数据dto
     */
    private TrdRepoDurationDto tradeDataDto;

    /**
     * 获取 tradeDataDto
     *
     * @return tradeDataDto.
     */
    public TrdRepoDurationDto getTradeDataDto() {
        return tradeDataDto;
    }

    /**
     * 设置 tradeDataDto
     *
     * @param tradeDataDto tradeDataDto
     */
    public void setTradeDataDto(TrdRepoDurationDto tradeDataDto) {
        this.tradeDataDto = tradeDataDto;
    }

}
