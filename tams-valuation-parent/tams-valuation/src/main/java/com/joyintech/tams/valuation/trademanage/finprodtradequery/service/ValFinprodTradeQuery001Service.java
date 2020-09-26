/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.finprodtradequery.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.finprodtradequery.dao.ValFinprodTradeQuery001Mapper;
import com.joyintech.tams.valuation.trademanage.finprodtradequery.dto.ValFinprodTradeQuery001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 金融产品交易查询Service
 * 
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service
public class ValFinprodTradeQuery001Service extends CommonExportService<ValFinprodTradeQuery001Dto> implements BaseProcessService<ValFinprodTradeQuery001Dto> {

    /**
     * ValFinprodTradeQuery001Mapper
     */
    @Autowired
    private ValFinprodTradeQuery001Mapper mapper;

    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception
     */
    public Page<ValFinprodTradeQuery001Dto> findPage(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ValFinprodTradeQuery001/findPage",
            params, ValFinprodTradeQuery001Dto.class);
    }

    /**
     * 不分页
     * 
     * @param params
     * @return
     */
    public List<ValFinprodTradeQuery001Dto> queryPageList(Map<String, Object> params) {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValFinprodTradeQuery001/queryPageList", params,
            ValFinprodTradeQuery001Dto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ValFinprodTradeQuery001Dto entity, OperationType type) {
        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValFinprodTradeQuery001/doComplete/" + type.name(), entity,
            AjaxResult.class);

    }

    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<String> getBusinessDataInFlow(ProcessMark processMark) {
        return mapper.getBusinessDataInFlow(processMark);
    }
}
