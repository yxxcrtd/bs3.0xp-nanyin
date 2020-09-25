/*
 * 文件名：RepBottomAsset001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repbottomasset.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.RepBottomAsset;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 底层资产/负债信息报送信息
 * @author fanshuanghu
 * @version 1.0 2018年12月27日
 * @see RepBottomAsset001Service
 * @since 1.0
 */
@Service
public class RepBottomAsset001Service extends CommonExportService<RepBottomAsset>{
    /**
     * 分页查询
     * @param map 查询参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    public Page<RepBottomAsset> findPage(Map<String,Object> map){
        String repAssetCode = (String)map.get("repAssetCode");
        String repBottomCode2 = (String)map.get("repBottomCode2");
        if(StringUtil.isNotEmpty(repAssetCode)){
            map.replace("repAssetCode", Arrays.asList(repAssetCode.split(",")));
        }
        else{
            map.replace("repAssetCode", new ArrayList<String>());
        }
        if(StringUtil.isNotEmpty(repBottomCode2)){
            map.replace("repBottomCode2", Arrays.asList(repBottomCode2.split(",")));
        }
        else{
            map.replace("repBottomCode2", new ArrayList<String>());
        }
        return restTemplate.postForPage(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepBottomAsset/findPage",
            map, RepBottomAsset.class);
    }
    
    /**
     * 获取对应资管及委外资产行内资产/负债编码（资管计划代码）
     * @param map 查询参数
     * @return 对应资管及委外资产行内资产/负债编码（资管计划代码）集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepAssetCode(Map<String,Object> map){
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepBottomAsset/getRepAssetCode",
            map, SelectOptionDto.class);
    }
    /**
     * 获取底层资产行内资产/负债编码（底层资产代码）
     * @param map 查询参数
     * @return 底层资产行内资产/负债编码（底层资产代码）集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepBottomCode2(Map<String,Object> map){
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepBottomAsset/getRepBottomCode2",
            map, SelectOptionDto.class);
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/report/debtreport/repbottomasset/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepBottomAsset> selectPageData(Map<String, Object> map) {
        String primaryKey = (String)map.get("primaryKey");
        String[] primaryKeyArray = null;
        List<Map<String,Object>> primaryKeyList = new ArrayList<>();
        if(StringUtil.isNotEmpty(primaryKey)){
            primaryKeyArray = primaryKey.split(",");
        }
        if(primaryKeyArray != null && primaryKeyArray.length > 0){
            for(String primaryKeys : primaryKeyArray){
                if(primaryKeys.split("/").length>2){
                    Map<String,Object> primaryKeyMap = new HashMap<>();
                    primaryKeyMap.put("repFinprodId", primaryKeys.split("/")[0]);
                    primaryKeyMap.put("repAssetCode", primaryKeys.split("/")[1]);
                    primaryKeyMap.put("repPositionDate", DateUtil.format(new Date(Long.valueOf(primaryKeys.split("/")[2]))));
                    primaryKeyList.add(primaryKeyMap);
                }
            }
        }
        map.put("primaryKeyList", primaryKeyList);
        return this.findPage(map);
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
        if(StringUtil.equals(propName,"repStatusDate") || StringUtil.equals(propName, "repPositionDate")){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }
}
