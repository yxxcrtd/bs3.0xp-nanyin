/*
 * 文件名：TrdPlanRateInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.trademanage.trdplannetvaluationtrade.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.trdplannetvaluationtrade.dto.TrdPlanNetValuationTradeDto;
import com.joyintech.tams.valuation.trademanage.trdplanratevaluationtrade.dto.TrdPlanRateValuationTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 计划类资产投资交易查询
 * @author fanshuanghu
 * @version 1.0 2018年11月22日
 * @see TrdPlanNetValuationTrade001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class TrdPlanNetValuationTrade001Service extends CommonExportService<TrdPlanNetValuationTradeDto>  implements BaseProcessService<TrdPlanNetValuationTradeDto> {
    /**
     * 查询
     * @param pageParam Map
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<TrdPlanNetValuationTradeDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdPlanNetValuationTrade/selectForPage",
                pageParam,TrdPlanNetValuationTradeDto.class);
    }

    /**
     * 工作流（留痕处理进行的操作）
     */
    @Override
    public void doComplete(TrdPlanNetValuationTradeDto entity, OperationType type) {
        simpleRestTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/TrdPlanNetValuationTrade/doComplete/" + type, entity, Integer.class);
    }


}
