/*
 * 文件名：TrdLendInvestTrade002Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年10月10日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.valuation.trademanage.trdintovaluationduration.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.trdintovaluationduration.dto.TrdIntoValuationDuration001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;


@Service
public class TrdIntoValuationDuration002Service extends BaseService<TrdIntoValuationDuration001Dto> implements BaseProcessService<TrdIntoValuationDuration001Dto> {
    @Override
    public void doComplete(TrdIntoValuationDuration001Dto trdDepoValuationDuration001Dto, OperationType operationType) {

    }

    /**
     * 根据交易编号查询详情
     */
    public TrdIntoValuationDuration001Dto queryTradeInfoByTradeId(String tradeId) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdIntoValuationDuration001/queryTradeInfoByTradeId",
                tradeId, TrdIntoValuationDuration001Dto.class);
    }


}
