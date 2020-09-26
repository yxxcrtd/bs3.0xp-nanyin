/*
 * 文件名：TaxScheme003Service.java
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
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme003Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 增值税则表
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxScheme003Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TaxScheme003Service extends CommonExportService<TaxScheme003Dto> implements BaseProcessService<TaxScheme003Dto> {
	
    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(TaxScheme003Dto entity, OperationType type) {
        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme003/doComplete/" + type.name(),
            entity, AjaxResult.class); 
    }
	
	
	/**
	 * 查询 增值税则表
	 * @param seqNo
	 * @return
	 */
	public TaxScheme003Dto findByPrimaryKey(String seqNo) {
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme003/findByPrimaryKey?seqNo="+seqNo, 
            TaxScheme003Dto.class);
    }
	
	/**
     * 增值税则表check(去除本金收益特征不能为空的校验后添加本方法)
     * @return
     * @throws Exception
     */
	public List<TaxScheme003Dto> checkVatTax(Map<String, Object> parameters) throws Exception {
	    return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme003/checkVatTax",
            parameters, TaxScheme003Dto.class);
	}
	
}
