/*
 * 文件名：ValYieldCurveInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhao
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.valyieldcurveinfo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
//import com.joyintech.tams.invest.basicdata.mstnaturalperson.dto.MstNaturalPerson001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.valyieldcurveinfo.dto.ValYieldCurveInfoDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 收益率曲线service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see ValYieldCurveInfoService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValYieldCurveInfoService extends CommonExportService<ValYieldCurveInfoDto> implements BaseProcessService<ValYieldCurveInfoDto>{
    /**
     * 
     * 查询
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<ValYieldCurveInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<ValYieldCurveInfoDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ValYieldCurveInfo/selectForPage",
            pageParam, ValYieldCurveInfoDto.class);
    }
    /**
     * 
     * 获取所有的数据
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return List<ValYieldCurveInfoDto>
     * @see
     * @since 1.0
     */
    public List<ValYieldCurveInfoDto> selectAllList(Map<String, Object> pageParam) {
//        return mstYieldCurveInfo001Mapper.select(pageParam);
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValYieldCurveInfo/selectAllList",
            pageParam, ValYieldCurveInfoDto.class);
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void doComplete(ValYieldCurveInfoDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValYieldCurveInfo/doComplete",
            entity, null);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals(propName, "curveDate")){
            cell.setCellValue(DateUtil.format((Date)value));
        }
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
        exportParam.config("/com/joyintech/tams/valuation/basedata/marketdata/valyieldcurveinfo/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<ValYieldCurveInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 曲线名称的联想下拉框事件
     * @param map
     * @return
     */
    public Page<SelectOptionDto> getCurveName(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/ValYieldCurveInfo/getCurveName", map,
                SelectOptionDto.class);
    }

    public ValYieldCurveInfoDto selectNK(ValYieldCurveInfoDto mstYieldCurveInfoDto) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ValYieldCurveInfo/selectNK",
                mstYieldCurveInfoDto, ValYieldCurveInfoDto.class);
    }
}
