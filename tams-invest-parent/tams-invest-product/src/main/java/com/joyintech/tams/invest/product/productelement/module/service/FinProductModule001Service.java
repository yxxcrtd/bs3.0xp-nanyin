/*
 * 文件名：FinProductModule001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productelement.module.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.module.dto.FinProductModuleInfoDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 产品系列模板查询Service
 * 
 * @author 何阳阳
 * @version 1.0 2018年8月31日
 * @see FinProductModuleService
 * @since 1.0
 */
@Service
public class FinProductModule001Service extends CommonExportService<FinProductModuleInfoDto>  
    implements BaseProcessService<FinProductModuleInfoDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinProductModuleInfoDto entity, OperationType type) {
        // TODO Auto-generated method stub
        
    }
    /**
     * 模板名称的联想下拉框
     * <功能详细描述>
     * @param param param
     * @return SelectOptionDto
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getTempletNameList(Map<String, Object> param) {
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductModule/getTempletNameList",param 
            ,SelectOptionDto.class);
    }

    /**
     * 画面查询
     * <功能详细描述>
     * @param parameters parameters
     * @return FinProductModuleInfoDto
     * @see
     * @since 1.0
     */
    public Page<FinProductModuleInfoDto> selectForPage(Map<String, Object> parameters) {
    	if (StringUtil.isNotEmpty(parameters.get("templetName"))) {
            parameters.put("templateId", StringUtil.splitToList(null, parameters.get("templetName").toString()));
        }
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductModule/selectForPage",
            parameters,FinProductModuleInfoDto.class);
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/product/productelement/module/conf/export_conf.xml");
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinProductModuleInfoDto> selectPageData(Map<String, Object> selectParam) {
        try {
            return selectForPage(selectParam);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
