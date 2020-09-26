/*
 * 文件名：TaxScheme007Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.taxparametermanage.taxscheme.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme007Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 会计场景计税配置
 * 
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxScheme007Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TaxScheme007Service extends CommonExportService<TaxScheme007Dto> implements BaseProcessService<TaxScheme007Dto> {

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(TaxScheme007Dto entity, OperationType type) {
        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme007/doComplete/" + type.name(), entity,
            AjaxResult.class);
    }

    /**
     * 查询 会计场景计税配置 - 关联
     * 
     * @param configId
     * @return
     */
    public TaxScheme007Dto findByPrimaryKey(String seqNo) {
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme007/findByPrimaryKey?seqNo=" + seqNo,
            TaxScheme007Dto.class);
    }

    /**
     * 查询 会计场景计税配置
     *
     * @param configId
     * @return
     */
    public TaxScheme007Dto getConfigInfoByKey(String configId) {
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme007/getConfigInfoByKey?configId=" + configId,
            TaxScheme007Dto.class);
    }

    /**
     * 获取基础科目性质 - 分页
     * @param pageParam
     * @return
     */
    public Page<SelectOptionDto> getBaseSubjectNature(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme007/getBaseSubjectNature",
            pageParam, SelectOptionDto.class);
    }

    /**
     * 获取基础科目性质 - 分页
     * @param pageParam
     * @return
     */
    public List<SelectOptionDto> getBaseSubjectNatureAll(Map<String, Object> pageParam) {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme007/getBaseSubjectNature",
            pageParam, SelectOptionDto.class);
    }

    /**
     * 下拉框 - 会计场景计税配置
     * @param pageParam
     * @return
     */
    public Page<SelectOptionDto> getConfigInfo(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme007/getConfigInfo",
            pageParam, SelectOptionDto.class);
    }
}
