/*
 * 文件名：MstIndexQuotationInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstindexquotationinfo.service;

import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.basicdata.mstindexquotationinfo.dto.MstIndexQuotationInfo001Dto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 指数行情信息service类
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月14日
 * @see MstIndexQuotationInfo001Service
 * @since 1.0
 */
@Service
public class MstIndexQuotationInfo001Service extends CommonExportService<MstIndexQuotationInfo001Dto> 
    implements BaseProcessService<MstIndexQuotationInfo001Dto> {

    /**
     * 分页查询
     * 
     * @param map 查询参数
     * @return Page<MstIndexQuotationInfo001Dto> 实体类泛型的page类
     * @throws Exception 错误
     * @see
     * @since 1.0
     */
    public Page<MstIndexQuotationInfo001Dto> selectForPage(Map<String, Object> map){
        return restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstIndexQuotationInfo/selectForPage",
            map, MstIndexQuotationInfo001Dto.class);
    }

    /**
     * 导出 {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/basicdata/mstindexquotationinfo/conf/export_conf.xml").dataSource(
                    DataSourceType.MASERT);
    }
    /**
     * 设置日期格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("relaeseDate".equals(propName) || "effDate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }
    /**
     * 流程中间类 {@inheritDoc}
     */
    @Override
    public void doComplete(MstIndexQuotationInfo001Dto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstIndexQuotationInfo/doComplete",
            entity, Integer.class);
    }
    /**
     * 导出
     */
    @Override
    public Page<MstIndexQuotationInfo001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 通过主键查询数据
     * <功能详细描述>
     * @param dto dto
     * @return 结果
     * @see
     * @since 1.0
     */
    public Object selectByPrimaryKey(MstIndexQuotationInfo001Dto dto) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstIndexQuotationInfo/selectByPrimaryKey",
            dto, Page.class);
    }
}
