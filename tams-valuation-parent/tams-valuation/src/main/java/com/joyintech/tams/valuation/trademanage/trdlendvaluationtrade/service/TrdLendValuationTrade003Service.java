/*
 * 文件名：TrdLendInvestTrade002Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年10月10日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.valuation.trademanage.trdlendvaluationtrade.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.trdlendvaluationtrade.dto.TrdLendValuationTrade003Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

@Service
public class TrdLendValuationTrade003Service extends BaseService<TrdLendValuationTrade003Dto> implements BaseProcessService<TrdLendValuationTrade003Dto> {
    @Override
    public void doComplete(TrdLendValuationTrade003Dto trdLendValuationTradeDto, OperationType operationType) {

    }

    /**
     * 根据交易编号查询详情
     */
    public TrdLendValuationTrade003Dto queryTradeInfoByTradeId(String tradeId) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdLendValuationTrade/queryTradeInfoByTradeId",
                tradeId, TrdLendValuationTrade003Dto.class);
    }
}
