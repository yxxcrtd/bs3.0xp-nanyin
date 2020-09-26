/*
 * 文件名：FundCurrency001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.trademanage.investtradequery.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.FundCurrencyDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.InvestTradeDto;

@Service
@Transactional(rollbackFor = Exception.class)
public class FundCurrency001Service extends CommonExportService<InvestTradeDto>{
    /**
     * 
     * 双击查看基金类资产
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    public FundCurrencyDto queryFundCurrencyList(Map<String, Object> params){
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/FundCurrency001/queryFundCurrencyList",
            params, FundCurrencyDto.class);
    }

}
