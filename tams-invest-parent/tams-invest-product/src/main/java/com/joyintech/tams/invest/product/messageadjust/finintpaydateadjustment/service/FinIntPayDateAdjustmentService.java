/*
 * 文件名：FinIntPayDateAdjustmentService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finintpaydateadjustment.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.messageadjust.finintpaydateadjustment.dto.FinIntPayDateAdjustmentDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 信息调整管理_付息日调整 
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月23日
 * @see FinIntPayDateAdjustmentService
 * @since 1.0
 */
@Service
public class FinIntPayDateAdjustmentService extends CommonExportService<FinIntPayDateAdjustmentDto> 
    implements BaseProcessService<FinIntPayDateAdjustmentDto>{
    
    /**
     * 
     * 产品代码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCashId(Map<String, Object> param){
        if(!StringUtil.isNotEmpty(param.get("initValue"))){
            User user = SecurityUtil.getLoginUser();
            param.put("userId", user.getUserName());
        }
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
            + "/FinIntPayDateAdjustment/getCashId",param,SelectOptionDto.class);
    }
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<FinIntPayDateAdjustmentDto>
     * @see
     * @since 1.0
     */
    public List<FinIntPayDateAdjustmentDto> selectForPage(Map<String, Object> pageParam){
        String workflowId = (String)pageParam.get("workflowId");
        if(StringUtil.isNotEmpty(workflowId) && "false".equals(pageParam.get("flag"))){
            return restTemplate.postForList(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinIntPayDateAdjustment/selectForPage", pageParam,
                FinIntPayDateAdjustmentDto.class); 
        }
        if(StringUtil.isNotEmpty(workflowId)){
            FinIntPayDateAdjustmentDto finIntPayDateAdjustmentDto = this.getBussinessEntityByWorkflowId(
                workflowId);
            /*Page<FinIntPayDateAdjustmentDto> page = new Page<>();
            page.setRoot(finIntPayDateAdjustmentDto.getSelectedItem1());
            page.setRecords(finIntPayDateAdjustmentDto.getSelectedItem1().size());*/
            //add by yejiejie 2019-4-2 start
            //if(StringUtil.equals(finIntPayDateAdjustmentDto.getOperateType(), "save")){
	            Map<String,Object> param = new HashMap<String,Object>();
	            param.put("cashId", finIntPayDateAdjustmentDto.getCashId());
	            List<FinIntPayDateAdjustmentDto> list = restTemplate.postForList(
	                    ServiceApplication.PRODUCT_SERVICE_NAME + "/FinIntPayDateAdjustment/selectForPage", param,
	                    FinIntPayDateAdjustmentDto.class); 
	            if(finIntPayDateAdjustmentDto != null &&  !StringUtil.isEmptyList(list)
	            		&& !StringUtil.isEmptyList(finIntPayDateAdjustmentDto.getSelectedItem1())){
		            for(int i=0;i<finIntPayDateAdjustmentDto.getSelectedItem1().size();i++){
		            	if(DateUtil.compareDate(finIntPayDateAdjustmentDto.getSelectedItem1().get(i).getVdate(), list.get(i).getVdate()) == 0
		            			&& DateUtil.compareDate(finIntPayDateAdjustmentDto.getSelectedItem1().get(i).getMdate(), list.get(i).getMdate()) == 0){
		            		//起息日，到期日都相等，则没有改
		            		//不做改变
		            	}else{
		            		finIntPayDateAdjustmentDto.getSelectedItem1().get(i).setPayStatus("");
		            	}
		            }
	            }
            //}
            //add by yejiejie 2019-4-2 end
            return finIntPayDateAdjustmentDto.getSelectedItem1();
        }
        else{
            return restTemplate.postForList(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinIntPayDateAdjustment/selectForPage", pageParam,
                FinIntPayDateAdjustmentDto.class); 
        }
    }
    
    /**
     * 
     * 获取付息日计划未付息最小日期
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Date
     * @see
     * @since 1.0
     */
    public Date selectForList(Map<String, Object> pageParam){
        List<FinIntPayDateAdjustmentDto> list = restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinIntPayDateAdjustment/selectForList", pageParam,
            FinIntPayDateAdjustmentDto.class);
        Date vdate = new Date();
        for(FinIntPayDateAdjustmentDto dto:list){
            if("未付息".equals(dto.getPayStatus())){
                vdate = dto.getVdate();
                break;
            }
        }
        return vdate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinIntPayDateAdjustmentDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinIntPayDateAdjustment/doComplete",
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
        exportParam.config("/com/joyintech/tams/invest/product/messageadjust/finintpaydateadjustment/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<FinIntPayDateAdjustmentDto> selectPageData(Map<String, Object> selectParam) {
        Page<FinIntPayDateAdjustmentDto> page = new Page<>();
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
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId String
     * @return FinIntPayDateAdjustmentDto
     * @see
     * @since 1.0
     */
    public FinProduct getVdateMdate(String finprodId){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", finprodId);
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
            + "/FinIntPayDateAdjustment/getVdateMdate",param,FinProduct.class);
    }

	public String queryProductIsSus(String finprodId) {
		Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", finprodId);
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
            + "/FinIntPayDateAdjustment/queryProductIsSus",param,String.class);
	}

}
