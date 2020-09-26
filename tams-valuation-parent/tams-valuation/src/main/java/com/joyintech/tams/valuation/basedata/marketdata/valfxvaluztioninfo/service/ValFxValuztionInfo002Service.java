/*
 * 文件名：ValFxValuztionInfo002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.dto.ValFxValuztionInfo002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 外汇估值信息
 * 
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see valRateSwapValuationInfo002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValFxValuztionInfo002Service extends CommonExportService<ValFxValuztionInfo002Dto> implements BaseProcessService<ValFxValuztionInfo002Dto> {

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(ValFxValuztionInfo002Dto entity, OperationType type) {
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                                   + "/ValFxValuztionInfo002/doComplete/" + type.name(),
            entity, AjaxResult.class);
    }

    /**
     * 查询 外汇估值信息
     * 
     * @param configId
     * @return
     */
    public ValFxValuztionInfo002Dto findByPrimaryKey(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                                          + "/ValFxValuztionInfo002/findByPrimaryKey",
            map, ValFxValuztionInfo002Dto.class);
    }


	/**
     * 交易编号获取
     *
     * @return 返回的数据
     */
	public List<SuggestDto> getFinprodId2(Map<String, Object> params) {

        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValFxValuztionInfo002/getFinprodId2",
                params, SuggestDto.class);
	}

}
