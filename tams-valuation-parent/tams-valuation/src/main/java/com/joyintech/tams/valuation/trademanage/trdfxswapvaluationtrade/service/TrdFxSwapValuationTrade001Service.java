/*
 * 文件名：TrdFixIncomeRateInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.trademanage.trdfxswapvaluationtrade.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.trdfxswapvaluationtrade.dto.TrdFxSwapValuationTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description:固收类交易service
 * @auther: yuzj
 * @date: 2022/1/5
 * @param:
 * @return:
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class TrdFxSwapValuationTrade001Service extends CommonExportService<TrdFxSwapValuationTradeDto> implements BaseProcessService<TrdFxSwapValuationTradeDto> {
    /**
     * 查询
     * @param pageParam Map
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<TrdFxSwapValuationTradeDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdFxSwapValuationTrade/selectForPage",
                pageParam, TrdFxSwapValuationTradeDto.class);
    }

    /**
     * 工作流（留痕处理进行的操作）
     */
    @Override
    public void doComplete(TrdFxSwapValuationTradeDto entity, OperationType type) {
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/TrdFxSwapValuationTrade/doComplete/" + type, entity, Integer.class);
    }


    /**
     * 根据条件查询所有
     *
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    public List<TrdFxSwapValuationTradeDto> findAll(Map<String, Object> params) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/TrdFxSwapValuationTrade/findAll",
                params, TrdFxSwapValuationTradeDto.class);

    }


}
