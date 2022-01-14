/*
 * 文件名：TrdLendInvestTrade002Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年10月10日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.valuation.trademanage.trdbuyoutrepovaluation.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.trdbuyoutrepovaluation.dto.TrdOutRightRepoValuationTrade001Dto;
import com.joyintech.tams.valuation.trademanage.trdlendvaluationtrade.dto.TrdLendValuationTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TrdOutRightRepoValuationTrade002Service extends BaseService<TrdOutRightRepoValuationTrade001Dto> implements BaseProcessService<TrdOutRightRepoValuationTrade001Dto> {




    @Override
    public void doComplete(TrdOutRightRepoValuationTrade001Dto trdLendValuationTradeDto, OperationType operationType) {

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
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdOutRightRepoValuationTrade001/findPortfolioId",
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
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdOutRightRepoValuationTrade001/findFinprodId",
                params, SelectOptionDto.class);
    }


    /**
     * 根据交易编号查询详情
     */
    public TrdOutRightRepoValuationTrade001Dto queryTradeInfoByTradeId(String tradeId) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdOutRightRepoValuationTrade001/queryTradeInfoByTradeId",
                tradeId, TrdOutRightRepoValuationTrade001Dto.class);
    }
}
