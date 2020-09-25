/*
 * 文件名：ExchangeAutoDurDealGenerateWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年7月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.dto.ExchangeAutoDurDealGenerateResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.service.ExchangeAutoDurDealWflStartService;
import com.joyintech.tams.invest.common.dto.period.trdexchangeduration.TrdExchangeDurDbInertDto;

/**
 * 外汇远期掉期到期交易自动生成Writer
 *
 * @author renping
 * @version 1.0 2019年7月19日
 * @see ExchangeAutoDurDealGenerateWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ExchangeAutoDurDealGenerateWriter extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    private ExchangeAutoDurDealWflStartService exchangeAutoDurDealWflStartService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回的list值
        @SuppressWarnings("unchecked")
        List<ExchangeAutoDurDealGenerateResult> resultList = (List<ExchangeAutoDurDealGenerateResult>)items;
        if (!StringUtil.isEmptyList(resultList)) {
            for (ExchangeAutoDurDealGenerateResult exchangeAutoDurDealGenerateResult : resultList) {
                TrdExchangeDurDbInertDto trdExchangeDurDbInertDto = exchangeAutoDurDealGenerateResult.getTrdExchangeDurDbInertDto();
                if (trdExchangeDurDbInertDto == null) {
                    continue;
                }
                if (exchangeAutoDurDealGenerateResult.getTrdExchangeDuration002ProcessDto() == null) {
                    exchangeAutoDurDealWflStartService.insertBusinessData(
                        trdExchangeDurDbInertDto);
                }
                else {
                    exchangeAutoDurDealWflStartService.startwfl(
                        exchangeAutoDurDealGenerateResult.getTrdExchangeDuration002ProcessDto());
                }
            }
        }
    }
}
