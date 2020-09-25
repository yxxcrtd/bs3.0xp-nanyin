/*
 * 文件名：FinProductOpenPlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finproductopenplanadjust.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.messageadjust.finproductopenplanadjust.dto.FinProductOpenPlanAdjustDto;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 信息调整管理_开放计划查询
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月17日
 * @see FinProductOpenPlanAdjustService
 * @since 1.0
 */
@Service
public class FinProductOpenPlanAdjustService extends CommonExportService<FinProductOpenPlanAdjustDto> 
    implements BaseProcessService<FinProductOpenPlanAdjustDto>{
    
    /**
     * 
     * 产品代码联想下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see 
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodIds(Map<String, Object> param) {
        User user = SecurityUtil.getLoginUser();
        param.put("userId", user.getUserName());
        param.put("openPlanType03", DictCodeList.申赎开放类型_申赎开放.getValue());
        param.put("openPlanType01", DictCodeList.申赎开放类型_申购开放.getValue());
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
            + "/FinProductOpenPlanAdjust/getFinprodId",param,SelectOptionDto.class);
    }
    
    /**
     * 
     * 开放计划查询
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<FinProductOpenPlanAdjustDto>
     * @see
     * @since 1.0
     */
    public Page<FinProductOpenPlanAdjustDto> selectForPage(Map<String, Object> pageParam){
        User user = SecurityUtil.getLoginUser();
        pageParam.put("userId", user.getUserName());
        //得到产品代码
        String finprodId = (String)pageParam.get("finprodId");
//        String scheduleType = (String)pageParam.get("scheduleType");
        if(StringUtil.isNotEmpty(finprodId)){
            pageParam.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
        }
//        if(StringUtil.isNotEmpty(scheduleType)){
//            pageParam.put("scheduleTypeList", StringUtil.splitToList(GlobalConstant.COMMA,scheduleType));
//        }
        pageParam.put("openPlanType03", "03");
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductOpenPlanAdjust/selectForPage", pageParam,
            FinProductOpenPlanAdjustDto.class); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinProductOpenPlanAdjustDto entity, OperationType type) {
        // TODO Auto-generated method stub
        
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
        exportParam.config("/com/joyintech/tams/invest/product/messageadjust/finproductopenplanadjust/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<FinProductOpenPlanAdjustDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals("openDateStr", propName)||StringUtil.equals("openDateEnd", propName)
            ||StringUtil.equals("openVdate", propName)||StringUtil.equals("openMdate", propName)
            ||StringUtil.equals("openNdate", propName)) {
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }

}
