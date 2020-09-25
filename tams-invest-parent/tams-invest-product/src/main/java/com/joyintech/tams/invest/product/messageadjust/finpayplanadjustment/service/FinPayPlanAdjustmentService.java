/*
 * 文件名：FinPayPlanAdjustmentService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.dto.FinPayPlanAdjustmentDto;
import com.joyintech.tams.invest.product.messageadjust.finproductratequotation.dto.FinProductRateQuotationDto;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 信息调整管理_付费计划调整  
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月18日
 * @see FinPayPlanAdjustmentService
 * @since 1.0
 */
@Service
public class FinPayPlanAdjustmentService extends CommonExportService<FinPayPlanAdjustmentDto> implements BaseProcessService<FinPayPlanAdjustmentDto>{
    
    /**
     * 
     * 产品代码联想下拉框
     * <功能详细描述>
     * @param param Map<String,Object>
     * @return List<SelectOptionDto>
     * @see 
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodIds(Map<String,Object> param) {
        if(!StringUtil.isNotEmpty(param.get("initValue"))){
            User user = SecurityUtil.getLoginUser();
            param.put("userId", user.getUserName());
        }
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
            + "/FinPayPlanAdjustment/getFinprodId",param,SelectOptionDto.class);
    }
    
    /**
     * 
     * 产品款型联想下拉框
     * <功能详细描述>
     * @param param Map<String,Object>
     * @return List<SelectOptionDto>
     * @see 
     * @since 1.0
     */
    public List<SelectOptionDto> getProdType(Map<String,Object> param) {
        if(StringUtil.isNotEmpty(param.get("finprodMarketId"))){
            param.put("finprodId",DictCodeListExt1.金融产品类型_理财产品.getValue() + "_" + param.get("finprodMarketId"));
        } else {
            return Collections.emptyList();
        }
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
            + "/FinPayPlanAdjustment/getProdType",param,SelectOptionDto.class);
    }
    
    /**
     * 
     * 根据产品代码获得费用名称
     * <功能详细描述>
     * @param param Map<String,Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFeeName(Map<String,Object> param){
    	// BS-1155 产品代码未选时，费用名称不显示数据 add by lizhi 20200712 start
    	if(!StringUtil.isNotEmpty(param.get("finprodMarketId"))){
    		return null;//new ArrayList<SelectOptionDto>();
    	}
    	// BS-1155 产品代码未选时，费用名称不显示数据 add by lizhi 20200712 end
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
            + "/FinPayPlanAdjustment/getFeeName",param,SelectOptionDto.class);
    }
    
    /**
     * 
     * 根据产品代码和费用名称，联动带出产品对应费用的付费计划
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<FinPayPlanAdjustmentDto>
     * @see
     * @since 1.0
     */
    public List<FinPayPlanAdjustmentDto> selectForPage(Map<String, Object> pageParam){
        String workflowId = (String)pageParam.get("workflowId");
        if(StringUtil.isNotEmpty(workflowId) && "false".equals(pageParam.get("flag"))){
            return restTemplate.postForList(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinPayPlanAdjustment/selectForPage", pageParam,
                FinPayPlanAdjustmentDto.class);
        }
        if(StringUtil.isNotEmpty(workflowId)){
            FinPayPlanAdjustmentDto finPayPlanAdjustmentDto = this.getBussinessEntityByWorkflowId(
                workflowId);
            /*Page<FinPayPlanAdjustmentDto> page = new Page<>();
            page.setRoot(finPayPlanAdjustmentDto.getSelectedItem1());
            page.setRecords(finPayPlanAdjustmentDto.getSelectedItem1().size());*/
            return finPayPlanAdjustmentDto.getSelectedItem1();
        }
        else{
            return restTemplate.postForList(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinPayPlanAdjustment/selectForPage", pageParam,
                FinPayPlanAdjustmentDto.class); 
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinPayPlanAdjustmentDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinPayPlanAdjustment/doComplete",
            entity, Integer.class);
        
    }
    
    /**
     * 导出
     * 
     * @param exportParam 导出信息
     * @see
     * @since 1.0
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/product/messageadjust/finpayplanadjustment/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<FinPayPlanAdjustmentDto> selectPageData(Map<String, Object> selectParam) {
        Page<FinPayPlanAdjustmentDto> page = new Page<>();
        page.setRoot(selectForPage(selectParam));
        page.setRecords(selectForPage(selectParam).size());
        return page;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals("vdate", propName)||StringUtil.equals("mdate", propName)
            ||StringUtil.equals("payDate", propName)) {
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }
    
    /**
     * 
     * 获取到期日
     * <功能详细描述>
     * @param param Map<String, Object> 
     * @return FinPayPlanAdjustmentDto
     * @see
     * @since 1.0
     */
    public FinPayPlanAdjustmentDto getMdate(Map<String, Object> param){
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinPayPlanAdjustment/getMdate", param,
            FinPayPlanAdjustmentDto.class); 
    }
    
    /**
     * 
     * 获取所有的付费计划
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return Date
     * @see
     * @since 1.0
     */
    public Date selectForList(Map<String, Object> param){
        List<FinPayPlanAdjustmentDto> list = restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinPayPlanAdjustment/selectForList", param,
            FinPayPlanAdjustmentDto.class);
        Date vdate = new Date();
        for(FinPayPlanAdjustmentDto finPayPlanAdjustmentDto:list){
            if("未支付".equals(finPayPlanAdjustmentDto.getPayStatus())){
                vdate = finPayPlanAdjustmentDto.getVdate();
                break;
            }
        }
        return vdate;
    }
}
