/*
 * 文件名：TradeAccount001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年8月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.investtradequery.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.TradeAccountDto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author Administrator
 * @version 1.0 2018年8月13日
 * @see TradeAccount001Service
 * @since 1.0
 */
@Service
public class TradeAccount001Service extends CommonExportService<TradeAccountDto> {

    /**
     * 交易账户收划款双击查看
     * 
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    public TradeAccountDto queryTradeAccount(String tradeId,String prodId) {
        Map<String, Object> params = new HashMap<>();
        params.put("tradeId", tradeId);
        params.put("prodId", prodId);
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/TradeAccount001/queryTradeAccount",
            params, TradeAccountDto.class);
    }
}
