/*
 * 文件名：TaxScheme002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.taxparametermanage.taxscheme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dao.TaxScheme002Mapper;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 税务方案
 * 
 * @author zhangwei
 * @version 1.0 2018年10月11日
 * @see TaxScheme002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TaxScheme002Service extends CommonExportService<TaxScheme002Dto> implements BaseProcessService<TaxScheme002Dto> {

    @Autowired
    TaxScheme002Mapper taxScheme002Mapper;

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(TaxScheme002Dto entity, OperationType type) {
        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme002/doComplete/" + type.name(),
            entity, AjaxResult.class); 
    }

    /**
     * 查询
     * 
     * @param dto
     */
    public TaxScheme002Dto findBySchId(String schId) {
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme002/findBySchId?schId="+schId, 
            TaxScheme002Dto.class);
    }

    /**
     * 通过 查询账套关联表 中 的税务方案;
     * 
     * @param schName
     * @return
     */
    public int countBokBookSetPlan(String schId) {
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme002/countBokBookSetPlan?schId="+schId,
            Integer.class);
    }


}
