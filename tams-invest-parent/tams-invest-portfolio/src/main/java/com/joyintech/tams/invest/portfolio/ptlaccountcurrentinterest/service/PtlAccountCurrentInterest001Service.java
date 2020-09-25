/*
 * 文件名：PtlAccountCurrentInterest001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.service;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.dto.PtlAccountCurrentInterest001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 账户活期利率
 * @author fanshuanghu
 * @version 1.0 2018年10月11日
 * @see PtlAccountCurrentInterest001Service
 * @since 1.0
 */
@Service
public class PtlAccountCurrentInterest001Service extends CommonExportService<PtlAccountCurrentInterest001Dto> 
            implements BaseProcessService<PtlAccountCurrentInterest001Dto>{
    
    @Autowired
    private IPlatformDate iPlatformDate;
    
    public Page<SelectOptionDto> getBusiAcctNo(Map<String,Object>  map){
        return restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlaccountcurrentinterest/getBusiAcctNo",map,
          SelectOptionDto.class);
    }
    
    public Page<PtlAccountCurrentInterest001Dto> findPage(Map<String,Object> map){
        Page<PtlAccountCurrentInterest001Dto> page = restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlaccountcurrentinterest/selectForPage",map,
            PtlAccountCurrentInterest001Dto.class);
        return page;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(PtlAccountCurrentInterest001Dto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setBranch(CacheUtil.getSysParam("noBranchNoValue"));
        restTemplate.postForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlaccountcurrentinterest/doCompelete" , entity,
            Integer.class);
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/portfolio/ptlaccountcurrentinterest/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PtlAccountCurrentInterest001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }
    
    /**
     * 设置单元格值
     * <p>
     * 如果单元格值需要特殊处理，比如数值计算等，可以实现该方法
     * </p>
     * 
     * @param beanName sheet对应的JavaBean
     * @param cell 单元格
     * @param propName 属性名
     * @param value 单元格的值
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if("effDate".equals(propName)){
            cell.setCellValue(DateUtil.format((Date)value, "yyyy-MM-dd"));
        }
    }
    
    
    //003界面利率信息显示
    /**
     * 利率信息展示
     * @param map 请求参数
     * @return 利率信息
     * @see
     * @since 1.0
     */
    public Page<PtlAccountCurrentInterest001Dto> ratePage(Map<String,Object> map){
        map.put("platDate", iPlatformDate.getSysDateStr());
        if(map.get("effDate") != null ){
            Date effDate = new Date(Long.valueOf((String)map.get("effDate")));
            map.replace("effDate", DateUtil.format(effDate));
        }
        Page<PtlAccountCurrentInterest001Dto> page = restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlaccountcurrentinterest/ratePage",map,
            PtlAccountCurrentInterest001Dto.class);
        return page;
    }
    
}
