/*
 * 文件名：ComFxValuztionInfo002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.common.basicdata.marketdata.valfxvaluztioninfo.service;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.constant.ServiceApplication;
import com.joyintech.tams.common.basicdata.marketdata.valfxvaluztioninfo.dto.ComFxValuztionInfo002Dto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 外汇估值信息
 * 
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see ComFxValuztionInfo002Service
 * @since 1.0
 */
@Service("comFxValuztionInfo002Service")
@Transactional(rollbackFor = Exception.class)
public class ComFxValuztionInfo002Service extends CommonExportService<ComFxValuztionInfo002Dto> implements BaseProcessService<ComFxValuztionInfo002Dto> {

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(ComFxValuztionInfo002Dto entity, OperationType type) {
        restTemplate.postForObject(ServiceApplication.CommonBasicDataService
                                   + "/ComFxValuztionInfo002/doComplete/" + type.name(),
            entity, AjaxResult.class);
    }

    /**
     * 查询 外汇估值信息
     * 
     * @param map
     * @return
     */
    public ComFxValuztionInfo002Dto findByPrimaryKey(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.CommonBasicDataService
                                          + "/ComFxValuztionInfo002/findByPrimaryKey",
            map, ComFxValuztionInfo002Dto.class);
    }


	/**
     * 交易编号获取
     *
     * @return 返回的数据
     */
	public List<SuggestDto> getFinprodId2(Map<String, Object> params) {

        return restTemplate.postForList(ServiceApplication.CommonBasicDataService + "/ComFxValuztionInfo002/getFinprodId2",
                params, SuggestDto.class);
	}

}
