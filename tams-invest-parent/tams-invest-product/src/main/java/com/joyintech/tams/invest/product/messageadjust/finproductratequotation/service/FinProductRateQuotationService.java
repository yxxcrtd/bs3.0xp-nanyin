/*
 * 文件名：FinProductRateQuotationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finproductratequotation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.messageadjust.finproductratequotation.dto.FinProductRateQuotationDto;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 信息调整管理_产品费率报价管理Service
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月11日
 * @see FinProductRateQuotationService
 * @since 1.0
 */
@Service
public class FinProductRateQuotationService extends CommonExportService<FinProductRateQuotationDto> 
    implements BaseProcessService<FinProductRateQuotationDto>{
    
    /**
     * 产品代码联想下拉框
     * @param param Map<String,Object>
     * @return List<SelectOptionDto>
     */
    public List<SelectOptionDto> getFinprodIds(Map<String,Object> param) {
        User user = SecurityUtil.getLoginUser();
        param.put("userId", user.getUserName());
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
                + "/FinProductRateQuotation/getFinprodId",param,SelectOptionDto.class);      
    }
    
    /**
     * 
     * 产品费率报价管理-查询
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<FinProductRateQuotationDto>
     * @see
     * @since 1.0
     */
    public Page<FinProductRateQuotationDto> selectForPage(Map<String, Object> pageParam){
        User user = SecurityUtil.getLoginUser();
        pageParam.put("userId", user.getUserName());
        return restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductRateQuotation/selectForPage", pageParam,
                FinProductRateQuotationDto.class);
    }

    /**
     * 实现docomplement方法
     */
    @Override
    public void doComplete(FinProductRateQuotationDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductRateQuotation/doComplete",
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
        exportParam.config("/com/joyintech/tams/invest/product/messageadjust/finproductratequotation/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<FinProductRateQuotationDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    /**
     * 
     * 新建页面产品代码下拉框
     * <功能详细描述>
     * @param param Map<String,Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSelectFinprodId(Map<String,Object> param){
        if(!StringUtil.isNotEmpty(param.get("initValue"))){
            User user = SecurityUtil.getLoginUser();
            param.put("userId", user.getUserName()); 
        }
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
                + "/FinProductRateQuotation/getSelectFinprodId",param,SelectOptionDto.class); 
    }
    /**
     * 
     * 新建页面产品代码联动适用款型下拉框
     * <功能详细描述>
     * @param param Map<String,Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSelectProdStyleByFinprodId(Map<String,Object> param){
        if(!StringUtil.isNotEmpty(param.get("finprodMarketId"))){
            return Collections.emptyList();
        }
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
            + "/FinProductRateQuotation/getSelectProdStyleByFinprodId",param,SelectOptionDto.class); 
    }
    
    /**
     * 
     * 新建页面画面联动
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<FinProductRateQuotationDto>
     * @see
     * @since 1.0
     */
    public Page<FinProductRateQuotationDto> selectOther(Map<String, Object> pageParam){
        /*User user = SecurityUtil.getLoginUser();
        pageParam.put("userId", user.getUserName());*/
        String workflowId = (String)pageParam.get("workflowId");
        if(StringUtil.isNotEmpty(workflowId) && "false".equals(pageParam.get("flag"))){
            return restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductRateQuotation/selectOther", pageParam,
                FinProductRateQuotationDto.class);
        }
        if(StringUtil.isNotEmpty(workflowId)){
            FinProductRateQuotationDto dto = this.getBussinessEntityByWorkflowId(
                workflowId);
            Page<FinProductRateQuotationDto> page = new Page<>();
            List<FinProductRateQuotationDto> list = new ArrayList<FinProductRateQuotationDto>();
            if(dto.getSelectedItem() != null){
                list=dto.getSelectedItem();
            }
            else{
                list.add(dto); 
            }
            page.setRoot(list);
            page.setRecords(list.size());
            return page;
        }
        else if(pageParam.get("pageModel").equals("1") || pageParam.get("pageModel").equals("2")){
            FinProductRateQuotationDto dto = this.selectDetail((String)pageParam.get("cashId"), 
                (String)pageParam.get("finprodId"), (String)pageParam.get("effDate"));
            List<FinProductRateQuotationDto> list = new ArrayList<FinProductRateQuotationDto>();
            list.add(dto);
            Page<FinProductRateQuotationDto> page = new Page<>();
            page.setRoot(list);
            page.setRecords(list.size());
            return page;
        }
        else{
            return restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductRateQuotation/selectOther", pageParam,
                FinProductRateQuotationDto.class);
        }
    }
    
    /**
     * 
     * 双击查看、修改取得数据源
     * <功能详细描述>
     * @param cashId String
     * @param finprodId String
     * @param effDate Date
     * @return FinProductRateQuotationDto
     * @see
     * @since 1.0
     */
    public FinProductRateQuotationDto selectDetail(String cashId, String finprodId ,String effDate){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("cashId", cashId);
        param.put("finprodId", finprodId);
        param.put("effDate", effDate);
        
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductRateQuotation/selectDetail", param,
            FinProductRateQuotationDto.class);
    }
    
    /**
     * 
     * 新建check
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return FinProductRateQuotationDto
     * @see
     * @since 1.0
     */
    public List<FinProductRateQuotationDto> selectAlreadyHava(Map<String, Object> param){
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductRateQuotation/selectAlreadyHava", param,
            FinProductRateQuotationDto.class);
    }
    
    /**
     * 
     * 根据金融产品代码拿到起息日、到期日
     * <功能详细描述>
     * @param finprodId String
     * @return FinProductRateQuotationDto
     * @see
     * @since 1.0
     */
    public FinProductRateQuotationDto getVdateAndMdate(String finprodId){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", finprodId);
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductRateQuotation/getVdateAndMdate", param,
            FinProductRateQuotationDto.class);
    }
}
