/*
 * 文件名：MstYieldCurveInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhao
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.yieldcurveinfo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.basicdata.constant.ServiceApplication;
import com.joyintech.tams.common.basicdata.marketdata.yieldcurveinfo.dto.ComYieldCurveInfoDto;
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
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 收益率曲线service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see ComYieldCurveInfoService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ComYieldCurveInfoService extends CommonExportService<ComYieldCurveInfoDto> implements BaseProcessService<ComYieldCurveInfoDto>{
    /**
     * 
     * 查询
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<MstYieldCurveInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<ComYieldCurveInfoDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(ServiceApplication.CommonBasicDataService + "/ComYieldCurveInfo/selectForPage",
            pageParam, ComYieldCurveInfoDto.class);
    }
    /**
     * 
     * 获取所有的数据
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return List<MstYieldCurveInfoDto>
     * @see
     * @since 1.0
     */
    public List<ComYieldCurveInfoDto> selectAllList(Map<String, Object> pageParam) {
//        return mstYieldCurveInfo001Mapper.select(pageParam);
        return restTemplate.postForList(ServiceApplication.CommonBasicDataService + "/ComYieldCurveInfo/selectAllList",
            pageParam, ComYieldCurveInfoDto.class);
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void doComplete(ComYieldCurveInfoDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForList(ServiceApplication.CommonBasicDataService + "/ComYieldCurveInfo/doComplete",
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
        exportParam.config("/com/joyintech/tams/common/basicdata/marketdata/yieldcurveinfo/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<ComYieldCurveInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 曲线名称的联想下拉框事件
     * @param map
     * @return
     */
    public Page<SelectOptionDto> getCurveName(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.CommonBasicDataService
                        + "/ComYieldCurveInfo/getCurveName", map,
                SelectOptionDto.class);
    }

    public ComYieldCurveInfoDto selectNK(ComYieldCurveInfoDto comYieldCurveInfoDto) {
        return restTemplate.postForObject(ServiceApplication.CommonBasicDataService + "/ComYieldCurveInfo/selectNK",
                comYieldCurveInfoDto, ComYieldCurveInfoDto.class);
    }
}
