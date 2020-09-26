/*
 * 文件名：TaxScheme005Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.taxparametermanage.taxscheme.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme005Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 资产应税信息
 * 
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxScheme005Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TaxScheme005Service extends CommonExportService<TaxScheme005Dto> implements BaseProcessService<TaxScheme005Dto> {

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(TaxScheme005Dto entity, OperationType type) {
        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme005/doComplete/" + type.name(), entity,
            AjaxResult.class);
    }

    /**
     * 查询 资产应税信息
     * 
     * @param seqNo
     * @return
     */
    public TaxScheme005Dto findByPrimaryKey(String seqNo) {
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme005/findByPrimaryKey?seqNo=" + seqNo,
            TaxScheme005Dto.class);
    }

    /**
     * 获取资产代码 - 下拉框
     * 
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> findAsset(Map<String, Object> pageParam)
        throws Exception {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme005/findAsset",
            pageParam, SelectOptionDto.class);
    }

    /**
     * 通过资产代码，获取资产信息
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public TaxScheme005Dto getAssetInfo(Map<String, Object> pageParam) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme005/getAssetInfo",
            pageParam, TaxScheme005Dto.class);
    }

}
