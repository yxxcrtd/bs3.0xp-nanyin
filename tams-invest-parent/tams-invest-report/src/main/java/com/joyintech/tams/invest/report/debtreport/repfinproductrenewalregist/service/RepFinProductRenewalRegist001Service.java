/*
 * 文件名：RepFinProductRenewalRegist001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repfinproductrenewalregist.service;
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
import com.joyintech.tams.invest.report.debtreport.repfinproductrenewalregist.dto.RepFinProductRenewalRegistDto;

/**
 * 产品存续期登记
 * @author fanshuanghu
 * @version 1.0 2018年12月23日
 * @see RepFinProductRenewalRegist001Service
 * @since 1.0
 */
@Service
public class RepFinProductRenewalRegist001Service extends CommonExportService<RepFinProductRenewalRegistDto>{
    /**
     * 分页查询
     * @param pageParam 查询参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    public Page<RepFinProductRenewalRegistDto> selectForPage(Map<String, Object> pageParam) {
        Object repFinprodName = pageParam.get("repFinprodName");
        if(repFinprodName != null && StringUtil.isNotEmpty(repFinprodName.toString())){
            pageParam.replace("repFinprodName", Arrays.asList(repFinprodName.toString().split(",")));
        }
        else{
            pageParam.replace("repFinprodName", null);
        }
        Object repFinprodId = pageParam.get("repFinprodId");
        if(repFinprodId !=null && StringUtil.isNotEmpty(pageParam.get("repFinprodId").toString())){
            pageParam.replace("repFinprodId", Arrays.asList(repFinprodId.toString().split("/")));
        }else{
            pageParam.replace("repFinprodId", null);
        }
        return restTemplate.postForPage(
              ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductRenewalRegist/findPage",
              pageParam, RepFinProductRenewalRegistDto.class);
    }
    /**
     * 获取金融产品代码
     * @param map 查询参数
     * @return 金融产品代码集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepFinprodName(Map<String, Object> map){
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductRenewalRegist/getRepFinprodName",
            map, SelectOptionDto.class);
    }
    /**
     * 获取登记编号
     * @param map 查询参数
     * @return 登记编号实体类
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepFinprodRegId(Map<String, Object> map){
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductRenewalRegist/getRepFinprodRegId",
            map, SelectOptionDto.class);
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/report/debtreport/repfinproductrenewalregist/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepFinProductRenewalRegistDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
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
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals(propName, "repStatusDate") || StringUtil.equals(propName, "repBusVdate") || StringUtil.equals(propName, "repBusMdate")){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }
}
