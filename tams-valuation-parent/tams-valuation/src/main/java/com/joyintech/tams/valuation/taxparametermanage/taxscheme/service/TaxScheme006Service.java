/*
 * 文件名：TaxScheme006Service.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.dao.BokSchemeConfigMapper;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dao.TaxScheme006Mapper;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme006Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 附加税则表
 * 
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxScheme006Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TaxScheme006Service extends CommonExportService<TaxScheme006Dto> implements BaseProcessService<TaxScheme006Dto> {

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(TaxScheme006Dto entity, OperationType type) {
        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme006/doComplete/" + type.name(), entity,
            AjaxResult.class);
    }

    /**
     * 查询 附加税则表
     * 
     * @param seqNo
     * @return
     */
    public TaxScheme006Dto findByPrimaryKey(String seqNo) {
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme006/findByPrimaryKey?seqNo=" + seqNo,
            TaxScheme006Dto.class);
    }

    /**
     * 下拉框 - 项目
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findProject(Map<String, Object> pageParam) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme006/findProject",
            pageParam, SelectOptionDto.class);

    }

    /**
     * 下拉框 - 值
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public String findValue(Map<String, Object> pageParam) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme006/findValue",
            pageParam, String.class);

    }

}
