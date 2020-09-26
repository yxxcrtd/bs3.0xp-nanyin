/*
 * 文件名：TaxSchBokCfgManage002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.taxparametermanage.taxschbokcfgmanage.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.taxparametermanage.taxschbokcfgmanage.dto.TaxSchBokCfgManage002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 会计场景计税配置
 * 
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxSchBokCfgManage002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TaxSchBokCfgManage002Service extends CommonExportService<TaxSchBokCfgManage002Dto> implements BaseProcessService<TaxSchBokCfgManage002Dto> {

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(TaxSchBokCfgManage002Dto entity, OperationType type) {
        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxSchBokCfgManage002/doComplete/" + type.name(), entity,
            AjaxResult.class);
    }

    /**
     * 查询 会计场景计税配置
     * 
     * @param configId
     * @return
     */
    public TaxSchBokCfgManage002Dto findByPrimaryKey(String configId) {
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxSchBokCfgManage002/findByPrimaryKey?configId=" + configId,
            TaxSchBokCfgManage002Dto.class);
    }

    /**
     * 获取基础科目性质 - 分页
     * @param pageParam
     * @return
     */
    public Page<SelectOptionDto> getBaseSubjectNature(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxSchBokCfgManage002/getBaseSubjectNature",
            pageParam, SelectOptionDto.class);
    }

}
