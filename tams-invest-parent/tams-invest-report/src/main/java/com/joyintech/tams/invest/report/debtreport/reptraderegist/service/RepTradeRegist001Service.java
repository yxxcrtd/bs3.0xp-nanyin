/*
 * 文件名：RepTradeRegist001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.reptraderegist.service;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.joyintech.tams.invest.report.debtreport.reptraderegist.dto.RepTradeRegistDto;

/**
 * 交易要素登记
 * @author fanshuanghu
 * @version 1.0 2018年12月28日
 * @see RepTradeRegist001Service
 * @since 1.0
 */
@Service
public class RepTradeRegist001Service extends CommonExportService<RepTradeRegistDto>{
    /**
     * 分页查询
     * @param map 查询参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    public Page<RepTradeRegistDto> findPage(Map<String,Object> map){
        String repFinprodId = (String)map.get("repFinprodId");
        if(StringUtil.isNotEmpty(repFinprodId)){
            map.replace("repFinprodId", Arrays.asList(repFinprodId.split(",")));
        }
        else{
            map.replace("repFinprodId", new ArrayList<>());
        }
        String repFinprodRegId = (String)map.get("repFinprodRegId");
        if(StringUtil.isNotEmpty(repFinprodRegId)){
            map.replace("repFinprodRegId", Arrays.asList(repFinprodRegId.split(",")));
        }
        else{
            map.replace("repFinprodRegId", new ArrayList<>());
        }
        return restTemplate.postForPage(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepTradeRegist/findPage",
            map, RepTradeRegistDto.class);
    }
    /**
     * 获取金融产品代码下拉框集合
     * @param map 查询参数
     * @return 金融产品代码下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepFinprodId(Map<String,Object> map){
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepTradeRegist/getRepFinprodId",
            map, SelectOptionDto.class);
    }
    /**
     * 获取行内交易编号下拉框集合
     * @param map 查询参数
     * @return 行内交易编号下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepBankTradeCode(Map<String,Object> map){
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepTradeRegist/getRepBankTradeCode",
            map, SelectOptionDto.class);
    }
    /**
     * 获取资产代码下拉框集合
     * @param map 查询参数
     * @return 资产代码下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepBankAssetCode(Map<String,Object> map){
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepTradeRegist/getRepBankAssetCode",
            map, SelectOptionDto.class);
    }
    /**
     * 获取金融产品登记编码下拉框集合
     * @param map 查询参数
     * @return 金融产品登记编码下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepFinprodRegId(Map<String,Object> map){
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepTradeRegist/getRepFinprodRegId",
            map, SelectOptionDto.class);
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/report/debtreport/reptraderegist/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepTradeRegistDto> selectPageData(Map<String, Object> map) {
        String primaryKey = (String)map.get("primaryKey");
        if(StringUtil.isNotEmpty(primaryKey)){
            map.replace("primaryKey", Arrays.asList(primaryKey.split(",")));
        }
        else{
            map.replace("primaryKey", null);
        }
        return restTemplate.postForPage(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepTradeRegist/findPage",
            map, RepTradeRegistDto.class);
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
        if(StringUtil.equals(propName, "repStatusDate") || StringUtil.equals(propName, "repTradeDate")){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }
}
