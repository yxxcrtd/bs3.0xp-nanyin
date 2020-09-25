/*
 * 文件名：ExchangeAutoDurDealGenerateReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年7月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.core.dto.SysBatchExecInfoDto;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.dto.ExchangeAutoDurDealGenerateReaderResult;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.period.trdexchangeduration.TrdExchangeDurDbInertDto;
import com.joyintech.tams.invest.common.dto.period.trdexchangeduration.TrdExchangeDurationDto;

/**
 * 外汇远期掉期到期交易自动生成reader相关业务处理
 *
 * @author renping
 * @version 1.0 2019年7月19日
 * @see ExchangeAutoDurDealGenerateReaderService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class ExchangeAutoDurDealGenerateReaderService extends BaseService {

    /**
     * 获取待处理数据
     *
     * @param param 传递参数
     * @return List<ExchangeAutoDurDealGenerateReaderResult>
     * @see
     * @since 1.0
     */
    public List<ExchangeAutoDurDealGenerateReaderResult> execute(Map<String, Object> param) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        SysBatchExecInfoDto dto = (SysBatchExecInfoDto)param.get(BatchConstant.BATCH_INFO);
        paramMap.put("mdateBegin", DateUtil.format(dto.getExecuteDate()));
        paramMap.put("mdateEnd", DateUtil.format(dto.getExecuteDate()));
        List<ExchangeAutoDurDealGenerateReaderResult> resultList = new ArrayList<ExchangeAutoDurDealGenerateReaderResult>();

        List<TrdExchangeDurDbInertDto> tradeDataList = restTemplate.postForList(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdExchangeDuration/findAllData", paramMap,
            TrdExchangeDurDbInertDto.class);

        if (!StringUtil.isEmptyList(tradeDataList)) {
            for (TrdExchangeDurDbInertDto trdExchangeDurDbInertDto : tradeDataList) {
                TrdExchangeDurationDto trdExchangeDurationDto = new TrdExchangeDurationDto();
                trdExchangeDurationDto.setTrdExchangeDurDbInertDto(trdExchangeDurDbInertDto);
                ExchangeAutoDurDealGenerateReaderResult resultDto = new ExchangeAutoDurDealGenerateReaderResult();
                resultDto.setTradeDataDto(trdExchangeDurationDto);
                resultList.add(resultDto);
            }
        }

        return resultList;
    }
}
