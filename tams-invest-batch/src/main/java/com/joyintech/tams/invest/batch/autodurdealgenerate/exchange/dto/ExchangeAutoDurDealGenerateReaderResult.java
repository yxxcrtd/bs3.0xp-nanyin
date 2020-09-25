/*
 * 文件名：ExchangeAutoDurDealGenerateReaderResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年7月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.invest.common.dto.period.trdexchangeduration.TrdExchangeDuration002ProcessDto;
import com.joyintech.tams.invest.common.dto.period.trdexchangeduration.TrdExchangeDurationDto;

/**
 * 外汇远期掉期到期交易自动生成reader结果
 *
 * @author renping
 * @version 1.0 2019年7月19日
 * @see ExchangeAutoDurDealGenerateReaderResult
 * @since 1.0
 */
public class ExchangeAutoDurDealGenerateReaderResult extends NeedOperateEntity {

    /**
     * 待处理数据dto
     */
    private TrdExchangeDurationDto tradeDataDto;

    /**
     * 流程启动用Dto
     */
    private TrdExchangeDuration002ProcessDto trdExchangeDuration002ProcessDto;

    /**
     * 获取 tradeDataDto
     *
     * @return tradeDataDto.
     */
    public TrdExchangeDurationDto getTradeDataDto() {
        return tradeDataDto;
    }

    /**
     * 设置 tradeDataDto
     *
     * @param tradeDataDto tradeDataDto
     */
    public void setTradeDataDto(TrdExchangeDurationDto tradeDataDto) {
        this.tradeDataDto = tradeDataDto;
    }

    /**
     * 获取 trdExchangeDuration002ProcessDto
     * 
     * @return trdExchangeDuration002ProcessDto.
     */
    public TrdExchangeDuration002ProcessDto getTrdExchangeDuration002ProcessDto() {
        return trdExchangeDuration002ProcessDto;
    }

    /**
     * 设置 trdExchangeDuration002ProcessDto
     * 
     * @param trdExchangeDuration002ProcessDto trdExchangeDuration002ProcessDto
     */
    public void setTrdExchangeDuration002ProcessDto(TrdExchangeDuration002ProcessDto trdExchangeDuration002ProcessDto) {
        this.trdExchangeDuration002ProcessDto = trdExchangeDuration002ProcessDto;
    }

}
