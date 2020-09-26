/*
 * 文件名：ValRateSwapValuationInfo002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.rateswapvaluationinfo.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.basedata.marketdata.rateswapvaluationinfo.dto.ValRateSwapValuationInfo002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 利率互换估值信息
 * 
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see valRateSwapValuationInfo002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValRateSwapValuationInfo002Service extends CommonExportService<ValRateSwapValuationInfo002Dto> implements BaseProcessService<ValRateSwapValuationInfo002Dto> {

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(ValRateSwapValuationInfo002Dto entity, OperationType type) {
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                                   + "/ValRateSwapValuationInfo002/doComplete/" + type.name(),
            entity, AjaxResult.class);
    }

    /**
     * 查询 利率互换估值信息
     * 
     * @param configId
     * @return
     */
    public ValRateSwapValuationInfo002Dto findByPrimaryKey(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                                          + "/ValRateSwapValuationInfo002/findByPrimaryKey",
            map, ValRateSwapValuationInfo002Dto.class);
    }

}
