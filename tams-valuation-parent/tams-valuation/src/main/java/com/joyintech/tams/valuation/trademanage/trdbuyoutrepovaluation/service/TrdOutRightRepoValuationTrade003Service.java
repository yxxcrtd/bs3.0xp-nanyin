/*
 * 文件名：TrdLendInvestTrade002Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年10月10日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.valuation.trademanage.trdbuyoutrepovaluation.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.trdbuyoutrepovaluation.dto.TrdOutRightRepoValuationTrade003Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TrdOutRightRepoValuationTrade003Service extends BaseService<TrdOutRightRepoValuationTrade003Dto> implements BaseProcessService<TrdOutRightRepoValuationTrade003Dto> {




    @Override
    public void doComplete(TrdOutRightRepoValuationTrade003Dto trdOutRightRepoValuationTrade003Dto, OperationType operationType) {

    }

    /**
     * 产品代码下拉框
     * @auther: Administrator
     * @date: 2021/12/29 17:10
     * @param:
     * @return:
     */
    public Page<SelectOptionDto> findPortfolioId(Map<String, Object> params) {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdOutRightRepoValuationTrade002/findPortfolioId",
                params, SelectOptionDto.class);
    }

    /**
     * 资产代码下拉框
     * @auther: Administrator
     * @date: 2021/12/29 17:10
     * @param:
     * @return:
     */
    public Page<SelectOptionDto> findFinprodId(Map<String, Object> params) {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdOutRightRepoValuationTrade002/findFinprodId",
                params, SelectOptionDto.class);
    }


    /**
     * 根据交易编号查询详情  到期确认
     */
    public TrdOutRightRepoValuationTrade003Dto queryTradeInfoByTradeId01(String tradeId) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdOutRightRepoValuationTrade002/queryTradeInfoByTradeId01",
                tradeId, TrdOutRightRepoValuationTrade003Dto.class);
    }
}
