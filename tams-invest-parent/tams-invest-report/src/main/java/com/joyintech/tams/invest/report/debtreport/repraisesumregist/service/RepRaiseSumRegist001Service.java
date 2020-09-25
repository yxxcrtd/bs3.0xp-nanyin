/*
 * 文件名：RepRaiseSumRegist001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repraisesumregist.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.RepRaiseSum;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.report.debtreport.repraisesumregist.dto.RepRaiseSumRegistDto;

/**
 * 募集期总量登记
 * @author fanshuanghu
 * @version 1.0 2018年12月28日
 * @see RepRaiseSumRegist001Service
 * @since 1.0
 */
@Service
public class RepRaiseSumRegist001Service extends CommonExportService<RepRaiseSumRegistDto>{
    /**
     * 分页查询
     * @param map 查询参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    public Page<RepRaiseSumRegistDto> findPage(Map<String,Object> map){
        String productCode = (String)map.get("productCode");
        if(StringUtil.isNotEmpty(productCode)){
            map.replace("productCode", Arrays.asList(productCode.split(",")));
        }
        else{
            map.replace("productCode", new ArrayList<>());
        }
        return restTemplate.postForPage(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepRaiseSumRegist/findPage",
            map, RepRaiseSumRegistDto.class);
    }
    /**
     * 获取金融产品代码下拉框集合
     * @param map 查询参数
     * @return 金融产品代码下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getProductCode(Map<String,Object> map){
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepRaiseSumRegist/getProductCode",
            map, SelectOptionDto.class);
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/report/debtreport/repraisesumregist/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepRaiseSumRegistDto> selectPageData(Map<String, Object> map) {
//        String productCode = (String)map.get("productCode");
//        if(StringUtil.isNotEmpty(productCode)){
//            map.replace("productCode", Arrays.asList(productCode.split(",")));
//        }
//        else{
//            map.replace("productCode", null);
//        }
        String repFinprodId = (String)map.get("repFinprodId");
        if(StringUtil.isNotEmpty(repFinprodId)){
            map.replace("repFinprodId", Arrays.asList(repFinprodId.split(",")));
        }
        else{
            map.replace("repFinprodId", null);
        }
        return restTemplate.postForPage(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepRaiseSumRegist/findPage",
            map, RepRaiseSumRegistDto.class);
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
        if(StringUtil.equals(propName, "repStatusDate")){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }
}
