/*
 * 文件名：CePreOccupancyManagement001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.creditmanagement.ceassetpositionmanagement.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetpositionmanagement.dto.CeAssetPositionManagement001Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月8日
 * @see CeAssetPositionManagement001Service
 * @since 1.0
 */
@Service
public class CeAssetPositionManagement001Service extends CommonExportService<CeAssetPositionManagement001Dto>{

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param parameters
     * @return
     * @see
     * @since 1.0
     */
    public Page<CeAssetPositionManagement001Dto> selectForPage(Map<String, Object> parameters) {
        return restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/CeAssetPositionManagement/selectForPage", parameters, CeAssetPositionManagement001Dto.class);
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/risk/creditmanagement/ceassetpositionmanagement/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<CeAssetPositionManagement001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param parameters
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryAssetCodeInfo(Map<String, Object> parameters) {
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetPositionManagement/queryAssetCodeInfo",
            parameters, SelectOptionDto.class);
    }
    
    /**
     * 格式化日期
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if (StringUtil.equals(propName, "valDate")) {
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }

}
