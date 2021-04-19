/*
 * 文件名：ComRateSwapValuationInfo002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.common.basicdata.marketdata.rateswapvaluationinfo.service;

import java.util.Map;

import com.joyintech.tams.common.basicdata.constant.ServiceApplication;
import com.joyintech.tams.common.basicdata.marketdata.rateswapvaluationinfo.dto.ComRateSwapValuationInfo002Dto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 利率互换估值信息
 * 
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see ComRateSwapValuationInfo002Service
 * @since 1.0
 */
@Service("comRateSwapValuationInfo002Service")
@Transactional(rollbackFor = Exception.class)
public class ComRateSwapValuationInfo002Service extends CommonExportService<ComRateSwapValuationInfo002Dto> implements BaseProcessService<ComRateSwapValuationInfo002Dto> {

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(ComRateSwapValuationInfo002Dto entity, OperationType type) {
        restTemplate.postForObject(ServiceApplication.CommonBasicDataService
                                   + "/ComRateSwapValuationInfo002/doComplete/" + type.name(),
            entity, AjaxResult.class);
    }

    /**
     * 查询 利率互换估值信息
     * 
     * @param map
     * @return
     */
    public ComRateSwapValuationInfo002Dto findByPrimaryKey(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.CommonBasicDataService
                                          + "/ComRateSwapValuationInfo002/findByPrimaryKey",
            map, ComRateSwapValuationInfo002Dto.class);
    }

}
