/*
 * 文件名：TaxScheme004Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.taxparametermanage.taxscheme.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme004Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 附加税则表
 * 
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxScheme004Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TaxScheme004Service extends CommonExportService<TaxScheme004Dto> implements BaseProcessService<TaxScheme004Dto> {

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(TaxScheme004Dto entity, OperationType type) {
        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme004/doComplete/" + type.name(), entity,
            AjaxResult.class);
    }

    /**
     * 查询 附加税则表
     * 
     * @param seqNo
     * @return
     */
    public TaxScheme004Dto findByPrimaryKey(String seqNo) {
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme004/findByPrimaryKey?seqNo=" + seqNo,
            TaxScheme004Dto.class);
    }
}
